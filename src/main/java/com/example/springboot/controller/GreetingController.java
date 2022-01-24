package com.example.springboot.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.springboot.dto.CustomerDTO;
import com.example.springboot.dto.FileDTO;
import com.example.springboot.exception.InvalidModifyCustomerException;
import com.example.springboot.mapper.CustomerMapper;
import com.example.springboot.service.CustomerService;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.EncryptionMethod;


@Controller
public class GreetingController {

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name",required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("html_name",name);		
		return "greeting";
	}
	
	
	@Autowired
	protected CustomerMapper customerMapper; 	
	
	@GetMapping("/register")
	public String register( Model model) {
		
		CustomerDTO customerdto = customerMapper.getCustomerByName("mary");
		String customer = customerdto.getName();
		
		return "register";
	}

 
	@PostMapping("/register")
	public String registerSubmit(@ModelAttribute CustomerDTO customerdto) {
		String name = customerdto.getName();
		int age = customerdto.getAge(); 
		
		customerMapper.addCustomer(customerdto);
		
		return "register_done";
	}
	
	@GetMapping("/querylist")
	public String queryCustomers(Model model ) {
		ArrayList<CustomerDTO> customerdtoList = customerMapper.getAllCustomer();
		
		for (int i = 0 ; i<customerdtoList.size();i++) {
			System.out.println(customerdtoList.get(i).getName());
		}
		model.addAttribute("customerlist", customerdtoList);

		model.addAttribute("message", "測試jsp");
		return "querylist";
	}
	
	
	@GetMapping("/query")
	public String query(Model model) {
		
		CustomerDTO customerdto = new CustomerDTO();//先給一個物件等等用
	    model.addAttribute("customer", customerdto);//把物件指定給html裡面的th:object使用

		return "queryform";
	}
	
	
	@PostMapping("/query")
	public String querySubmit(@ModelAttribute CustomerDTO customerdto,Model model) {
		
		
		model.addAttribute("customer", customerdto);//因為下一個html有input的th:object,所以也要把物件指定給html裡面的th:object使用
		
		String name = customerdto.getName();
		CustomerDTO customerDtoByname = customerMapper.getCustomerByName(name);
		
		model.addAttribute("customerlist", customerDtoByname);//因為下一個html表格的th:object,所以也要把物件指定給html裡面的th:object使用

		return "querylist";
	}
	
	
	@GetMapping("/account_maintain")
	public String accountMaintain(@RequestParam(name="name" ,required=false)String name,@RequestParam(name="id", required=true) long id,Model model) {
					
		CustomerDTO customerName = new CustomerDTO();
		customerName.setName(name);
		customerName.setId(id);
	
		
		model.addAttribute("customer", customerName);
		return "account_maintain";
	}
	
	@PostMapping("/account_maintain")
	public String accountMaintain(@ModelAttribute CustomerDTO customerdto,Model model) {
		
		model.addAttribute("customer", customerdto);
		
	   //取db資料
		CustomerDTO cusomerDtoFromDb = customerMapper.getCustomerByName(customerdto.getName());
		if (cusomerDtoFromDb.getAge().equals(customerdto.getAge())&& customerdto.getNewAgeModify().equals(customerdto.getNewAgeConfirm())) {
			customerdto.setAge(customerdto.getNewAgeConfirm());
			customerMapper.updateCustomerAgeById(customerdto);
			
			return "greeting";
		}
		else {
			customerdto.setMessage("帳戶密碼錯誤");
			model.addAttribute("customer",customerdto);
			return "account_maintain";
		}
	}
	
//上傳檔案	
	@GetMapping("/uploadfile")
	public String uploadfile(Model model) {
		return "uploadfile";
	}
	
	   @Value("${filePath}")
	   private String filePath;
	  
	   public String uploadFile(MultipartFile file, Model model)  {
		   FileDTO filedto = new FileDTO();

			//檔名處理
			String  originalFilename= file.getOriginalFilename();
	        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());
	        String fname = originalFilename.substring(0,originalFilename.lastIndexOf("."));
        
			DateTime datetime = new DateTime().now();
			String nowstamp = datetime.toString("yyyyMMddhhmmss"); 
			System.out.println("時間格式"+nowstamp);

			
			
			//目錄是否存在	
			String realPath = filePath+fname+nowstamp+"."+suffix;
			System.out.println("完整檔案名"+realPath);
			File realPathFile = new File(realPath);
			if (!realPathFile.getParentFile().exists()) {
				realPathFile.mkdirs();
			}
			try {
			//存入檔案	
				file.transferTo(realPathFile);
				System.out.println("檔案上傳成功!");
				
				ZipFile zipfile =addFileToZip(realPath,"1234567");
				System.out.println("檔案壓縮成功!");
				
				extractZipfile(zipfile,"1234567");
				System.out.println("解壓縮");
				
			} catch (IOException e) {		
				e.printStackTrace();
			}

			return realPath;
	   }
	   

	@PostMapping("/uploadfile")
	public String uploadfilesubmit(MultipartFile file, Model model, HttpServletRequest request, HttpServletResponse response ) {
		//檢查是否登入  否則擋下來
		
		System.out.println("登入者資訊為：" +request.getSession().getAttribute("user"));
		if (request.getSession().getAttribute("user")==null) {
			model.addAttribute("errorMessage", "尚未登入！");
			return "/uploadfile";
		}
		
		
		
		
		
		//上傳檔案
		System.out.println("檔案是否上傳"+file);
		if (file.isEmpty() ) {
			System.out.println("空的");
			
			model.addAttribute("errorMessage", "上傳失敗，請選擇檔案！");
			return "/uploadfile";
		}
		
		try { 
			readfileAddcustomer(uploadFile(file ,model));
		} catch (Exception e) {
			e.printStackTrace();
		} 
      
		return "uploadfile";
	}
	
	
	public void readfileAddcustomer(String filerealPath) {
		File doc = new File(filerealPath);

		try {
			BufferedReader obj = new BufferedReader(new FileReader(doc));

			// 建立新檔案放insert資料的回檔
			File writename = new File("C:\\Users\\redcl\\Downloads\\test4.txt"); // 相對路徑，如果沒有則要建立一個新的output。txt檔案
			writename.createNewFile(); // 建立新檔案
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));


			int lineNum = 0;
			String customerdata = null;

			while ((customerdata = obj.readLine()) != null) {
	
				String[] items = customerdata.split(",");
				List<String> container = Arrays.asList(items);
				CustomerDTO customerdto = new CustomerDTO();

				for (int i = 0; i < container.size(); i++) {
					if (i == 0) {
						customerdto.setId(Long.parseLong(container.get(i)));
					}
					if (i == 1) {
						customerdto.setName(container.get(i));
					}
					if (i == 2) {
						customerdto.setAge(Integer.valueOf(container.get(i)));
					}
				}
				// insert DB
				if (customerMapper.getCustomerById(customerdto.getId()) == 1) {
					out.write(customerdata + ",不OK\r\n");
					continue;
				} 
				
				int i = customerMapper.addCustomer(customerdto);
				if (i == 1) {
					out.write(customerdata + ",OK\r\n");
				}	
			}
			out.close(); // 最後記得關閉檔案
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	//壓縮檔案
	
	public ZipFile addFileToZip(String sourceFile, String password){
		ZipParameters ziparameters = new ZipParameters();
		ziparameters.setEncryptFiles(true);
		ziparameters.setCompressionLevel(CompressionLevel.HIGHER);
		ziparameters.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD);
		

		ZipFile zipfile = new ZipFile(sourceFile+".zip");
		zipfile.setPassword(password.toCharArray());
		
		
		try {
			zipfile.addFile(new File(sourceFile), ziparameters);
			zipfile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return zipfile;
	}
	//解壓縮檔案
	public void extractZipfile(ZipFile zipfile, String password) {
		
		String targetfile = zipfile.toString(); //C:\Users\redcl\Downloads\測試上傳20211209022110.txt.zip
		System.out.println("A");
		
		String fname = targetfile.substring(0,targetfile.lastIndexOf("."));//C:\Users\redcl\Downloads\測試上傳20211209022858.txt
		System.out.println(fname);
		
		try {
		if(zipfile.isEncrypted()) {
			zipfile.setPassword(password.toCharArray());
			System.out.println("C");
		}
			zipfile.extractAll(filePath);// output directory is not valid
			zipfile.close();
			System.out.println("D");
		} catch (IOException e) {
			System.out.println("E");
			e.printStackTrace();
		}		
		
	}
	
	}


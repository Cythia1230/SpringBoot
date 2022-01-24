package com.example.springboot.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springboot.dto.RaceInfoDTO;
import com.example.springboot.mapper.RaceInfoMapper;

@Controller
public class DanceController {
	
	@Autowired
	protected RaceInfoMapper raceInfoMapper;
	

	
	@GetMapping("/danceRaceList")
	public String danceEnrollList(Model model) {
		
		List<RaceInfoDTO> raceInfoList = raceInfoMapper.getAllRaceInfo();	
		for (int i=0 ;i<raceInfoList.size();i++) {			
			raceInfoList.get(i).setStringHoldDate(timeparser(raceInfoList.get(i).getHoldDate())); 
			raceInfoList.get(i).setStringEnrollStDate(timeparser(raceInfoList.get(i).getEnrollStDate())); 
			raceInfoList.get(i).setStringEnrollEndDate(timeparser(raceInfoList.get(i).getEnrollEndDate())); 
		}

		model.addAttribute("raceInfoList",raceInfoList);
		return "danceRaceList";
	}
	
	
	
	@GetMapping("/danceEnroll")
	public String danceEnroll( Model model,@RequestParam(name="raceId",required=true) String raceId) {
		
		LocalDateTime enrollEndTime = raceInfoMapper.getRaceInfoById(Integer.valueOf(raceId)).getEnrollEndDate();
		
		
		
		LocalDateTime nowtime = LocalDateTime.now();
		
		//LocalDateTime enrollEndTime = LocalDateTime.parse(raceInfo.getStringEnrollEndDate(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
		
		if(nowtime.isAfter(enrollEndTime)) {
			System.out.println("超過報名時間");
		}

		return "danceRaceList";
	}
	
	
	public String timeparser(LocalDateTime localDateTime) {
       
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = localDateTime.format(formatter); 		
		return  formatDateTime;
	}

}

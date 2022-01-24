package com.example.springboot.dto;

import java.util.Date;

public class CustomerDTO {
    private Long id;
    
    private String name;
    
    private Integer age;
    
    private Integer newAgeModify;
    
    private Integer newAgeConfirm;
    
    private String createBy;
    
    private Date createDt;
    
    private String modifyBy;
    
    private Date modifyDt;
    
    private String message;
    


	private String account;
    private String password;
    


	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDt() {
        return modifyDt;
    }

    public void setModifyDt(Date modifyDt) {
        this.modifyDt = modifyDt;
    }

  //改年齡  
    public Integer getNewAgeModify() {
        return newAgeModify;
    }

    public void setNewAgeModify(Integer newAgeModify) {
        this.newAgeModify = newAgeModify;
    }

    public Integer getNewAgeConfirm() {
        return newAgeConfirm;
    }

    public void setNewAgeConfirm(Integer newAgeConfirm) {
        this.newAgeConfirm = newAgeConfirm;
    }
    
    // 訊息
    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}

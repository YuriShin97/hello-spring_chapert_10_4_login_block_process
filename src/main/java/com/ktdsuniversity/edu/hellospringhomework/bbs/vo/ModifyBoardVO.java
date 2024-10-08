package com.ktdsuniversity.edu.hellospringhomework.bbs.vo;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ModifyBoardVO {
	
	private int id;
	
	@NotBlank(message = "제목은 필수 입력값입니다.")
	@Size(min = 5, message = "제목은 5자리 이상 입력해주세요.")
	private String subject; // 게시글 제목
	
    private String email; // 이메일
    private String content; // 게시글 내용

    private String fileName;
    private String originFileName;
    
    private String ip;
    
    // 게터, 세터 메서드 정의
    public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	private MultipartFile file;
    
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
    
    
}

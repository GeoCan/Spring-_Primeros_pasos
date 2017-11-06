package com.plenumsoft.vuzee.api.models;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class CandidateUpdateModel {
	
//    private MultipartFile file;
	@NotNull
	@Size(min = 2, max = 200)
	private String name;
	@NotNull
	@Size(min = 2, max = 20)
	private String positionApplied;	 
	 

	
//	public MultipartFile getFile() {
//		return file;
//	}
//	public void setFile(MultipartFile file) {
//		this.file = file;
//	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPositionApplied() {
		return positionApplied;
	}
	public void setPositionApplied(String positionApplied) {
		this.positionApplied = positionApplied;
	}
	
	
}

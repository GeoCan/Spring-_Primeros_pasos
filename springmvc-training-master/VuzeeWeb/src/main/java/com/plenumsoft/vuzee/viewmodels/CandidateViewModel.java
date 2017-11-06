package com.plenumsoft.vuzee.viewmodels;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Date;


import com.plenumsoft.vuzee.controllers.CandidateRestController;

/**
 * @author jdominguez
 *
 */
public class CandidateViewModel  {

	private Long id;
	private String name;
	private String positionApplied;
	private Date createdAt;
	private String createdBy;
	private String urlCV;
	
	
	
	
	
	public String getUrlCV() {
		return linkTo(methodOn(CandidateRestController.class).Index()).withRel("cv").getHref();
	}


	public CandidateViewModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public CandidateViewModel(Long id, String name, String positionApplied, Date createdAt, String createdBy) {
		super();
		this.id = id;
		this.name = name;
		this.positionApplied = positionApplied;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
	}
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
	public String getPositionApplied() {
		return positionApplied;
	}
	public void setPositionApplied(String positionApplied) {
		this.positionApplied = positionApplied;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	
}

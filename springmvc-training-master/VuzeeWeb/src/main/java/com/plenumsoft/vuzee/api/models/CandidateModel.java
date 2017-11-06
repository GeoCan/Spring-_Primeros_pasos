package com.plenumsoft.vuzee.api.models;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.plenumsoft.vuzee.api.controllers.RestCandidateController;
import com.plenumsoft.vuzee.controllers.CandidateRestController;

/**
 * @author jdominguez
 *
 */
public class CandidateModel {

	private Long id;
	private String name;
	private String positionApplied;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private Date createdAt;
	private String createdBy;
	private Boolean hasFile;
	private String urlCV;

	public Boolean getHasFile() {
		return hasFile;
	}

	public void setHasFile(Boolean hasFile) {
		this.hasFile = hasFile;
	}

	public void setUrlCV(String urlCV) {
		this.urlCV = urlCV;
	}

	public String getUrlCV() {
		if (hasFile)
			return linkTo(methodOn(RestCandidateController.class).getCVFile(id)).withRel("cv").getHref();
		return null;
	}

	public CandidateModel() {
		super();
		// TODO Auto-generated constructor stub
	}



	public CandidateModel(Long id, String name, String positionApplied, Date createdAt, String createdBy,
			Boolean hasFile) {
		super();
		this.id = id;
		this.name = name;
		this.positionApplied = positionApplied;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.hasFile = hasFile;
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

package com.dj.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VENDOR_CONTENT")
public class VendorContent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VENDOR_CONTENT_ID")
	private Long contentId;
	
	@Column(name="TYPE_ID")
	private Long TypeId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VENDOR_ID")
	private Vendor vendor;
	
	@Column(name="FILE_ID")
	private Integer fileId;
	
	
	public Long getContentId() {
		return contentId;
	}
	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}
	public Long getTypeId() {
		return TypeId;
	}
	public void setTypeId(Long typeId) {
		TypeId = typeId;
	}
	
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	
	

}

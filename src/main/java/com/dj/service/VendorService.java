package com.dj.service;

import java.util.List;

import com.dj.dto.Vendor;
import com.dj.model.EquipmentDto;
import com.dj.model.VendorDto;

public interface VendorService {

	void vendorSignUp(Vendor vendor);

	boolean verifyEmail(Long id, String code);

	boolean verifyPasswordLink(Long id, String code);

	VendorDto fetchDetails(Long id);

	List<EquipmentDto> fetchEquipments(Long categoryId);

}

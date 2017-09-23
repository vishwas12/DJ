package com.dj.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.dj.application.exception.CustomGenericException;
import com.dj.aws.AWSService;
import com.dj.dto.UiData;

@RestController
@RequestMapping("/fileupload")
public class FileUploadController {
	
	@Autowired
	AWSService awsService;
	
	@RequestMapping(value = "/hello" ,method=RequestMethod.POST)
	public ResponseEntity<UiData> handleFormUpload(@RequestParam("file") MultipartFile file,
            @RequestParam(required = true) String type,@RequestParam(required = true) Long userId) throws IOException {
		UiData uiData =  new UiData();
		if (file.isEmpty()) {
            throw new CustomGenericException("FILE_EMPTY",HttpStatus.BAD_REQUEST);
        }
		ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        try {
        	String url = awsService.uploadContentFile(file.getOriginalFilename(), type, file.getInputStream(), metadata, userId);
        	uiData.setSuccess(true);
    		uiData.setData(url);
    		uiData.setMessage("SUCCESS");
    		return ResponseEntity.ok(uiData);
        }
        catch(Exception e) {
        	throw new CustomGenericException("UNABLE_TO_UPLOAD_FILE", HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
		
    }

}

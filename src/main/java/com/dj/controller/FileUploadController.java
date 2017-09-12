package com.dj.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fileupload")
public class FileUploadController {
	
	@RequestMapping(value = "/hello" ,method=RequestMethod.POST)
	public void handleFormUpload(@RequestParam(required =false) String name,
            @RequestParam("file") MultipartFile file) throws IOException {
		FileOutputStream outputStream = null;
		File dest =  new File("/home/sagara/sagar/images");
		file.transferTo(dest);
        /*if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            
            try {
                outputStream = new FileOutputStream(new File("/"));
                outputStream.write(bytes);
                outputStream.close();
            } catch (Exception e) {
                System.out.println("Error while saving file");
            }
            // store the bytes somewhere
        }*/
		
		

    }

}

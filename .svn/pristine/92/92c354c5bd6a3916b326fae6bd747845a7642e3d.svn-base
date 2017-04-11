package com.dj.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.codec.Base64;

import com.dj.application.exception.CustomGenericException;

public class GeneralUtils {
	public boolean isNullOrBlank(String obj){
		boolean flag = true;
		if(obj!=null && !"".equals(obj)){
			flag = false;
		}
		return flag;
	}
	
	public static boolean creatImageBase64(String imageArray, String fileName, String uploadPath){
		boolean isSuccessfull = false;
		if (imageArray != null) {
			byte [] imageAsArray = Base64.decode(imageArray.getBytes());
			OutputStream outputStream = null;
			try {
				File newFile = new File(uploadPath+ fileName);
				if(!newFile.getParentFile().exists()){
					newFile.getParentFile().mkdirs();
				}
				if (!newFile.exists()) {
					newFile.createNewFile();
				}
				outputStream = new FileOutputStream(newFile);
				outputStream.write(imageAsArray);
				isSuccessfull = true;
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(outputStream!=null){
					try {
						outputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return isSuccessfull;
	}
	
	public static JSONObject getBody(HttpServletRequest request) throws CustomGenericException {
		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

		body = stringBuilder.toString();

		JSONObject json = null;
		if (!body.isEmpty()) {
			try {
				json = new JSONObject(body);
			} catch (JSONException e) {
				e.printStackTrace();
				throw new CustomGenericException(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}
		return json;
	}
}


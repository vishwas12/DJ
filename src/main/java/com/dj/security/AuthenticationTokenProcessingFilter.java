package com.dj.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.GenericFilterBean;

import com.dj.application.exception.CustomGenericException;
import com.dj.dto.Login;
import com.dj.dto.User;
import com.dj.service.UserService;
import com.dj.service.ValidationService;
import com.dj.utils.GeneralUtils;

/**
 * 
 * @author vishwasg
 *
 */
public class AuthenticationTokenProcessingFilter extends GenericFilterBean {
	
	
	@Autowired
	ValidationService validationService;
	
	@Autowired
	UserService userService;
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		boolean isAuthenticated = true;
		CustomGenericException exception = new CustomGenericException();
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String referrer = request.getRequestURI().substring(request.getContextPath().length());
		String userAuthKey = "";
			userAuthKey = (String) request.getHeader("accessToken");

		try {
			isAuthenticated = validateApi(request, referrer, userAuthKey, exception, isAuthenticated);
		} catch (CustomGenericException e) {
			isAuthenticated = false;
			exception.setExceptionMsg(e.getExceptionMsg());
			exception.setExceptionCode(e.getExceptionCode());
		}
		
		if (isAuthenticated) {
			chain.doFilter(request, response);
		} else {
			processError(req, res, exception);
			return;
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param referrer
	 * @param userAuthKey
	 * @param exception
	 * @param isAuthenticated
	 * @return
	 * @throws GenericException
	 */
	private boolean validateApi(HttpServletRequest request, String referrer, String userAuthKey, CustomGenericException exception, boolean isAuthenticated) throws CustomGenericException {
		String  emailId = null, password = null;
		CustomGenericException genException = null;
		JSONObject requestBody = null;

		try {
			if (!RequestMethod.POST.toString().equalsIgnoreCase(request.getMethod())) {
				throw new CustomGenericException("Invalid Request Format", HttpStatus.METHOD_NOT_ALLOWED);
			}
			requestBody = GeneralUtils.getBody(request);
			
			if (requestBody == null && userAuthKey == null) {
				throw new CustomGenericException("Invalid Request", HttpStatus.BAD_REQUEST);
			}
			
			if (referrer.contains("login.json")) {
				if (null != requestBody) {
					emailId = (String) requestBody.get("email");
					password = (String) requestBody.get("password");
					String deviceId = "9999";
					
					try {
						deviceId = (String) requestBody.get("deviceId");
					} catch (Exception e) {
						
					}

					if (emailId==null && "".equals(emailId)) {
						throw new CustomGenericException("Invalid Request", HttpStatus.BAD_REQUEST);
					} else if (password == null && "".equals(password)) {
						throw new CustomGenericException("Invalid Request", HttpStatus.BAD_REQUEST);
					} else {
						Login login = new Login();
						login.setEmail(emailId);
						login.setPassword(password);
						login.setDeviceId(deviceId);
						
						User user = validationService.validateLogin(login);
						
						if (user !=null) {
							setAuthentication(user, request);
						}
					}
					
				} 
			} else if(referrer!=null){
				if(userAuthKey!=null) {
					User user = userService.getUserByAccessToken(userAuthKey);
					boolean flag = validationService.validateToken(userAuthKey);
					if(user == null && !flag) {
						genException = new CustomGenericException("Invalid Token Passed", HttpStatus.BAD_REQUEST);
						isAuthenticated = false;
					} else {
						setAuthentication(user, request);
					}
				} else {
					genException = new CustomGenericException("Invalid Token Passed", HttpStatus.BAD_REQUEST);
					isAuthenticated = false;
				}
			}
			
			//converting body to String
			if (requestBody != null) {
				String requestString = requestBody.toString();
				request.setAttribute("BODY", requestString);
			}
		} catch (JSONException e) {
			genException = new CustomGenericException("Incorrect JSON Format", HttpStatus.BAD_REQUEST);
			isAuthenticated = false;
		} catch (NullPointerException e) {
			genException = new CustomGenericException("Invalid Token Passed", HttpStatus.BAD_REQUEST);
			isAuthenticated = false;
		}

		if (null != genException) {
			exception.setExceptionMsg(genException.getMessage());
			exception.setExceptionCode(genException.getExceptionCode());
		}
		return isAuthenticated;
	}
	
	/**
	 * 
	 * @param user
	 * @param request 
	 */
	private void setAuthentication(User user, HttpServletRequest request) {
		SecurityContextHolder.getContext().setAuthentication(null);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		token.setDetails(new WebAuthenticationDetails((HttpServletRequest) request));
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(),authorities); // this.authenticationProvider.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	/**
	 * 
	 * @param req
	 * @param res
	 * @param exception
	 */
	private void processError(ServletRequest req, ServletResponse res, CustomGenericException exception) {
		HttpServletResponse response = (HttpServletResponse) res;
		try {
			// Set response content type
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			JSONObject jsonOut = new JSONObject(exception);

			PrintWriter out = response.getWriter();
			out.write(jsonOut.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

package com.edu.springboot.security;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class MyAuthFailureHandler 
		implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request, 
			HttpServletResponse response,
			AuthenticationException exception) 
					throws IOException, ServletException {
		
		String errorMsg = "";
		
		if (exception instanceof BadCredentialsException) {
			loginFailureCnt(request.getParameter("admin_id"));
			errorMsg = "아이디/비밀번호 오류입니다(1)";
        } 
		else if (exception instanceof 
					InternalAuthenticationServiceException) {
			loginFailureCnt(request.getParameter("admin_id"));
        	errorMsg = "아이디/비밀번호 오류입니다(2)";
        } 
		else if (exception instanceof DisabledException) {
			errorMsg = "계정이 비활성화되었습니다(3)";
        } 
		else if (exception instanceof CredentialsExpiredException) {
			errorMsg = "비밀번호 유효기간이 만료 되었습니다(4)";
        }
		System.out.println("errorMsg="+ errorMsg);
		request.setAttribute("errorMsg", errorMsg);
		request.getRequestDispatcher("/admin34/login.do?error")
			.forward(request, response);
	}
	
	public void loginFailureCnt(String username) {
		System.out.println("요청 아이디:"+ username);
		/* 틀린 횟수 업데이트
		틀린 횟수 조회
		만약 3회 이상 실패했다면 계정 잠금처리 */
	}
}

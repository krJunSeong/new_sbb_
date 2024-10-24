package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController
	{
		@GetMapping("/hello") // :8080/hello에 url 발생시 실행
		@ResponseBody		// 출력 결과가 문자열이다
		public String hello()
		{
				return "Hello Spring Boot Board";
		}
		
		
	}

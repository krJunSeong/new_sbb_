package com.mysite.sbb;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
public class DataNotFoundException extends RuntimeException
	{
		// 데이터베이스에서 특정 엔티티 또는 데이터를 찾을 수 없을 때 발생시키는 예외 클래스로 만들었다
		private static final long serialVersionUTD = 1L;
		public DataNotFoundException(String message)
		{
			super(message);
		}
		
	}

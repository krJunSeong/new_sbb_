package com.mysite.sbb;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Setter
public class HelloLombok
	{
	    private String hello;
	    private int lombok;

	    private final String _hello;
	    private final int _lombok;
	    
		public static void main(String[] args)
			{
				HelloLombok h = new HelloLombok("_h", 2);
				h.setHello("안녕?");
				h.setLombok(5);
				
				System.out.println(h.getHello());
				System.out.println(h.getLombok());
				
				System.out.println(h.get_hello());
				System.out.println(h.get_lombok());
				
			}

	}

package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;

import com.mysite.sbb.answer.Answer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question
	{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		// @GeneratedValue: 입력하지 않아도 자동으로 1씩 더한다
		// strategy: strategy 옵션을 생략한다면 @GeneratedValue 애너테이션이 지정된 모든 속성에 번호를 차례로 생성하므로 순서가 일정한 고유 번호를 가질 수 없게 된다.

		@Column(length = 200)
		private String subject;
		
		@Column(columnDefinition = "Text")
		private String content;
		
		private LocalDateTime createDate;	
		
		@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
		// caseade = CascadeType.REMOVE
		//		- 질문이 삭제되면 자동으로 답변들도 같이 삭제되는 옵션
		private List<Answer> answerList;
	}
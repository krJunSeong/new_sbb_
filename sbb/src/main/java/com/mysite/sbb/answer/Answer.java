package com.mysite.sbb.answer;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer
	{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		@Column(columnDefinition = "TEXT")
		private String content;
		
		private LocalDateTime createDate;
		
		@ManyToOne
	    private SiteUser author;
		
		@ManyToOne
		// M : N 하나의 질문에 답변은 여러 개, 답변: Many, 질문: One
		private Question question;
		
		private LocalDateTime modifyDate;
		
	    @ManyToMany
	    Set<SiteUser> voter;
	}
package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;

import com.mysite.sbb.question.QuestionService;

@SpringBootTest
class SbbApplicationTests
	{
		@Autowired
		private QuestionRepository questionRepository;

		@Autowired
		private AnswerRepository answerRepository;
		@Autowired
		private QuestionService questionService;
		 
/*
//		@Test
//		void testJpa()
//			{
//				Question q1 = new Question();
//				q1.setSubject("sbb가 무엇인가요?");
//				q1.setContent("sbb에 대해 알고 싶습니다.");
//				q1.setCreateDate(LocalDateTime.now());
//				this.questionRepository.save(q1);
//				
//		        Question q2 = new Question();
//		        q2.setSubject("스프링부트 모델 질문입니다.");
//		        q2.setContent("id는 자동으로 생성되나요?");
//		        q2.setCreateDate(LocalDateTime.now());
//		        this.questionRepository.save(q2);  // 두번째 질문 저장
//			}

//		@Test
//		void testJpa2()
//			{
//				// findAll: Question에 저장된 모든 정보 조회
//				List<Question> all = this.questionRepository.findAll();
//				assertEquals(2, all.size()); // 수가 같은지 Test Check
//
//				Question q = all.get(0);
//				assertEquals("sbb가 무엇인가요?", q.getSubject());
//			}
//
//		@Test
//		void testJpa3()
//			{
//				Optional<Question> oq = this.questionRepository.findById(1);
//				if (oq.isPresent())
//					{
//						Question q = oq.get();
//						assertEquals("sbb가 무엇인가요?", q.getSubject());
//					}
//			}
//		
//		@Test
//		void testJpa4()
//		{
//	        Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
//	        assertEquals(1, q.getId());
//		}
//		
//		@Test
//		void findSujConJpa5()
//		{
//			Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해 알고 싶습니다.");
//	        assertEquals(1, q.getId());
//		}
		
//		@Test
//		void findLikeJpa6()
//		{
//			 List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
//			 Question q = qList.get(0); 
//			 assertEquals(1, q.getSubject());
//		}
		
//		@Test
//		void replaceJpa()
//		{
//			// Data 수정하기
//			Optional<Question> oq = this.questionRepository.findById(1);
//			assertTrue(oq.isPresent());
//			
//			Question q = oq.get();
//			q.setSubject("수정된 제목");
//			
//			this.questionRepository.save(q);
//		}
		
//		@Test
//		void deleteJpa()
//		{
//			// Delete Jpa
//			assertEquals(2, this.questionRepository.count());
//			Optional<Question> oq = this.questionRepository.findById(1);
//			assertTrue(oq.isPresent());
//			
//			Question q = oq.get();
//			
//			this.questionRepository.delete(q);
//			assertEquals(1, this.questionRepository.count());
//		}
		
//		@Test
//		void saveDataJpa()
//		{
//			Optional<Question> oq = this.questionRepository.findById(2);
//			assertTrue(oq.isPresent());
//			Question q = oq.get();
//			
//			Answer a = new Answer();
//			a.setContent("네 자동으로 생성됩니다.");
//			a.setQuestion(q);
//			a.setCreateDate(LocalDateTime.now());
//			this.answerRepository.save(a);
//		}
		
		 
//		@Test
//		void selectJpa()
//		{
//			// answer에 id 1인 것 찾기
//			Optional<Answer> oa = this.answerRepository.findById(1);
//			assertTrue(oa.isPresent());
//			
//			// Answer a에 찾은 것 넣어주기
//			Answer a = oa.get();
//			assertEquals(2, a.getQuestion().getId());
//		}
		*/
		/*
		@Test
		void selectProToAns()
		{
			Optional<Question> oq = this.questionRepository.findById(2);
			assertTrue(oq.isPresent());
			Question q = oq.get();
			
			List<Answer> answerList = q.getAnswerList();
			
			assertEquals(1, answerList.size());
			assertEquals("네 자동으로 생성됩니다", answerList.get(0).getContent());
		}
		*/
	    @Test
	    void testJpa() {
	        for (int i = 1; i <= 300; i++) {
	            String subject = String.format("테스트 데이터입니다:[%03d]", i);
	            String content = "내용무";
	            this.questionService.create(subject, content);
	        }
	    }
	}
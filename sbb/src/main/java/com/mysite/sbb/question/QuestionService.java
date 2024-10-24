package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class QuestionService
	{
		private final QuestionRepository questionRepository;

		// 서비스(service)는 데이터 처리를 위해 작성하는 클래스이다.
		public List<Question> getList()
			{
				return this.questionRepository.findAll();
			}

		public Page<Question> getList(int page)
			{
				// 1.Sort.Order라는 객체가 들어갈 List 생성
				List<Sort.Order> sorts = new ArrayList<>();
				
				// 2. sorts에 추가(createDate) 라는 기준으로 역방향 출력
				sorts.add(Sort.Order.desc("createDate"));
				
				// 3. 이를 기반으로 pageable을 만든다
				Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
				
				// 4. 이를 기반으로 출력한 Page를 돌려준다
				return this.questionRepository.findAll(pageable);
			}

		public Question getQuestion(Integer id)
			{
				Optional<Question> question = this.questionRepository.findById(id);

				if (question.isPresent())
					{
						return question.get();
					} else
					throw new DataNotFoundException("question not found");
			}

		// 제목(subject)과 내용(content)을 입력받아 이를 질문으로 저장하는 create 메서드를 만들었다.
		public void create(String subject, String content)
			{
				Question q = new Question();
				q.setSubject(subject);
				q.setContent(content);
				q.setCreateDate(LocalDateTime.now());
				this.questionRepository.save(q);
			}
	}

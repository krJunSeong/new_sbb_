package com.mysite.sbb.question;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.mysite.sbb.answer.AnswerForm;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

// RequiredArgsConstructor
// final이 붙은 속성을 포함하는 생성자를 자동으로 만들어 주는 역할을 한다.
// QuestionCotroller를 생성할 때 questionRepository가 객체가 만들어지고, 자동으로 주입된다.

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController
	{
		private final QuestionService questionService;
		private final UserService userService;
		// private final QuestionRepository questionRespository;
		// question/list url 실행시 작동

		/*
		 * @GetMapping("/list") public String list(Model model) { // 매개변수로 Model 지정하면
		 * 객체가 자동으로 생성된다 // List<Question> questionList =
		 * this.questionRespository.findAll(); List<Question> questionList =
		 * this.questionService.getList();
		 * 
		 * // model 객체에 questionList라는 이름으로 questionList 변수 저장
		 * model.addAttribute("questionList", questionList);
		 * 
		 * // resources/templates/html 파일 이름을 적어준다. return "question_list"; }
		 */

		@GetMapping("/list")
		public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page)
			{
				Page<Question> paging = this.questionService.getList(page);
				model.addAttribute("paging", paging);
				return "question_list";
			}

		@GetMapping(value = "/detail/{id}")
		public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm)
			{
				Question question = this.questionService.getQuestion(id);
				model.addAttribute("question", question);
				return "question_detail";
			}

		@PreAuthorize("isAuthenticated()")
		@PostMapping("/create")
		public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult,
				Principal parincipal)
			{
				if (bindingResult.hasErrors())
					{
						return "question_form";
					}
				SiteUser siteUser = this.userService.getUser(parincipal.getName());
				this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
				return "redirect:/question/list";
			}

		@PreAuthorize("isAuthenticated()")
		@GetMapping("/create")
		public String questionCreate(QuestionForm questionForm)
			{
				return "question_form";
			}

		@PreAuthorize("isAuthenticated()")
		@GetMapping("/modify/{id}")
		public String questionModify(QuestionForm questionForm, @PathVariable("id") Integer id, Principal principal)
			{
				// 2. 수정할 질문 제목과 내용을 questionForm 객체에 id 값으로 조회
				Question question = this.questionService.getQuestion(id);

				// 1. 사용자와 질문 != 질문 작성자: 수정권한 없음 error 발생
				if (!question.getAuthor().getUsername().equals(principal.getName()))
					{
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
					}

				// 3. subject와 Content를 저장하고, question_form으로 넘긴다
				questionForm.setSubject(question.getSubject());
				questionForm.setContent(question.getContent());
				return "question_form";
			}

		@PreAuthorize("isAuthenticated()")
		@PostMapping("/modify/{id}")
		public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal,
				@PathVariable("id") Integer id)
			{
				if (bindingResult.hasErrors())
					{
						return "question_form";
					}
				Question question = this.questionService.getQuestion(id);
				if (!question.getAuthor().getUsername().equals(principal.getName()))
					{
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
					}
				this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
				return String.format("redirect:/question/detail/%s", id);
			}

		@PreAuthorize("isAuthenticated()")
		@GetMapping("/delete/{id}")
		public String questionDelete(Principal principal, @PathVariable("id") Integer id)
			{
				/*
				 * 사용자가 [삭제] 버튼을 클릭했다면 URL로 전달받은 id값을 사용하여 Question 데이터를 조회한 후, 로그인한 사용자와 질문
				 * 작성자가 동일한 경우, 질문을 삭제한다. 버튼생성; Questino_detail.html 삭제기능; Question Service
				 * URL처리: Question Controller
				 */
				Question question = this.questionService.getQuestion(id);
				if (!question.getAuthor().getUsername().equals(principal.getName()))
					{
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
					}
				this.questionService.delete(question);
				return "redirect:/";
			}

		@PreAuthorize("isAuthenticated()")
		@GetMapping("/vote/{id}")
		public String questionVote(Principal principal, @PathVariable("id") Integer id)
			{
				/*	
				 * 추천 버튼: question_detail.html
				 * 정말 추천하시겠습니까?: question_detail.html -> javascript "recommend"
				   추천인 저장: array 
				 
				 
				 */
				Question question = this.questionService.getQuestion(id);
				SiteUser siteUser = this.userService.getUser(principal.getName());
				this.questionService.vote(question, siteUser);
				return String.format("redirect:/question/detail/%s", id);
			}

	}
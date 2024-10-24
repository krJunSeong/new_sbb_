package com.mysite.sbb.question;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.answer.AnswerForm;

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

		@PostMapping("/create")
		public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult)
			{
				if (bindingResult.hasErrors())
					{
						return "question_form";
					}
				this.questionService.create(questionForm.getSubject(), questionForm.getContent());
				return "redirect:/question/list";
			}

		@GetMapping("/create")
		public String questionCreate(QuestionForm questionForm)
			{
				return "question_form";
			}
	}
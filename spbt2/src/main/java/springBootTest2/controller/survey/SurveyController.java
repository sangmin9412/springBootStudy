package springBootTest2.controller.survey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springBootTest2.command.SurveyCommand;
import springBootTest2.service.survey.SurveyInsertService;
import springBootTest2.service.survey.SurveyService;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	@Autowired
	SurveyInsertService surveyInsertService;
	@Autowired
	SurveyService surveyService;
	@RequestMapping(value = "surveyForm")
	public String surveyForm() {
		return "thymeleaf/survey/surveyInsert";
	}
	@RequestMapping("surveyInsert")
	public String surveyInsert(
				SurveyCommand surveyCommand,
				HttpSession session
			) throws Exception {
		surveyInsertService.surveyInsert(surveyCommand, session);
		return "redirect:/survey/surveyForm";
	}
	@RequestMapping("survey")
	public String survey(
				Model model
			) throws Exception {
		surveyService.execute(model);
		return "thymeleaf/survey/surveyForm";
	}
	@RequestMapping("surveyOk")
	public void surveyOk(
		
			) {
		
	}
}


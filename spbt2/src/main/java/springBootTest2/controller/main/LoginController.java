package springBootTest2.controller.main;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springBootTest2.command.LoginCommand;
import springBootTest2.service.main.LoginService;

@Controller
public class LoginController {
	@Autowired
	LoginService loginService;
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String url() {
		return "redirect:/";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String submit(
				@Validated LoginCommand loginCommand,
				BindingResult result,
				HttpSession session,
				Model model,
				HttpServletResponse response
			) throws Exception {
		if (result.hasErrors()) {
			return "thymeleaf/index";
		}
		return loginService.execute(loginCommand, session, model, response);
	}
	@RequestMapping(value = "/main/logout", method = RequestMethod.GET)
	public String logout(
				HttpSession session,
				HttpServletResponse response
			) {
		Cookie autoLoginCookie = new Cookie("autoLogin", "");
		autoLoginCookie.setPath("/");
		autoLoginCookie.setMaxAge(0);
		response.addCookie(autoLoginCookie);
		session.invalidate();
		return "redirect:/";
	}

}

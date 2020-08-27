package springBootTest2.controller.main;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import springBootTest2.command.LoginCommand;
import springBootTest2.controller.CookieAction;

@Controller
public class MainController {
	@Autowired
	CookieAction cookieAction;
	@ModelAttribute
	LoginCommand setLoginCommand() {
        return new LoginCommand();
    }
	@RequestMapping(value = "/")
	public String home(
				HttpServletRequest request 
			) {
		cookieAction.execute(request);
		return "thymeleaf/index";
	}
}

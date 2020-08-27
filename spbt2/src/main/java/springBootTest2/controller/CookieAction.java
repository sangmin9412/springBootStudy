package springBootTest2.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import springBootTest2.command.AuthInfo;

@Component
@Service
public class CookieAction {
	public void execute(HttpServletRequest request) {
		Cookie [] cookies = request.getCookies();
		HttpSession session = request.getSession();
		if(cookies != null && cookies.length > 0) {
			for(Cookie c: cookies) {
				if(c.getName().startsWith("id")) {
					request.setAttribute("isId", c.getValue());
				}
				if(c.getName().equals("autoLogin")) {
					 AuthInfo authInfo = new AuthInfo(c.getValue(),null, null,null);
					 session.setAttribute("authInfo",authInfo);
				}
			}
		}
	}
}

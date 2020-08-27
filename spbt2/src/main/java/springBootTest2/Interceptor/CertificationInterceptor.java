package springBootTest2.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import springBootTest2.command.AuthInfo;

public class CertificationInterceptor extends HandlerInterceptorAdapter {
	// 컨트롤러 (RequestMapping이 선언된 메서드 진입) 실행 직전에 동작.
	// 반환 값이 true일 경우 정상적으로 진행이 되고, false일 경우 실행이 멈춥니다.
	// 전달인자 중 Object handler는 핸들러 매핑이 잦은 컨트롤러 클래스 객체입니다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		if (ObjectUtils.isEmpty(authInfo)) {
			response.sendRedirect("/");
			return false;
		} else {
			return true;
		}
	}
	// 컨트롤러 진입 후 view가 렌더링 되기 전 수행이 됩니다.
	// 전달인자의 modelAndView를 통해 화면 단에 들어가는 데이터 등의 조작이 가능합니다.
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	// 컨트롤러 진입 후 view가 정상적으로 렌더링 된 후 제일 마지막에 실행이 되는 메서드입니다.
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
	
}

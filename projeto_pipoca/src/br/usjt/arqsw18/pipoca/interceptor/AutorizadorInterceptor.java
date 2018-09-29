package br.usjt.arqsw18.pipoca.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

	public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response,
			Object controller) throws Exception {
		
		String uri = request.getRequestURI();
		
		if(uri.endsWith("LOGIN") || uri.endsWith("FAZERLOGIN") ||
				uri.contains("CSS") || uri.contains("JS") ||
				uri.contains("IMG") || uri.endsWith("CADASTRO") || uri.endsWith("CRIAR_USUARIO")){
				return true;
		}
		
		if(request.getSession().getAttribute("USUARIOLOGADO") != null) {
			return true;
		}
		
		response.sendRedirect("LOGIN");
		return false;
	}
}
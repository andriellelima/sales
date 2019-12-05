package br.ufac.si.academico.filtros;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class loginFiltro implements Filter {
	
	public void destroy() {
		
	}

	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession httpsession = httpRequest.getSession(false);

        String loginURI = httpRequest.getContextPath() + "/login.xhtml";
        String usuarioInclusao = httpRequest.getContextPath() + "/usuarioInclusao.xhtml";

        boolean acessandoInclusao = httpRequest.getRequestURI().equals(usuarioInclusao);

        boolean estaLogado = (httpsession != null && httpsession.getAttribute("usuarioLogado") != null);
        boolean acessandoLogin = httpRequest.getRequestURI().equals(loginURI);
        boolean acessandoRecursos = httpRequest.getRequestURI().startsWith(httpRequest.getContextPath()+"/resources");

         if(estaLogado||  acessandoLogin || acessandoRecursos || acessandoInclusao ) {
            chain.doFilter(request, response);
        }else {
            httpResponse.sendRedirect(loginURI);
        }

	}

	public void init(FilterConfig fcon) throws ServletException{
		
	}
}

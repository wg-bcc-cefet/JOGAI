package br.cefetrj.jogai.infra;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Essa classe trata de filtrar os caractéres da língua portuguesa a fim de que
 * não tenhamos erros nas interações com o database
 * 
 * @author LABPESQUISA
 * 
 */
public class CharacterEncodingFilter implements Filter, Serializable {
	private static final long serialVersionUID = -4246457499875267088L;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
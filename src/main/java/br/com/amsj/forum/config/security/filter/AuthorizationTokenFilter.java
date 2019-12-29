package br.com.amsj.forum.config.security.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.amsj.forum.config.security.TokenService;
import br.com.amsj.forum.model.Usuario;
import br.com.amsj.forum.repository.UsuarioRepository;

public class AuthorizationTokenFilter extends OncePerRequestFilter {
	
	public AuthorizationTokenFilter(UsuarioRepository usuarioRepository, TokenService tokenService) {
		this.usuarioRepository = usuarioRepository;
		this.tokenService = tokenService;
	}

	private static String BEARER = "Bearer";
	
	private UsuarioRepository usuarioRepository;
	
	private TokenService tokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getBearerToken(request);
		
		boolean isValidoToken = tokenService.isValid(token);
		
		if(isValidoToken) {
			authenticateToken(token);
		}

		filterChain.doFilter(request, response);
	}

	private void authenticateToken(String token) {
		Long id = tokenService.getIdUsuario(token);
		Optional<Usuario> optional = usuarioRepository.findById(id);
		Usuario usuario = optional.get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String getBearerToken(HttpServletRequest request) {
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(authorizationHeader == null || authorizationHeader.isEmpty() || !authorizationHeader.startsWith(BEARER)) {
			return null;
		}

		String token = authorizationHeader.substring(BEARER.length()+1);
		return token;
	}

}

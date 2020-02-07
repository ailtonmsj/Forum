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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.amsj.forum.model.Usuario;
import br.com.amsj.forum.repository.UsuarioRepository;

public class AuthorizationBasicFilter extends OncePerRequestFilter {


	public AuthorizationBasicFilter(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	private UsuarioRepository usuarioRepository;
	private static final String BASIC = "Basic";
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(authorizationHeader != null && !authorizationHeader.isEmpty()) {
			// Authenticate Basic
			if(authorizationHeader.startsWith(BASIC)) {
				
				String authorizationBase64 = authorizationHeader.substring(BASIC.length() + 1);
				byte[] authorizationDecodeByte = Base64.decode(authorizationBase64.getBytes());
				String emailPasswordDecoded = new String(authorizationDecodeByte, "UTF-8");
				String[] emailPassword = emailPasswordDecoded.split(":");
				String email = emailPassword[0];
				String password = emailPassword[1];
				password = new BCryptPasswordEncoder().encode(password);
				authenticateBasic(email, password);
				
			}
		}
		filterChain.doFilter(request, response);
	}
	
	private void authenticateBasic(String email, String password) {
		
		Optional<Usuario> optional = usuarioRepository.findByEmailAndSenha(email, password);
		if(optional.isPresent()) {
			Usuario usuario = optional.get();
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, password, usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
	}
}

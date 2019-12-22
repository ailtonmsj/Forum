package br.com.amsj.forum.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.amsj.forum.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${forum.jwt.expiration}")
	private Long tokenExpiration;
	
	@Value("${forum.jwt.secretkey}")
	private String secretKey;
	
	public String generateToken(Authentication authentication) {
		
		Usuario usuario = (Usuario) authentication.getPrincipal();
		
		Date now = new Date();
		Date expiration = new Date(now.getTime() + tokenExpiration);
		
		return Jwts.builder()
				.setIssuer("Forum Api")
				.setSubject(usuario.getId().toString())
				.setIssuedAt(now)
				.setExpiration(expiration)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}

}

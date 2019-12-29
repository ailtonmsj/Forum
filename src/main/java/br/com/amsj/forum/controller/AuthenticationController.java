package br.com.amsj.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.amsj.forum.config.security.TokenService;
import br.com.amsj.forum.controller.dto.TokenDto;
import br.com.amsj.forum.controller.form.FormLogin;

@RestController
@RequestMapping("/oauth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/token")
	public ResponseEntity<TokenDto> getToken(@RequestBody @Validated FormLogin formLogin){
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = formLogin.convert();
		
		try {
			Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			String token = tokenService.generateToken(authentication);
			
			System.out.println("generated");
			System.out.println("token:-"+token+"-");

			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}

}

package com.springtest.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.springtest.security.models.SecMainUserModel;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {
	// Genera TOKEN (Validando su construcción, vigencia, etc)
	
	// Propósito DEBUG
	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private int expiration;
	
	
	// Genera Token
	// JsonWebTokenService.builder = 
	// 		Establece usuario, fecha de expiración (Tiempo actual + expiración * 1000), firma con secret en HS512, compacta
	public String generateToken(Authentication auth) {
		SecMainUserModel mainUser = (SecMainUserModel) auth.getPrincipal();
		return Jwts.builder().setSubject(mainUser.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration * 1000))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	
	public String getUsernameFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}
	
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e){
			logger.error("Token mal formado");
		} catch (UnsupportedJwtException e){
			logger.error("Token no soportado");
		} catch (ExpiredJwtException e){
			logger.error("Token expirado");
		} catch (IllegalArgumentException e){
			logger.error("Token vacio");
		} catch (SignatureException e){
			logger.error("Token falla en la firma");
		} 
		return false;
	}
	
}

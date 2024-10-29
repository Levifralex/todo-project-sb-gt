package com.levifralex.todo_api_rest.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.levifralex.todo_api_rest.dto.UserLogin;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	@Value("${jwt.secret}")
	private String JWT_SECRET;

	public boolean validateToken(String token, UserDetails userDetails) {
		String username = extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		Claims claims = getClaims(token);
		Date expiration = claims.getExpiration();
		return expiration == null || expiration.before(new Date());
	}

	public String extractUsername(String token) {
		return getClaims(token).getSubject();
	}

	public String generateToken(UserLogin request) {

		return Jwts.builder().subject(request.getUsername()).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(getSigningKey(), Jwts.SIG.HS256).compact();

		/*
		 * return Jwts.builder().setSubject(request.getUsername()).setIssuedAt(new
		 * Date()) .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 *
		 * 10)) // 10 horas .signWith(getSigningKey(),
		 * SignatureAlgorithm.HS256).compact();
		 */
	}

	public Claims getClaims(String token) {
		try {
			return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
			/*
			 * return Jwts.parserBuilder() .setSigningKey(getSigningKey()) .build()
			 * .parseClaimsJws(token) .getBody();
			 */
		} catch (JwtException e) {
			System.out.println("Token inválido o firma incorrecta: " + e.getMessage());
			return null;
		}
	}

	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
	}

}

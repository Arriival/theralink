package com.theralink.config.security.jwt;

import com.theralink.common.exception.ApplicationException;
import com.theralink.common.exception.UnAuthorizeException;
import com.theralink.config.session.SessionStorage;
import com.theralink.domain.basic.AuthenticatedUserInfo;
import com.theralink.domain.user.UserPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Optional;

@Component
public class JWTProvider {

	@Value("${app.jwtSecret}}")
	private String jwtSecret;

	@Value("${app.session.expireTime}")
	private int jwtExpirationInMin;

	public String generateToken(Authentication authentication, HttpServletRequest request) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMin * 60000);
		String token = Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuedAt(new Date()).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
		SessionStorage.put(userPrincipal.getUsername(), new AuthenticatedUserInfo(userPrincipal, token));
		return token;
	}

	public String generateToken(AuthenticatedUserInfo authenticatedUserInfo) {
		UserPrincipal userPrincipal = authenticatedUserInfo.getUserPrincipal();
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMin * 60000);
		String token = Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuedAt(new Date()).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
		SessionStorage.put(userPrincipal.getUsername(), new AuthenticatedUserInfo(userPrincipal, token));
		return token;
	}

	public String getUserUsernameFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		}
		catch (MalformedJwtException | SignatureException | IllegalArgumentException | UnsupportedJwtException | ExpiredJwtException ex) {
			return false;
		}

	}

	public String refreshToken(HttpServletRequest request) {
		String oldToken = "";
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			oldToken = bearerToken.substring(7);
		}
		if (validateToken(oldToken)) {
			String username = getUserUsernameFromJWT(oldToken);
			Optional<AuthenticatedUserInfo> authenticatedUserInfo = SessionStorage.getIfExist(username);
			if (authenticatedUserInfo.isPresent()) {
				return generateToken(authenticatedUserInfo.get());
			}
			else {
				throw new UnAuthorizeException("نشست کاربر منقضی شده است. لطفا دوباره وارد شوید.", 15);
			}
		}
		else {
			throw new UnAuthorizeException("Invalid Token To Re-Authorization", 16);
		}
	}

	public Boolean clearSession(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			String token = bearerToken.substring(7);
			if (validateToken(token)) {
				String username = getUserUsernameFromJWT(token);
				SessionStorage.remove(username);
				return true;
			}
			else {
				throw new ApplicationException("Invalid Token", 16);
			}
		}
		else {
			throw new ApplicationException("error in sign-out");
		}
	}
}

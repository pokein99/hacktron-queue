package com.dbs.hacktron.common;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dbs.hacktron.constants.JwtConstants;
import com.dbs.hacktron.exception.JwtTokenException;

public class JwtUtil {

	public static String generateToken(String secretKey, String issuer, long expTimeInMillis, String userId) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		Date totExpTime = new Date(nowMillis + expTimeInMillis);
		String signedToken = "";
		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		Builder token = JWT.create().withIssuer(issuer).withIssuedAt(now).withExpiresAt(totExpTime);
		signedToken = token.sign(algorithm);
		return signedToken;

	}

	public static Boolean verifyJWTToken(String secretKey, String issuer, String token)
			throws JwtTokenException {
			if (token != null) {
				Algorithm algorithm = Algorithm.HMAC256(secretKey);
				JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
				DecodedJWT jwt = verifier.verify(token);
				issuer = jwt.getIssuer();
				Date expiresAt = jwt.getExpiresAt();
				Date issuedAt = jwt.getIssuedAt();
				Date currentDate = new Date();
				if (expiresAt.compareTo(currentDate) < 0) {
					throw new JwtTokenException("Jwt token got expired");
				}
				else {
					return true;
				}
			} else {
				throw new JwtTokenException("Jwt token is not avalible in header");
			}
	}
	
	public static Boolean tokenVerifier(String token) throws JwtTokenException {
		try {
			return JwtUtil.verifyJWTToken(JwtConstants.secretKey, JwtConstants.issuers,token);
		}catch (JwtTokenException e) {
			throw new JwtTokenException(e.getMessage());
		}
	}
}

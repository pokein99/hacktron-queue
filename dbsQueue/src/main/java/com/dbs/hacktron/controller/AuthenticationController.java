package com.dbs.hacktron.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.hacktron.common.JwtUtil;
import com.dbs.hacktron.constants.JwtConstants;

@RestController
public class AuthenticationController {

	@GetMapping("generateToken/{userId}")
	public String generateToken(@PathVariable String userId) {
		return JwtUtil.generateToken(JwtConstants.secretKey, JwtConstants.issuers, JwtConstants.expTimeInMillis,
				userId);
	}
}

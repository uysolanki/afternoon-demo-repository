package com.excelr.BanerAfternoonJwt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationResponse {
	private final String jwt;
}

package com.excelr.BanerAfternoonJwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excelr.BanerAfternoonJwt.model.AuthenticationRequest;
import com.excelr.BanerAfternoonJwt.model.AuthenticationResponse;
import com.excelr.BanerAfternoonJwt.security.MyUserDetailsService;
import com.excelr.BanerAfternoonJwt.util.JwtUtil;

@RestController
public class JwtController 
{
	@Autowired
	MyUserDetailsService userDetailsService;
	
	@Autowired
	JwtUtil jwtTokenUtil;
	
	@Autowired
	AuthenticationManager authenticationManager;

	 @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
	        try {
	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
	        }
	        catch (BadCredentialsException e) {
	            throw new Exception("Incorrect username or password", e);
	        }

	        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

	        final String jwt = jwtTokenUtil.generateToken(userDetails);

	        return ResponseEntity.ok(new AuthenticationResponse(jwt));
	    }
	 
	 
	 @PostMapping("/addData")
	 public String addData()
	 {
		 return "You are on Add Data page";
	 }
	 
	 @GetMapping("/viewData")
	 public String editData()
	 {
		 return "You are on Edit Data page";
	 }
	 
	 @PutMapping("/updateData")
	 public String updateData()
	 {
		 return "You are on Update Data page";
	 }
	 
	 @DeleteMapping("/deleteData")
	 public String deleteData()
	 {
		 return "You are on Delete Data page";
	 }

}

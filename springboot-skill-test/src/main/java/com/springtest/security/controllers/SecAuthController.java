package com.springtest.security.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springtest.security.dto.JwtDto;
import com.springtest.security.dto.LoginUserDto;
import com.springtest.security.dto.NewUserDto;
import com.springtest.security.enums.SecRoleNameEnum;
import com.springtest.security.jwt.JwtProvider;
import com.springtest.security.models.SecRoleModel;
import com.springtest.security.models.SecUserModel;
import com.springtest.security.services.SecRoleService;
import com.springtest.security.services.SecUserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class SecAuthController {

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	SecUserService userSv;
	@Autowired
	SecRoleService roleSv;
	@Autowired
	JwtProvider jwtProvider;
	
	@PostMapping("/new")
	public ResponseEntity<?> createUser(
			@Valid @RequestBody NewUserDto newUser,
			BindingResult bindingResult
			) {
		if(bindingResult.hasErrors())
			return new ResponseEntity<>("Campos diligenciados de manera incorrecta.", HttpStatus.BAD_REQUEST);
		if(userSv.existsByUsername(newUser.getUsername()))
			return new ResponseEntity<>("Nombre de usuario ya existe.", HttpStatus.BAD_REQUEST);
		if(userSv.existsByEmail(newUser.getEmail()))
			return new ResponseEntity<>("Email ya ha sido usado.", HttpStatus.BAD_REQUEST);
		SecUserModel user = new SecUserModel(
				newUser.getName(), 
				newUser.getUsername(), 
				newUser.getEmail(), 
				passwordEncoder.encode(newUser.getPassword()));
		Set<SecRoleModel> roles = new HashSet<>();
		roles.add(roleSv.getByRoleName(SecRoleNameEnum.ROLE_USER).get());
		if(newUser.getRoles().contains("admin"))
			roles.add(roleSv.getByRoleName(SecRoleNameEnum.ROLE_ADMIN).get());
		user.setRoles(roles);
		userSv.createUser(user);
			return new ResponseEntity<>("Usuario Guardado", HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(
			@Valid @RequestBody LoginUserDto loginUser,
			BindingResult bindingResult
			) {
		if(bindingResult.hasErrors())
			return new ResponseEntity("Campos mal diligenciados.", HttpStatus.BAD_REQUEST);
		Authentication authentication =
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);
	}
	
}

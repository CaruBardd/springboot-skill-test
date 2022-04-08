package com.springtest.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springtest.security.models.SecMainUserModel;
import com.springtest.security.models.SecUserModel;

@Service
public class SecUserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	SecUserService userSv;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SecUserModel user = userSv.getByUsername(username).get();
		return SecMainUserModel.build(user);
	}
	
	

}

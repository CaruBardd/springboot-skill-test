package com.springtest.security.models;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecMainUserModel implements UserDetails{
	
	private String name;
	private String username;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public SecMainUserModel(String name, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static SecMainUserModel build(SecUserModel user) {
		List<GrantedAuthority> authorities = user.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRoleName().name())).collect(Collectors.toList());
	return new SecMainUserModel (user.getName(), user.getUsername(), user.getEmail(), user.getPassword(), authorities);
	}
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

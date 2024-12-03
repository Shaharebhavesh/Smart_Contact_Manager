package com.smart.config;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.smart.model.User;

public class CustomerUserDetail implements UserDetails {

	private User user;
	
	public CustomerUserDetail(User user) {
		super();
		this.user = user;
	}

	@Override
	 public Collection<? extends GrantedAuthority> getAuthorities() {

		
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRole());
		
		return Collections.singleton(simpleGrantedAuthority);
		//return List.of(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return ((UserDetails) user).isAccountNonLocked();
//		//return true;
//	}
	//
	@Override
	public boolean isAccountNonLocked() {
	    return user.isAccountNonLocked(); // Assuming User class has a method to check if the account is locked
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		//return user.isEnabled();
		return true;
	}

}

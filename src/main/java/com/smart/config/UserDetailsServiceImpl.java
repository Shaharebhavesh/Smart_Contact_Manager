package com.smart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smart.dao.UserRepository;
import com.smart.model.User;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	/*@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//fetching user by db
		
		User user=userRepository.getUserByUserName(username);
		
		if(user==null)
		{
			throw new UsernameNotFoundException("Could not Found User..!!!");
		}
		
		CustomerUserDetail customUserdetail= new CustomerUserDetail(user);
		
		return customUserdetail;
	}*/
	//
	/* @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = userRepository.getUserByUserName(username);
	        if (user == null) {
	            throw new UsernameNotFoundException("User not found: " + username);
	        }

	        if (!user.isActive()) {
	            String errorMessage = "User account is disabled: " + username;
	            System.out.println(errorMessage); // Log the error message
	            throw new DisabledException(errorMessage); // Throw DisabledException with the message
	        }

	        if (user.isLocked()) {
	            throw new LockedException("User account is locked: " + username);
	        }

	        return new CustomerUserDetail(user);
	    }*/
	///
	 

//	    @Override
//	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//	        User user = userRepository.getUserByUserName(username);
//	        if (user == null) {
//	            throw new UsernameNotFoundException("User not found: " + username);
//	        }
//
//	        if (!user.isActive()) {
//	            // Log the disabled account message instead of throwing an exception
//	            System.out.println("User account is disabled: " + username);
//	            // You can also log this message to your application's logging system
//	            // logger.error("User account is disabled: " + username);
//	        }
//
//	        if (user.isLocked()) {
//	            throw new LockedException("User account is locked: " + username);
//	        }
//
//	        return new CustomerUserDetail(user);
//	    }
	///
	 @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        if (!user.isActive()) {
            String errorMessage = "User account is disabled: " + username;
            System.out.println(errorMessage); // Log the error message
            throw new DisabledException(errorMessage); // Throw DisabledException with the message
        }

        if (user.isLocked()) {
            throw new LockedException("User account is locked: " + username);
        }

        return new CustomerUserDetail(user);
    }
//
}

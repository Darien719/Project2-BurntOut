package com.jobportal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.exception.UserAlreadyExistsException;
import com.jobportal.exception.UserNotFoundException;
import com.jobportal.model.Company;
import com.jobportal.model.User;
import com.jobportal.model.UserRole;
import com.jobportal.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor(onConstructor=@__(@Autowired))
@NoArgsConstructor
public class UserService {
	
	private UserRepository userRepo;
	
//	public boolean verifyUser() {		Still need to do research into salts and encryption as a whole
//		return false;					
//	}
	
	/*----------------CRUD METHODS----------------*/
	
	public void insertUser(User user) throws UserAlreadyExistsException {
		if(userRepo.findByUsername(user.getUsername()) != null) {
			throw new UserAlreadyExistsException("User with that username already exists");
		} else if(userRepo.findByEmail(user.getEmail()) != null) {
			throw new UserAlreadyExistsException("User with that email already exists");
		} else {
			userRepo.save(user);			
		}
	}
	
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<>();
		if(true) {	//need a test to see if current session is an administrator
			userList = userRepo.findAll();
		}
		return userList;
	}
	
	public void updateUser(User user) throws UserNotFoundException {
		if(userRepo.findById(user.getUserId())!=null) {
			userRepo.save(user);
		} else {
			throw new UserNotFoundException("That user was not able to be found!");
		}
	}
	
	public void deleteUser(User user) throws UserNotFoundException {
		if(user!=null) {	//need to check that the user exists in the first place	
//			if() { //check if that user is the one with the current session
//				userRepo.delete(user);
//			} else if(){ //check if current session is administrator
				userRepo.delete(user);
//			}
		} else {
			System.out.println("User Does Not Exist");
			throw new UserNotFoundException();
		}
	}
	
	/*----------------GET-BY METHODS----------------*/
	
	public User getUserByUsername(String username) {
		User user = null;
		if(true) { 	//need a test to verify session here
			user = userRepo.findByUsername(username);
		}
		return user;
	}
	
	public User getUserByEmail(String email) {
		User user = null;
		if(true) {	//need a test to verify session here
			user = userRepo.findByEmail(email);
		}
		return user;
	}
	
	public List<User> getUsersByRole(UserRole userRole) {
		List<User> userList = new ArrayList<>();
		if(true) {	//need a test to verify session here
			userList = userRepo.findByUserRole(userRole);
		}
		return userList;
	}
	
	public List<User> getUsersByCompany(Company company) {
		List<User> userList = new ArrayList<>();
		if(true) {	//you guessed it, need session verification
			userList = userRepo.findByCompanyId(company);
		}
		return userList;
	}
	
}

package techrovers.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import techrovers.entity.UserMst;
import techrovers.model.CustomError;
import techrovers.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public ResponseEntity<List<UserMst>> getAllUsers(HttpServletRequest req){
		return new ResponseEntity<List<UserMst>>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserMst> getUserById(HttpServletRequest req, @PathVariable(name = "id") Long id){
		return new ResponseEntity<UserMst>(userService.getUserById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ResponseEntity<CustomError> saveUser(HttpServletRequest req, @RequestBody UserMst userMst) throws Exception{
		userService.saveUser(userMst);
		CustomError ce = new CustomError(00, "User created successfully");
		return new ResponseEntity<CustomError>(ce, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ResponseEntity<CustomError> updateUser(HttpServletRequest req, @RequestBody UserMst userMst) throws Exception{
		userService.updateUser(userMst);
		CustomError ce = new CustomError(00, "User Updated successfully");
		return new ResponseEntity<CustomError>(ce, HttpStatus.OK);
	}
	

}

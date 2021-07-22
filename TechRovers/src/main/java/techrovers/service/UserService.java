package techrovers.service;

import java.util.List;

import techrovers.entity.UserMst;

public interface UserService {
	
	List<UserMst> getAllUsers();
	
	UserMst getUserById(Long id);
	
	void saveUser(UserMst userMst) throws Exception;
	
	void updateUser(UserMst userMst) throws Exception;
}

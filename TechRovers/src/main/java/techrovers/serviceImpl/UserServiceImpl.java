package techrovers.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import techrovers.entity.UserMst;
import techrovers.model.CustomException;
import techrovers.repository.RoleRepository;
import techrovers.repository.UserRepository;
import techrovers.service.UserService;
import techrovers.util.Constant;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	@Lazy
	private RoleRepository roleRepository;
	
	@Override
	public List<UserMst> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserMst getUserById(Long id) {
		return userRepository.findById(id).orElseGet(null);
	}

	@Override
	public void saveUser(UserMst userMst) throws Exception {
		UserMst user = userRepository.findByEmail(userMst.getEmail());
		if(Objects.nonNull(user)) {
			throw new CustomException(Constant.CUSTOM_ERROR_CODE, "User with email "+userMst.getEmail()+" already exists");
		}
		userRepository.save(userMst);
	}

	@Override
	public void updateUser(UserMst userMst) throws Exception {
		UserMst user = userRepository.findByEmail(userMst.getEmail());
		if(Objects.nonNull(user) && !Objects.equals(user.getId(), userMst.getId())) {
			throw new CustomException(Constant.CUSTOM_ERROR_CODE, "User with email "+userMst.getEmail()+" already exists");
		}
		userMst.setUpdatedDate(new Date());
		userRepository.save(userMst);
	}
}

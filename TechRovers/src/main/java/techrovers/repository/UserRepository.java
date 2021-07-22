package techrovers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import techrovers.entity.UserMst;

@Repository
public interface UserRepository extends JpaRepository<UserMst, Long>{
	
	UserMst findByEmail(String email);

}

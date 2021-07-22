package techrovers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import techrovers.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByType(Integer type);
}

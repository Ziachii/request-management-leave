package com.my_project.API.management_system.repository;
import com.my_project.API.management_system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    //Optional<Role> findById(Long aLong);

//    getRoleById(Long id);
}

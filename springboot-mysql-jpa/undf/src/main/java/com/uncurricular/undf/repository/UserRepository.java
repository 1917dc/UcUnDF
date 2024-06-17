package com.uncurricular.undf.repository;

import com.uncurricular.undf.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

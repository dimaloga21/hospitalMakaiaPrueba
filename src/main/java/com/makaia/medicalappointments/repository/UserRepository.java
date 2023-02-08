package com.makaia.medicalappointments.repository;

import com.makaia.medicalappointments.model.UserE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserE, Integer> {
}

package com.makaia.medicalappointments.repository;

import com.makaia.medicalappointments.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findByUserId(String userId);
}

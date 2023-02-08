package com.makaia.medicalappointments.service;

import com.makaia.medicalappointments.model.Appointment;

import java.util.Optional;


public interface AppointmentService {

    Optional<Appointment> saveAppointment(Appointment appointment);

    Appointment getAppointment(Integer idUser);

    Appointment editAppointment(Integer idUser, Appointment appointment);

    boolean deleteAppointment(Integer idUser);
}

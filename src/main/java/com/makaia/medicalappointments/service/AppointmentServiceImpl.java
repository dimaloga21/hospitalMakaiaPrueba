package com.makaia.medicalappointments.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.makaia.medicalappointments.model.Appointment;
import com.makaia.medicalappointments.repository.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService{

    private final AppointmentRepository appointmentRepository;

    @Override
    public Optional<Appointment> saveAppointment(Appointment appointment) {
        BusinessValidationService validationService = new BusinessValidationService();
        List<Appointment> user = appointmentRepository.findByUserId(appointment.getUserId());

        if(user.isEmpty()){
            Appointment appWithDate = validationService.setDateAppointment(appointment);
            return Optional.of(appointmentRepository.save(appWithDate));
        }else{
            return Optional.empty();
        }
    }

    @Override
    public Appointment getAppointment(Integer idAppointment) {
        return appointmentRepository.findById(idAppointment).orElseThrow(() -> {throw new RuntimeException();});
    }

    @Override
    public Appointment editAppointment(Integer idAppointment, Appointment appointment) {
        Appointment searchedAppointment = appointmentRepository.findById(idAppointment).get();
        searchedAppointment.setSpecialty(appointment.getSpecialty());
        searchedAppointment.setDate(appointment.getDate());
        searchedAppointment.setUserType(appointment.getUserType());
        searchedAppointment.setUserId(appointment.getUserId());
        return appointmentRepository.save(searchedAppointment);
    }

    @Override
    public boolean deleteAppointment(Integer idAppointment) {
        try {
            appointmentRepository.deleteById(idAppointment);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

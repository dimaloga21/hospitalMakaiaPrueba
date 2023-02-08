package com.makaia.medicalappointments.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.makaia.medicalappointments.model.Appointment;
import com.makaia.medicalappointments.service.AppointmentService;
import com.makaia.medicalappointments.service.BusinessValidationService;
import com.makaia.medicalappointments.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/agendar")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity save(@RequestBody Appointment appointment) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        BusinessValidationService businessValidation = new BusinessValidationService();
        if(!businessValidation.validateUserType(appointment.getUserType())){
            map = businessValidation.createResponseWrongUserType();
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }
        Optional<Appointment> appointmentCont = appointmentService.saveAppointment(appointment);
        if(appointmentCont.isPresent()){
            map = businessValidation.createResponseSaveOk(appointment);
            return new ResponseEntity(map, HttpStatus.CREATED);
        }else{
            map = businessValidation.createResponseUserExists(appointment);
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getAppointment(@PathVariable("id") Integer idAppointment) {
        return new ResponseEntity(appointmentService.getAppointment(idAppointment), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity editAppointment(@PathVariable("id") Integer idAppointment, @RequestBody Appointment appointment) {
        return new ResponseEntity(appointmentService.editAppointment(idAppointment, appointment), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAppointment(@PathVariable("id") Integer idAppointment) {
        boolean response = appointmentService.deleteAppointment(idAppointment);

        if(response){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}




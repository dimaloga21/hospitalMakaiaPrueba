package com.makaia.medicalappointments.service;

import com.makaia.medicalappointments.model.Appointment;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;


public class BusinessValidationService {

    public Appointment setDateAppointment(Appointment appointment){
        int daysAppointment = 10; //EPS appointment days
        LocalDate startDate = LocalDate.now();
        int finalDate = 0;

        if(appointment.getUserType().equalsIgnoreCase("PARTICULAR")){
            daysAppointment = 7;
        } else if (appointment.getUserType().equalsIgnoreCase("POLIZA")) {
            daysAppointment = 8;
        }

        LocalDate finalDateWithBusinessDays = startDate.plusDays(daysAppointment);
        finalDate = calculateBusinessDays(startDate, finalDateWithBusinessDays);

        appointment.setDate(startDate.plusDays(finalDate));
        return appointment;
    }

    private int  calculateBusinessDays(LocalDate startDate, LocalDate finalDate ){
        int diffDays= 0;

        while(startDate.isBefore(finalDate)) {
            if (!startDate.getDayOfWeek().toString().equals("SUNDAY") && !startDate.getDayOfWeek().toString().equals("SATURDAY")) {
                diffDays++;
            }else{
                diffDays++;
                finalDate = finalDate.plusDays(1);
            }
            startDate = startDate.plusDays(1);
        }
        return diffDays - 1;
    }

    public Map<String, Object>  createResponseSaveOk(Appointment appointment){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("id", appointment.getId() );
        map.put("appointmentDate", appointment.getDate());
        return map;
    }

    public Map<String, Object>  createResponseUserExists(Appointment appointment){
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        String badRequestMsg = "El usuario con identificación "
                + appointment.getUserId()
                + " ya tiene una cita agendada, por lo cual no podrá realizar más agendamientos.";

        map.put("message", badRequestMsg);
        return map;
    }

    public Map<String, Object> createResponseWrongUserType() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        String badRequestMsg = "Tipo de usuario no permitido en el hospital";

        map.put("message", badRequestMsg);
        return map;
    }

    public boolean validateUserType(String userTypeIn){

        for (UserType userType : UserType.values()) {
            if (userType.name().equalsIgnoreCase(userTypeIn)) {
                return true;
            }
        }
        return false;
    }

}

package com.CarePets.controllers;

import com.CarePets.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins = "*")

public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @DeleteMapping(path = "/appointments/{id}")
    public void deleteAppointment(@PathVariable Long id){
        appointmentService.deleteAppointment(id);
    }

}

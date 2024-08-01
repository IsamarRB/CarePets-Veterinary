package com.CarePets.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CreateAppointmentDTO {


    private LocalDateTime dateTime;
    private String typeConsult;
    private String reason;
    private String status;
    private Long idPet;

}

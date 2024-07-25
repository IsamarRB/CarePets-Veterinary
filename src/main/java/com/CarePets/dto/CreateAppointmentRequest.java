package com.CarePets.dto;

import java.time.LocalDateTime;

public class CreateAppointmentRequest {
    private Long petId;
    private LocalDateTime dateTime;
    private String typeConsult;
    private String reason;
    private String status;

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTypeConsult() {
        return typeConsult;
    }

    public void setTypeConsult(String typeConsult) {
        this.typeConsult = typeConsult;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

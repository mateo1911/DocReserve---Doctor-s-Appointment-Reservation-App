package com.mateo.doktor_rezervacija.api.dto.appointment;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AppointmentCreateRequest {

    @NotNull
    private Long doctorId;

    @NotNull
    private LocalDateTime startTime;

    private String note;

    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}

package com.mateo.doktor_rezervacija.api.dto.appointment;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AppointmentUpdateRequest {

    @NotNull
    private LocalDateTime startTime;

    private String note;

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}

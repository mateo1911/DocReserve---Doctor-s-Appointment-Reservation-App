package com.mateo.doktor_rezervacija.api.dto.appointment;

import com.mateo.doktor_rezervacija.domain.AppointmentStatus;

import java.time.LocalDateTime;

public class AppointmentResponse {

    private Long id;
    private Long doctorId;
    private String doctorName;
    private LocalDateTime startTime;
    private AppointmentStatus status;
    private String note;

    public AppointmentResponse(Long id, Long doctorId, String doctorName,
                               LocalDateTime startTime, AppointmentStatus status, String note) {
        this.id = id;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.startTime = startTime;
        this.status = status;
        this.note = note;
    }

    public Long getId() { return id; }
    public Long getDoctorId() { return doctorId; }
    public String getDoctorName() { return doctorName; }
    public LocalDateTime getStartTime() { return startTime; }
    public AppointmentStatus getStatus() { return status; }
    public String getNote() { return note; }
}

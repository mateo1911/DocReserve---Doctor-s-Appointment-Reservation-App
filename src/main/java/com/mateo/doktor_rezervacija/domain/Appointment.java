package com.mateo.doktor_rezervacija.domain;

import com.mateo.doktor_rezervacija.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Doctor doctor;

    @ManyToOne(optional = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status = AppointmentStatus.BOOKED;

    @Column(length = 500)
    private String note;

    public Appointment() {}

    public Appointment(Doctor doctor, User user, LocalDateTime startTime, String note) {
        this.doctor = doctor;
        this.user = user;
        this.startTime = startTime;
        this.note = note;
        this.status = AppointmentStatus.BOOKED;
    }

    public Long getId() { return id; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public AppointmentStatus getStatus() { return status; }
    public void setStatus(AppointmentStatus status) { this.status = status; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}

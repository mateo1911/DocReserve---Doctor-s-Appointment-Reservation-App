package com.mateo.doktor_rezervacija.service;

import com.mateo.doktor_rezervacija.domain.Appointment;
import com.mateo.doktor_rezervacija.domain.AppointmentStatus;
import com.mateo.doktor_rezervacija.domain.Doctor;
import com.mateo.doktor_rezervacija.repository.AppointmentRepository;
import com.mateo.doktor_rezervacija.repository.DoctorRepository;
import com.mateo.doktor_rezervacija.user.User;
import com.mateo.doktor_rezervacija.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.access.AccessDeniedException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              DoctorRepository doctorRepository,
                              UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }

    public Appointment create(Long doctorId, String userEmail, LocalDateTime startTime, String note) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Appointment appointment = new Appointment(doctor, user, startTime, note);
        appointment.setStatus(AppointmentStatus.BOOKED);

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Appointment findById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
    }

    public Appointment update(Long id, LocalDateTime startTime, String note) {
        Appointment appointment = findById(id);
        appointment.setStartTime(startTime);
        appointment.setNote(note);
        return appointmentRepository.save(appointment);
    }

    public void delete(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new IllegalArgumentException("Appointment not found");
        }
        appointmentRepository.deleteById(id);
    }
    public List<Appointment> findAllForUser(String email) {
        return appointmentRepository.findByUserEmail(email);
    }

    public Appointment findByIdForUser(Long id, String email) {
        return appointmentRepository.findByIdAndUserEmail(id, email)
                .orElseThrow(() -> new AccessDeniedException("Access denied"));
    }


}

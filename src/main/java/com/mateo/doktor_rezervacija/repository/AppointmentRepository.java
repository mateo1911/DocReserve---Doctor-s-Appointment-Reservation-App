package com.mateo.doktor_rezervacija.repository;

import com.mateo.doktor_rezervacija.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByUserEmail(String email);

    Optional<Appointment> findByIdAndUserEmail(Long id, String email);
}
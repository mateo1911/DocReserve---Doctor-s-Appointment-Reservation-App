package com.mateo.doktor_rezervacija.repository;

import com.mateo.doktor_rezervacija.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}

package com.mateo.doktor_rezervacija.repository;

import com.mateo.doktor_rezervacija.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}

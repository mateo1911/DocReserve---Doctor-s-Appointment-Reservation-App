package com.mateo.doktor_rezervacija.service;

import com.mateo.doktor_rezervacija.domain.Doctor;
import com.mateo.doktor_rezervacija.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor create(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public Doctor findById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
    }

    public Doctor update(Long id, Doctor updated) {
        Doctor doctor = findById(id);
        doctor.setFullName(updated.getFullName());
        doctor.setSpecialization(updated.getSpecialization());
        return doctorRepository.save(doctor);
    }

    public void delete(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new IllegalArgumentException("Doctor not found");
        }
        doctorRepository.deleteById(id);
    }
}

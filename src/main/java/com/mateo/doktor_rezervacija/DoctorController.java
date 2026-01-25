package com.mateo.doktor_rezervacija.api;

import com.mateo.doktor_rezervacija.domain.Doctor;
import com.mateo.doktor_rezervacija.service.DoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // USER + ADMIN (read)
    @GetMapping("/api/user/doctors")
    public List<Doctor> getAll() {
        return doctorService.findAll();
    }

    @GetMapping("/api/user/doctors/{id}")
    public Doctor getById(@PathVariable Long id) {
        return doctorService.findById(id);
    }

    // ADMIN (CRUD)
    @PostMapping("/api/admin/doctors")
    public Doctor create(@RequestBody Doctor doctor) {
        return doctorService.create(doctor);
    }

    @PutMapping("/api/admin/doctors/{id}")
    public Doctor update(@PathVariable Long id, @RequestBody Doctor doctor) {
        return doctorService.update(id, doctor);
    }

    @DeleteMapping("/api/admin/doctors/{id}")
    public void delete(@PathVariable Long id) {
        doctorService.delete(id);
    }
}

package com.mateo.doktor_rezervacija.api;

import com.mateo.doktor_rezervacija.api.dto.appointment.*;
import com.mateo.doktor_rezervacija.domain.Appointment;
import com.mateo.doktor_rezervacija.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // USER
    @PostMapping("/api/user/appointments")
    public AppointmentResponse create(@Valid @RequestBody AppointmentCreateRequest req) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Appointment a = appointmentService.create(req.getDoctorId(), email, req.getStartTime(), req.getNote());
        return map(a);
    }

    @GetMapping("/api/user/appointments")
    public List<AppointmentResponse> getAllForUser() {
        return appointmentService.findAll().stream().map(this::map).toList();
    }

    @GetMapping("/api/user/appointments/{id}")
    public AppointmentResponse getById(@PathVariable Long id) {
        return map(appointmentService.findById(id));
    }

    @PutMapping("/api/user/appointments/{id}")
    public AppointmentResponse update(@PathVariable Long id, @Valid @RequestBody AppointmentUpdateRequest req) {
        return map(appointmentService.update(id, req.getStartTime(), req.getNote()));
    }

    @DeleteMapping("/api/user/appointments/{id}")
    public void delete(@PathVariable Long id) {
        appointmentService.delete(id);
    }

    // ADMIN
    @GetMapping("/api/admin/appointments")
    public List<AppointmentResponse> getAllAdmin() {
        return appointmentService.findAll().stream().map(this::map).toList();
    }

    private AppointmentResponse map(Appointment a) {
        return new AppointmentResponse(
                a.getId(),
                a.getDoctor().getId(),
                a.getDoctor().getFullName(),
                a.getStartTime(),
                a.getStatus(),
                a.getNote()
        );
    }
}

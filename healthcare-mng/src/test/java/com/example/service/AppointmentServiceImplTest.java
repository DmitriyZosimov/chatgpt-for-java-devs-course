package com.example.service;

import com.example.model.Appointment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class AppointmentServiceImplTest {

    @Autowired
    private AppointmentServiceImpl appointmentService;

    @Test
    public void testCreateAppointment() {
        Appointment appointment = new Appointment();
        appointment.setDate(LocalDate.of(2023, 8, 30));

        Appointment createdAppointment = appointmentService.createAppointment(appointment);

        assertNotNull(createdAppointment.getId());
        assertEquals(LocalDate.of(2023, 8, 30), createdAppointment.getDate());
    }

    @Test
    public void testGetAppointmentById() {
        // Assuming there is an appointment with ID 1 in the database
        Appointment appointment = appointmentService.getAppointmentById(1L);

        assertNotNull(appointment);
        assertEquals(1L, appointment.getId().longValue());
    }

    @Test
    public void testGetAllAppointments() {
        // Assuming there are only 3 appointments in the database
        List<Appointment> appointments = appointmentService.getAllAppointments();

        assertNotNull(appointments);
        assertEquals(3, appointments.size());
    }

    @Test
    public void testUpdateAppointment() {
        // Assuming there is an appointment with ID 2 in the database
        Appointment appointment = appointmentService.getAppointmentById(2L);
        appointment.setDate(LocalDate.of(2023, 9, 1));

        Appointment updatedAppointment = appointmentService.updateAppointment(appointment);

        assertNotNull(updatedAppointment);
        assertEquals(LocalDate.of(2023, 9, 1), updatedAppointment.getDate());
    }

    @Test
    public void testUpdateAppointment_ShouldReturnNull() {
        Appointment appointment = new Appointment();
        appointment.setId(999L);
        appointment.setDate(LocalDate.of(2023, 9, 1));

        Appointment updatedAppointment = appointmentService.updateAppointment(appointment);

        assertNull(updatedAppointment);
    }

    @Test
    public void testDeleteAppointment() {
        // Assuming there is an appointment with ID 3 in the database
        appointmentService.deleteAppointment(3L);

        assertNull(appointmentService.getAppointmentById(3L));
    }
}


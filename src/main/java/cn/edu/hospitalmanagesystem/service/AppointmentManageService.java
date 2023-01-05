package cn.edu.hospitalmanagesystem.service;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public interface AppointmentManageService {
    void order(Long patientId, Long DoctorId, Timestamp timestamp);

    void updateOrder(Long orderId,int status);
}

package cn.edu.hospitalmanagesystem.service;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public interface AppointmentManageService {
    void order(Long patientId, Long DoctorId, Timestamp timestamp);

    void updateOrder(Long orderId,int status);

    ArrayList<Map<String,Object>> getOrder(long id);

    ArrayList<Map<String,Object>> getOrderByStatus(long id,int status);

    List<Long> getFreeTime(long doctorId,long time);
}

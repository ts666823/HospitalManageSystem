package cn.edu.hospitalmanagesystem.service.impl;

import cn.edu.hospitalmanagesystem.model.AppointmentEntity;
import cn.edu.hospitalmanagesystem.repository.AppointmentRepository;
import cn.edu.hospitalmanagesystem.repository.DoctorRepository;
import cn.edu.hospitalmanagesystem.service.AppointmentManageService;
import com.github.yitter.idgen.YitIdHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Service
public class AppointmentManageServiceImpl implements AppointmentManageService {
    @Resource
    AppointmentRepository appointmentRepository;

    @Resource
    DoctorRepository doctorRepository;

    @Override
    public void order(Long patientId, Long doctorId, Timestamp timestamp) {
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setDoctorId(doctorId);
        appointmentEntity.setPatientId(patientId);
        appointmentEntity.setTime(timestamp);
        appointmentEntity.setStatus(0);
        appointmentEntity.setMoney(doctorRepository.findDoctorEntityById(doctorId).getPrice());
        appointmentEntity.setId(YitIdHelper.nextId());
        appointmentRepository.save(appointmentEntity);
    }

    @Override
    public void updateOrder(Long orderId,int status) {
        AppointmentEntity appointmentEntity = appointmentRepository.findAppointmentEntityById(orderId);
        appointmentEntity.setStatus(status);
        appointmentEntity.setStatus(status);
        appointmentRepository.save(appointmentEntity);
    }

}

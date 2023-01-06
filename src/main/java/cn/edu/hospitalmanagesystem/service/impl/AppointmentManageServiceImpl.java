package cn.edu.hospitalmanagesystem.service.impl;

import cn.edu.hospitalmanagesystem.model.AppointmentEntity;
import cn.edu.hospitalmanagesystem.model.DoctorEntity;
import cn.edu.hospitalmanagesystem.repository.AppointmentRepository;
import cn.edu.hospitalmanagesystem.repository.DoctorRepository;
import cn.edu.hospitalmanagesystem.service.AppointmentManageService;
import com.github.yitter.idgen.YitIdHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public ArrayList<Map<String,Object>> getOrder(long id) {
        List<AppointmentEntity> appointmentEntityList = appointmentRepository.findAppointmentEntitiesByDoctorIdOrPatientIdOrderByTime(id,id);
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        for (AppointmentEntity appointmentEntity :appointmentEntityList){
            HashMap<String,Object> mmap = new HashMap<>();
            mmap.put("id",appointmentEntity.getId());
            mmap.put("patientId",appointmentEntity.getPatientId());
            mmap.put("money",appointmentEntity.getMoney());
            mmap.put("time",appointmentEntity.getTime().getTime());
            mmap.put("status",appointmentEntity.getStatus());
            DoctorEntity doctorEntity = doctorRepository.findDoctorEntityById(appointmentEntity.getDoctorId());
            HashMap<String,Object> doctorMap = new HashMap<>();
            doctorMap.put("doctorId",doctorEntity.getId());
            doctorMap.put("name",doctorEntity.getName());
            doctorMap.put("outpatient",doctorEntity.getOutpatient());
            doctorMap.put("description",doctorEntity.getDescription());
            doctorMap.put("sex",doctorEntity.getSex());
            mmap.put("doctor",doctorMap);
            list.add(mmap);
        }
        return list;
    }

}

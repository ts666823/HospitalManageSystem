package cn.edu.hospitalmanagesystem.service.impl;

import cn.edu.hospitalmanagesystem.enums.OrderStatus;
import cn.edu.hospitalmanagesystem.model.AppointmentEntity;
import cn.edu.hospitalmanagesystem.model.DoctorEntity;
import cn.edu.hospitalmanagesystem.model.PatientEntity;
import cn.edu.hospitalmanagesystem.repository.AppointmentRepository;
import cn.edu.hospitalmanagesystem.repository.DoctorRepository;
import cn.edu.hospitalmanagesystem.repository.PatientRepository;
import cn.edu.hospitalmanagesystem.service.AppointmentManageService;
import com.github.yitter.idgen.YitIdHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AppointmentManageServiceImpl implements AppointmentManageService {
    @Resource
    AppointmentRepository appointmentRepository;

    @Resource
    DoctorRepository doctorRepository;

    @Resource
    PatientRepository patientRepository;

    @Override
    public OrderStatus order(Long patientId, Long doctorId, Timestamp timestamp) {
        PatientEntity patientEntity = patientRepository.findPatientEntityById(patientId);
        Integer accountBalance = patientEntity.getAccountBalance();
        Integer price = doctorRepository.findDoctorEntityById(doctorId).getPrice();
        if (accountBalance <price) return OrderStatus.NotEnoughMoney;
        patientEntity.setAccountBalance(accountBalance-price);
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setDoctorId(doctorId);
        appointmentEntity.setPatientId(patientId);
        appointmentEntity.setTime(timestamp);
        appointmentEntity.setStatus(0);
        appointmentEntity.setMoney(price);
        appointmentEntity.setId(YitIdHelper.nextId());
        appointmentRepository.save(appointmentEntity);
        return OrderStatus.Success;
    }

    @Override
    public void updateOrder(Long orderId,int status) {
        AppointmentEntity appointmentEntity = appointmentRepository.findAppointmentEntityById(orderId);
        appointmentEntity.setStatus(status);
        if (status == 1){
            DoctorEntity doctorEntity = doctorRepository.findDoctorEntityById(appointmentEntity.getDoctorId());
            PatientEntity patientEntity = patientRepository.findPatientEntityById(appointmentEntity.getPatientId());
            Integer price = doctorEntity.getPrice();
            Integer accountBalance = patientEntity.getAccountBalance();
            accountBalance+=price;
            patientEntity.setAccountBalance(accountBalance);
            patientRepository.save(patientEntity);
        }
        appointmentRepository.save(appointmentEntity);
    }

    @Override
    public ArrayList<Map<String,Object>> getOrder(long id) {
        List<AppointmentEntity> appointmentEntityList = appointmentRepository.findAppointmentEntitiesByDoctorIdOrPatientIdOrderByTimeDesc(id,id);
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

    @Override
    public ArrayList<Map<String, Object>> getOrderByStatus(long id, int status) {
        List<AppointmentEntity> appointmentEntityList = appointmentRepository.findAppointmentEntitiesByDoctorIdOrPatientIdOrderByTimeDesc(id,id);
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        for (AppointmentEntity appointmentEntity :appointmentEntityList){
            if (appointmentEntity.getStatus() != status) continue;
            HashMap<String,Object> mmap = new HashMap<>();
            mmap.put("id",appointmentEntity.getId());
            mmap.put("money",appointmentEntity.getMoney());
            mmap.put("time",appointmentEntity.getTime().getTime());
            mmap.put("status",appointmentEntity.getStatus());
            PatientEntity patientEntity = patientRepository.findPatientEntityById(appointmentEntity.getPatientId());
            HashMap<String,Object> patientMap = new HashMap<>();
            patientMap.put("patientId",patientEntity.getId());
            patientMap.put("name",patientEntity.getName());
            patientMap.put("sex",patientEntity.getSex());
            patientMap.put("idNumber",patientEntity.getIdNumber());
            mmap.put("patient",patientMap);
            list.add(mmap);
        }
        return list;
    }

    @Override
    public List<Long> getFreeTime(long doctorId,long time) {
        List<AppointmentEntity> entities = appointmentRepository.findAppointmentEntitiesByDoctorId(doctorId);
        Date date = new Date(time);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String curDay = dateFormat.format(date);
        try {
            date = dateFormat.parse(curDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Long> result = new ArrayList<>();
        for (int i=0;i<12;i++){
            date.setHours(7+i);
            result.add(date.getTime());
        }
        for (AppointmentEntity appointmentEntity:entities){
            result.remove(appointmentEntity.getTime().getTime());
        }
        return result;
    }

}

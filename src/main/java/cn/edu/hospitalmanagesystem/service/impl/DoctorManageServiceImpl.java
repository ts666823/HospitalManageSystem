package cn.edu.hospitalmanagesystem.service.impl;

import cn.edu.hospitalmanagesystem.enums.LoginStatus;
import cn.edu.hospitalmanagesystem.model.DoctorEntity;
import cn.edu.hospitalmanagesystem.repository.DoctorRepository;
import cn.edu.hospitalmanagesystem.service.DoctorManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class DoctorManageServiceImpl implements DoctorManageService {
    @Resource
    DoctorRepository doctorRepository;

    @Override
    public LoginStatus doctorLogin(String idNumber, String pwd) {
        DoctorEntity doctorEntity = doctorRepository.findDoctorEntityByIdNumber(idNumber);
        if(doctorEntity == null)
        {
            return LoginStatus.NoDoctor;
        }
        else if(Objects.equals(doctorEntity.getPassword(), pwd)){
            return LoginStatus.Success;
        }
        else{
            return LoginStatus.IncorrectPassword;
        }
    }

    @Override
    public Long getIdByIdNumber(String idNumber) {
        DoctorEntity patient = doctorRepository.findDoctorEntityByIdNumber(idNumber);
        if (patient == null) {
            throw new RuntimeException("User email not registered");
        } else {
            return patient.getId();
        }
    }
}
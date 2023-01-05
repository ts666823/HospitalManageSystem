package cn.edu.hospitalmanagesystem.service;

import cn.edu.hospitalmanagesystem.enums.LoginStatus;
import cn.edu.hospitalmanagesystem.model.DoctorEntity;
import org.springframework.stereotype.Service;

@Service
public interface DoctorManageService {
    LoginStatus doctorLogin(String idNumber, String pwd);

    Long getIdByIdNumber(String idNumber);

    DoctorEntity getDoctorEntity(Long id);

    Long update(DoctorEntity doctorEntity);

}

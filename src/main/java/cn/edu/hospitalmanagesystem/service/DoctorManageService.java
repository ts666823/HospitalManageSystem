package cn.edu.hospitalmanagesystem.service;

import cn.edu.hospitalmanagesystem.enums.LoginStatus;
import cn.edu.hospitalmanagesystem.model.DoctorEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface DoctorManageService {
    LoginStatus doctorLogin(String idNumber, String pwd);

    Long getIdByIdNumber(String idNumber);

    DoctorEntity getDoctorEntity(Long id);

    Long update(DoctorEntity doctorEntity);

    List<DoctorEntity> getDoctors(String outpatient);

}

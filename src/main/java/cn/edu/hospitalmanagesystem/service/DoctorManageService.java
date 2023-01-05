package cn.edu.hospitalmanagesystem.service;

import cn.edu.hospitalmanagesystem.enums.LoginStatus;
import org.springframework.stereotype.Service;

@Service
public interface DoctorManageService {
    LoginStatus doctorLogin(String idNumber, String pwd);

    Long getIdByIdNumber(String idNumber);

}

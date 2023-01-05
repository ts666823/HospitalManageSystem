package cn.edu.hospitalmanagesystem.service;

import cn.edu.hospitalmanagesystem.enums.LoginStatus;
import cn.edu.hospitalmanagesystem.enums.SignupStatus;
import cn.edu.hospitalmanagesystem.model.PatientEntity;
import org.springframework.stereotype.Service;


@Service
public interface PatientManageService {
    SignupStatus patientSignup(PatientEntity userEntity);
    SignupStatus patientSignup(String name, String idNumber, String pwd, String sex, int account_balance);
    LoginStatus patientLogin(String idNumber, String pwd);
    Long getIdByIdNumber(String idNumber);
}

package cn.edu.tongji.hospitalmanagesystem.service;

import cn.edu.tongji.hospitalmanagesystem.enums.LoginStatus;
import cn.edu.tongji.hospitalmanagesystem.enums.SignupStatus;
import cn.edu.tongji.hospitalmanagesystem.model.PatientEntity;
import org.springframework.stereotype.Service;

/**
 * @author tangshuo
 * @version 1.0.0
 * @ClassName PatientManageService.java
 * @Description TODO
 * @createTime 2023年01月04日 10:11:00
 */
@Service
public interface PatientManageService {
    SignupStatus patientSignup(PatientEntity userEntity);
    SignupStatus patientSignup(String name, String idNumber, String pwd, String sex, int account_balance);
    LoginStatus patientLogin(String idNumber, String pwd);
    Long getIdByIdNumber(String idNumber);
}

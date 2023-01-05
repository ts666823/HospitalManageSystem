package cn.edu.hospitalmanagesystem.service.impl;

import cn.edu.hospitalmanagesystem.enums.LoginStatus;
import cn.edu.hospitalmanagesystem.enums.SignupStatus;
import cn.edu.hospitalmanagesystem.model.PatientEntity;
import cn.edu.hospitalmanagesystem.repository.PatientRepository;
import cn.edu.hospitalmanagesystem.service.PatientManageService;
import com.github.yitter.idgen.YitIdHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;


@Service
public class PatientManageServiceImpl implements PatientManageService {
    @Resource
    PatientRepository patientRepository;

    @Override
    public SignupStatus patientSignup(PatientEntity patientEntity) {
        return patientSignup(patientEntity.getName(),patientEntity.getIdNumber(),patientEntity.getPassword(),patientEntity.getSex(),patientEntity.getAccountBalance());
    }
    
    @Override
    public SignupStatus patientSignup(String name, String idNumber, String pwd, String sex, int account_balance) {
        if(patientRepository.findPatientEntityByIdNumber(idNumber) != null)
        {
            return SignupStatus.IdNumberExisted;
        }
        PatientEntity patientEntity = new PatientEntity();
        long id = YitIdHelper.nextId();
        patientEntity.setId(id);
        patientEntity.setName(name);
        patientEntity.setIdNumber(idNumber);
        patientEntity.setPassword(pwd);
        patientEntity.setSex(sex);
        patientEntity.setAccountBalance(account_balance);

        patientRepository.save(patientEntity);
        return SignupStatus.Successful;
    }

    @Override
    public LoginStatus patientLogin(String idNumber, String pwd) {
        PatientEntity patientEntity = patientRepository.findPatientEntityByIdNumber(idNumber);
        if(patientEntity == null)
        {
            return LoginStatus.NoPatient;
        }
        else if(Objects.equals(patientEntity.getPassword(), pwd)){
            return LoginStatus.Success;
        }
        else{
            return LoginStatus.IncorrectPassword;
        }
    }

    @Override
    public Long getIdByIdNumber(String idNumber) {
        PatientEntity patient = patientRepository.findPatientEntityByIdNumber(idNumber);
        if (patient == null) {
            throw new RuntimeException("User email not registered");
        } else {
            return patient.getId();
        }
    }
}

package cn.edu.tongji.hospitalmanagesystem.repository;

import cn.edu.tongji.hospitalmanagesystem.model.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tangshuo
 * @version 1.0.0
 * @ClassName PatientRepository.java
 * @Description TODO
 * @createTime 2023年01月04日 10:12:00
 */
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    PatientEntity findPatientEntityByIdNumber(String idNumber);
}

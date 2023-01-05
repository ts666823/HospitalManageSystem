package cn.edu.hospitalmanagesystem.repository;

import cn.edu.hospitalmanagesystem.model.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    PatientEntity findPatientEntityByIdNumber(String idNumber);
}

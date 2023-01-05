package cn.edu.hospitalmanagesystem.repository;

import cn.edu.hospitalmanagesystem.model.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<DoctorEntity,Long> {
    DoctorEntity findDoctorEntityByIdNumber(String idNumber);

    DoctorEntity findDoctorEntityById(Long id);

}

package cn.edu.hospitalmanagesystem.repository;

import cn.edu.hospitalmanagesystem.model.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MedicineRepository extends JpaRepository<MedicineEntity,Long> {

    MedicineEntity findMedicineEntityById(long id);
}

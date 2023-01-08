package cn.edu.hospitalmanagesystem.service;

import cn.edu.hospitalmanagesystem.model.MedicineEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicineManageService {
    Long addMedicine(MedicineEntity medicineEntity);

    List<MedicineEntity> getMedicine();

    List<MedicineEntity> searchMedicine(String name);

}

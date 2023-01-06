package cn.edu.hospitalmanagesystem.service.impl;

import cn.edu.hospitalmanagesystem.model.MedicineEntity;
import cn.edu.hospitalmanagesystem.repository.MedicineRepository;
import cn.edu.hospitalmanagesystem.service.MedicineManageService;
import com.github.yitter.idgen.YitIdHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MedicineManageServiceImpl implements MedicineManageService {

    @Resource
    MedicineRepository medicineRepository;

    @Override
    public Long addMedicine(MedicineEntity medicineEntity) {
        long id = YitIdHelper.nextId();
        medicineEntity.setId(id);
        medicineRepository.save(medicineEntity);
        return id;
    }

    @Override
    public List<MedicineEntity> getMedicine() {
      return   medicineRepository.findAll();
    }
}

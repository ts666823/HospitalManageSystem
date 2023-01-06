package cn.edu.hospitalmanagesystem.service.impl;

import cn.edu.hospitalmanagesystem.model.MedicineEntity;
import cn.edu.hospitalmanagesystem.model.RecommendEntity;
import cn.edu.hospitalmanagesystem.repository.MedicineRepository;
import cn.edu.hospitalmanagesystem.repository.RecommendRepository;
import cn.edu.hospitalmanagesystem.service.RecommendManageService;
import com.github.yitter.idgen.YitIdHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendManageServiceImpl implements RecommendManageService {
    @Resource
    RecommendRepository recommendRepository;

    @Resource
    MedicineRepository medicineRepository;

    @Override
    public List<MedicineEntity> getRecommendMedicine(Long id) {
        List<RecommendEntity> recommends = recommendRepository.findRecommendEntitiesByAppointmentId(id);
        List<MedicineEntity> result = new ArrayList<>();
        for (RecommendEntity recommendEntity:recommends){
            result.add(medicineRepository.findMedicineEntityById(recommendEntity.getMedicineId()));
        }

        return result;
    }

    @Override
    public Long recommend(RecommendEntity recommendEntity) {
        long id = YitIdHelper.nextId();
        recommendEntity.setId(id);
        recommendRepository.save(recommendEntity);
       return id;
    }
}

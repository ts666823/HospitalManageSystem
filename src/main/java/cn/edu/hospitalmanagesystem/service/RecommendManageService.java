package cn.edu.hospitalmanagesystem.service;

import cn.edu.hospitalmanagesystem.model.MedicineEntity;
import cn.edu.hospitalmanagesystem.model.RecommendEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecommendManageService {

    List<MedicineEntity> getRecommendMedicine(Long id);

    Long recommend(RecommendEntity recommendEntity);
}

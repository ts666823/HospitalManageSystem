package cn.edu.hospitalmanagesystem.repository;

import cn.edu.hospitalmanagesystem.model.MedicineEntity;
import cn.edu.hospitalmanagesystem.model.RecommendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendRepository extends JpaRepository<RecommendEntity, Long> {

    List<RecommendEntity> findRecommendEntitiesByAppointmentId(long id);
}

package cn.edu.hospitalmanagesystem.repository;

import cn.edu.hospitalmanagesystem.model.MedicineEntity;
import cn.edu.hospitalmanagesystem.model.RecommendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface RecommendRepository extends JpaRepository<RecommendEntity, Long> {

    List<RecommendEntity> findRecommendEntitiesByAppointmentId(long id);

    @Transactional
    void deleteByMedicineIdAndAppointmentId(long medicineId, long appointmentId);
}

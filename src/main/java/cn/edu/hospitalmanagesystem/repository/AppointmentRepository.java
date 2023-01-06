package cn.edu.hospitalmanagesystem.repository;

import cn.edu.hospitalmanagesystem.model.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    List<AppointmentEntity> findAppointmentEntitiesByDoctorIdOrPatientIdOrderByTimeDesc(long doctorId,long patientId);


    AppointmentEntity findAppointmentEntityById(long id);


}

package cn.edu.hospitalmanagesystem.repository;

import cn.edu.hospitalmanagesystem.model.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    AppointmentEntity findAppointmentEntityByDoctorIdAndPatientId(long doctorId,long patientId);

    AppointmentEntity findAppointmentEntityById(long id);
}

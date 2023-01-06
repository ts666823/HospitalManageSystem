package cn.edu.hospitalmanagesystem.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "recommend", schema = "Study", catalog = "")
public class RecommendEntity {
    private long id;
    private long patientId;
    private long doctorId;
    private long medicineId;
    private long appointmentId;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "patient_id")
    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    @Basic
    @Column(name = "doctor_id")
    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    @Basic
    @Column(name = "medicine_id")
    public long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(long medicineId) {
        this.medicineId = medicineId;
    }

    @Basic
    @Column(name = "appointment_id")
    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecommendEntity that = (RecommendEntity) o;
        return id == that.id && patientId == that.patientId && doctorId == that.doctorId && medicineId == that.medicineId && appointmentId == that.appointmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientId, doctorId, medicineId, appointmentId);
    }
}

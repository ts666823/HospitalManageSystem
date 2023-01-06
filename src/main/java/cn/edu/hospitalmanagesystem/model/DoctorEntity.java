package cn.edu.hospitalmanagesystem.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "doctor", schema = "Study", catalog = "")
public class DoctorEntity {
    private long id;
    private String name;
    private String idNumber;
    private String password;
    private String sex;
    private String outpatient;
    private Integer price;
    private String description;
    private String image;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "id_number")
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "outpatient")
    public String getOutpatient() {
        return outpatient;
    }

    public void setOutpatient(String outpatient) {
        this.outpatient = outpatient;
    }

    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorEntity that = (DoctorEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(idNumber, that.idNumber) && Objects.equals(password, that.password) && Objects.equals(sex, that.sex) && Objects.equals(outpatient, that.outpatient) && Objects.equals(price, that.price) && Objects.equals(description, that.description) && Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, idNumber, password, sex, outpatient, price, description, image);
    }
}

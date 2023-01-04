package cn.edu.tongji.hospitalmanagesystem.model;


import javax.persistence.*;
import java.util.Objects;

/**
 * @author tangshuo
 * @version 1.0.0
 * @ClassName PatientEntity.java
 * @Description TODO
 * @createTime 2023年01月04日 10:39:00
 */
@Entity
@Table(name = "patient", schema = "Study", catalog = "")
public class PatientEntity {
    private long id;
    private String name;
    private String idNumber;
    private String password;
    private String sex;
    private Integer accountBalance;

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
    @Column(name = "account_balance")
    public Integer getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Integer accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientEntity that = (PatientEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(idNumber, that.idNumber) && Objects.equals(password, that.password) && Objects.equals(sex, that.sex) && Objects.equals(accountBalance, that.accountBalance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, idNumber, password, sex, accountBalance);
    }
}

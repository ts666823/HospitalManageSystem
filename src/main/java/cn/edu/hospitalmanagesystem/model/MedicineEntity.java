package cn.edu.hospitalmanagesystem.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "medicine", schema = "Study", catalog = "")
public class MedicineEntity {
    private long id;
    private String name;
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
        MedicineEntity that = (MedicineEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, image);
    }
}

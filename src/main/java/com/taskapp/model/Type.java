package com.taskapp.model;


import javax.persistence.*;

@Entity
@Table(name ="type")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_type_seq")
    @SequenceGenerator(sequenceName = "id_type_seq", name = "id_type_seq", initialValue = 1, allocationSize = 1)
    private int id;

    private String type;

    public Type() {
    }

    public Type(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}

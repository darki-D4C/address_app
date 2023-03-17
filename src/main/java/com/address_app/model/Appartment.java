package com.address_app.model;

import javax.persistence.*;

@Entity
@Table(name = "appartments")
public class Appartment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "area")
    private float area;

    @ManyToOne
    @JoinColumn(name = "house_id", nullable = false)
    private House house;

    public Appartment() {

    }

    public Appartment(float area, House house) {
        this.area = area;
        this.house = house;
    }

    public long getId() {
        return id;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return "Appartment [id=" + id + ", title=" + area + ", desc=" + house + ", published=";
    }

}

package pl.pwr.edu.IntetnetApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String serviceType;
    private float servicePrice;

    protected PriceList(){}
    public PriceList(Integer id, String serviceType, float servicePrice) {
        this.id = id;
        this.serviceType = serviceType;
        this.servicePrice = servicePrice;
    }

    public Integer getId() {
        return id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public float getServicePrice() {
        return servicePrice;
    }
}

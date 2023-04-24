package pl.pwr.edu.IntetnetApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Installation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String address;
    private int routerNumber;
    private String serviceType;

    protected Installation(){}
    public Installation(Integer id, String address, int routerNumber, String serviceType) {
        this.id = id;
        this.address = address;
        this.routerNumber = routerNumber;
        this.serviceType = serviceType;
    }

    public Integer getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public int getRouterNumber() {
        return routerNumber;
    }

    public String getServiceType() {
        return serviceType;
    }
}

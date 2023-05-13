package pl.edu.pwr.internetapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="installations")
public class Installation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "installation_id")
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "router_number")
    private String routerNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_type_id")
    private ServiceType serviceType;

    public Installation() {
    }

    public Installation(Long id, String address, String routerNumber,ServiceType serviceType, Client client) {
        this.id = id;
        this.address = address;
        this.routerNumber = routerNumber;
        this.serviceType = serviceType;
        this.client = client;
    }

    public Installation(String address, String routerNumber,ServiceType serviceType, Client client) {
        this.address = address;
        this.routerNumber = routerNumber;
        this.serviceType = serviceType;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRouterNumber() {
        return routerNumber;
    }

    public void setRouterNumber(String routerNumber) {
        this.routerNumber = routerNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(){

    }


}


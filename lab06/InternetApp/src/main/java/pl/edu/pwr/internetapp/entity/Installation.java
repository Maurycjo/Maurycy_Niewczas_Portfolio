package pl.edu.pwr.internetapp.entity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_type_id")
    private ServiceType serviceType;

    @OneToMany(mappedBy = "installation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Charge> charges = new HashSet<>();

    @OneToMany(mappedBy = "installation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Payment> payments = new HashSet<>();

    public Installation() {
    }

    public Installation(String address, String routerNumber, Client client, ServiceType serviceType, Set<Charge> charges, Set<Payment> payments) {
        this.address = address;
        this.routerNumber = routerNumber;
        this.client = client;
        this.serviceType = serviceType;
        this.charges = charges;
        this.payments = payments;
    }

    public Installation(Long id, String address, String routerNumber, Client client, ServiceType serviceType, Set<Charge> charges, Set<Payment> payments) {
        this.id = id;
        this.address = address;
        this.routerNumber = routerNumber;
        this.client = client;
        this.serviceType = serviceType;
        this.charges = charges;
        this.payments = payments;
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

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Set<Charge> getCharges() {
        return charges;
    }

    public void setCharges(Set<Charge> charges) {
        this.charges = charges;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }
}


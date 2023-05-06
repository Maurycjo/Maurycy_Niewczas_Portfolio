package pl.edu.pwr.internetapp.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="service_types")
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_type_id")
    private Long id;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "price")
    private float price;

    @OneToMany(mappedBy = "serviceType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Installation> installations = new HashSet<>();

    public ServiceType() {
    }

    public ServiceType(Long id, String serviceName, float price, Set<Installation> installations) {
        this.id = id;
        this.serviceName = serviceName;
        this.price = price;
        this.installations = installations;
    }

    public ServiceType(String serviceName, float price, Set<Installation> installations) {
        this.serviceName = serviceName;
        this.price = price;
        this.installations = installations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Set<Installation> getInstallations() {
        return installations;
    }

    public void setInstallations(Set<Installation> installations) {
        this.installations = installations;
    }
}

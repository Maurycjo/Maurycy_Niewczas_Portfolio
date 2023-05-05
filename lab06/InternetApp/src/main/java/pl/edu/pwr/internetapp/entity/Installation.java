package pl.edu.pwr.internetapp.entity;

import jakarta.persistence.*;

@Entity(name = "Installation")
@Table(
        name = "Installation"
)
public class Installation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "Installation_Id"
    )
    private Long id;

    @Column(
            name = "address"
    )
    private String address;
    @Column(
            name = "Router_Number"
    )
    private int routerNumber;

    @Column(
            name = "Service_Type"
    )
    private String serviceType;

    public Installation() {
    }

    public Installation(Long id, String address, int routerNumber, String serviceType) {
        this.id = id;
        this.address = address;
        this.routerNumber = routerNumber;
        this.serviceType = serviceType;
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

    public int getRouterNumber() {
        return routerNumber;
    }

    public void setRouterNumber(int routerNumber) {
        this.routerNumber = routerNumber;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public String toString() {
        return "Installation{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", routerNumber=" + routerNumber +
                ", serviceType=" + serviceType +
                '}';
    }

    @ManyToOne
    @JoinColumn(
            name = "PriceList_Id",
            nullable = false
    )
    private PriceList priceListId;

    @ManyToOne
    @JoinColumn(
            name = "Client_Id",
            nullable = false
    )
    private Client clientId;

}

package pl.edu.pwr.internetapp.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "Price List"
)
public class PriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "PriceList_Id"
    )
    private Long id;
    @Column(
            name = "Service_Type",
            columnDefinition = "varchar(255)"
    )
    private String type;

    @Column(
            name = "Price"
    )
    private Float price;

    public PriceList() {
    }

    public PriceList(Long id, String type, Float price) {
        this.id = id;
        this.type = type;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}

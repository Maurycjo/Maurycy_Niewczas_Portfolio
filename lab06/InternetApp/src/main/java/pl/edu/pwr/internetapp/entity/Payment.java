package pl.edu.pwr.internetapp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "Payments"
)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "Opłata ID"
    )
    private Long id;
    @Column(
            name = "Opłata"
    )
    private LocalDate date;
    @Column(
            name = "Kwota"
    )
    private Float price;

    public Payment() {
    }

    public Payment(Long id, LocalDate date, Float price) {
        this.id = id;
        this.date = date;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(
            name = "Installation_Id",
            nullable = false
    )
    private Installation installationId;
}

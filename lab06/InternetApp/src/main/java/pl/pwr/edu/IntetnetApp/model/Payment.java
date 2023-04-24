package pl.pwr.edu.IntetnetApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private LocalDate paymentDate;
    private float paymentValue;

    protected Payment(){}
    public Payment(Integer id, LocalDate paymentDate, float paymentValue) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.paymentValue = paymentValue;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", paymentDate=" + paymentDate +
                ", paymentValue=" + paymentValue +
                '}';
    }


    public Integer getId() {
        return id;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public float getPaymentValue() {
        return paymentValue;
    }
}

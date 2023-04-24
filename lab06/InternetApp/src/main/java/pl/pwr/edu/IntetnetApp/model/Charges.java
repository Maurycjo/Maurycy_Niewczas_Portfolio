package pl.pwr.edu.IntetnetApp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Charges {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private LocalDate paymentDeadline;
    private float paymentAmount;

    protected Charges(){}
    public Charges(Integer id, LocalDate paymentDeadline, float paymentAmount) {
        this.id = id;
        this.paymentDeadline = paymentDeadline;
        this.paymentAmount = paymentAmount;
    }

    @Override
    public String toString() {
        return "Charges{" +
                "id=" + id +
                ", paymentDeadline=" + paymentDeadline +
                ", paymentAmount=" + paymentAmount +
                '}';
    }


    public Integer getId() {
        return id;
    }

    public LocalDate getPaymentDeadline() {
        return paymentDeadline;
    }

    public float getPaymentAmount() {
        return paymentAmount;
    }
}

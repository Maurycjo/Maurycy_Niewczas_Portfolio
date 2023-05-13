package pl.edu.pwr.internetapp.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="charges")
public class Charge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "charge_id")
    private Long id;

    @Column(name = "payment_deadline")
    private LocalDate paymentDeadline;

    @Column(name = "amount_to_pay")
    private float amountToPay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "installation_id")
    private Installation installation;

    public Charge() {
    }

    public Charge(LocalDate paymentDeadline, float amountToPay, Installation installation) {
        this.paymentDeadline = paymentDeadline;
        this.amountToPay = amountToPay;
        this.installation = installation;
    }

    public Charge(Long id, LocalDate paymentDeadline, float amountToPay, Installation installation) {
        this.id = id;
        this.paymentDeadline = paymentDeadline;
        this.amountToPay = amountToPay;
        this.installation = installation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPaymentDeadline() {
        return paymentDeadline;
    }

    public void setPaymentDeadline(LocalDate paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }

    public float getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(float amountToPay) {
        this.amountToPay = amountToPay;
    }

    public Installation getInstallation() {
        return installation;
    }

    public void setInstallation(Installation installation) {
        this.installation = installation;
    }
}


package pl.edu.pwr.internetapp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "amount_paid")
    private float amountPaid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "installation_id")
    private Installation installation;

    public Payment() {
    }

    public Payment(LocalDate paymentDate, float amountPaid, Installation installation) {
        this.paymentDate = paymentDate;
        this.amountPaid = amountPaid;
        this.installation = installation;
    }

    public Payment(Long id, LocalDate paymentDate, float amountPaid, Installation installation) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.amountPaid = amountPaid;
        this.installation = installation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Installation getInstallation() {
        return installation;
    }

    public void setInstallation(Installation installation) {
        this.installation = installation;
    }
}




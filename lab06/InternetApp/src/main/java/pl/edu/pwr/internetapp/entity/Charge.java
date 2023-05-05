package pl.edu.pwr.internetapp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "Charges"
)
public class Charge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "Charge_ID"
    )
    private Long id;
    @Column(
            name = "Payment_Deadline"
    )
    private LocalDate paymentDeadline;
    @Column(
            name = "Amount_To_Pay"
    )
    private Float amountToPay;
    @Column(
            name = "Is_Paid"
    )
    private Boolean isPaid;

    public Charge() {
    }

    public Charge(Long id, LocalDate paymentDeadline, Float amountToPay, Boolean isPaid, Installation installationId) {
        this.id = id;
        this.paymentDeadline = paymentDeadline;
        this.amountToPay = amountToPay;
        this.isPaid = isPaid;
        this.installationId = installationId;
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

    public Float getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(Float amountToPay) {
        this.amountToPay = amountToPay;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Installation getInstallationId() {
        return installationId;
    }

    public void setInstallationId(Installation installationId) {
        this.installationId = installationId;
    }

    @ManyToOne
    @JoinColumn(
            name = "Installation_Id",
            nullable = false
    )
    private Installation installationId;

}

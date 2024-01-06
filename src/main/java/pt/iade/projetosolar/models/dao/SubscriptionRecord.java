package pt.iade.projetosolar.models.dao;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "subscriptions")
public class SubscriptionRecord {

    @Id
    @GeneratedValue
    @Column(name = "sus_id")
    private int id;

    @Column(name = "sus_init_date")
    private Date initDate;
    @Column(name = "sus_is_active")
    private boolean endDate;

    @ManyToOne
    @JoinColumn(name = "sus_cli_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "sus_sub_id")
    private Subscription subscription;

    public CoWork getCoWork() {
        return subscription.getCoWork();
    }
}

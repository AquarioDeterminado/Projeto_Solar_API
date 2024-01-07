package pt.iade.projetosolar.models.dao.utils;

import jakarta.persistence.*;
import pt.iade.projetosolar.models.dao.subscriptions.SubscriptionRecord;

import java.util.List;


@Entity
@Table(name = "renewal_strategy")
public class RenewalStrategy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    private int id;

    @Column(name = "res_timing_by_day")
    private double timing;

    @OneToMany(mappedBy = "renewalStrategy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SubscriptionRecord> subscriptionRecords;

    public int getId() {
        return id;
    }

    public double getTiming() {
        return timing;
    }
}

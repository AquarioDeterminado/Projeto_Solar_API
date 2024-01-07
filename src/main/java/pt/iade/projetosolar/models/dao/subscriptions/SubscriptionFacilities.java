package pt.iade.projetosolar.models.dao.subscriptions;

import jakarta.persistence.*;
import pt.iade.projetosolar.models.dao.utils.Facility;

@Entity
@Table(name = "subscriptions_facilities")
public class SubscriptionFacilities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "suf_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "suf_sub_id")
    private Subscription subscription;

    @ManyToOne
    @JoinColumn(name = "suf_fac_id")
    private Facility facility;
}

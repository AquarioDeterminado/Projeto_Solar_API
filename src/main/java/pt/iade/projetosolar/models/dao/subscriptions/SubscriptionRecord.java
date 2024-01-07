package pt.iade.projetosolar.models.dao.subscriptions;

import jakarta.persistence.*;
import pt.iade.projetosolar.models.dao.coworks.CoWork;
import pt.iade.projetosolar.models.dao.users.Client;
import pt.iade.projetosolar.models.dao.utils.RenewalStrategy;

import java.util.Calendar;
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

    @Column(name = "sus_last_renewal_date")
    private Date lastRenewalDate;

    @Column(name = "sus_is_active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "sus_cli_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "sus_sub_id")
    private Subscription subscription;

    @ManyToOne
    @JoinColumn(name = "sus_res_id")
    private RenewalStrategy renewalStrategy;

    public CoWork getCoWork() {
        return subscription.getCoWork();
    }

    public int getId() {
        return id;
    }

    public Date getInitDate() {
        return initDate;
    }

    public Date isEndDate() {
        return lastRenewalDate;
    }

    public Client getClient() {
        return client;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public Date getNextRenewalDate() {
        Date nextRenewalDate = null;
        double day_internval = this.renewalStrategy.getTiming();

        Calendar calendar = Calendar.getInstance();
        if (this.lastRenewalDate != null) {
            calendar.setTime(this.lastRenewalDate);
        } else {
            calendar.setTime(this.initDate);
        }

        calendar.add(Calendar.DAY_OF_YEAR, (int) day_internval);
        nextRenewalDate = calendar.getTime();

        return nextRenewalDate;
    }
}

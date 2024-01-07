package pt.iade.projetosolar.models.exportInfo;

import pt.iade.projetosolar.models.dao.subscriptions.Subscription;
import pt.iade.projetosolar.models.dao.subscriptions.SubscriptionRecord;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubscriptionInfo {

    private int id;
    private CoworkInfo space;
    private String title;
    private double price;
    private Image subIcon;
    private Date nextRenewalDate;

    public SubscriptionInfo(SubscriptionRecord s) {
        Subscription subscription = s.getSubscription();

        this.setId(s.getId());
        this.setSpace(new CoworkInfo(subscription.getCoWork()));
        this.setTitle(subscription.getName());
        this.setPrice(subscription.getPrice());
        //this.setSubIcon(subscription.getSubIcon()); TODO
        this.setNextRenewalDate(s.getNextRenewalDate());
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSpace(CoworkInfo space) {
        this.space = space;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSubIcon(Image subIcon) {
        this.subIcon = subIcon;
    }

    public void setNextRenewalDate(Date nextRenewalDate) {
        this.nextRenewalDate = nextRenewalDate;
    }

    public int getId() {
        return id;
    }

    public CoworkInfo getSpace() {
        return space;
    }


    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public Image getSubIcon() {
        return subIcon;
    }

    public Date getNextRenewalDate() {
        return nextRenewalDate;
    }


    public static ArrayList<SubscriptionInfo> getSubscriptionsInfo(List<SubscriptionRecord> subscriptions) {
        ArrayList<SubscriptionInfo> subscriptionsInfo = new ArrayList<>();
        for (SubscriptionRecord s : subscriptions) {
            SubscriptionInfo subscriptionInfo = new SubscriptionInfo(s);
            subscriptionsInfo.add(subscriptionInfo);
        }
        return subscriptionsInfo;
    }
}

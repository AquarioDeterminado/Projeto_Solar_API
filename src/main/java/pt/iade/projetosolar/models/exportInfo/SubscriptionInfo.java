package pt.iade.projetosolar.models.exportInfo;

import pt.iade.projetosolar.models.dao.subscriptions.Subscription;
import pt.iade.projetosolar.models.dao.subscriptions.SubscriptionRecord;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubscriptionInfo {

    private int id;
    private String title;
    private double price;
    private Image subIcon;
    private Date nextRenewalDate;
    private String spaceName;

    public SubscriptionInfo(SubscriptionRecord s) {
        Subscription subscription = s.getSubscription();

        this.setId(s.getId());
        this.setTitle(subscription.getName());
        this.setPrice(subscription.getPrice());
        //this.setSubIcon(subscription.getSubIcon()); TODO
        this.setNextRenewalDate(s.getNextRenewalDate());
    }

    public SubscriptionInfo(Subscription s) {
        this.setId(s.getId());
        this.setTitle(s.getName());
        this.setPrice(s.getPrice());
        //this.setSubIcon(s.getSubIcon()); TODO
        this.setNextRenewalDate(null);
        this.spaceName = s.getCoWork().getName();
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public static ArrayList<SubscriptionInfo> getSubscriptionRecordsInfo(List<SubscriptionRecord> subscriptions) {
        ArrayList<SubscriptionInfo> subscriptionsInfo = new ArrayList<>();
        for (SubscriptionRecord s : subscriptions) {
            SubscriptionInfo subscriptionInfo = new SubscriptionInfo(s);
            subscriptionsInfo.add(subscriptionInfo);
        }
        return subscriptionsInfo;
    }

    public static ArrayList<SubscriptionInfo> getSubscriptionInfo(List<Subscription> subscriptions) {
        ArrayList<SubscriptionInfo> subscriptionsInfo = new ArrayList<>();
        for (Subscription s : subscriptions) {
            SubscriptionInfo subscriptionInfo = new SubscriptionInfo(s);
            subscriptionsInfo.add(subscriptionInfo);
        }
        return subscriptionsInfo;
    }

}

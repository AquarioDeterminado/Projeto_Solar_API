package pt.iade.projetosolar.views;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.iade.projetosolar.controllers.repositories.SubscriptionRecordRepository;
import pt.iade.projetosolar.controllers.repositories.UserRepository;
import pt.iade.projetosolar.models.dao.subscriptions.SubscriptionRecord;
import pt.iade.projetosolar.models.dao.users.User;
import pt.iade.projetosolar.models.exportInfo.SubscriptionInfo;
import pt.iade.projetosolar.models.importInfo.Id;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/subscriptions")
public class SubscriptionsInfo {

    final UserRepository userRepository;
    final SubscriptionRecordRepository subscriptionRecordRepository;

    public SubscriptionsInfo(UserRepository userRepository, SubscriptionRecordRepository subscriptionRecordRepository) {
        this.userRepository = userRepository;
        this.subscriptionRecordRepository = subscriptionRecordRepository;
    }

    @PostMapping(path = "/getUserSubscriptions")
    public SubscriptionInfo[] getUserSubscriptions(@RequestBody Id id) {
        User user = userRepository.findById(id.id()).get();

        List<SubscriptionRecord> subscriptions = user.getSubscriptions();
        ArrayList<SubscriptionInfo> response = SubscriptionInfo.getSubscriptionRecordsInfo(subscriptions);

        return response.toArray(new SubscriptionInfo[0]);
    }

    @PostMapping(path = "/cancelSubscription")
    public SubscriptionInfo cancelSubscription(@RequestBody Id subId) {
        SubscriptionRecord subscriptionRecord = subscriptionRecordRepository.findById(subId.id()).get();
        subscriptionRecord.deactivate();
        subscriptionRecordRepository.save(subscriptionRecord);
        return new SubscriptionInfo(subscriptionRecord);
    }

}

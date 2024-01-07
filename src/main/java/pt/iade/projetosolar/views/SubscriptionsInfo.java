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
import pt.iade.projetosolar.models.importInfo.UserId;

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
    public SubscriptionInfo[] getUserSubscriptions(@RequestBody UserId userId) {
        User user = userRepository.findById(userId.id()).get();

        List<SubscriptionRecord> subscriptions = user.getSubscriptions();
        ArrayList<SubscriptionInfo> response = SubscriptionInfo.getSubscriptionsInfo(subscriptions);

        return response.toArray(new SubscriptionInfo[0]);
    }
}

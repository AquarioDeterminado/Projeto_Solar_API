package pt.iade.projetosolar.controllers;

import pt.iade.projetosolar.controllers.repositories.CoWorkRepository;
import pt.iade.projetosolar.controllers.repositories.UserRepository;
import pt.iade.projetosolar.models.dao.coworks.CoWork;
import pt.iade.projetosolar.models.dao.subscriptions.SubscriptionRecord;
import pt.iade.projetosolar.models.dao.users.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CoworkController {

    private final CoWorkRepository coworkRepository;
    private final UserRepository userRepository;

    public CoworkController(CoWorkRepository coworkRepository, UserRepository userRepository) {
        this.coworkRepository = coworkRepository;
        this.userRepository = userRepository;
    }

    public CoWork getCowork(int coworkId) {
        return coworkRepository.findById(coworkId).get();
    }

    public ArrayList<CoWork> getNearCoWorks(int userId) {
        ArrayList<CoWork> nearCoWorks = new ArrayList<>();

        Iterable<CoWork> allCoworks = coworkRepository.findAll();
        nearCoWorks.addAll((Collection<? extends CoWork>) allCoworks);

        return nearCoWorks;
    }

    private ArrayList<CoWork> getUserSubbedCoworks(int userId) {
        ArrayList<CoWork> userSubbedCoworks = new ArrayList<>();
        User user = userRepository.findById(userId).get();
        List<SubscriptionRecord> subscriptions = user.getSubscriptions();
        for (SubscriptionRecord subscription : subscriptions) {
            userSubbedCoworks.add(subscription.getCoWork());
        }
        return userSubbedCoworks;
    }


}

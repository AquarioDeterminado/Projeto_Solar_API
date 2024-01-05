package pt.iade.projetosolar.models.dao;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @ManyToMany
    @JoinTable(name = "subscriptions",
            joinColumns = @JoinColumn(name = "sus_cli_id"),
            inverseJoinColumns = @JoinColumn(name = "sus_sub_id"))
    private List<Subscription> subscriptions = new ArrayList<>();

    public List<Subscription> getSubscriptions() {return subscriptions;}
}

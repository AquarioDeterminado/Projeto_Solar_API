package pt.iade.projetosolar.models.dao.users;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import pt.iade.projetosolar.models.dao.subscriptions.Susbcribed;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entity")
public class EntityDBO {
    @Id
    @Column(name = "ent_id") private Long id;
    @Column(name = "ent_name") private String name;
    @Column(name = "ent_phone") private String address;
    @Column(name = "ent_email") private String email;
    @Column(name = "ent_creation_date") private String password;

    @OneToOne(mappedBy = "entityDBO")
    private Client client;

    @OneToOne(mappedBy = "entityDBO")
    private User user;

    @OneToMany(mappedBy = "entity")
    private List<Susbcribed> subscribedGroups = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }



    @Nullable
    public Client getClient() {
        return client;
    }

    public List<Susbcribed> getSubscribedGroups() {return subscribedGroups;}
}

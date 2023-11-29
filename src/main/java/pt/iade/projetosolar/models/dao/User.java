package pt.iade.projetosolar.models.dao;

import jakarta.persistence.*;

@Entity
@Table(name = "Individual")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;

    public User(String id, String userName) {
        this.userName = userName;
    }

}

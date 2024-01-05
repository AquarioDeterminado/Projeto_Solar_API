package pt.iade.projetosolar.models.dao;

import jakarta.persistence.*;
import org.springframework.data.repository.CrudRepository;
import pt.iade.projetosolar.controllers.LogInController;

import java.sql.Date;

@Entity
@Table(name = "individual")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ind_id") private int id;
    @Column(name = "ind_ent_id") private int ent_id;
    @Column(name = "ind_email") private String email;
    @Column(name = "ind_password") private String password;
    @Column(name = "ind_name") private String userName;
    @Column(name = "ind_bDate") private Date birthDate;
    @Column(name = "ind_account_creation_date") private Date creationDate;
    @Column(name = "ind_active") private boolean isActive;

    public User() {
        this.creationDate = new Date(System.currentTimeMillis());
        this.isActive = true;
    }

    public User(String userName, String email, String password, String phone, Date bDate) {
        this.creationDate = new Date(System.currentTimeMillis());
        this.isActive = true;
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setPassword(String password) {
        if(LogInController.verifyPassword(password))
            this.password = password;
    }
}

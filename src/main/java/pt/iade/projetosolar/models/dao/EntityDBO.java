package pt.iade.projetosolar.models.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "entity")
public class EntityDBO {
    @Id
    @Column(name = "ent_id") private Long id;
    @Column(name = "ent_name") private String name;
    @Column(name = "ent_phone") private String address;
    @Column(name = "ent_email") private String email;
    @Column(name = "ent_creation_date") private String password;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

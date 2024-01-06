package pt.iade.projetosolar.models.dao.users;

import jakarta.persistence.*;

@Entity
@Table(name = "company_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coe_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "coe_cmp_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "coe_ent_id")
    private EntityDBO entityDBO;
}

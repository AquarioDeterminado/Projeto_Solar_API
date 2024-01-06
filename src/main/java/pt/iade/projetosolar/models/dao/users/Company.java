package pt.iade.projetosolar.models.dao.users;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmp_id")
    private int id;

    @Column(name = "cmp_location")
    private String location;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cmp_cli_id", referencedColumnName = "cli_id")
    private Client client;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();

    public Company() {
    }

    public Company(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public Client getClient() {
        return client;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void getEmployees() {
    }
}

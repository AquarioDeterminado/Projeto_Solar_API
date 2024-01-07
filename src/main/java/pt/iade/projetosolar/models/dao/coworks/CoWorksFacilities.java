package pt.iade.projetosolar.models.dao.coworks;

import jakarta.persistence.*;
import pt.iade.projetosolar.models.dao.utils.Facility;

@Entity
@Table(name = "cowork_spaces_facilities")
public class CoWorksFacilities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cof_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "cof_spc_id")
    private CoWork coWork;

    @ManyToOne
    @JoinColumn(name = "cof_fac_id")
    private Facility facility;

}

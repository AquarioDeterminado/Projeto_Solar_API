package pt.iade.projetosolar.models.dao.workstations;

import jakarta.persistence.*;
import pt.iade.projetosolar.models.dao.users.User;

import java.sql.Date;

@Entity
@Table(name = "workstation_uses")
public class WorkStationUse {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "wus_id") private int id;

    @Column (name = "wus_init_time") private Date startTime;
    @Column (name = "wus_final_time") private Date endTime;

    @ManyToOne
    @JoinColumn(name = "wus_ind_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "wus_wks_id")
    private WorkStation workStation;

    public Date getEndTime() { return endTime; }
}
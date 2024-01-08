package pt.iade.projetosolar.models.dao.workstations;

import jakarta.persistence.*;
import pt.iade.projetosolar.models.dao.users.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workstation")
public class WorkStation {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "wks_id") private int id;

    @ManyToOne
    @JoinColumn(name = "wks_wrg_id")
    private WorkStationsGroup workStationsGroup;

    @OneToMany(mappedBy = "workStation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WorkStationUse> uses = new ArrayList<>();

    public int getId() { return id; }

    public boolean isBeingUsed() {
        for (WorkStationUse use : uses)
            if (use.getEndTime() == null)
                return true;

        return false;
    }

    public boolean reservedByUser(int userId) {
        for (WorkStationUse use : uses)
            if (use.getEndTime() == null && use.getUser().getId() == userId)
                return true;

        return false;
    }

    public void reserve(User user) {
        Date date = new Date(System.currentTimeMillis());
        WorkStationUse use = new WorkStationUse(date, user, this);
        uses.add(use);
    }

    public void free() {
        Date date = new Date(System.currentTimeMillis());
        for (WorkStationUse use : uses)
            if (use.getEndTime() == null)
                use.setEndTime(date);
    }

}

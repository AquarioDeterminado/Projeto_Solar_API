package pt.iade.projetosolar.models.exportInfo;

import pt.iade.projetosolar.models.dao.events.Event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventInfo implements Serializable {

    private int id;
    private String title;
    private String descrip;
    private String location;
    private Date initDate;
    private Date endDate;
    private int full;
    private int reachedMin;

    public EventInfo (Event event){
        this.id = event.getId();
        this.title = event.getName();
        this.descrip = event.getDescription();
        this.location = event.getLocation();
        this.initDate = event.getStart();
        this.endDate = event.getEnd();
        this.full = event.isFull() ? 1 : 0;
        this.reachedMin = event.reachedMinGoal() ? 1 : 0;
    }

    public static ArrayList<EventInfo> getEventsInfo(List<Event> events){
        ArrayList<EventInfo> eventInfos = new ArrayList<>();
        for (Event event : events) {
            eventInfos.add(new EventInfo(event));
        }
        return eventInfos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descripn) {
        this.descrip = descrip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setFull(boolean full) {
        this.full = full ? 1 : 0;
    }

    public boolean getFull() {
        return full == 1;
    }

    public void setReachedMin(boolean reachedMin) {
        this.reachedMin = reachedMin ? 1 : 0;
    }

    public boolean getReachedMin() {
        return reachedMin == 1;
    }
}

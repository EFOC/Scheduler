package com.example.eosorio1.project;

/**
 * Created by osori on 4/12/2018.
 */
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Event {
//    private T location; Location coming soon
    private int id;
    private String eventName;
    private String eventDesc;
    private LocalTime endTime;
//    private String day;
    private LocalDateTime dateTime;

    Event(){
        id = 0;
        eventName = "";
        eventDesc = "";
//        day =  "";
    }

    Event(int id, String eventName, String eventDesc, LocalDateTime dateTime, LocalTime endTime){
        this.id = id;
        this.eventName = eventName;
        this.eventDesc = eventDesc;
        this.dateTime = dateTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    @Override
    public String toString(){
        StringBuilder event = new StringBuilder();
        event.append("ID: " + id + "\n"+ eventName + "\n" + eventDesc + "\n" + dateTime + "\n" + endTime+"\n");
        return event.toString();
    }
    public String display(){
        return "ID: " + id + "\n"+ eventName + "\n" + eventDesc + "\nDay: " + dateTime.getDayOfWeek() + " " + dateTime.getDayOfMonth() +
                "\nYear: " + dateTime.getYear() +
                "\nStarting at: " + dateTime.getHour() +":" + dateTime.getMinute() +
                "\nEnding at: " + endTime;
    }
}

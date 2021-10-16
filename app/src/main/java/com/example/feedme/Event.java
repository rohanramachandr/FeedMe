package com.example.feedme;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;
import org.bson.types.ObjectId;
public class Event extends RealmObject {
    @PrimaryKey
    private ObjectId _id = new ObjectId();
    private String name = "Task";
    @Required
    private String status = EventStatus.Open.name();
    public void setStatus(EventStatus status) {
        this.status = status.name();
    }
    public String getStatus() {
        return this.status;
    }
    public ObjectId get_id() {
        return _id;
    }
    public void set_id(ObjectId _id) {
        this._id = _id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Event(String _name) {
        this.name = _name;
    }

    public enum EventStatus {
        Open("Open"),
        InProgress("In Progress"),
        Complete("Complete");
        String displayName;
        EventStatus(String displayName) {
            this.displayName = displayName;
        }
    }


    public Event() {}
}

//package com.example.feedme;
//
//import io.realm.RealmObject;
//import io.realm.annotations.PrimaryKey;
//import io.realm.annotations.RealmClass;
//import io.realm.annotations.Required;
//import org.bson.types.ObjectId;
//
//public class User extends RealmObject {
//    @PrimaryKey
//    private ObjectId _id = new ObjectId();
//    @Required
//    private String firstName = "First";
//    private String status = UserStatus.Open.name();
//
//
//    public void setStatus(UserStatus status) {
//        this.status = status.name();
//    }
//    public String getStatus() {
//        return this.status;
//    }
//    public ObjectId get_id() {
//        return _id;
//    }
//    public void set_id(ObjectId _id) {
//        this._id = _id;
//    }
//    public String getFirstName() {
//        return firstName;
//    }
//    public void setFirstName(String name) {
//        this.firstName = name;
//    }
//    public User(String _firstName) {
//        this.firstName = _firstName;
//    }
//    public User() {}
//
//
//}
//

package com.anar.PEAK;

import java.io.Serializable;
import java.util.List;

public class Post implements Serializable {

    //Costructors
    public Post(String postCode, int postCapacity, String adress ){
        this.id=id;
        this.postCapacity=postCapacity;
        this.postAdress =adress;
        this.postCode=postCode;
        setDateCreated();
        setTimeCreated();
    }



    public Post(){
        setDateCreated();
        setTimeCreated();
    }

    //fields
    private int id;
    private List<FeederLoad> loadList;
    private PostLoad load;
    private String postCode;
    private int postCapacity;
    private String postAdress;
    private LocationPoint postLocation;
    private String dateCreated;
    private String timeCreated;
    private PostVoltage voltage;



    //properties

    public List<FeederLoad> getLoadList() {
        return loadList;
    }

    public void setLoadList(List<FeederLoad> loadList) {
        this.loadList = loadList;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public int getPostCapacity() {
        return postCapacity;
    }

    public void setPostCapacity(int postCapacity) {
        this.postCapacity = postCapacity;
    }

    public String getPostAdress() {
        return postAdress;
    }

    public void setPostAdress(String postAdress) {
        this.postAdress = postAdress;
    }

    public LocationPoint getPostLocation() {
        return postLocation;
    }

    public void setPostLocation(LocationPoint postLocation) {
        this.postLocation = postLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PostLoad getLoad() {
        return load;
    }

    public void setLoad(PostLoad load) {
        this.load = load;
    }


    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated() {
       // this.dateCreated = Utils.parseDate();
        this.dateCreated = Utils.parseHijriDate();

    }
    public void setDateCreated(String date) {
        this.dateCreated = date;

    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated() {
        this.timeCreated = Utils.parseTime();
    }
    public void setTimeCreated(String time) {
        this.timeCreated = time;
    }

    public PostVoltage getVoltage() {
        return voltage;
    }

    public void setVoltage(PostVoltage voltage) {
        this.voltage = voltage;
    }
}

package com.example.maps;

public class Workshopinformation {

    private String Image;
     String name;
    private String address;
    private String contact;
    private String spintext;
    double latitude;
    double longitude;
    private int role;

    public Workshopinformation(){

    }
    Workshopinformation( String name, String address, String contact, String spintext, double latitude, double longitude, int role){
        this.name=name;
        this.address=address;
        this.contact=contact;
        this.spintext=spintext;
        this.latitude=latitude;
        this.longitude=longitude;
        this.role=role;
    }
    Workshopinformation(String Image, String name, String address, String contact, String spintext, double latitude, double longitude, int role){
        this.Image=Image;
        this.name=name;
        this.address=address;
        this.contact=contact;
        this.spintext=spintext;
        this.latitude=latitude;
        this.longitude=longitude;
        this.role=role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSpintext() {
        return spintext;
    }

    public void setSpintext(String spintext) {
        this.spintext = spintext;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void DataIsUpdated() {
    }

    public void DataIsDeleted() {
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}

package com.example.marianne.ejendomsselskabetbmapp;


import android.util.Log;

import org.w3c.dom.Text;

import java.util.Date;

public class Inspectionlist {

    public int _id;
    public String _date;
    public String _room; // Skal laves til enum
    public String _addres; // Skal laves til enum
    public int _acquisition;
    public String _taskdescription;
    public int _scheduled;
    public int _completed;
    public String _pictureaddress;

    public Inspectionlist(){}

    public Inspectionlist(String room){
        this._taskdescription = room;
    }

    public Inspectionlist(String _date, String _room, String _addres, int _acquisition,
                          String _taskdescription, int _scheduled
    ) {
        this._date = _date;
        this._room = _room;
        this._addres = _addres;
        this._acquisition = _acquisition;
        this._taskdescription = _taskdescription;
        this._scheduled = _scheduled;
    }
    // En fuld constructor dog uden id
    public Inspectionlist(String _date, String _room, String _addres, int _acquisition,
                          String _taskdescription, int _scheduled, int _completed,
                          String _pictureaddress) {
        this._date = _date;
        this._room = _room;
        this._addres = _addres;
        this._acquisition = _acquisition;
        this._taskdescription = _taskdescription;
        this._scheduled = _scheduled;
        this._completed = _completed;
        this._pictureaddress = _pictureaddress;
        Log.d("data til db: ", this._date.toString() +" "+ this._room + " " + this._taskdescription);
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public void set_room(String _room) {
        this._room = _room;
    }

    public void set_addres(String _addres) {
        this._addres = _addres;
    }

    public void set_acquisition(int _acquisition) {
        this._acquisition = _acquisition;
    }

    public void set_taskdescription(String _taskdescription) {
        this._taskdescription = _taskdescription;
    }

    public void set_scheduled(int _scheduled) {
        this._scheduled = _scheduled;
    }

    public void set_completed(int _completed) {
        this._completed = _completed;
    }

    public void set_pictureaddress(String _pictureaddress) {
        this._pictureaddress = _pictureaddress;
    }

    public int get_id() {
        return _id;
    }

    public String get_date() {
        return _date;
    }

    public String get_room() {
        return _room;
    }

    public String get_addres() {
        return _addres;
    }

    public int get_acquisition() {
        return _acquisition;
    }

    public String get_taskdescription() {
        return _taskdescription;
    }

    public int get_scheduled() {
        return _scheduled;
    }

    public int get_completed() {
        return _completed;
    }

    public String get_pictureaddress() {
        return _pictureaddress;
    }
}

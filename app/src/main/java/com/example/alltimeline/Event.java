package com.example.alltimeline;

public class Event {
    int id;
    int c_id;
    String name;
    int year;
    String memo;

    public Event(int id, int c_id, String name, int year, String memo) {
        this.id = id;
        this.c_id = c_id;
        this.name = name;
        this.year = year;
        this.memo = memo;
    }

    //setters
    public void setid(int id) {
        this.id = id;
    }

    public void setc_id(int id) {
        this.c_id = id;
    }

    public void setname(String name) {
        this.name = name;
    }

    public void setyear(int year) {
        this.year = year;
    }

    public void setmemo(String memo) {
        this.memo = memo;
    }

    //getters
    public int getid() {
        return this.id;
    }

    public int getc_id() {
        return this.c_id;
    }

    public String getname() {
        return this.name;
    }

    public int getyear() {
        return this.year;
    }

    public String getmemo() {
        return this.memo;
    }
}

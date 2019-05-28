package com.example.alltimeline;

public class Category {
    int id;
    String name;
    int type;

    public Category(int id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    //setters
    public void setid(int id) {
        this.id = id;
    }

    public void setname(String name) {
        this.name = name;
    }

    public void settype(int type) {
        this.type = type;
    }

    //getters
    public int getid() {
        return this.id;
    }

    public String getname() {
        return this.name;
    }

    public int gettype() {
        return this.type;
    }

    @Override
    public String toString() { return this.name; }
}

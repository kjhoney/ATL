package com.example.alltimeline;
import android.app.Application;
import java.io.Serializable;
import java.util.ArrayList;

public class MyApplication extends Application {

    public int Category_num = 0;
    public int User_Category_num = 0;
    public ArrayList<Category> Category_list = new ArrayList<>();
    public ArrayList<Category> User_Category_list = new ArrayList<>();

    public static class Category implements Serializable {
        public int event_num;
        public String title;
        public ArrayList<Event> eventList = new ArrayList<>();

        public Category(String title) {
            this.event_num = 0;
            this.title = title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void Add_Event(int index, int year, int length, String title) {
            Event newEvent = new Event(index, year, length, title);
            eventList.add(newEvent);
            event_num++;
            return;
        }
    }

    public static class Event implements Serializable {
        public int index;
        public int year;
        public int length;
        public String title;

        public Event(int index, int year, int length, String title) {
            this.index = index;
            this.year = year;
            this.length = length;
            this.title = title;
        }
    }

    public void setCategory_num(int num) {
        this.Category_num = num;
    }

    public int getCategory_num(){
        return this.Category_num;
    }

    public void setUser_Category_num(int num) {
        this.User_Category_num = num;
    }

    public int getUser_Category_num(){
        return this.User_Category_num;
    }

    public void Add_Category(Category category) {
        Category_num++;
        Category_list.add(category);
    }

    public void Add_User_Category(Category category) {
        User_Category_num++;
        User_Category_list.add(category);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public ArrayList<Category> getCategory_list(){
        return Category_list;
    }
}

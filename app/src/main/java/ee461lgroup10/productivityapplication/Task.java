package ee461lgroup10.productivityapplication;

/**
 * Created by dylan_000 on 3/29/2017.
 */
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Task {
    private int id;
    private String name;
    private String date;

    public Task(){}

    public Task(int id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDate(){
        return date;
    }
}

package ee461lgroup10.productivityapplication;

/**
 * Created by dylan_000 on 3/29/2017.
 */
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Task {
    private String name;
    private String date;


    private int id;

    public Task(){}

    public Task(int id, String name, String date) {

        this.id = id;
        this.name = name;
        this.date = date;
    }

    public Task(String name, String date)
    {
        this.name = name;
        this.date = date;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getName(){
        return name;
    }

    public String getDate(){
        return date;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

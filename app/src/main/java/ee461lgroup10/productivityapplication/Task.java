package ee461lgroup10.productivityapplication;

/**
 * Created by dylan_000 on 3/29/2017.
 */


public class Task {
    private String name;
    private String date;
    private String location;
    private int id;

    public Task(){}


    public Task(String name, String date, String location)
    {
        this.name = name;
        this.date = date;
        this.location = location;
    }

    public Task(int id, String name, String date, String location) {

        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

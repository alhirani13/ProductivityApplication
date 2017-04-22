package ee461lgroup10.productivityapplication;

/**
 * Created by dylan_000 on 3/29/2017.
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    private int id;
    private String name;
    private Date date;

    public Task(){}

    public Task(int id, String name, String date) throws ParseException {
        this.id = id;
        this.name = name;
        DateFormat fmt = new SimpleDateFormat("MM/dd/yy");
        this.date = fmt.parse(date);
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDate(String date) throws ParseException {
        DateFormat fmt = new SimpleDateFormat("MM/dd/yy");
        this.date = fmt.parse(date);
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Date getDate(){
        return date;
    }
}

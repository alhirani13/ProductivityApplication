package ee461lgroup10.productivityapplication;

import android.app.DialogFragment;
import android.content.Intent;
import android.net.ParseException;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {
    ListView mCalendarDayTasks;

    private DBHandler db;

    //TODO: remove/refactor this, this is to make sure layout works (need to replace with SQL data)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        db = new DBHandler(this);

        String[] names = new String[db.getAllTasks().size()];
        for(int i = 0; i < db.getAllTasks().size(); i++)
        {
            names[i] = db.getAllTasks().get(i).getName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CalendarActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, names);
        mCalendarDayTasks = (ListView) findViewById(R.id.calendarTaskList);

        mCalendarDayTasks.setAdapter(adapter);

        mCalendarDayTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CalendarActivity.this, TaskListActivity.class);
                startActivity(intent);
            }
        });



    }



}
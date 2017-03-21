package ee461lgroup10.productivityapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView mCalendarDayTasks;

        //TODO: remove/refactor this, this is to make sure layout works (need to replace with SQL data)
        String[] useless = new String[] {
                "No tasks yet",
                "Enter a task with date to display here",
                "Al and Dylan are cool",
                "Please scroll",
                "Need to figure out check boxes",
                "Send help this is taking too long",
                "Please WORK"
        };

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CalendarActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, useless);

        mCalendarDayTasks = (ListView)findViewById(R.id.calendarTaskList);
        mCalendarDayTasks.setAdapter(adapter);

        //TODO: Set so that whenever someone clicks thing in list it goes to taskList??
        mCalendarDayTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        })
    }
}

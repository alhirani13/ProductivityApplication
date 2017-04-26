package ee461lgroup10.productivityapplication;

import android.app.DialogFragment;
import android.content.Intent;
import android.net.ParseException;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {
    CalendarView mCalendarView;
    ListView mCalendarDayTasks;
    ArrayAdapter<String> adapter;
    private DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        db = new DBHandler(this);

        mCalendarDayTasks = (ListView) findViewById(R.id.calendarTaskList);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.appBarLayout);
        setSupportActionBar(toolbar);

        //long g = mCalendarView.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String comparison = sdf.format(new Date(mCalendarView.getDate()));
        Task[] names1 = new Task[db.getAllTasks().size()];
        for(int i = 0; i < db.getAllTasks().size(); i++)
        {
            names1[i] = db.getAllTasks().get(i);
        }
        List<String> a1 = new ArrayList<String>();
        for(int i = 0; i < names1.length; i++)
        {
            if(sdf.format(names1[i].getDate()).equals(comparison))
                a1.add(names1[i].getName());
        }
        adapter = new ArrayAdapter<String>(CalendarActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, a1);
        mCalendarDayTasks.setAdapter(adapter);




        mCalendarDayTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CalendarActivity.this, TaskListActivity.class);
                startActivity(intent);
            }
        });

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Task[] names = new Task[db.getAllTasks().size()];
                String comparison = (month+1) + "/" + dayOfMonth + "/" + year;
                for(int i = 0; i < db.getAllTasks().size(); i++)
                {
                    names[i] = db.getAllTasks().get(i);
                }
                List<String> a = new ArrayList<String>();
                for(int i = 0; i < names.length; i++)
                {
                    if(names[i].getDate().equals(comparison))
                        a.add(names[i].getName());
                }
                adapter = new ArrayAdapter<String>(CalendarActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, a);
                mCalendarDayTasks.setAdapter(adapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.addTask) {
            Intent createTaskIntent = new Intent(CalendarActivity.this, CreateTaskActivity.class);
            startActivity(createTaskIntent);
        }
        else if(id == R.id.goToMap)
        {
            Intent webIntent = new Intent(CalendarActivity.this, WebActivity.class);
            startActivity(webIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}

package ee461lgroup10.productivityapplication;

import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    DBHandler mDBHandler;
    Cursor task;
    SQLiteDatabase db;
    SimpleDateFormat sdf;
    String currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appBarLayout);
        setSupportActionBar(toolbar);

        mDBHandler = DBHandler.getInstance(this);
        db = mDBHandler.getReadableDatabase();
        mCalendarView = (CalendarView)findViewById(R.id.calendarView);
        sdf = new SimpleDateFormat("MM/dd/yyyy");
        currentDate = sdf.format(new Date(mCalendarView.getDate()));
        //task = db.rawQuery("SELECT id AS _id, * FROM tasks WHERE date = " + currentDate, null);
        task = db.rawQuery("SELECT id AS _id, * FROM tasks", null);

        mCalendarDayTasks = (ListView) findViewById(R.id.calendarTaskList);
        TaskCursorAdapter adapter = new TaskCursorAdapter(this, task);
        mCalendarDayTasks.setAdapter(adapter);


        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(CalendarActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, names);
        mCalendarDayTasks = (ListView) findViewById(R.id.calendarTaskList);
        mCalendarDayTasks.setAdapter(adapter);*/


        mCalendarDayTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CalendarActivity.this, TaskListActivity.class);
                startActivity(intent);
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

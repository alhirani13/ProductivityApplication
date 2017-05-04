package ee461lgroup10.productivityapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {
    CalendarView mCalendarView;
    ListView mCalendarDayTasks;
    DBHandler mDBHandler;
    Cursor task;
    SQLiteDatabase db;
    SimpleDateFormat sdf;
    String currentDate;
    TaskCursorAdapter adapter;

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
        task = db.rawQuery("SELECT id AS _id, * FROM tasks WHERE date =?", new String[] {currentDate});


        mCalendarDayTasks = (ListView) findViewById(R.id.calendarTaskList);
        adapter = new TaskCursorAdapter(this, task);
        mCalendarDayTasks.setAdapter(adapter);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.MINUTE, 59);

        Intent intent = new Intent(this, Notification_reciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


        mCalendarDayTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CalendarActivity.this, TaskListActivity.class);
                startActivity(intent);
            }
        });

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                currentDate = sdf.format(new Date(mCalendarView.getDate()));
                task = db.rawQuery("SELECT id AS _id, * FROM tasks WHERE date =?", new String[] {currentDate});
                adapter.changeCursor(task);
                adapter.notifyDataSetChanged();
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

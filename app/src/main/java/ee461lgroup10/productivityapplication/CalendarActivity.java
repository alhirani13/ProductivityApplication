package ee461lgroup10.productivityapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

public class CalendarActivity extends AppCompatActivity {
    CalendarView mCalendarView;
    ListView mCalendarDayTasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CalendarActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, Stringtasks.useless);
        mCalendarDayTasks = (ListView)findViewById(R.id.calendarTaskList);
        mCalendarDayTasks.setAdapter(adapter);
        mCalendarView = (CalendarView)findViewById((R.id.calendarView));

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

            }
        });

    }

}

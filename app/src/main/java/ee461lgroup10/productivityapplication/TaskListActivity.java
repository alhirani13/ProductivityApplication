package ee461lgroup10.productivityapplication;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class TaskListActivity extends AppCompatActivity implements DatePickerFragment.OnDataPass {

    private ListView mTasks;
    private Button mNameTaskButton;
    public String m_Text = "";
    public String m_Date = "";
    private DialogFragment newFragment;
    private int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TaskListActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, Stringtasks.useless);
        mTasks = (ListView) findViewById(R.id.taskListView);
        mTasks.setAdapter(adapter);*/

        mNameTaskButton = (Button) findViewById(R.id.TaskName);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }



    public void showDatePickerDialog(View v) {
        newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");

    }


    public void showAlertDialog(View v) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(TaskListActivity.this);
        alert.setMessage("Please Enter Task Name");
        alert.setTitle("New Task");

        alert.setView(edittext);

        alert.setPositiveButton("Get 'er Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //What ever you want to do with the value
                Editable YouEditTextValue = edittext.getText();
                m_Text = YouEditTextValue.toString();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });

        alert.show();
    }

    @Override
    public void onDataPass(String data) {
        m_Date = data;
    }

    public void makeEntry() {
        Intent backToCalendar = new Intent(TaskListActivity.this, CalendarActivity.class);
        backToCalendar.putExtra("Task Name", m_Text);
        backToCalendar.putExtra("Task Date", m_Date);
        startActivity(backToCalendar);
    }

}

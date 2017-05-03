package ee461lgroup10.productivityapplication;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class CreateTaskActivity extends AppCompatActivity {

    private ListView mTasks;
    private Button mNameTaskButton;
    public String m_Text = "";
    public String m_Date = "";
    private DialogFragment newFragment;
    private DBHandler db;
    private TextView mTaskNameText;
    private TextView mSetDateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mNameTaskButton = (Button) findViewById(R.id.TaskName);
        mTaskNameText = (TextView) findViewById(R.id.NameTaskText);
        mSetDateText = (TextView) findViewById(R.id.SetDateText);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = DBHandler.getInstance(this);

        Button confirm =(Button)findViewById(R.id.ConfirmButton);
        confirm.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                makeEntry();
                Intent intent = new Intent(CreateTaskActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }



    public void showDatePickerDialog(View v) {
        newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");

    }

    public void makeEntry() {
        db.addTask(new Task(m_Text, m_Date));
    }

    public void showAlertDialog(View v) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(CreateTaskActivity.this);
        alert.setMessage("Please Enter Task Name");
        alert.setTitle("New Task");

        alert.setView(edittext);

        alert.setPositiveButton("Get 'er Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //What ever you want to do with the value
                Editable YouEditTextValue = edittext.getText();
                m_Text = YouEditTextValue.toString();
                mTaskNameText.setText(m_Text);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });

        alert.show();
    }


    public void setM_Date(String data) {
        m_Date = data;
        mSetDateText.setText(m_Date);
    }



}
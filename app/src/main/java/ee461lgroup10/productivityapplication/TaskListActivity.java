package ee461lgroup10.productivityapplication;

import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TaskListActivity extends AppCompatActivity {
    ListView mTaskListTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.appBarLayout);
        setSupportActionBar(toolbar);


        DBHandler handler = DBHandler.getInstance(this);
        SQLiteDatabase db = handler.getReadableDatabase();
        Cursor taskCursor = db.rawQuery("SELECT id AS _id, * FROM tasks", null);
        mTaskListTasks = (ListView) findViewById(R.id.taskListTasks);
        TaskCursorAdapter taskAdapter = new TaskCursorAdapter(this, taskCursor);
        mTaskListTasks.setAdapter(taskAdapter);

        /*
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TaskListActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, Stringtasks.useless);
        mTaskListTasks = (ListView)findViewById(R.id.taskListTasks);
        mTaskListTasks.setAdapter(adapter);*/

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.addTask) {
            Intent createTaskIntent = new Intent(TaskListActivity.this, CreateTaskActivity.class);
            startActivity(createTaskIntent);

        }
        if (id == R.id.goToMap)
        {
            Intent webIntent = new Intent(TaskListActivity.this, WebActivity.class);
            startActivity(webIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}

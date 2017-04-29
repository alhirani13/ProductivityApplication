package ee461lgroup10.productivityapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dylan_000 on 3/29/2017.
 */

public class DBHandler extends SQLiteOpenHelper{

    private static DBHandler mInstance;

    //Database Version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "taskInfo";
    //Contacts table name
    private static final String TABLE_TASKS = "tasks";
    //Tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE = "date";

    public DBHandler(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DBHandler getInstance(Context context) {
        if (mInstance == null)
        {
            mInstance = new DBHandler(context.getApplicationContext());
        }
        return mInstance;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TASKS +
                "(" +
                    KEY_ID + " INTEGER PRIMARY KEY," +
                    KEY_NAME + " TEXT," +
                    KEY_DATE + " TEXT" +
                ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_TASKS);
        //Creating tables again
        onCreate(db);
    }

    //Adding new Task
    public void addTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName()); //Task Name
        values.put(KEY_DATE, task.getDate()); //Task Date
        db.insert(TABLE_TASKS, null, values);
        db.close(); //Closing Database Connection
    }

    //Getting one Task, Read Record
    public Task getTask(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TASKS, new String[] { KEY_ID, KEY_NAME, KEY_DATE}, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        Task contact = new Task(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        //return task
        return contact;
    }

    //Getting all tasks
    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<Task>();
        //Select all query
        String selectQuery = "SELECT * FROM " + TABLE_TASKS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                Task task = new Task();
                task.setId(Integer.parseInt(cursor.getString(0)));
                task.setName(cursor.getString(1));
                task.setDate(cursor.getString(2));
                //adding contact to list
                taskList.add(task);
            } while(cursor.moveToNext());
        }
        //return contact list
        return taskList;
    }

    //Getting Task Count
    public int getTasksCount(){
        String countQuery = "SELECT * FROM " + TABLE_TASKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        //return count
        return cursor.getCount();
    }

    //Updating a task
    public int updateTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());
        values.put(KEY_DATE, task.getDate());
        //updating row
        return db.update(TABLE_TASKS, values, KEY_ID + " = ?", new String[]{String.valueOf(task.getId())});
    }

    //Deleting a task
    public void deleteTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, KEY_ID + " = ?", new String[] {String.valueOf(task.getId())});
        db.close();
    }

}

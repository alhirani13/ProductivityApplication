package ee461lgroup10.productivityapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Calendar;

public class NotificationActivity extends AppCompatActivity
        implements View.OnClickListener{


    DBHandler mDBHandler;
    Cursor task;
    SQLiteDatabase db;
    String currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        findViewById(R.id.btnNotification).setOnClickListener(this);

        mDBHandler = DBHandler.getInstance(this);
        db = mDBHandler.getReadableDatabase();
        Calendar c = Calendar.getInstance();
        //sdf = new SimpleDateFormat("MM/dd/yyyy");
        currentDate = c.get(Calendar.MONTH) + "/" + c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.YEAR);
        if(currentDate.charAt(1) == '/')
        {
            currentDate = "0" + currentDate;
        }
        if(currentDate.charAt(4) == '/')
        {
            currentDate = currentDate.substring(0,3) + "0" + currentDate.substring(3);
        }
        task = db.rawQuery("SELECT id AS _id, * FROM tasks WHERE date =?", new String[] {currentDate});
    }

    private void createNotification() {

        //getResources();
        // Add an expanded layout to the notification
        //NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        //bigTextStyle.setBigContentTitle("This is a big notification");
        //bigTextStyle.bigText(getResources().getString(R.string.LongMsg));
        //builder.setStyle(bigTextStyle);

        // Add action buttons to the Notification if they are supported
        // Use the same PendingIntent as we use for the main notification action
        //builder.addAction(R.mipmap.ic_launcher,"Action 1", pendingIntent);
        //builder.addAction(R.mipmap.ic_launcher,"Action 2", pendingIntent);

        // Set the lock screen visibility of the notification
        // builder.setVisibility(Notification.VISIBILITY_PUBLIC);



    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnNotification) {
            createNotification();
        }
    }
}

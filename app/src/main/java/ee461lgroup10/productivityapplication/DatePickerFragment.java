package ee461lgroup10.productivityapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by dylan_000 on 4/22/2017.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private String s;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        month += 1;
        if(month < 10)
        {
            s = "0" + month + "/" + day + "/" + year;
            if(day < 10)
            {
                s = "0" + month + "/0" + day + "/" + year;
            }
        }
        else
        {
            s =  month + "/" + day + "/" + year;
            if(day < 10)
            {
                s = month + "/0" + day + "/" + year;
            }
        }
        ((CreateTaskActivity)getActivity()).setM_Date(s);
        // Do something with the date chosen by the user
    }
}

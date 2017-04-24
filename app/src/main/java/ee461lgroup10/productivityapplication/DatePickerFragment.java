package ee461lgroup10.productivityapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by dylan_000 on 4/22/2017.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private String s;

    public interface OnDataPass {
        public void onDataPass(String data);
    }

    OnDataPass dataPasser;


    public void onAttach(TaskListActivity a) {
        super.onAttach(a);
        dataPasser = a;
    }

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
        s =  month + "/" + day + "/" + year;
        dataPasser.onDataPass(s);
        // Do something with the date chosen by the user
    }
}

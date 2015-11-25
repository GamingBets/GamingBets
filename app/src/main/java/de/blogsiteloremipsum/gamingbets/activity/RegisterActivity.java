package de.blogsiteloremipsum.gamingbets.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import de.blogsiteloremipsum.gamingbets.R;
import de.blogsiteloremipsum.gamingbets.classes.Globals;

public class RegisterActivity extends AppCompatActivity {

    static Button b;
    static Date d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        b = (Button) findViewById(R.id.dob);

    }

    public void showDatePickerDialog(View v){
        DialogFragment dialog = new DatePickerFragment();
        FragmentManager fm = getFragmentManager();
        dialog.show(fm, "datePicker");

    }

    public boolean RegisterAttempt(View v){
        EditText MailEdit = (EditText) findViewById(R.id.mail);
        EditText PwEdit = (EditText) findViewById(R.id.password);

        return false;
    }

    public static class DatePickerFragment extends DialogFragment
                                            implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day){
            d=new Date(year,month,day);
            String txt = String.valueOf(d);
            b.setText(txt);
        }
    }
}

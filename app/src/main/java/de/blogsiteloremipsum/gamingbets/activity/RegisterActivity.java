package de.blogsiteloremipsum.gamingbets.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.sql.Date;
import java.util.GregorianCalendar;

import android.util.Log;

import de.blogsiteloremipsum.gamingbets.R;
import de.blogsiteloremipsum.gamingbets.classes.Globals;
import de.blogsiteloremipsum.gamingbets.classes.UnregisteredUser;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.communication.client.LocalClientSocket;

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
        EditText UserEdit = (EditText) findViewById(R.id.username);
        EditText MailEdit = (EditText) findViewById(R.id.mail);
        EditText PwEdit = (EditText) findViewById(R.id.password);
        EditText PwEdit2 = (EditText) findViewById(R.id.password2);
        TextView Status = (TextView) findViewById(R.id.Status);

        UnregisteredUser ur = new UnregisteredUser();

        ur.setUserName(UserEdit.getText().toString());
        ur.setEmail(MailEdit.getText().toString());
        ur.setDob(d);
        ur.setPassword(PwEdit.getText().toString());

        new RegisterTask().execute(ur);

        return true;
    }

    public boolean register(UnregisteredUser ur) {
        Globals g = (Globals) getApplication();
        LocalClientSocket client = g.getClient();
        return client.register(ur.getUserName(),ur.getEmail(),ur.getPassword(),ur.getDob());
    }

    private class RegisterTask extends AsyncTask<UnregisteredUser, Void, Boolean>{

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected Boolean doInBackground(UnregisteredUser... params){
            Log.d("Registration Attempt","Attempt started");
            if (register(params[0])){
                return true;
            }
            else{
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean b){
            TextView Status = (TextView) findViewById(R.id.Status);
            if (b){
                Status.setText("Registration successful");
                Status.setVisibility(View.VISIBLE);
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
            else{
                Status.setText("Registration unsuccessful");
                Status.setVisibility(View.VISIBLE);
            }
        }
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

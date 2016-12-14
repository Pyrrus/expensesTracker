package com.example.guest.expensestracker;

import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    @Bind(R.id.categorySpinner) Spinner mSpinner;
    @Bind(R.id.editText) EditText mEdit;
    @Bind(R.id.button) Button mButton;
    @Bind(R.id.Date) TextView mDate;
    private String[] categories;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        categories = getResources().getStringArray(R.array.categories);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(dataAdapter);

        mButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String output = "";
        Double number;

        if (mSpinner.getSelectedItem().toString().equals("Pick a category")) {
            output += "need to pick a category\n";
        }

        if (mEdit.getText().toString().isEmpty()) {
            output += "need a number";
        }

        if (output.equals("")) {
            number = Double.parseDouble(mEdit.getText().toString());

            DatabaseReference expensesRef = FirebaseDatabase
                    .getInstance()
                    .getReference(mSpinner.getSelectedItem().toString());
            expensesRef.push().setValue(new Expense(number));
            Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(MainActivity.this, output, Toast.LENGTH_SHORT).show();
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void setDate(final Calendar calendar) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

        ((TextView) findViewById(R.id.Date))
                .setText(dateFormat.format(calendar.getTime()));

    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }
}

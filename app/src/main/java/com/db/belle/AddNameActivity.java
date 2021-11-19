package com.db.belle;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.Date;

public class AddNameActivity extends Activity implements View.OnClickListener {
    private Button addTodoBtn;
    private EditText userName;
    private TextView age;
    private RadioGroup gender;
    private RadioButton radioButton;
    private EditText mail;
    private TextView birthDate;
    private CalendarView calendarView;
    private DBManager dbManager;
    private Integer ageN;
    private Integer selectedYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add Record");

        setContentView(R.layout.activity_add_record);

        userName = findViewById(R.id.name);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
        mail = findViewById(R.id.email);
        birthDate = findViewById(R.id.date);
        calendarView = findViewById(R.id.calenderView);


        addTodoBtn = (Button) findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String msg = "Selected date Day: " + i2 + " Month : " + (i1 + 1) + " Year " + i;
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                selectedYear = i;
                //ageCalc();
            }
        });
        gender.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            radioButton = findViewById(checkedId);
            Toast.makeText(getApplicationContext(), "My Device: "
                    + radioButton.getText(), Toast.LENGTH_SHORT).show();
        });
    }

    public void ageCalc() {
        ageN = 2021 - selectedYear;
        age.setText(ageN);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:

                final String name = userName.getText().toString();
                final String years = age.getText().toString();
                final String sex = radioButton.getText().toString();
                final String address= String.valueOf(mail.getText());
//                final Date dateOfBirth = (Date) birthDate.getText();

                dbManager.insert(name, years, sex, address/*, dateOfBirth*/);

                Intent main = new Intent(AddNameActivity.this, NameListActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }

}

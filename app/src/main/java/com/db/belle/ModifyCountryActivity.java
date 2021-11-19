package com.db.belle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class ModifyCountryActivity extends Activity implements View.OnClickListener {

    private EditText userName, mail;
    private TextView age;
    private Button updateBtn, deleteBtn;
    private RadioGroup gender;
    private RadioButton radioButton;

    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Modify Record");

        setContentView(R.layout.activity_modify_record);

        dbManager = new DBManager(this);
        dbManager.open();

        userName = findViewById(R.id.nameM);
        age = findViewById(R.id.ageM);
        gender = findViewById(R.id.genderM);
        mail = findViewById(R.id.emailM);
        //birthDate = findViewById(R.id.date);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        //String desc = intent.getStringExtra("age");
        String desc2 = intent.getStringExtra("mail");
        //String desc3 = intent.getStringExtra("birthDate");
        _id = Long.parseLong(id);

        userName.setText(name);
        //age.setText(desc);
        mail.setText(desc2);
        //birthDate.setText(desc3);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
        gender.setOnCheckedChangeListener((group, checkedId) -> {
            // checkedId is the RadioButton selected
            radioButton = findViewById(checkedId);
            Toast.makeText(getApplicationContext(), "My Device: "
                    + radioButton.getText(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String name = userName.getText().toString();
                Integer years = Integer.valueOf(age.toString());
                String sex = radioButton.getText().toString();
                String address= String.valueOf(mail.getText());
                //Date dateOfBirth = (Date) birthDate.getText();

                dbManager.update(_id, name, years, sex, address/*, dateOfBirth*/);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), NameListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
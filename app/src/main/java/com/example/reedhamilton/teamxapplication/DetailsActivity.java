package com.example.reedhamilton.teamxapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Reed Hamilton on 3/21/2017.
 */

public class DetailsActivity extends Activity {

    EditText name;
    EditText age;
    EditText gender;
    EditText location;
    EditText school;
    EditText eMail;
    TeamXDBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        DB = new TeamXDBHelper(this);
        name = (EditText) findViewById(R.id.etName);
        age = (EditText) findViewById(R.id.etAge);
        gender = (EditText) findViewById(R.id.etGender);
        location = (EditText) findViewById(R.id.etLoaction);
        school = (EditText) findViewById(R.id.etSchool);
        eMail = (EditText) findViewById(R.id.etEmail);
        save();
    }

    public void save(){
        Button save = (Button) findViewById(R.id.bDetails);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u_name = name.getText().toString();
                String u_gender = gender.getText().toString();
                String u_location = location.getText().toString();
                String u_school = school.getText().toString();
                String u_email = eMail.getText().toString();
                if((u_name == null) || (u_gender == "") || (u_location == "") || (u_school == "") || (u_email == "") ||
                        (age.getText().toString() == "")){
                    Toast.makeText(DetailsActivity.this,"All fields must be filled.", Toast.LENGTH_LONG).show();
                }
                else {
                    Bundle bun = getIntent().getExtras();
                    String user_name = bun.getString("username");
                    String pwd = bun.getString("password");
                    DB.updatePerson(user_name, pwd, u_name, u_gender, 0, u_location, u_school, u_email);
                    Toast.makeText(DetailsActivity.this, "Details Updated", Toast.LENGTH_LONG).show();
                    Intent boom = new Intent(DetailsActivity.this, ResultsActivity.class);
                    startActivity(boom);
                    }

            }
        });
    }
}

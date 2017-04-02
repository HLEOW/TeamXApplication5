package com.example.reedhamilton.teamxapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * Created by Reed Hamilton on 3/21/2017.
 */

public class PreferencesActivity extends Activity {
    Button save;
    RatingBar location;
    float loc;
    RatingBar employRate;
    float employ;
    RatingBar school;
    float schl;
    RatingBar cost;
    float cst;
    RatingBar salary;
    float sal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefs);
        save();
    }

    public void save(){
        save = (Button) findViewById(R.id.bSavePrefs);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location = (RatingBar) findViewById(R.id.locationRating);
                loc = location.getRating();
                employRate = (RatingBar) findViewById(R.id.employmentRating);
                employ = employRate.getRating();
                school = (RatingBar) findViewById(R.id.majorRating);
                schl = school.getRating();
                cost = (RatingBar) findViewById(R.id.tuitionRating);
                cst = cost.getRating();
                salary = (RatingBar) findViewById(R.id.salaryRating);
                sal = salary.getRating();
                Toast.makeText(PreferencesActivity.this,"Preferences Saved", Toast.LENGTH_LONG).show();
                Intent boom = new Intent(PreferencesActivity.this, ResultsActivity.class);
                Bundle my_prefs = new Bundle();
                my_prefs.putFloat("location", loc);
                my_prefs.putFloat("employment", employ);
                my_prefs.putFloat("school", schl);
                my_prefs.putFloat("cost",cst);
                my_prefs.putFloat("salary",sal);
                boom.putExtras(my_prefs);
                startActivity(boom);
            }
        });
    }
}

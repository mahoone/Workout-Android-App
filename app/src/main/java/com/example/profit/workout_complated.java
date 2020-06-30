package com.example.profit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class workout_complated extends AppCompatActivity {

    private Button close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_complated);
        userUIs();

        //Close button

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workout_complated.this.finish();
                Intent i = new Intent(workout_complated.this, BottomNavActivity.class);
                startActivity(i);

            }
        });
    }

    //Declare UI's

    private void userUIs(){
        close = (Button)findViewById(R.id.btnClose);
    }
}

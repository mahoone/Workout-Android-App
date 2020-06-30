package com.example.profit;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainLogRegActivity extends AppCompatActivity {

    private Button SignUp;
    private TextView Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_log_reg);
        declareUI();

        //Underline.

        TextView txt = (TextView) findViewById(R.id.tvFbButton);
        txt.setPaintFlags(txt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        TextView logintxt = (TextView) findViewById(R.id.tvLoginButton);
        logintxt.setPaintFlags(logintxt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        // Go to LoginActivity, Signup, Facebook login pages.

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainLogRegActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainLogRegActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    //Declare UI views.

    private void declareUI(){
        Login = (TextView)findViewById(R.id.tvLoginButton);
        SignUp = (Button)findViewById(R.id.btnSignUp);
    }
}

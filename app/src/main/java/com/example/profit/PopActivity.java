package com.example.profit;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PopActivity extends Activity {

    private EditText userEmail;
    private Button cancel;
    private Button submit;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        userUIviews();

        //Set parameters for Pop activity window.

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.4));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        //Close pop up window.

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopActivity.this.finish();
            }
        });

        //Reset user password via email.

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = userEmail.getText().toString().trim();

                if(useremail.equals("")){
                    Toast.makeText(PopActivity.this, "Please enter valid email address.", Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(PopActivity.this, "Password reset email sent.", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(PopActivity.this, LoginActivity.class));
                            }else{
                                Toast.makeText(PopActivity.this, "Password reset email failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    //Declare UI's.

    private void userUIviews(){
        userEmail = (EditText)findViewById(R.id.etEmailReset);
        cancel = (Button)findViewById(R.id.btnCancel);
        submit = (Button)findViewById(R.id.btnSubmit);
        firebaseAuth = FirebaseAuth.getInstance();
    }
}

package com.example.profit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {


    private Button GoBack;
    private EditText userName, userPassword, userEmail;
    private Button regButton;
    private FirebaseAuth firebaseAuth;
    private TextView termsAndCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setupUIViews();

        //Automatic popping up keyboard and focus on first textbox on start Activity.

        userName.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(userName, InputMethodManager.SHOW_IMPLICIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        TextView termsAndCon = (TextView) findViewById(R.id.tandc);
        termsAndCon.setPaintFlags(termsAndCon.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        //Terms and conditions.

        termsAndCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browser= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.websitepolicies.com/policies/view/YXwtRGw4"));
                startActivity(browser);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();

        //Registration.

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()){
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                            }else{
                                Toast.makeText(SignupActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        GoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, MainLogRegActivity.class);
                startActivity(intent);
            }
        });
    }

    //Declare UI views.

    private void setupUIViews(){
        GoBack = (Button)findViewById(R.id.btnBack);
        userName = (EditText)findViewById(R.id.etUserName);
        userPassword = (EditText)findViewById(R.id.etUserPassword);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        regButton = (Button)findViewById(R.id.btnLogin);
    }

    //Validation for textboxes.

    private Boolean validation(){
      Boolean result = false;

      String name = userName.getText().toString();
      String password = userPassword.getText().toString();
      String email = userEmail.getText().toString();

      if(name.isEmpty() || password.isEmpty() || email.isEmpty()) {
          Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
      }else{
          result = true;
      }
      return result;
    }
}

package com.asl.crud.quizapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {
    private EditText memail, mpass;
    private Button msignin;
    private ProgressDialog md;
    private TextView mloginscreen;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        memail = findViewById(R.id.loginemail);
        mpass = findViewById(R.id.loginpassword);
        msignin = findViewById(R.id.loginButton);
        mloginscreen = findViewById(R.id.signupButtons);

        md = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            //lets go to the activity of all categories

            finish();//close current profile
            startActivity(new Intent(getApplicationContext(), CategoryActivity.class));

        }


        mloginscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();//close current profile
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
            }
        });


        msignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = memail.getText().toString().trim();
                final String password = mpass.getText().toString().trim();

                // mp.setVisibility(View.VISIBLE);
                md.setCanceledOnTouchOutside(false);
                md.setMessage("Login in");
                md.setTitle("please Wait");
                md.show();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    loginadmin(email, password);

                }

            }
        });
    }

    private void loginadmin(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    finish();//close current profile
                    startActivity(new Intent(getApplicationContext(), CategoryActivity.class));

                }else{
                    md.dismiss();
                    String errror=task.getException().getMessage().toString();
                    Toast.makeText(LoginActivity.this,errror,Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

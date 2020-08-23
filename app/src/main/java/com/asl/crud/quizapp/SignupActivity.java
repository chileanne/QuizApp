package com.asl.crud.quizapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {
private EditText memail,mpass,mno;
private Button msignup;
private String mmemail,mmpass;
private ProgressDialog md;
private DatabaseReference mplayersref;
private TextView mloginscreen;
private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



        memail=findViewById(R.id.signupemail);
        mpass=findViewById(R.id.signuppassword);
        msignup=findViewById(R.id.signupButton);
        mloginscreen=findViewById(R.id.signuplogin);
        mno=findViewById(R.id.signupno);

        md=new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            //lets go to the activity of all categories

            finish();//close current profile
            startActivity(new Intent(getApplicationContext(),CategoryActivity.class));

        }

            //get you to the login screen
        mloginscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();//close current profile
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });


        msignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                    String email = memail.getText().toString().trim();
                    String password = mpass.getText().toString().trim();
                    String mmn=mno.getText().toString().trim();

                    md.setCanceledOnTouchOutside(false);
                    md.setCancelable(false);
                    md.setMessage("Creating User");
                    md.setTitle("please Wait");
                    md.show();

                    if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(mmn)) {

                        register_user(email,password,mmn);
                    } else {
                        md.dismiss();
                        Toast.makeText(SignupActivity.this, "empty fields", Toast.LENGTH_SHORT).show();
                    }

                }

                }
        });


    }

    private void register_user(final String email, final String password, final String mmn) {
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser currentuser=FirebaseAuth.getInstance().getCurrentUser();
                    String uid=currentuser.getUid();
                    mplayersref= FirebaseDatabase.getInstance().getReference().child("Players").child(uid);
                    HashMap<String, String> usermap=new HashMap<>();
                    usermap.put("emailadress", email);
                    usermap.put("userid", uid);
                    usermap.put("poneno", mmn);


                    //progressDialog.dismiss();
                    //setting the inputed values and checking of the task is succesful
                    mplayersref.setValue(usermap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                finish();//close current profile
                                startActivity(new Intent(getApplicationContext(),CategoryActivity.class));
                            }else{
                                md.dismiss();
                                String m=task.getException().getMessage().toString();
                                Toast.makeText(SignupActivity.this,m,Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }else{
                    md.dismiss();
                    String m=task.getException().getMessage().toString();
                    Toast.makeText(SignupActivity.this,m,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

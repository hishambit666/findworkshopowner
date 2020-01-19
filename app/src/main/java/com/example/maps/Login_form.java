package com.example.maps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_form extends AppCompatActivity {

    EditText txtEmail, txtPassword;
    Button btn_login, btn_reg,btnproceed;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        getSupportActionBar().setTitle("Login Form");

        btnproceed=(Button)findViewById(R.id.button_proceed);
        txtEmail = (EditText) findViewById(R.id.txt_email);
        txtPassword = (EditText) findViewById(R.id.txt_password);
        btn_login = (Button) findViewById(R.id.buttonLogin);

        firebaseAuth = FirebaseAuth.getInstance();
        btnproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Login_form.this,MapsActivity.class);
                startActivity(i);

            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String Email = txtEmail.getText().toString().trim();
                String Password = txtPassword.getText().toString().trim();

                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(Login_form.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Password)) {
                    Toast.makeText(Login_form.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Password.length() < 6) {
                    Toast.makeText(Login_form.this, "Password too Short", Toast.LENGTH_SHORT).show();
                }
                firebaseAuth.signInWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(Login_form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    startActivity(new Intent(getApplicationContext(), Navigation.class));

                                } else {

                                    Toast.makeText(Login_form.this, "Login Failed or User not Available", Toast.LENGTH_SHORT).show();

                                }

                            }
                        });
            }






        });


    }
    public void btn_signupForm(View view){
        startActivity(new Intent(getApplicationContext(),Register.class));
    }


}
package com.example.maps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.AbstractSet;

public class Register extends AppCompatActivity {

    EditText txtFullName, txtUserName, txtEmail, txtPassword, txtConfirmPassword;
    Button btn_register;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register Form");
        txtFullName = (EditText) findViewById(R.id.txt_full_name);
        txtUserName = (EditText) findViewById(R.id.txt_User_name);
        txtEmail = (EditText) findViewById(R.id.txt_email);
        txtPassword = (EditText) findViewById(R.id.txt_password);
        txtConfirmPassword = (EditText) findViewById(R.id.txt_confirm_password);
        btn_register = (Button) findViewById(R.id.button_reg);

        firebaseAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String FullName = txtFullName.getText().toString().trim();
                String UserName = txtUserName.getText().toString().trim();
                String Email = txtEmail.getText().toString().trim();
                String Password = txtPassword.getText().toString().trim();
                String ConfirmPassword = txtConfirmPassword.getText().toString().trim();



                if (TextUtils.isEmpty(FullName)) {
                    Toast.makeText(Register.this, "Please Enter Full Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(UserName)) {
                    Toast.makeText(Register.this, "Please Enter User Name", Toast.LENGTH_SHORT).show();

                    return;
                }
                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(Register.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Password)) {
                    Toast.makeText(Register.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(ConfirmPassword)) {
                    Toast.makeText(Register.this, "Please Re Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Password.length() < 6) {
                    Toast.makeText(Register.this, "Password too Short", Toast.LENGTH_SHORT).show();
                }

                ;
                if (Password.equals(ConfirmPassword)) {

                    firebaseAuth.createUserWithEmailAndPassword(Email, Password)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        startActivity(new Intent(getApplicationContext(), Login_form.class));
                                        Toast.makeText(Register.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                    } else {

                                        Toast.makeText(Register.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });

                }

            }
        });
    }
}
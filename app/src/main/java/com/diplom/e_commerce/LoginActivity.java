package com.diplom.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if(cUser != null)
        {
            Toast.makeText(this, "User not null", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "User is null", Toast.LENGTH_SHORT).show();
        }
    }

    private void init()
    {
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        mAuth = FirebaseAuth.getInstance();
    }
    public void onClickSignUp(View view)
    {
        if(!TextUtils.isEmpty(etEmail.getText().toString()) && !TextUtils.isEmpty(etPass.getText().toString())){
            mAuth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(getApplicationContext(), "User signUp Successful", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "User signUp failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please, enter the Email and Password", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickSignIn(View view)
    {
        if(!TextUtils.isEmpty(etEmail.getText().toString()) && !TextUtils.isEmpty(etPass.getText().toString())) {
            mAuth.signInWithEmailAndPassword(etEmail.getText().toString(), etPass.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(getApplicationContext(), "User signIp Successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, DatabaseActivity.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "User signUp failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

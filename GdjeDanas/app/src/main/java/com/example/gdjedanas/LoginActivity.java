package com.example.gdjedanas;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText emailLoginEditText;
    private EditText passwordLoginEditText;

    private Button loginButton;

    private TextView registerNowText;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailLoginEditText =
                findViewById(R.id.emailLoginEditText);

        passwordLoginEditText =
                findViewById(R.id.passwordLoginEditText);

        loginButton =
                findViewById(R.id.loginButton);

        registerNowText =
                findViewById(R.id.registerNowText);

        mAuth = FirebaseAuth.getInstance();

        // login dugme

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser();
            }
        });

        // otvaranje registracije

        registerNowText.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Intent intent =
                                new Intent(LoginActivity.this,
                                        RegisterActivity.class);

                        startActivity(intent);
                    }
                });
    }

    private void loginUser() {

        String email =
                emailLoginEditText.getText().toString();

        String password =
                passwordLoginEditText.getText().toString();

        // provjera polja

        if (TextUtils.isEmpty(email)) {

            emailLoginEditText.setError("Unesite email");

            return;
        }

        if (TextUtils.isEmpty(password)) {

            passwordLoginEditText.setError("Unesite password");

            return;
        }

        // firebase login

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    Toast.makeText(
                                            LoginActivity.this,
                                            "Uspješna prijava",
                                            Toast.LENGTH_SHORT).show();

                                    Intent intent =
                                            new Intent(LoginActivity.this,
                                                    HomeActivity.class);

                                    startActivity(intent);

                                    finish();

                                } else {

                                    Toast.makeText(
                                            LoginActivity.this,
                                            "Pogrešan email ili password",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
    }
}
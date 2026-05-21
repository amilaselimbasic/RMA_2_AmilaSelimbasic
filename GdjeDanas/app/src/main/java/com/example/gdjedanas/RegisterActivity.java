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

import com.example.gdjedanas.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    private Button registerButton;

    private TextView loginText;

    private FirebaseAuth mAuth;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // povezivanje xml komponenti

        nameEditText = findViewById(R.id.nameEditText);

        emailEditText = findViewById(R.id.emailEditText);

        passwordEditText = findViewById(R.id.passwordEditText);

        confirmPasswordEditText =
                findViewById(R.id.confirmPasswordEditText);

        registerButton = findViewById(R.id.registerButton);

        loginText = findViewById(R.id.loginText);

        // firebase auth

        mAuth = FirebaseAuth.getInstance();

        // firebase database

        databaseReference =
                FirebaseDatabase.getInstance().getReference("Users");

        // klik na register dugme

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerUser();
            }
        });

        // povratak na login ekran

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    private void registerUser() {

        String name = nameEditText.getText().toString();

        String email = emailEditText.getText().toString();

        String password = passwordEditText.getText().toString();

        String confirmPassword =
                confirmPasswordEditText.getText().toString();

        // student komentar:
        // provjera da li su polja prazna

        if (TextUtils.isEmpty(name)) {

            nameEditText.setError("Unesite ime");

            return;
        }

        if (TextUtils.isEmpty(email)) {

            emailEditText.setError("Unesite email");

            return;
        }

        if (TextUtils.isEmpty(password)) {

            passwordEditText.setError("Unesite password");

            return;
        }

        // provjera passworda

        if (!password.equals(confirmPassword)) {

            confirmPasswordEditText
                    .setError("Passwordi nisu isti");

            return;
        }

        // firebase registracija

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    FirebaseUser firebaseUser =
                                            mAuth.getCurrentUser();

                                    String userId =
                                            firebaseUser.getUid();

                                    // student komentar:
                                    // spremam korisnika u realtime database

                                    User user =
                                            new User(name, email);

                                    databaseReference.child(userId)
                                            .setValue(user);

                                    Toast.makeText(
                                            RegisterActivity.this,
                                            "Registracija uspješna",
                                            Toast.LENGTH_SHORT).show();

                                    Intent intent =
                                            new Intent(RegisterActivity.this,
                                                    MainActivity.class);

                                    startActivity(intent);

                                    finish();

                                } else {

                                    Toast.makeText(
                                            RegisterActivity.this,
                                            "Greška: "
                                                    + task.getException()
                                                    .getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
    }
}
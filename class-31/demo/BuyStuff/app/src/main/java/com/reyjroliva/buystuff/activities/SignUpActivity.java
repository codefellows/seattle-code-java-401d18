package com.reyjroliva.buystuff.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.reyjroliva.buystuff.R;


public class SignUpActivity extends AppCompatActivity {
  public static final String TAG = "SignUpActivity";

  Button submitButton;
  EditText emailEditText;
  EditText passwordEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_up);

    emailEditText = findViewById(R.id.SignUpActivityEmailEditText);
    passwordEditText = findViewById(R.id.SignUpActivityPasswordEditText);
    submitButton = findViewById(R.id.SignUpActivitySubmitButton);

    setupSubmitButton();
  }

  void setupSubmitButton(){
    submitButton.setOnClickListener(v -> {
      // Cognito Sign up Logic
      Amplify.Auth.signUp(emailEditText.getText().toString(),
        passwordEditText.getText().toString(), // Cognito default password policy is 8 characters, no other requirements
        AuthSignUpOptions.builder()
          .userAttribute(AuthUserAttributeKey.email(), emailEditText.getText().toString())
          .build(),
        successResponse -> {
          Log.i(TAG, "Signup succeeded: " + successResponse.toString());
          Intent goToVerifyActivity = new Intent(SignUpActivity.this, VerifyActivity.class);
          // add an intent extra to help prefill the email in the verify activity
          startActivity(goToVerifyActivity);
        },
        failureResponse -> Log.i(TAG, "Signup failed with username: " + "reynaldo.oliva@codefellows.com" + " with this message: " + failureResponse.toString())
      );
    });
  }
}

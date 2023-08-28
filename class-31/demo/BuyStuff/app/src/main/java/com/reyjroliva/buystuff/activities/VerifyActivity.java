package com.reyjroliva.buystuff.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;
import com.reyjroliva.buystuff.R;

public class VerifyActivity extends AppCompatActivity {
  private static String TAG = "VerifyActivty";

  Button submitButton;
  EditText emailEditText;
  EditText verificationCodeEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_verify);

    emailEditText = findViewById(R.id.VerifyActivityEmailEditText);
    submitButton = findViewById(R.id.VerifyActivitySubmitButton);
    verificationCodeEditText = findViewById(R.id.VerifyActivityCodeEditText);

    setupSubmitButton();
  }

  void setupSubmitButton() {
    //Cognito Verification Logic
    submitButton.setOnClickListener(v -> {
      Amplify.Auth.confirmSignUp(emailEditText.getText().toString(),
        verificationCodeEditText.getText().toString(),
        success -> {
          Log.i(TAG, "Verification succeeded: " + success.toString());
          Intent goToLoginIntent = new Intent(VerifyActivity.this, LoginActivity.class);
          // pass the user's email to the login activity for faster login
          // hint: can also pass the password from the signup through verify to login
          startActivity(goToLoginIntent);
        },
        failure -> {
          Log.i(TAG, "Verification failed: " + failure.toString());
        }
      );
    });
  }
}

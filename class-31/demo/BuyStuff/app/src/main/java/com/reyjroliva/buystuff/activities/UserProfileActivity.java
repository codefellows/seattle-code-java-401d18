package com.reyjroliva.buystuff.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.cognito.result.AWSCognitoAuthSignOutResult;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.reyjroliva.buystuff.MainActivity;
import com.reyjroliva.buystuff.R;

public class UserProfileActivity extends AppCompatActivity {
  private static final String TAG = "UserProfileActivity";
  public static final String USER_NICKNAME_TAG = "userNickname"; // at top of class, so other classes can use it -- don't redeclare anywhere else!

  SharedPreferences preferences; // put at top of class, so it can live long enough to be used in the onClickListener
  AuthUser authUser; // Lecture36 Follow up: Creating an auth user to track whether or a not a user is signed in. Used for rendering buttons

  Button loginButton;
  Button logoutButton;
  Button signUpButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_user_profile);

    preferences = PreferenceManager.getDefaultSharedPreferences(this);

    loginButton = findViewById(R.id.UserProfileActivityLoginButton);
    logoutButton = findViewById(R.id.UserProfileActivityLogoutButton);
    signUpButton = findViewById(R.id.UserProfileActivitySignUpButton);

    setupUserNicknameEditText();
    setupSaveButton();
    setupSignUpButton();
    setupLoginButton();
    setupLogoutButton();
  }

  @Override
  protected void onResume() {
    super.onResume();
    /* Lecture36 Follow up: Determine if user is logged in.
    * If the user is logged in, only show the Logout button.
    * If a user is not logged in, only show the Sign Up/Login buttons
    * This can also be used to grab the user's attributes if you choose to use this
    * instead of the fetchUserAttributes in the MainActivity
    * NOTE: remember to call the render buttons function whether it fails or succeeds!
    */
    Amplify.Auth.getCurrentUser(
      success -> {
        Log.i(TAG, "User authenticated with username: " + success.getUsername());
        authUser = success;
        runOnUiThread(this::renderButtons);
      },
      failure -> {
        Log.i(TAG, "There is no current authenticated user");
        authUser = null;
        runOnUiThread(this::renderButtons);
      }
    );
  }

  void setupUserNicknameEditText() {
    String userNickname = preferences.getString(USER_NICKNAME_TAG, null);
    ((EditText)findViewById(R.id.UserProfileActivityUsernameInputEditText)).setText(userNickname);
  }

  void setupSaveButton() {
    ((Button)findViewById(R.id.UserProfileActivitySaveButton)).setOnClickListener(v -> {
      SharedPreferences.Editor preferencesEditor = preferences.edit(); // this fails if you declare "preferences" in onCreate(), rather than just initializing there
      EditText userNicknameEditText = (EditText) findViewById(R.id.UserProfileActivityUsernameInputEditText);
      String userNicknameString = userNicknameEditText.getText().toString();

      preferencesEditor.putString(USER_NICKNAME_TAG, userNicknameString);
      preferencesEditor.apply(); // nothing will save if you forget this line!!

      //Snackbar.make(findViewById(R.id.userProfileActivityView), "Settings saved!", Snackbar.LENGTH_SHORT).show();
      Toast.makeText(UserProfileActivity.this, "Settings saved!", Toast.LENGTH_SHORT).show();
    });
  }

  void setupSignUpButton() {
    signUpButton.setOnClickListener(v -> {
      Intent goToSignUpActivityIntent = new Intent(UserProfileActivity.this, SignUpActivity.class);
      startActivity(goToSignUpActivityIntent);
    });
  }

  void setupLoginButton() {
    loginButton.setOnClickListener(v -> {
      Intent goToLoginActivityIntent = new Intent(UserProfileActivity.this, LoginActivity.class);
      startActivity(goToLoginActivityIntent);
    });
  }

  void setupLogoutButton() {
   logoutButton.setOnClickListener(v -> {
     //Cognito Logout Logic
     AuthSignOutOptions signOutOptions = AuthSignOutOptions.builder()
       .globalSignOut(true)
       .build();

     Amplify.Auth.signOut(signOutOptions,
       signOutResult -> {
         if(signOutResult instanceof AWSCognitoAuthSignOutResult.CompleteSignOut) {
           Log.i(TAG, "Global sign out successful!");
           // Lecture36 Followup: Send user back to MainActivity on Logout
           Intent goToMainActivity = new Intent(UserProfileActivity.this, MainActivity.class);
           startActivity(goToMainActivity);
         } else if (signOutResult instanceof AWSCognitoAuthSignOutResult.PartialSignOut) {
           Log.i(TAG, "Partial sign out successful!");
         } else if (signOutResult instanceof AWSCognitoAuthSignOutResult.FailedSignOut) {
           Log.i(TAG, "Logout failed: " + signOutResult.toString());
         }
       }
     );
   });
  }

  void renderButtons() {
    if(authUser == null) {
      logoutButton.setVisibility(View.INVISIBLE);
      loginButton.setVisibility(View.VISIBLE);
      signUpButton.setVisibility(View.VISIBLE);
    } else {
      logoutButton.setVisibility(View.VISIBLE);
      loginButton.setVisibility(View.INVISIBLE);
      signUpButton.setVisibility(View.INVISIBLE);
    }
  }
}

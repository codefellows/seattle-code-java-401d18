package com.reyjroliva.buystuff.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.reyjroliva.buystuff.R;

import org.w3c.dom.Text;

public class UserProfileActivity extends AppCompatActivity {
  public static final String USER_NICKNAME_TAG = "userNickname"; // at top of class, so other classes can use it -- don't redeclare anywhre else!
  SharedPreferences preferences; // put at top of class, so it can live long enough to be used in the onClickListener

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_profile);

    preferences = PreferenceManager.getDefaultSharedPreferences(this);

    setupUserNicknameEditText();
    setupSaveButton();
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
}

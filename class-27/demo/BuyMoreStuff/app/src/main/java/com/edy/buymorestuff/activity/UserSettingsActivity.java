package com.edy.buymorestuff.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.edy.buymorestuff.R;

public class UserSettingsActivity extends AppCompatActivity
{
  SharedPreferences preferences;
  public static final String USER_NICKNAME_TAG = "userNickname";

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_settings);

    preferences = PreferenceManager.getDefaultSharedPreferences(this);
    String userNickname = preferences.getString(USER_NICKNAME_TAG, "");
    if (!userNickname.isEmpty())
    {
      EditText userNicknameEditText = (EditText) findViewById(R.id.nicknameInputEditText);
      userNicknameEditText.setText(userNickname);
    }

    Button saveButton = findViewById(R.id.userSettingsSaveButton);
    saveButton.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View view)
      {
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        EditText userNicknameEditText = (EditText) findViewById(R.id.nicknameInputEditText);
        String userNicknameString = userNicknameEditText.getText().toString();

        preferencesEditor.putString(USER_NICKNAME_TAG, userNicknameString);
        preferencesEditor.apply();
      }
    });
  }
}

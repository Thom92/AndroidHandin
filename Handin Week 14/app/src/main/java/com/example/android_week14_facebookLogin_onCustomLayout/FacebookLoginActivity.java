package com.example.android_week14_facebookLogin_onCustomLayout;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_week14_facebookLogin_onCustomLayout.Auth.FacebookManager;
import com.facebook.login.widget.LoginButton;

public class FacebookLoginActivity extends AppCompatActivity {
    private FacebookManager facebookManager;
    private LoginButton loginButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebook_login_layout);

        loginButton = findViewById(R.id.login_button);
        facebookManager = new FacebookManager(); //FBmanager class instance
        facebookManager.handleFacebookLogin(loginButton,this); //Calls method with loginbutton and this activity
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        facebookManager.callbackManager.onActivityResult(requestCode,resultCode,data); //Notify callbackManager
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1){ // -1 is successful login
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}

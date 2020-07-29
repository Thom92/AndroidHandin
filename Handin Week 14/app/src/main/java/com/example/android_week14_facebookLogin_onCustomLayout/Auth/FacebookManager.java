package com.example.android_week14_facebookLogin_onCustomLayout.Auth;


import android.app.Activity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class FacebookManager {
    public CallbackManager callbackManager; //Callbackmanager

    //To handle loginprocess
    public void handleFacebookLogin(LoginButton loginButton, Activity activity){
        callbackManager = CallbackManager.Factory.create(); //Facebook class to handle events
        //Provide which permissions u want to have in array, can add more by commaseperation
        //Will start the login process
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("email"));
        //Needs to listen for facebook's response
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println("Facebook login OK " + loginResult.getAccessToken().getUserId());
            }

            @Override
            public void onCancel() {
                System.out.println("U canceled bro");

            }

            @Override
            public void onError(FacebookException error) {
                System.out.println("Facebook login error! " + error.getMessage());
            }
        });

    }


}
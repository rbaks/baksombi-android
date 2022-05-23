package com.example.baksombi.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.baksombi.model.Authentication;
import com.example.baksombi.model.Token;
import com.example.baksombi.model.User;
import com.example.baksombi.view.activity.AuntheticationActivity;
import com.example.baksombi.view.activity.MainActivity;

public class AuthenticationHelper {
    public static void initAuthentication(Context context, Authentication authentication){
        SharedPreferences shared = context.getSharedPreferences(MainActivity.PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString(User.PREF_ID, authentication.getUser().getId());
        editor.putString(User.PREF_NAME, authentication.getUser().getName());
        editor.putString(Token.PREF_TOKEN, authentication.getTokens().getAccess().getToken());
        editor.putString(Token.PREF_REFRESH, authentication.getTokens().getRefresh().getToken());
        HttpHelper.getInstance().setToken(authentication.getTokens().getAccess().getToken());
        editor.commit();
    }
}

package com.example.baksombi.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baksombi.R;
import com.example.baksombi.helper.HttpHelper;
import com.example.baksombi.model.Authentication;
import com.example.baksombi.model.Token;
import com.example.baksombi.model.User;
import com.google.android.material.textfield.TextInputEditText;

public class AuntheticationActivity extends AppCompatActivity {

    Button connect;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isConnected();
        setContentView(R.layout.activity_login);
        connect = findViewById(R.id.btn_authenticate);
        username = findViewById(R.id.ipt_username);
        password = findViewById(R.id.ipt_password);
        connect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new AuthenticationTask().execute(username.getText().toString(),password.getText().toString());
            }
        });
    }

    public void isConnected(){
        String connected = getSharedPreferences(MainActivity.PREFERENCE, Context.MODE_PRIVATE).getString(Token.PREF_TOKEN, "");
        if(!connected.equals("")){
            redirectToHome();
        }
    }

    public void redirectToHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public class AuthenticationTask extends AsyncTask<String, String, Authentication>{

        @Override
        protected Authentication doInBackground(String... strings) {
            try{
                User user = new User();
                user.setEmail(strings[0]);
                user.setPassword(strings[1]);
                return (Authentication) HttpHelper.getInstance().post("/auth/login", Authentication.class, user);
            }
            catch(Exception e){
                e.printStackTrace();
                AuntheticationActivity.this.runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        Toast.makeText(AuntheticationActivity.this, "Incorrect password or email", Toast.LENGTH_LONG).show();
                    }
                });
                return null;
            }
        }

        @Override
        protected void onPostExecute(Authentication authentication){
            if(authentication != null){
                SharedPreferences shared = AuntheticationActivity.this.getSharedPreferences(MainActivity.PREFERENCE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                editor.putString(User.PREF_ID, authentication.getUser().getId());
                editor.putString(User.PREF_NAME, authentication.getUser().getName());
                editor.putString(Token.PREF_TOKEN, authentication.getTokens().getAccess().getToken());
                editor.putString(Token.PREF_REFRESH, authentication.getTokens().getRefresh().getToken());
                HttpHelper.getInstance().setToken(authentication.getTokens().getAccess().getToken());
                editor.commit();
                redirectToHome();
            }
        }
    }

}
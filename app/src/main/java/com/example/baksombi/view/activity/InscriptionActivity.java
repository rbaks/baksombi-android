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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baksombi.R;
import com.example.baksombi.helper.AuthenticationHelper;
import com.example.baksombi.helper.HttpHelper;
import com.example.baksombi.model.Authentication;
import com.example.baksombi.model.Token;
import com.example.baksombi.model.User;
import com.google.android.material.textfield.TextInputEditText;

public class InscriptionActivity extends AppCompatActivity {

    Button signup;
    EditText username;
    EditText password;
    EditText name;
    TextView alreadyAccount;
    LinearLayout container;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        signup = findViewById(R.id.btn_sign_up);
        username = findViewById(R.id.ipt_new_username);
        password = findViewById(R.id.ipt_new_password);
        name = findViewById(R.id.ipt_new_name);
        container = findViewById(R.id.container_sign_up);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        alreadyAccount = findViewById(R.id.lbl_already_account);
        alreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToLogin();
            }
        });
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                load();
                new SignupTask().execute(username.getText().toString(),password.getText().toString(),name.getText().toString());
            }
        });
    }

    private void load(){
        container.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void unload(){
        container.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
    public void redirectToLogin(){
        Intent intent = new Intent(this, AuntheticationActivity.class);
        startActivity(intent);
        finish();
    }

    public void redirectToHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public class SignupTask extends AsyncTask<String, String, Authentication>{

        @Override
        protected Authentication doInBackground(String... strings) {
            try{
                User user = new User();
                user.setEmail(strings[0]);
                user.setPassword(strings[1]);
                user.setName(strings[2]);
                return (Authentication) HttpHelper.getInstance().post("/auth/register", Authentication.class, user);
            }
            catch(Exception e){
                e.printStackTrace();
                InscriptionActivity.this.runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        unload();
                        Toast.makeText(InscriptionActivity.this, "Please provide a valid input", Toast.LENGTH_LONG).show();
                    }
                });
                return null;
            }
        }

        @Override
        protected void onPostExecute(Authentication authentication){
            if(authentication != null){
                AuthenticationHelper.initAuthentication(InscriptionActivity.this, authentication);
                redirectToHome();
            }
        }
    }

}
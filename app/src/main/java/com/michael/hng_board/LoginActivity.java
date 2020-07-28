package com.michael.hng_board;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.michael.hng_board.Utils.Helper;
import com.michael.hng_board.homepage.Home_activity;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    Helper helper;

    TextView signIn;
    EditText Username, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        helper = new Helper(this);

        Username = findViewById(R.id.login_email_username);
        Password = findViewById(R.id.login_password);

        signIn = findViewById(R.id.go_to_signin);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }

    public void loginApi(View view) {

        String username = Username.getText().toString();
        String password = Password.getText().toString();


        Login login = new Login();
        login.execute(username,password);
        helper.progressDialogStart("Logging in", "Please wait while we log you in");

    }

    public class Login extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            Log.i("test", "test");

            OkHttpClient okHttpClient = new OkHttpClient();

            String email = strings[0];
            String password = strings[1];


            RequestBody requestBody = new FormBody.Builder()
                    .add("email", email)
                    .add("password", password)
                    .build();

            Request request = new Request.Builder().url("https://hngboard.herokuapp.com/users/login").post(requestBody).build();

            try{
                Response response = okHttpClient.newCall(request).execute();//gets a response from the server
                Log.i("hhh", "hhh0");
                String result = response.body().string();
                Log.i("responseBody", result);
                JSONObject jsonObject = new JSONObject(result);
                String resultData = jsonObject.getString("status");
                if (resultData.equalsIgnoreCase("success")){

                    JSONObject newjsonObject = new JSONObject(result);
                    String dataData = jsonObject.getString("data");
                    JSONObject datajsonObject = new JSONObject(dataData);
                    String token = datajsonObject.getString("token");
                    String userId = datajsonObject.getString("userId");

                    helper.progressDialogEnd();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();

                    SharedPreferences sharedPreferences = getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", token);
                    editor.putString("userId", userId);
                    editor.apply();

                }else {
                    helper.progressDialogEnd();
                    showToast("Login failed! Check login details");
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }

    public void showToast(final String Text){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, Text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
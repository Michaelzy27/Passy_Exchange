package com.michael.hng_board;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUpActivity extends AppCompatActivity {

    TextView logIn;
    EditText Username, Email, Password, Track, FirstName, LastNmae, Location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        logIn = findViewById(R.id.go_to_login);
        Username = findViewById(R.id.username);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        Track = findViewById(R.id.track);
        FirstName = findViewById(R.id.first_name);
        LastNmae = findViewById(R.id.last_name);
        Location = findViewById(R.id.location);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });
    }

    public void signUp(View view) {

        OkHttpClient okHttpClient = new OkHttpClient();

        String username = Username.getText().toString();
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        String track = Track.getText().toString();
        String firstName = FirstName.getText().toString();
        String lastName = LastNmae.getText().toString();
        String location = Location.getText().toString();

        RequestBody requestBody = new FormBody.Builder()
                .add("email", email)
                .add("password", password)
                .add("firstName", firstName)
                .add("lastNmae", lastName)
                .add("location", location)
                .add("userName", username)
                .add("track", track)
                .build();

        Request request = new Request.Builder().url("https://hngboard.herokuapp.com/users/register").post(requestBody).build();

        try {
            Response response = okHttpClient.newCall(request).execute();

            if (response.isSuccessful()){

                String responseBody = response.body().string();
                Log.i("response", responseBody);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
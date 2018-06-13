package com.logui.arghasen.criotam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcome extends AppCompatActivity {
    private Button login;
    private Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        login = (Button) findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlogin();
            }
        });
        signup = (Button)findViewById(R.id.btnSignup);
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                opensignup();
            }
        });
    }
    public void openlogin(){
        Intent intent = new Intent(this , login.class);
        startActivity(intent);
    }
    public void opensignup(){
        Intent intent = new Intent(this,signup.class);
        startActivity(intent);
    }


}

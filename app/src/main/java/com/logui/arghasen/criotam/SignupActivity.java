package com.logui.arghasen.criotam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth auth;


    ProgressDialog pd ;

    @BindView(R.id.password) EditText password;
    @BindView(R.id.username) EditText username;
    @BindView(R.id.email) EditText email;
    @BindView(R.id.login) TextView login;
    @BindView(R.id.signup) Button signup;
    @BindView(R.id.parent_layout) RelativeLayout parent_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();

        pd = new ProgressDialog(this);
        pd.setMessage("Registering user...");
        pd.setCancelable(false);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                checkifuserExist();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    public void registerUser(){

        if(check()){

            auth.createUserWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                    .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                pd.dismiss();
                                Snackbar snackbar = Snackbar
                                        .make(parent_layout, "Registration failed!", Snackbar.LENGTH_LONG);
                                snackbar.show();
                            } else {
                                    storeindatabase();
                                sendEmailVerification();
                            }

                        }
                    });

        }

    }

    public boolean check(){

        if (TextUtils.isEmpty(email.getText())) {
            email.setError("Field can't be empty!");
            return false;
        }

        if (TextUtils.isEmpty(password.getText())) {
            password.setError("Field can't be empty!");
            return false;
        }

        if (TextUtils.isEmpty(username.getText())) {
            username.setError("Field can't be empty!");
            return false;
        }

        return true;
    }

    public void sendEmailVerification(){

        final FirebaseUser user = auth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {

                        pd.dismiss();

                        if (task.isSuccessful()) {
                            FirebaseAuth.getInstance().signOut();
                            Snackbar snackbar = Snackbar
                                    .make(parent_layout, "We have send a verification link at " +
                                            "your email address. Please verify your email address and login.", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        } else {
                            Snackbar snackbar = Snackbar
                                    .make(parent_layout, "Failed to send verification link at your email address.", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    }
                });
    }

    public void checkifuserExist(){

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userNameRef = dbRef.child("Admin").child(username.getText().toString().trim());
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()) {
                    //create new user
                    registerUser();

                }else{
                    pd.dismiss();
                    Snackbar snackbar = Snackbar
                            .make(parent_layout, "Player already exist.", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        userNameRef.addListenerForSingleValueEvent(eventListener);

    }

    public void storeindatabase()  {

        UserPOJO myPOJO = new UserPOJO(username.getText().toString().trim(),
                email.getText().toString().trim(), password.getText().toString().trim());

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.child("Admin").child(username.getText().toString().trim()).setValue(myPOJO, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

            }
        });
    }

}

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
import android.widget.RelativeLayout;
import android.widget.TextView;

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

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.signup)
    TextView signup;
    @BindView(R.id.parent_layout)
    RelativeLayout parent_layout;

    private FirebaseAuth auth;

    ProgressDialog pd ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();

        pd = new ProgressDialog(this);
        pd.setMessage("Please wait...");
        pd.setCancelable(false);

        pd.show();
            if (auth.getCurrentUser() != null) {
                if(checkIfEmailVerified()) {
                    pd.dismiss();
                    startActivity(new Intent(LoginActivity.this, PlayersActivity.class));
                    finish();
                }
            }else{
                pd.dismiss();
            }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check()){
                    pd.show();
                    getEmailandLogin();

                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                finish();
            }
        });
    }

    public boolean check(){

        if (TextUtils.isEmpty(password.getText())) {
            password.setError("Field can't be empty!");
            return false;
        }

        if (TextUtils.getTrimmedLength(password.getText())<6) {
            password.setError("Minimum 6 characters required!");
            return false;
        }

        if (TextUtils.isEmpty(username.getText())) {
            username.setError("Field can't be empty!");
            return false;
        }

        return true;
    }

    private boolean checkIfEmailVerified()
    {
        FirebaseUser user = auth.getCurrentUser();

        pd.dismiss();
        if(user!=null) {
            if (user.isEmailVerified()) {
                return true;

            } else {
                pd.dismiss();
                //sendEmailVerification();
                FirebaseAuth.getInstance().signOut();
                Snackbar snackbar = Snackbar
                        .make(parent_layout, "Please verify your email first.", Snackbar.LENGTH_LONG);
                snackbar.show();
                //FirebaseAuth.getInstance().signOut();
                return false;
            }
        }else{

        }

        return false;
    }

    public void sendEmailVerification(){

        final FirebaseUser user = auth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {

                        pd.dismiss();

                        if (task.isSuccessful()) {
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

    public void getEmailandLogin(){

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userNameRef = dbRef.child("Admin");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pd.dismiss();
                if(!dataSnapshot.exists()) {
                    pd.dismiss();
                    Snackbar snackbar = Snackbar
                            .make(parent_layout, "Username does not exist!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    }

                else {

                    UserPOJO user = dataSnapshot.child(username.getText().toString().trim()).getValue(UserPOJO.class);

                    if (user != null) {
                        login(user.getEmail());
                        Log.d("email", user.getEmail());
                    } else {

                        pd.dismiss();
                        Snackbar snackbar = Snackbar
                                .make(parent_layout, "Username does not exist!", Snackbar.LENGTH_LONG);
                        snackbar.show();


                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        userNameRef.addListenerForSingleValueEvent(eventListener);

    }

    public void login(String email){

        if(auth.getCurrentUser()!=null){
            if(checkIfEmailVerified()){
                Intent intent = new Intent(LoginActivity.this, PlayersActivity.class);
                startActivity(intent);
                finish();
            }
        }
         else {
            auth.signInWithEmailAndPassword(email, password.getText().toString().trim())
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            pd.dismiss();
                            if (!task.isSuccessful()) {
                                // there was an error
                                Snackbar snackbar = Snackbar
                                        .make(parent_layout, "check your credentials!", Snackbar.LENGTH_LONG);
                                snackbar.show();

                            } else {
                                if(checkIfEmailVerified()) {
                                    Intent intent = new Intent(LoginActivity.this, PlayersActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                    });
        }
    }

}

package com.example.android.vitalrecycling;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    //used to debug the application using logcat
    private static final String TAG = "MainActivity";

    // declaring the objects in the activity to use throughout program
    private TextView mTitleTextView;
    private TextView mTitle2TextView;
    private TextView mQuestionTextView;
    private Button mCashButton;
    private Button mRecycleButton;
    private Button mCampusButton;
    private Button SignOutButton;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");  //logging the activity lifecycle
        setContentView(R.layout.activity_main);

        mTitleTextView = findViewById(R.id.title_main);
        mTitle2TextView = findViewById(R.id.title2);
        mQuestionTextView = findViewById(R.id.question1);

        mCashButton = findViewById(R.id.cashbutton);

        SignOutButton = findViewById(R.id.signoutbutton);

        //action for the cash button
        mCashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* simple toast
                Toast.makeText(MainActivity.this, R.string.cash_toast, Toast.LENGTH_SHORT).show();
                */
                Intent i;
                i = new Intent(MainActivity.this, CashActivity.class);
                startActivity(i);
            }
        });


        mRecycleButton = findViewById(R.id.recyclebutton);

        //action for the recycle button
        mRecycleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // simple toast
                //Toast.makeText(MainActivity.this, R.string.recycle_toast, Toast.LENGTH_SHORT).show();

                Intent i;
                i = new Intent(MainActivity.this, RecycleActivity.class);
                startActivity(i);

            }
        });

        mCampusButton = findViewById(R.id.campusbutton);

        mCampusButton. setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //simple toast
                //Toast.makeText(MainActivity.this, R.string.recycle_toast, Toast.LENGTH_SHORT).show();

                Intent i;
                i = new Intent(MainActivity.this, CampusActivity.class);
                startActivity(i);

            }
        });


        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(MainActivity.this);


        SignOutButton. setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //simple toast
                //Toast.makeText(MainActivity.this, R.string.recycle_toast, Toast.LENGTH_SHORT).show();
                signOut();
            }
        });

    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity.this,"Successfully signed out",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, SignIn.class));
                        finish();
                    }
                });
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

}

package com.gallery.sync;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.login_register_button)
    TextView mRegisterButton;
    @Bind(R.id.login_email)
    EditText mLoginUser;
    @Bind(R.id.login_password)
    EditText mPassword;
    @Bind(R.id.login_button)
    TextView mLoginButton;
    Firebase myFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Firebase.setAndroidContext(this);
        myFirebaseRef=FireBase.getFirebase();

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),RegisterActivity.class);
                startActivity(intent);
            }


        });
    }

    private void doLogin() {
        String username;
        String password;
        username = mLoginUser.getText().toString();
        password = mPassword.getText().toString();

        if (username.equals("") || password.equals("")) {
            Toast.makeText(this, "Please fill the forms", Toast.LENGTH_SHORT).show();
        } else {
            myFirebaseRef.authWithPassword(username, password, new Firebase.AuthResultHandler() {
                @Override
                public void onAuthenticated(AuthData authData) {
                    Toast.makeText(getBaseContext(),"Login succesful!",Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(getBaseContext(), GalleryActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onAuthenticationError(FirebaseError firebaseError) {
                    Toast.makeText(getBaseContext(),firebaseError.toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

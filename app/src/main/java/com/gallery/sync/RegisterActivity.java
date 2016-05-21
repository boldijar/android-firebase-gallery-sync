package com.gallery.sync;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {
    @Bind(R.id.register_button)
    TextView mRegisterButton;
    @Bind(R.id.register_email)
    EditText mEmail;
    @Bind(R.id.register_password)
    EditText mPassword;
    @Bind(R.id.register_retype_password)
    EditText mRetypedPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRegister();
            }
        });
    }

    private void doRegister() {
        if(!mPassword.getText().toString().equals(mRetypedPassword.getText().toString())){
            Toast.makeText(this,"Passwords do not match!", Toast.LENGTH_SHORT).show();
        }else{
            FireBase.getFirebase().createUser(mEmail.getText().toString(), mPassword.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
                @Override
                public void onSuccess(Map<String, Object> result) {
                    Toast.makeText(getBaseContext(),"Success!",Toast.LENGTH_SHORT);
                }
                @Override
                public void onError(FirebaseError firebaseError) {
                    Toast.makeText(getBaseContext(), firebaseError.toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

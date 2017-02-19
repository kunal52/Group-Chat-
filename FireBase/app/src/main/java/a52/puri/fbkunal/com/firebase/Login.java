package a52.puri.fbkunal.com.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {

    EditText email;
    EditText pass;
    Button button;
    TextView textView;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileAcrivity.class));
        }
        progressDialog = new ProgressDialog(this);

        email = (EditText) findViewById(R.id.editText3);
        pass = (EditText) findViewById(R.id.editText4);
        button = (Button) findViewById(R.id.button3);
        textView = (TextView) findViewById(R.id.textView3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userLogin();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }

    void userLogin() {
        progressDialog.setMessage("Login");
        progressDialog.show();
        String s1 = email.getText().toString().trim();
        String s2 = pass.getText().toString().trim();
        firebaseAuth.signInWithEmailAndPassword(s1, s2)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileAcrivity.class));
                        } else
                            Toast.makeText(getApplicationContext(), "Sign in Unsuccesfull", Toast.LENGTH_LONG).show();
                    }
                });
    }

}


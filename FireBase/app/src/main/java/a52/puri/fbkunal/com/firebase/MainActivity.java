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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import io.cloudboost.CloudApp;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText email;
    EditText pass;
    EditText name;
    Button signin;
    TextView textView;
    ProgressDialog progressDialog;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=FirebaseDatabase.getInstance().getReference().child("users");
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileAcrivity.class));
        }

        email = (EditText) findViewById(R.id.editText);
        pass = (EditText) findViewById(R.id.editText2);
        signin = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.textView);
        name= (EditText) findViewById(R.id.full_name);

        progressDialog = new ProgressDialog(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeruser();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });


    }

    public void registeruser() {
        progressDialog.setMessage("Registering");
        progressDialog.show();

        final String s1=email.getText().toString().trim();
        final String s2=pass.getText().toString().trim();
        final String s3=name.getText().toString().trim();
        firebaseAuth.createUserWithEmailAndPassword(s1,s2 )
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            database.push().setValue(new User(s1,s2,s3));
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileAcrivity.class));

                        } else {
                            Toast.makeText(getApplicationContext(), "Registered Unsucessfull", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}

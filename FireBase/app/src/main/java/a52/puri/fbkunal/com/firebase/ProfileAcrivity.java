package a52.puri.fbkunal.com.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileAcrivity extends AppCompatActivity {
    Button log_out;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    Button send;
    EditText messgae;
    ListView messagelist;
    FirebaseListAdapter<Chat> firebaseListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_acrivity);
        firebaseAuth = FirebaseAuth.getInstance();
        log_out = (Button) findViewById(R.id.log_out);
        send = (Button) findViewById(R.id.send);
        messgae = (EditText) findViewById(R.id.mess_text);
        messagelist = (ListView) findViewById(R.id.mess_list);

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(getApplicationContext(), Login.class));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference().child("group");

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageSend();
            }
        });

        messageRecieved();

    }

    void logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(getApplicationContext(), Login.class));
    }

    void messageSend() {
        databaseReference.push().setValue(new Chat(messgae.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getEmail()));
        messgae.setText(" ");
    }

    void messageRecieved() {
        firebaseListAdapter = new FirebaseListAdapter<Chat>(this, Chat.class, R.layout.listview_layout, databaseReference) {
            @Override
            protected void populateView(View v, Chat model, int position) {
                ((TextView) v.findViewById(R.id.text2)).setText(model.getMessage());
                ((TextView) v.findViewById(R.id.text1)).setText(model.getName());
            }
        };
        messagelist.setAdapter(firebaseListAdapter);
        messagelist.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
        messagelist.setStackFromBottom(true);
    }

}

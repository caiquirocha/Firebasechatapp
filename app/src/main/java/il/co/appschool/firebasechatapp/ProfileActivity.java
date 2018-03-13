package il.co.appschool.firebasechatapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {
    TextView tvName, tvDisplay;
    Button btnEdit;
    public SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tvName = findViewById(R.id.tvFullName);
        tvDisplay = findViewById(R.id.tvDisplayName);
        btnEdit = findViewById(R.id.btnEditProfile);
        Intent intent = getIntent();
        String name = intent.getStringExtra("First name")+" "+intent.getStringExtra("Last name");
        tvName.setText(name);
        tvDisplay.setText(intent.getStringExtra("Display name"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_gotoContacts)
            startActivity(new Intent(ProfileActivity.this, ContactsActivity.class));
        else if(item.getItemId() == R.id.action_settings)
        {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.action_sign_out){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
        }
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        sp=getApplicationContext().getSharedPreferences("settings",0);
        String background = sp.getString("background", null);
        if (background != null) {
            getWindow().getDecorView().findViewById(android.R.id.content).setBackgroundColor(Color.parseColor(background));
        }
    }
}

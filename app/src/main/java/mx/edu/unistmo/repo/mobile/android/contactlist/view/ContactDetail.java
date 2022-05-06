package mx.edu.unistmo.repo.mobile.android.contactlist.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;
import mx.edu.unistmo.repo.mobile.android.contactlist.MainActivity;
import mx.edu.unistmo.repo.mobile.android.contactlist.R;
import mx.edu.unistmo.repo.mobile.android.contactlist.model.Contact;

public class ContactDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        /* Enabling up navigation */
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        /* Retrieving contact info via extra parameters */
        Bundle extras = getIntent().getExtras();
        Contact contact;

        try {
            contact = (Contact) extras.get("KEY_EXTRA_CONTACT");
        } catch(Exception e) {
            contact = new Contact();
        }

        /* Set contact info to views */
        TextView tvName = findViewById(R.id.tvName);
        TextView tvTelephone = findViewById(R.id.tvTelephone);
        TextView tvEmail = findViewById(R.id.tvEmail);

        tvName.setText(contact.getName());
        tvTelephone.setText(contact.getTelephone());
        tvEmail.setText(contact.getEmail());

        /* Set onclick listeners for a call-phone or send email */
        LinearLayout llTelephone = findViewById(R.id.llTelephone);
        LinearLayout llEmail = findViewById(R.id.llEmail);

        llTelephone.setOnClickListener(view -> {
            String telephone = tvTelephone.getText().toString();
            Intent i = new Intent(Intent.ACTION_DIAL);

            i.setData(Uri.parse("tel:" + telephone));

            if (i.resolveActivity(getPackageManager()) != null)
                startActivity(i);
        });
        llEmail.setOnClickListener(view -> {
            String email = tvEmail.getText().toString();
            Intent i = new Intent(Intent.ACTION_SENDTO);

            i.setData(Uri.parse("mailto:"));
            i.putExtra(Intent.EXTRA_EMAIL, new String[] { email });

            if (i.resolveActivity(getPackageManager()) != null)
                startActivity(Intent.createChooser(i, "Send e-mail"));
        });

        /* Set onclick listener to open another activity */
        Button openTestActivity = findViewById(R.id.btnOpenTestActivity);

        openTestActivity.setOnClickListener(view -> {
            Intent i = new Intent(this, Test.class);

            startActivity(i);
        });

        /* Register a textview to open a context-menu */
        registerForContextMenu(tvName);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this, "Back's tap!", Toast.LENGTH_SHORT).show();

            /* **
            Intent i = new Intent(this, MainActivity.class);

            startActivity(i);
            finish();
             */
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()) {
            case R.id.miAbout:
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                break;
            case R.id.miSettings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.miRotate:
                Toast.makeText(this, "Rotate", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.menu_options, menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()) {
            case R.id.miAbout:
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                break;
            case R.id.miSettings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onContextItemSelected(item);
    }
}
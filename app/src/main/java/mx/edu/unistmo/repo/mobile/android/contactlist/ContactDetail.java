package mx.edu.unistmo.repo.mobile.android.contactlist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
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

public class ContactDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        Contact contact;

        try {
            contact = (Contact) extras.get("KEY_EXTRA_CONTACT");
        } catch(Exception e) {
            contact = new Contact();
        }
        TextView tvName = findViewById(R.id.tvName);
        TextView tvTelephone = findViewById(R.id.tvTelephone);
        TextView tvEmail = findViewById(R.id.tvEmail);

        tvName.setText(contact.getName());
        tvTelephone.setText(contact.getTelephone());
        tvEmail.setText(contact.getEmail());

        LinearLayout llTelephone = findViewById(R.id.llTelephone);
        LinearLayout llEmail = findViewById(R.id.llEmail);

        llTelephone.setOnClickListener(view -> {
            String telephone = tvTelephone.getText().toString();
            Intent i = new Intent(Intent.ACTION_DIAL);

            i.setData(Uri.parse("tel:" + telephone));
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivity(i);
            }
        });
        llEmail.setOnClickListener(view -> {
            String email = tvEmail.getText().toString();
            Intent i = new Intent(Intent.ACTION_SENDTO);

            i.setData(Uri.parse("mailto:"));
            i.putExtra(Intent.EXTRA_EMAIL, new String[] { email });
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivity(Intent.createChooser(i, "Send e-mail"));
            }
        });

        Button openTestActivity = findViewById(R.id.btnOpenTestActivity);

        openTestActivity.setOnClickListener(view -> {
            Intent i = new Intent(this, Test.class);

            startActivity(i);
        });

        registerForContextMenu(tvName);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
        //finish();
    }

    /* **
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

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
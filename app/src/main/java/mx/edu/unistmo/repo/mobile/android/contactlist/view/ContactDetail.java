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
import mx.edu.unistmo.repo.mobile.android.contactlist.view.fragments.ContactDetailFragment;

public class ContactDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        /* Enabling up navigation */
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        /* Inflating a fragment to show contact info... */
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fcvFragmentContainer, ContactDetailFragment.class, null)
                    .commit();
            /*
            Bundle bundle = new Bundle();
            bundle.putInt("some_int", 0);

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, ExampleFragment.class, bundle)
                    .commit();
             */
        }

        /* Set onclick listener to open another activity */
        Button openTestActivity = findViewById(R.id.btnOpenTestActivity);

        openTestActivity.setOnClickListener(view -> {
            Intent i = new Intent(this, Test.class);

            startActivity(i);
        });

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
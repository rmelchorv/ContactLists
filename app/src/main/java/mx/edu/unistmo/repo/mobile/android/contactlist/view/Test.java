package mx.edu.unistmo.repo.mobile.android.contactlist.view;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import java.util.Objects;
import mx.edu.unistmo.repo.mobile.android.contactlist.R;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        /* Enabling up navigation */
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        /* Associating an imageview for show a popup-menu */
        ImageView ivLauncher = findViewById(R.id.ivLauncher);

        ivLauncher.setOnClickListener(this::inflateMenuPopUp);
    }

    @SuppressLint("NonConstantResourceId")
    public void inflateMenuPopUp(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);

        popupMenu.getMenuInflater().inflate(R.menu.menu_options, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(menuItem -> {

            switch(menuItem.getItemId()) {
                case R.id.miAbout:  /* getBaseContext() */
                    Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.miSettings:
                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }

            return false;
        });

        popupMenu.show();
    }
}
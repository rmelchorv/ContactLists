package mx.edu.unistmo.repo.mobile.android.contactlist.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import java.util.Objects;
import mx.edu.unistmo.repo.mobile.android.contactlist.R;
import mx.edu.unistmo.repo.mobile.android.contactlist.view.fragment.DatePickerFragment;

public class TestActivity extends AppCompatActivity {

    private static final int REQUEST_BT = 1;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        /* Enabling up navigation */
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        /* Associating an imageview for show a popup-menu */
        ImageView ivLauncher = findViewById(R.id.ivLauncher);

        ivLauncher.setOnClickListener(this::inflateMenuPopUp);

        /* Showing a date picker */
        Button date = findViewById(R.id.btnDate);

        date.setOnClickListener(view -> {
            DialogFragment newFragment = new DatePickerFragment();

            newFragment.show(getSupportFragmentManager(), "datePicker");
        });

        /* Request for a Bluetooth permission  */
        Button btnBluetooth = findViewById(R.id.btnBluetooth);
        
        btnBluetooth.setOnClickListener(view -> requestBluetoothPermission());
     }

    private void requestBluetoothPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Bluetooth permission already has been granted!", Toast.LENGTH_LONG).show();
            setupBluetooth();
        } else {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.BLUETOOTH))
                Toast.makeText(this, "Bluetooth permission is needed to perform... <something>!", Toast.LENGTH_LONG).show();

            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.BLUETOOTH }, REQUEST_BT);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_BT) {

            if (grantResults[0] != PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Bluetooth was not granted!", Toast.LENGTH_LONG).show();
            else
                setupBluetooth();
        } else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @SuppressLint("MissingPermission")
    private void setupBluetooth() {
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

        if (btAdapter == null)
            Toast.makeText(this, "Device doesn't support Bluetooth!", Toast.LENGTH_LONG).show();
        else if (!btAdapter.isEnabled()) {
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

            startActivity(i);
        }
    }

    @SuppressLint("NonConstantResourceId")
    public void inflateMenuPopUp(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);

        popupMenu.getMenuInflater().inflate(R.menu.menu_options, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(menuItem -> {

            switch(menuItem.getItemId()) {
                case R.id.miAbout:  /* getBaseContext() */
                    Intent i = new Intent(this, SliderActivity.class);

                    startActivity(i);
                    break;
                case R.id.miRotate:
                    Toast.makeText(this, "Rotate", Toast.LENGTH_SHORT).show();
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
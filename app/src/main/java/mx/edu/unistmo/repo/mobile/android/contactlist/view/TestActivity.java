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
    private static final int REQUEST_ENABLE_BT = 0;

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

        /* Manage permission */
        Button btnBluetooth = findViewById(R.id.btnBluetooth);

        btnBluetooth.setOnClickListener(view -> {
            requestBluetoothPermission();
            setUpBluetooth();
        });
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

    public void requestBluetoothPermission() {

        /* Check if permission is already available */
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH)
                != PackageManager.PERMISSION_GRANTED) {
            /* Permission has been granted */

            /* Provide an additional rationale to the user if the permission was not granted
             *   and the user would benefit from additional context for the use of the permission */
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.BLUETOOTH))
                Toast.makeText(this, "Bluetooth permission is needed to perform... <something>!", Toast.LENGTH_SHORT).show();

            /* Request Bluetooth permission */
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.BLUETOOTH }, REQUEST_BT);
        }
    }

    @SuppressLint("MissingPermission")
    public void setUpBluetooth() {
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

        if (btAdapter == null)
            Toast.makeText(this, "Device doesn't support Bluetooth!", Toast.LENGTH_SHORT).show();
        else if (!btAdapter.isEnabled()) {
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

            startActivity(i);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_BT) {
            /* Received permission result for Bluetooth permission */

            /* Check: if the only required permission has not been granted, then */
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED)
                /* ... Bluetooth permission was denied, so we cannot use this feature */
                Toast.makeText(this, "Permission was not granted!", Toast.LENGTH_SHORT).show();
        } else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
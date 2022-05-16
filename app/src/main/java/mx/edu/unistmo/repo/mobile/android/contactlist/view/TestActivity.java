package mx.edu.unistmo.repo.mobile.android.contactlist.view;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

    private static final int REQUEST_CODE = 1;
    private static final int PERMISSION_ENABLE_REQUEST_CODE = 0;

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

        Button btnBluetooth = findViewById(R.id.btnBluetooth);

        btnBluetooth.setOnClickListener(view -> {

            requestBluetoothPermission();

            //getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)
/*
            BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();

            if(adapter == null)
                Toast.makeText(this, "There is no bluetooth device found!", Toast.LENGTH_SHORT).show();
            else if (!adapter.isEnabled()) {
                Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

                //startActivityIfNeeded(i, PERMISSION_ENABLE_REQUEST_CODE);

                ActivityResultLauncher<String> mGetContent = registerForActivityResult(
                    new ActivityResultContracts.RequestPermission(), isGranted -> {
                        if (isGranted)
                        {
                            //do something
                        }
                });

                mGetContent.launch(Manifest.permission.BLUETOOTH);
            }
            */
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

    /* Check if permission is declared */
    public boolean isBluetoothPermissionGranted() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH);

        return result == PackageManager.PERMISSION_GRANTED;
    }

    /* Check if permission is enabled */
    public void requestBluetoothPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.BLUETOOTH))
            Toast.makeText(this, "Permission has already granted!", Toast.LENGTH_SHORT).show();
        else
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.BLUETOOTH }, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (isBluetoothPermissionGranted())
                    Toast.makeText(this, "Permission has granted!", Toast.LENGTH_SHORT).show();
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
package mx.edu.unistmo.repo.mobile.android.contactlist.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import mx.edu.unistmo.repo.mobile.android.contactlist.R;

public class ContactFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);

        /* Enabling up navigation */
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        /* Setting up the listener for save data in (an internal) text file */
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(view -> {
            try {
                EditText etName = findViewById(R.id.etName);
                FileOutputStream os = openFileOutput("contact.txt", Context.MODE_APPEND);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    os.write(etName.getText().toString().getBytes(StandardCharsets.UTF_8));
                }

                os.close();
                etName.setText("");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
package mx.edu.unistmo.repo.mobile.android.contactlists;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ContactDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        Bundle extras = getIntent().getExtras();
        Contact contact = (Contact) extras.get("KEY_EXTRA_CONTACT");

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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
        finish();
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
}
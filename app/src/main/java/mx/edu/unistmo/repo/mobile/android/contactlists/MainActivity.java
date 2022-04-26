package mx.edu.unistmo.repo.mobile.android.contactlists;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Juan Perez","9710000001","juan.perez@gmail.com"));
        contacts.add(new Contact("Luis Lopez","9710000002","luis.lopez@gmail.com"));
        contacts.add(new Contact("Lalo Gomez","9710000003","lalo.gomez@gmail.com"));

        ArrayList<String> names = new ArrayList<>();
        for (Contact contact : contacts)
            names.add(contact.getName());

        ListView lstContacts = findViewById(R.id.lstContacts);
        lstContacts.setAdapter(
            new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names)
        );
        lstContacts.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(this, ContactDetail.class);

            intent.putExtra("KEY_EXTRA_CONTACT", contacts.get(i));
            startActivity(intent);
            finish();
        });
    }
}
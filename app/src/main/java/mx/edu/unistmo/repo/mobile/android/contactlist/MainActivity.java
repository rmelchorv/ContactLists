package mx.edu.unistmo.repo.mobile.android.contactlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import mx.edu.unistmo.repo.mobile.android.contactlist.adapters.ContactAdapter;
import mx.edu.unistmo.repo.mobile.android.contactlist.model.Contact;
import mx.edu.unistmo.repo.mobile.android.contactlist.view.ContactDetail;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Setting contact list */
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Juan Penas","9710000001","juan.penas@gmail.com"));
        contacts.add(new Contact("Lalo Lopez","9710000002","lalo.lopez@gmail.com"));
        contacts.add(new Contact("Paco Gomez","9710000003","paco.gomez@gmail.com"));
        contacts.add(new Contact("John Senna","9710000004","john.senna@gmail.com"));
        contacts.add(new Contact("Patt Games","9710000005","patt.games@gmail.com"));
        contacts.add(new Contact("Alan Horne","9710000006","alan.horne@gmail.com"));
        contacts.add(new Contact("Pepe Pecas","9710000007","pepe.pecas@gmail.com"));
        contacts.add(new Contact("Hugo Sante","9710000008","hugo.sante@gmail.com"));
        contacts.add(new Contact("Paco Picas","9710000009","paco.picas@gmail.com"));
        contacts.add(new Contact("Luis Aloro","9710000010","luis.aloro@gmail.com"));
        contacts.add(new Contact("Beth Owner","9710000011","beth.owner@gmail.com"));
        contacts.add(new Contact("Dart Vader","9710000012","dart.vader@gmail.com"));

        ArrayList<String> names = new ArrayList<>();
        for (Contact contact : contacts)
            names.add(contact.getName());

        RecyclerView rvContacts = findViewById(R.id.rvContacts);

        /* How to show contact info? Via RecyclerView? */
        if (rvContacts.getVisibility() == View.VISIBLE) {
            rvContacts.setAdapter(new ContactAdapter(this, contacts));
            rvContacts.setLayoutManager(new LinearLayoutManager(this));
        }
        /* or using a ListView? */
        else {
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
}
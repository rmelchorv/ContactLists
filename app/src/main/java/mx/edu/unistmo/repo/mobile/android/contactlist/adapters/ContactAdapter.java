package mx.edu.unistmo.repo.mobile.android.contactlist.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import mx.edu.unistmo.repo.mobile.android.contactlist.model.Contact;
import mx.edu.unistmo.repo.mobile.android.contactlist.R;
import mx.edu.unistmo.repo.mobile.android.contactlist.view.ContactDetail;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    LayoutInflater inflater;
    ArrayList<Contact> contacts;

    public ContactAdapter(Context context, ArrayList<Contact> contacts) {
        this.inflater = LayoutInflater.from(context);
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.cardview_contact, parent, false);

        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);

        holder.cvName.setText(contact.getName());
        holder.cvTelephone.setText(contact.getTelephone());
        holder.cvEmail.setText(contact.getEmail());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ContactDetail.class);

            intent.putExtra("KEY_EXTRA_CONTACT", contact);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {

        ImageView cvPhoto;
        TextView cvName;
        TextView cvTelephone;
        TextView cvEmail;

        public ContactViewHolder(View itemView) {
            super(itemView);

            cvPhoto = itemView.findViewById(R.id.cvPhoto);
            cvName = itemView.findViewById(R.id.cvName);
            cvTelephone = itemView.findViewById(R.id.cvTelephone);
            cvEmail = itemView.findViewById(R.id.cvEmail);
        }
    }
}
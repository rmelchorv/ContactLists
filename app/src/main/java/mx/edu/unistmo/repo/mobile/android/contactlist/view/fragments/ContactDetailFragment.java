package mx.edu.unistmo.repo.mobile.android.contactlist.view.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import mx.edu.unistmo.repo.mobile.android.contactlist.R;
import mx.edu.unistmo.repo.mobile.android.contactlist.model.ContactModel;

public class ContactDetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        /* Retrieving contact info via extra parameters */
        ContactModel contact;

        try {
            contact = (ContactModel) requireArguments().get("KEY_EXTRA_CONTACT");
        } catch(Exception e) {
            contact = new ContactModel();
        }

        /* Set contact info to views */
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvTelephone = view.findViewById(R.id.tvTelephone);
        TextView tvEmail = view.findViewById(R.id.tvEmail);

        tvName.setText(contact.getName());
        tvTelephone.setText(contact.getTelephone());
        tvEmail.setText(contact.getEmail());

        /* Register a textview to open a context-menu */
        registerForContextMenu(tvName);

        /* Set onclick listeners for get a call-phone or send email */
        LinearLayout llTelephone = view.findViewById(R.id.llTelephone);
        LinearLayout llEmail = view.findViewById(R.id.llEmail);

        llTelephone.setOnClickListener(layoutView -> {
            String telephone = tvTelephone.getText().toString();
            Intent i = new Intent(Intent.ACTION_DIAL);

            i.setData(Uri.parse("tel:" + telephone));

            if (i.resolveActivity(view.getContext().getPackageManager()) != null)
                startActivity(i);
        });
        llEmail.setOnClickListener(layoutView -> {
            String email = tvEmail.getText().toString();
            Intent i = new Intent(Intent.ACTION_SENDTO);

            i.setData(Uri.parse("mailto:"));
            i.putExtra(Intent.EXTRA_EMAIL, new String[] { email });

            if (i.resolveActivity(view.getContext().getPackageManager()) != null)
                startActivity(Intent.createChooser(i, "Send e-mail"));
        });

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        requireActivity().getMenuInflater().inflate(R.menu.menu_options, menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()) {
            case R.id.miAbout:
                Toast.makeText(getContext(), "About", Toast.LENGTH_SHORT).show();
                break;
            case R.id.miRotate:
                Toast.makeText(getContext(), "Rotate", Toast.LENGTH_SHORT).show();
                break;
            case R.id.miSettings:
                Toast.makeText(getContext(), "Settings", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onContextItemSelected(item);
    }
}
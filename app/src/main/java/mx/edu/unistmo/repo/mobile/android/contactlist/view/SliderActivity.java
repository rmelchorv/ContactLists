package mx.edu.unistmo.repo.mobile.android.contactlist.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.ArrayList;
import java.util.Objects;
import mx.edu.unistmo.repo.mobile.android.contactlist.R;
import mx.edu.unistmo.repo.mobile.android.contactlist.adapter.SliderAdapter;
import mx.edu.unistmo.repo.mobile.android.contactlist.view.fragment.ContactDetailFragment;

public class SliderActivity extends AppCompatActivity {

    private ViewPager2 pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        /* Setting up fragments... */
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new Fragment());
        fragments.add(new ContactDetailFragment());

        /* and prepare the ViewPager */
        pager = findViewById(R.id.vpPager);
        pager.setAdapter(new SliderAdapter(this, fragments));

        /* Finally, associate pager with TabLayout */
        TabLayout tabs = findViewById(R.id.tlTabs);
        new TabLayoutMediator(tabs, pager,
                (tab, position) -> {
                    //tab.setIcon(R.drawable.ic_profile)
                    //tab.setText("Tab " + (position + 1))
                }).attach();
        Objects.requireNonNull(tabs.getTabAt(0)).setIcon(R.drawable.ic_pet);
        Objects.requireNonNull(tabs.getTabAt(1)).setIcon(R.drawable.ic_profile);
    }

    @Override
    public void onBackPressed() {

        if (pager.getCurrentItem() == 0)
            super.onBackPressed();
        else
            pager.setCurrentItem(pager.getCurrentItem() - 1);
    }
}
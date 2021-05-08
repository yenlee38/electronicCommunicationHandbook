package com.example.electroniccommunicationhandbook.ui.teacher.list_class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.electroniccommunicationhandbook.R;
import com.google.android.material.tabs.TabLayout;

public class ListClass extends AppCompatActivity {
    TabLayout mTabLayout;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_class);
        //---------- Hook-------------
        mTabLayout= findViewById(R.id.tab_class);
        mViewPager= findViewById(R.id.viewpage_class);

        //--------- init-----------
        VPClassAdapterFragment viewpageAdapter =
                new VPClassAdapterFragment(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewpageAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }
}
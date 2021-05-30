package com.example.electroniccommunicationhandbook.ui.student.fee;

import android.content.Intent;
import android.os.Bundle;

import com.example.electroniccommunicationhandbook.MainActivity;
import com.example.electroniccommunicationhandbook.MainActivity_parent;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.electroniccommunicationhandbook.ui.student.fee.ui.main.SectionsPagerAdapter;

public class FeeViewActivity extends AppCompatActivity {

    private AppCompatButton btn_comeback;
    private UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_view);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        btn_comeback= findViewById(R.id.btn_comeback);
        userLocalStore= new UserLocalStore(this.getApplicationContext());
        btn_comeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int role= userLocalStore.getRoleLocal();
                if(role==1){
                    if(role==3){
                        Intent intent= new Intent(getApplicationContext(), MainActivity_parent.class);
                        startActivity(intent);
                    }
                    if(role==2)
                    {
                        Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }

                }
            }
        });

    }
}
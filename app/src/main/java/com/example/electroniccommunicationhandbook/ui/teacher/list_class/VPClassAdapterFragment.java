package com.example.electroniccommunicationhandbook.ui.teacher.list_class;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.electroniccommunicationhandbook.R;

public class VPClassAdapterFragment extends FragmentStatePagerAdapter {

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        if(position==0){
            title ="Semester 1";
        }else {
            title="Semester 2";
        }
        return title;
    }

    public VPClassAdapterFragment(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

//    @Override
//    public int getItemPosition(@NonNull Object object) {
//       // return super.getItemPosition(object);
//        return POSITION_NONE;
//    }

    @Override
    public int getCount() {
        return 2;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.e("position", String.valueOf(position));
        return ClassSem1Fragment.newInstance(position+1);
    }

}
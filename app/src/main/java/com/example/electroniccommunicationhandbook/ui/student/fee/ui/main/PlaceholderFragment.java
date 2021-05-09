package com.example.electroniccommunicationhandbook.ui.student.fee.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Fee;
import com.example.electroniccommunicationhandbook.entity.Point;
import com.example.electroniccommunicationhandbook.ui.student.fee.FeeAdapter;
import com.example.electroniccommunicationhandbook.ui.student.point.PointAdapter;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private RecyclerView recyclerView;
    private FeeAdapter feeAdapter;
    private ArrayList<Fee> listFee;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_fee_view, container, false);
        recyclerView = root.findViewById(R.id.recycelviewFee);

        listFee= new ArrayList<>();
        listFee.add(new Fee(1,2020,580000));
        listFee.add(new Fee(1,2020,580000));
        listFee.add(new Fee(1,2020,580000));
        listFee.add(new Fee(1,2020,580000));
        listFee.add(new Fee(1,2020,580000));
        listFee.add(new Fee(1,2020,580000));

        feeAdapter= new FeeAdapter(listFee,this.getContext());
        recyclerView.setAdapter(feeAdapter);
        LinearLayoutManager layoutManager= new LinearLayoutManager(root.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        return root;
    }
}
package com.example.electroniccommunicationhandbook.ui.student.point.ui.main;

import android.content.Context;
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
import com.example.electroniccommunicationhandbook.entity.Point;
import com.example.electroniccommunicationhandbook.ui.student.point.PointAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private RecyclerView recyclerView;
    private PointAdapter pointAdapter;
    private ArrayList<Point> listpoint;

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
        View root = inflater.inflate(R.layout.fragment_point_view, container, false);
        recyclerView =root.findViewById(R.id.recycelviewPoint);

        listpoint= new ArrayList<Point>();
        listpoint.add(new Point(8.5f,9,8.75f,'A',true,1,"2020-2021",1,"Cơ sở dữ liệu"));
        listpoint.add(new Point(8.5f,5.5f,6.5f,'B',false,1,"2020-2021",1,"Lập trình di động"));
        listpoint.add(new Point(8.5f,5.5f,6.5f,'B',true,1,"2020-2021",1,"Hệ điều hành"));
        listpoint.add(new Point(8.5f,5.5f,6.5f,'B',true,1,"2020-2021",1,"QL phần mềm"));
        listpoint.add(new Point(8.5f,5.5f,6.5f,'B',false,1,"2020-2021",1,"LT Web"));

        pointAdapter =new PointAdapter(listpoint,root.getContext() );
        recyclerView.setAdapter(pointAdapter);
        LinearLayoutManager layout= new LinearLayoutManager(root.getContext());
        layout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layout);

        return root;
    }
}
package com.example.electroniccommunicationhandbook.ui.student.notification.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Announcement;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.repository.AnnouncementRepository;
import com.example.electroniccommunicationhandbook.ui.student.notification.NotificationAdapter;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.RotatingCircle;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.github.ybq.android.spinkit.style.WanderingCubes;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private int index;
    private AnnouncementRepository repository;
    private RecyclerView recyclerView;

    private ArrayList<Announcement> listAnnouncement;
    private UserLocalStore userLocalStore;
    private Student studentLocal ;
    private Context context;
    private int role;
    private ProgressBar progressBar ;

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
        index = 1;

        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notification, container, false);
        context= this.getContext();
        recyclerView= root.findViewById(R.id.recyleviewNotification);


        userLocalStore= new UserLocalStore(context);
        role= userLocalStore.getRoleLocal();
        if(role ==2){
            studentLocal = userLocalStore.getStudentLocal();
        }



        if(index==2)
        {
            progressBar = (ProgressBar) root.findViewById(R.id.spin_kit1);
            Sprite doubleBounc = new ThreeBounce();
            progressBar.setIndeterminateDrawable(doubleBounc);
            repository= ViewModelProviders.of(this).get(AnnouncementRepository.class);
            repository.findAnnouncementByStudentId(studentLocal.getStudentId());
            repository.getAnnouncmentList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Announcement>>() {
                @Override
                public void onChanged(ArrayList<Announcement> announcements) {
                    if(announcements!=null){
                        loadNotification(announcements,root);
                    }
                    else {

                    }
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });
        }

        return root;
    }

    void loadNotification(ArrayList<Announcement> announcements, View root){
        listAnnouncement= new ArrayList<>(announcements);

        NotificationAdapter adapter=new NotificationAdapter(listAnnouncement,root.getContext() );
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layout= new LinearLayoutManager(root.getContext());
        layout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layout);
    }


}
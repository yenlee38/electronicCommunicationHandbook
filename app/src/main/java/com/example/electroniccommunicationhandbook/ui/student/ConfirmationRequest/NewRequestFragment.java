package com.example.electroniccommunicationhandbook.ui.student.ConfirmationRequest;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.ViewModel.ConfirmationRequestViewModel;
import com.example.electroniccommunicationhandbook.entity.ConfirmationPaper;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_ConfirmationPaper;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static com.example.electroniccommunicationhandbook.util.Comon.CODE_SUCCESSFUL;

public class NewRequestFragment extends Fragment {
    Button btnNewRequest;
    RadioButton radConfirmationTranscript;
    RadioButton radConfirmationStudent;
    Student student;
    int id_Confirmation_Student = 28;
    int id_Confirmation_Transcript = 29;
    ConfirmationRequestViewModel confirmationRequestViewModel;
    Button btn_back;
    public NewRequestFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        confirmationRequestViewModel = ViewModelProviders.of(this).get(ConfirmationRequestViewModel.class);
        btnNewRequest = view.findViewById(R.id.btnSendNewRequest);
        radConfirmationStudent = view.findViewById(R.id.rdGetConfirmStudent);
        radConfirmationTranscript = view.findViewById(R.id.rdGetTranscript);
        btn_back= view.findViewById(R.id.btn_new_to_main_board);
        UserLocalStore userLocalStore = new UserLocalStore(getContext());
        student = userLocalStore.getStudentLocal();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_newRequestFragment_to_boardConfirmation);

            }
        });
        btnNewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Student_ConfirmationPaper newConfirmation = new Student_ConfirmationPaper();
                ConfirmationPaper categoryPaper;
                if (radConfirmationStudent.isChecked()) {
                    categoryPaper = new ConfirmationPaper(id_Confirmation_Student);
                } else {
                    categoryPaper = new ConfirmationPaper(id_Confirmation_Transcript);
                }
//                /// SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
//                Date first_date = Calendar.getInstance().getTime();
//
                Student_ConfirmationPaper student_confirmationPaper = null;
//
//                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//                Date date = new Date();


                student_confirmationPaper = new Student_ConfirmationPaper(Calendar.getInstance().getTime(), student, categoryPaper);

//                new Thread(new Runnable() {
//                   @Override
//                   public void run() {
                       confirmationRequestViewModel.createConfirmation(student_confirmationPaper);
//                   }
//               });
                Dialog dialog =new Dialog(getActivity());

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.wait_request);
                dialog.show();

                    for (int i=-10000; i<1000000000;i=i+2)
                        i--;

                dialog.dismiss();
                //TODO: send request
                int code= confirmationRequestViewModel.getCode();
                if (code == CODE_SUCCESSFUL) {
                    Navigation.findNavController(view).navigate(R.id.action_newRequestFragment_to_successfulRequestFragment3);
                } else {
                    Navigation.findNavController(view).navigate(R.id.action_newRequestFragment_to_failureRequestFragment);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_request, container, false);

    }
}
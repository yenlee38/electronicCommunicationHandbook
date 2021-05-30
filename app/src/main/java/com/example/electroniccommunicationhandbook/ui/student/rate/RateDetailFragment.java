package com.example.electroniccommunicationhandbook.ui.student.rate;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.electroniccommunicationhandbook.MainActivity;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Student_Class;
import com.example.electroniccommunicationhandbook.repository.StudentRepository;

import javax.security.auth.Destroyable;


public class RateDetailFragment extends Fragment {

    private Student_Class student_class;
    private TextView tv_name;
    private TextView tv_name_subject_rate;
    private TextView edt_comment;
    private AppCompatButton btn_rate_teacher;
    private AppCompatButton btn_rate_1;
    private AppCompatButton btn_rate_2;
    private AppCompatButton btn_rate_3;
    private AppCompatButton btn_rate_4;
    private AppCompatButton btn_rate_5;
    private StudentRepository studentRepository;
    private ImageView img_back;
    private static int RATE = 0;

    public RateDetailFragment() {
        studentRepository = new StudentRepository();

        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        student_class = new Student_Class();
        student_class = (Student_Class) getArguments().getSerializable("student_class_object");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rate_detail, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tv_name_subject_rate = view.findViewById(R.id.tv_name_subject_rate);
        tv_name = view.findViewById(R.id.tv_name);
        edt_comment = view.findViewById(R.id.edt_comment);
        btn_rate_teacher = view.findViewById(R.id.btn_rate_teacher);
        img_back = view.findViewById(R.id.img_back);
        btn_rate_1 = view.findViewById(R.id.btn_rate_1);
        btn_rate_2 = view.findViewById(R.id.btn_rate_2);
        btn_rate_3 = view.findViewById(R.id.btn_rate_3);
        btn_rate_4 = view.findViewById(R.id.btn_rate_4);
        btn_rate_5 = view.findViewById(R.id.btn_rate_5);

        tv_name_subject_rate.setText(student_class.get_class().getSubject().getName());
        tv_name.setText(student_class.get_class().getTeacher().getName());
        RATE = student_class.getRating();
        if (student_class.getComment() != null) edt_comment.setText(student_class.getComment());
        setRateButton();

//        img_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

        btn_rate_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RATE = 1;
                setRateButton();
            }
        });

        btn_rate_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RATE = 2;
                setRateButton();
            }
        });

        btn_rate_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RATE = 3;
                setRateButton();
            }
        });

        btn_rate_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RATE = 4;
                setRateButton();
            }
        });

        btn_rate_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RATE = 5;
                setRateButton();
            }
        });


        btn_rate_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = "";
                if (edt_comment.getText() != null)
                    comment = edt_comment.getText().toString();
                Student_Class st = new Student_Class(student_class.getStudentClassId(), RATE, comment);
                studentRepository.updateStudentClassById(st, student_class.getStudentClassId().getStudentId(), student_class.getStudentClassId().getClassId());
                /// studentRepository.updateStudentClass(student_class);
            }
        });
    }

    private void setRateButton() {

        if (RATE == 1) {
            btn_rate_1.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_rate));
            btn_rate_2.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_not_rate));
            btn_rate_3.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_not_rate));
            btn_rate_4.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_not_rate));
            btn_rate_5.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_not_rate));
        } else if (RATE == 2) {
            btn_rate_1.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_rate));
            btn_rate_2.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_rate));
            btn_rate_3.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_not_rate));
            btn_rate_4.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_not_rate));
            btn_rate_5.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_not_rate));
        } else if (RATE == 3) {
            btn_rate_1.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_rate));
            btn_rate_2.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_rate));
            btn_rate_3.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_rate));
            btn_rate_4.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_not_rate));
            btn_rate_5.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_not_rate));
        } else if (RATE == 4) {
            btn_rate_1.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_rate));
            btn_rate_2.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_rate));
            btn_rate_3.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_rate));
            btn_rate_4.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_rate));
            btn_rate_5.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_not_rate));
        } else if (RATE >= 5) {
            btn_rate_1.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_rate));
            btn_rate_2.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_rate));
            btn_rate_3.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_rate));
            btn_rate_4.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_rate));
            btn_rate_5.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_rate));
        } else if (RATE < 1) {
            btn_rate_1.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_not_rate));
            btn_rate_2.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_not_rate));
            btn_rate_3.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_not_rate));
            btn_rate_4.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_not_rate));
            btn_rate_5.setBackground(getActivity().getDrawable(R.drawable.ic_round_star_not_rate));
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onBackPressed() {
        startActivity(new Intent(getActivity(), RateTeacherActivity.class));
        onDestroy();
    }
}
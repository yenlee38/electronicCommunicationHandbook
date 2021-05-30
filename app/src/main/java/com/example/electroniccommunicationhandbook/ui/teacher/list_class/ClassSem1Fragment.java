package com.example.electroniccommunicationhandbook.ui.teacher.list_class;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.ViewModel.TeacherClassViewModel;
import com.example.electroniccommunicationhandbook.entity.Teacher;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;
import com.example.electroniccommunicationhandbook.entity.Class;

import java.util.ArrayList;

public class ClassSem1Fragment extends Fragment {
    TextView tvTotalClass, tvTotalStudent;
    RecyclerView rcvClasses;
    ArrayList<Class> listClass;
    TeacherClassViewModel teacherClassViewModel;
    Teacher teacher;

    public ClassSem1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class_semp1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       //
        tvTotalClass = view.findViewById(R.id.tv_class_total_sem1);
        tvTotalStudent = view.findViewById(R.id.tv_class_total_sem1);

        teacherClassViewModel= ViewModelProviders.of(this).get(TeacherClassViewModel.class);
        rcvClasses = view.findViewById(R.id.rcv_Classes);
        UserLocalStore userLocalStore = new UserLocalStore(getContext());
        teacher = userLocalStore.getTeacherLocal();
        Log.e("Teacher",teacher.getTeacherID());
//        //Subject
//        Subject subjectA = new Subject("BMW1456", "Bảo mật web", 3, "Hiểu cách hack, tự về your web site");
//        Subject subjectB = new Subject("BMW2dfr", "Bảo mật web", 3, "Hiểu cách hack, tự về your web site");
//        //Student :))
//        Student studentA = new Student("Student1", "Thao Le", Calendar.getInstance().getTime(), "Nu", "0333226399", "Cu Chi",
//                "thaole3010001@gmail.com", null, "VCM2434", null, "2000", "Công nghệ thông tin");
//        Student studentB = new Student("Student2", "Yen Le", Calendar.getInstance().getTime(), "Nam", "0333s76399", "Cu Chi",
//                "thaole3010002@gmail.com", null, "VCM23334", null, "2000", "Công nghệ thông tin");
//        Student studentC = new Student("Student3", "Thanh Le", Calendar.getInstance().getTime(), "Nam", "0332876399", "Cu Chi",
//                "thaole3010003@gmail.com", null, "VCM231", null, "2000", "Công nghệ thông tin");
//        Student studentD = new Student("Student4", "Hoang Le", Calendar.getInstance().getTime(), "Nu", "0333276392", "Vung Tau",
//                "thaole3010004@gmail.com", null, "VCM2343", null, "2000", "Công nghệ thông tin");
//        Student studentE = new Student("Student5", "Y Le", Calendar.getInstance().getTime(), "Nu", "0336872390", "An Nhon Tay ",
//                "thaole301000@5gmail.com", null, "VCM2341", null, "2000", "Công nghệ thông tin");
//
//        //Class
//        Class classA = new Class("Class001", subjectA, teacher, 1, 2020, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), 2, "A217", 0730, 1030,
//                null, null);
//        Class classB = new Class("Class002", subjectA, teacher, 2, 2019, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), 2, "A206", 0730, 1030,
//                null, null);
//        Class classC = new Class("Class003", subjectB, teacher, 1, 2020, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), 2, "A217", 0730, 1030,
//                null, null);
//        Class classD = new Class("Class004", subjectB, teacher, 1, 2020, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), 2, "A217", 0730, 1030,
//                null, null);
//
//        Student_Class student_classA1 = new Student_Class("Student1", "Class001",
//                3.4f, 7.87f, 0, "Giang vieen nhiet tinh", null,studentA);
//        Student_Class student_classA2 = new Student_Class("Student2", "Class001",
//                3.4f, 7.87f, 0, "Giang vieen nhiet tinh", null,studentB);
//        Student_Class student_classA3 = new Student_Class("Student3", "Class002",
//                3.4f, 7.87f, 0, "Giang vieen nhiet tinh", null,studentC);
//        Student_Class student_classA4 = new Student_Class("Student4", "Class002",
//                3.4f, 7.87f, 0, "Giang vieen nhiet tinh", null,studentD);
//        Student_Class student_classA5 = new Student_Class("Student5", "Class001",
//                3.4f, 7.87f, 0, "Giang vieen nhiet tinh", null,studentE);
//
//        Collection<Student_Class> students = new ArrayList<>();
//        students.add(student_classA1);
//        students.add(student_classA2);
//        students.add(student_classA3);
//        students.add(student_classA4);
//        students.add(student_classA5);
//
////        students.add(studentD);
////        students.add(studentC);
////        students.add(studentB);
////        students.add(studentA);
//        classA.setStudents(students);
//        classB.setStudents(students);
//        classC.setStudents(students);
//        classD.setStudents(students);
//
//        List<Class> listClass = new ArrayList<>();
//        listClass.add(classA);
//        listClass.add(classB);
//        listClass.add(classC);
//        listClass.add(classD);
        //confirmationRequestViewModel.
//        confirmationRequestViewModel.findAllConfirmation(student.getStudentId()).observe(getViewLifecycleOwner(),
//                new Observer<ArrayList<Student_ConfirmationPaper>>() {
//                    @Override
//                    public void onChanged(ArrayList<Student_ConfirmationPaper> student_confirmationPapers) {
//                        if(student_confirmationPapers != null){
//                            Log.e("onChange",student_confirmationPapers.toString());
//                            historiesRequest=student_confirmationPapers;
//                        }
//                    }
//                });
        teacherClassViewModel.findClassOfTeacher(teacher.getTeacherID()).observe(getViewLifecycleOwner(),
                new Observer<ArrayList<Class>>() {
                    @Override
                    public void onChanged(ArrayList<Class> classes) {
                        if(!classes.isEmpty()){
                            Log.e("Vo tam","jhjhdhjhjhd");
                            listClass= classes;
                            Log.e("Log class of teacher", listClass.toString());
                            ListClassAdapter adapter = new ListClassAdapter(listClass);
                            rcvClasses.setLayoutManager(new LinearLayoutManager(getContext()));
                            tvTotalClass.setText(String.valueOf(listClass.size()));

                            rcvClasses.setAdapter(adapter);
                        }
                    }
                });


    }
}
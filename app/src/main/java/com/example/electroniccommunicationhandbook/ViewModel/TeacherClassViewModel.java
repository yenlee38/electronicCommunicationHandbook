package com.example.electroniccommunicationhandbook.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.repository.MainRepository;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherClassViewModel extends AndroidViewModel {
    ArrayList<Class> dummy;
    ArrayList<Student> dummyStudent;
    MutableLiveData<ArrayList<Class>> listClass = new MutableLiveData<>();
    MutableLiveData<ArrayList<Student>> listStudent = new MutableLiveData<>();

    public TeacherClassViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<ArrayList<Class>> findClassOfTeacher(String teacherId, int semester, int year) {
        MainRepository.getInstance().getClassService().getClassOfTeacher(teacherId, semester, year).enqueue(
                new Callback<ArrayList<Class>>() {
                    private Call<Class> call;
                    private Throwable t;

                    @Override
                    public void onResponse(Call<ArrayList<Class>> call, Response<ArrayList<Class>> response) {
                        dummy = response.body();
                        listClass.postValue(dummy);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Class>> call, Throwable t) {
                    }
                }
        );
        return listClass;
    }

    public MutableLiveData<ArrayList<Student>> findStudentOfClass(String classId) {
        MainRepository.getInstance().getClassService().getStudentInClass(classId).enqueue(
                new Callback<ArrayList<Student>>() {
                    private Call<Student> call;
                    private Throwable t;

                    @Override
                    public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {
                        Log.e(String.valueOf(response.code()), response.message());
                        if (response.isSuccessful()) {
                            dummyStudent = response.body();
                            listStudent.postValue(dummyStudent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Student>> call, Throwable t) {
                    }
                }
        );
        return listStudent;
    }
}

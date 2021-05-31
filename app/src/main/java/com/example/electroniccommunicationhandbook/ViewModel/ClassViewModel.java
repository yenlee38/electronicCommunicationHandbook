package com.example.electroniccommunicationhandbook.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_Class;
import com.example.electroniccommunicationhandbook.repository.MainRepository;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassViewModel extends AndroidViewModel {
    MutableLiveData<ArrayList<Student>> students;
    ArrayList<Student> dummy;
    int mclassId;

    public ClassViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<ArrayList<Student>> findStudentInClass(String classId) {
        students = new MutableLiveData<>();
        MainRepository.getInstance().getClassService().getStudentInClass(classId).enqueue(
                new Callback<ArrayList<Student>>() {
                    private Call<Student> call;
                    private Throwable t;

                    @Override
                    public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {
                        dummy = response.body();
                        students.postValue(dummy);
                    }
                    @Override
                    public void onFailure(Call<ArrayList<Student>> call, Throwable t) {
                    }
                }
        );
        return students;
    }

    public MutableLiveData<ArrayList<Student>> getStudents() {
        return students;
    }
}

package com.example.electroniccommunicationhandbook.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_ConfirmationPaper;
import com.example.electroniccommunicationhandbook.repository.MainRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.electroniccommunicationhandbook.util.Comon.CODE_ERROR;
import static com.example.electroniccommunicationhandbook.util.Comon.CODE_SUCCESSFUL;

public class ConfirmationRequestViewModel extends AndroidViewModel {

    public int STUDENT_CONFIRMATION_PAPER = 26;
    public int SCORE_CERTIFICATE_PAPER = 27;

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    private int Code = CODE_SUCCESSFUL;
    ArrayList<Student_ConfirmationPaper> dummy;
    MutableLiveData<ArrayList<Student_ConfirmationPaper>> histories = new MutableLiveData<>();

    Student student;

    public ConfirmationRequestViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<ArrayList<Student_ConfirmationPaper>> findAllConfirmation(String StudentId) {
        dummy = new ArrayList<>();
        MainRepository.getInstance().getRequestPaperService().getConfirmationPapers(StudentId).enqueue(
                new Callback<ArrayList<Student_ConfirmationPaper>>() {
                    private Call<Student_ConfirmationPaper> call;
                    private Throwable t;

                    @Override
                    public void onResponse(Call<ArrayList<Student_ConfirmationPaper>> call, Response<ArrayList<Student_ConfirmationPaper>> response) {
                        Log.e(String.valueOf(response.code()), response.message());
                        if (response.isSuccessful()) {
                            dummy = response.body();
                            histories.postValue(dummy);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Student_ConfirmationPaper>> call, Throwable t) {
                        histories = null;
                    }
                }
        );
        return histories;
    }

    public int createConfirmation(Student_ConfirmationPaper student_confirmationPaper) {
        //final int code ;
        MainRepository.getInstance().getRequestPaperService().createNewConfirmation(student_confirmationPaper).enqueue(
                new Callback<Student_ConfirmationPaper>() {
                    @Override
                    public void onResponse(Call<Student_ConfirmationPaper> call, Response<Student_ConfirmationPaper> response) {
                        if (response.isSuccessful()) {
                            Code = CODE_SUCCESSFUL;
                        } else {
                            Code = CODE_ERROR;
                        }
                    }

                    @Override
                    public void onFailure(Call<Student_ConfirmationPaper> call, Throwable t) {
                        Code = CODE_ERROR;
                    }
                }
        );
        return Code;
    }
}

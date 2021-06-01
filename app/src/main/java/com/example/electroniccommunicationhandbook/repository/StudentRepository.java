package com.example.electroniccommunicationhandbook.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.electroniccommunicationhandbook.entity.Class;
import com.example.electroniccommunicationhandbook.entity.SchoolTime;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.service.StudentService;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudentRepository {
    private static final String BASE_URL = "https://apihandbookversion2.herokuapp.com/";

    private StudentService studentService;
    public Student student;
    private MutableLiveData<ArrayList<Class>> classResponseLiveData;
    public ArrayList<Class> lClass;
    public MutableLiveData<ArrayList<SchoolTime>> lSchoolTime;
    private static final int NUMBER_OF_THREADS = 4;

    public Student getStudent() {
        return student;
    }

    public ArrayList<Class> getlClass() {
        return lClass;
    }



    public static ExecutorService getDatabaseWriteExecutor() {
        return databaseWriteExecutor;
    }

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setClassResponseLiveData(MutableLiveData<ArrayList<Class>> classResponseLiveData) {
        this.classResponseLiveData = classResponseLiveData;
    }


    public static void setInstance(StudentRepository instance) {
        StudentRepository.instance = instance;
    }

    private static StudentRepository instance;

    public static StudentRepository getInstance(){
        if(instance== null){
            instance= new StudentRepository();
        }
        return instance;
    }

    public StudentRepository() {
        lClass = new ArrayList<Class>();
        lSchoolTime = new MutableLiveData<>();
        classResponseLiveData = new MutableLiveData<>();
        MainRepository mainRepository;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public okhttp3.Response intercept(@NotNull Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                        "Bearer " + MainRepository.getToken());

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        studentService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StudentService.class);
    }

    public void getInfo(String id) {

        studentService.getInfo(id).enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if(response.isSuccessful())
                {
                    student = response.body();
                    Log.e("ID :", student.getStudentId() );

                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                student= null;
                Log.e("Failure : ", t.toString() );
            }
        });
    }
   

    public MutableLiveData<ArrayList<SchoolTime>> setLSchoolTimeLiveData(){
        studentService.getListSchoolTime()
                .enqueue(new Callback<ArrayList<SchoolTime>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SchoolTime>> call, Response<ArrayList<SchoolTime>> response) {
                        if(response.isSuccessful()){ lSchoolTime.postValue(response.body());
                            Log.e("lSchoolTime", "hihi");}
                    }

                    @Override
                    public void onFailure(Call<ArrayList<SchoolTime>> call, Throwable t) {
                        lSchoolTime = null;
                        Log.e("Failure : ", t.toString() );
                    }
                });
        return lSchoolTime;

//        Call<List<SchoolTime>> callSync = studentService.getListSchoolTime();
//
//        try
//        {
//            Response<List<SchoolTime>> response = callSync.execute();
//            lSchoolTime = response.body();
//
//            //API response
//
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }

        //return lSchoolTime;
    }

    public void setClassForSchedule(String idStudent, int year, int semester){
        studentService.getSchedule(idStudent, year, semester)
                .enqueue(new Callback<ArrayList<Class>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Class>> call, Response<ArrayList<Class>> response) {
                        if(response.isSuccessful()){
                            lClass = response.body();
                            Log.e("lClass :", String.valueOf(lClass.size()));
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Class>> call, Throwable t) {
                        lClass = null;
                        Log.e("Failure class: ", t.toString() );
                    }
                });
//        return lClass;
//
//        Call<List<Class>> callSync = studentService.getSchedule(idStudent, year, semester);
//
//        try
//        {
//            Response<List<Class>> response = callSync.execute();
//            lClass = response.body();
////            Log.e("lClass :", String.valueOf(lClass.size()));
//            //API response
//
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//            Log.e("Failure class: ", ex.toString() );
//        }

       // return lClass;
    }

    public MutableLiveData<ArrayList<Class>> getSchedule(String idStudent, int year, int semester) {
        Log.e("vo livedata: ", "vo roi");
       studentService.getSchedule(idStudent, year, semester)
               .enqueue(new Callback<ArrayList<Class>>() {
                   @Override
                   public void onResponse(Call<ArrayList<Class>> call, Response<ArrayList<Class>> response) {
                       if(response.isSuccessful()){
                           classResponseLiveData.postValue(response.body());
                           Log.e("value: ", String.valueOf(response.body().size()));
                       }

                   }

                   @Override
                   public void onFailure(Call<ArrayList<Class>> call, Throwable t) {
                       classResponseLiveData.postValue(null);
                       Log.e("null livedata: ", "vo roi");
                   }


               });

       return classResponseLiveData;
    }



    public MutableLiveData<ArrayList<Class>> getClassResponseLiveData() {
        return classResponseLiveData;
    }
}

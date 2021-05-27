package com.example.electroniccommunicationhandbook.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_Class;
import com.example.electroniccommunicationhandbook.service.StudentClassService;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class PointRepository extends AndroidViewModel {

    private static final String BASE_URL = "https://api-spring-handbook.herokuapp.com/";
    //private static final String BASE_URL = "http://localhost:8080/";

    private StudentClassService studentClassService;
    private Student_Class point;
    private Student student;
    private ArrayList<Student_Class> a;
    private MutableLiveData<ArrayList<Student_Class>> studentClassList;
    private static PointRepository instance;

    public static PointRepository getInstance(Application application){
        if(instance== null){
            instance= new PointRepository(application);
        }
        return instance;
    }

    public PointRepository(Application app) {
        super(app);
        a = new ArrayList<>();
        a.add(new Student_Class());
        studentClassList= new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public okhttp3.Response intercept(@NotNull Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                       "Bearer " +MainRepository.getToken());

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        studentClassService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StudentClassService.class);
    }


    public Student_Class findPointByStudentAndClassID(String studentId, String classId){
        studentClassService.findPointByClassAndStudentID(studentId,classId).enqueue(new Callback<Student_Class>() {
            @Override
            public void onResponse(Call<Student_Class> call, Response<Student_Class> response) {
                if(response.isSuccessful()){
                  point = response.body();
                }
            }

            @Override
            public void onFailure(Call<Student_Class> call, Throwable t) {
                point= null;
            }
        });
        return  point;
    }

    public MutableLiveData<ArrayList<Student_Class>> findPointEverySemester(String studentId, int year, int semester){

        studentClassService.findByStudentIdAndYearAndSemester(studentId, year,semester).enqueue(new Callback<ArrayList<Student_Class>>() {
            @Override
            public void onResponse(Call<ArrayList<Student_Class>> call, Response<ArrayList<Student_Class>> response) {
                if(response.isSuccessful()){
                    a= response.body();
                    studentClassList.postValue( a);
                   // Log.e("XOng",""+ studentClassList.getValue().size());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Student_Class>> call, Throwable t) {
                studentClassList= null;
                Log.e("Failure : ", t.toString() );
            }
        });
      //  Log.e("XOng",""+ studentClassList.getValue().size());
        return studentClassList;
    }

    public MutableLiveData<ArrayList<Student_Class>> getStudentClassList(){return studentClassList;}
}

package com.example.electroniccommunicationhandbook.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.electroniccommunicationhandbook.entity.Class;
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

    private static final String BASE_URL = "https://apihandbookversion2.herokuapp.com/";


    private StudentClassService studentClassService;
    private Student_Class point;
    private Student student;
    private ArrayList<Student_Class> a;
    private MutableLiveData<ArrayList<Student_Class>> studentClassList;
    private MutableLiveData<ArrayList<Class>> classList;


    private MutableLiveData<ArrayList<Student_Class>> allClass;
    private MutableLiveData<ArrayList<Class>> allClassOfTeacher;
    private MutableLiveData<ArrayList<Student_Class>> allStudentClassOfTeacher;


    private static PointRepository instance;

    public static PointRepository getInstance(Application application){
        if(instance== null){
            instance= new PointRepository(application);
        }
        return instance;
    }

    public PointRepository(Application app) {
        super(app);
      //  a = new ArrayList<>();
        //a.add(new Student_Class());
        studentClassList= new MutableLiveData<>();
        allClass = new MutableLiveData<>();
        allClassOfTeacher = new MutableLiveData<>();
        allStudentClassOfTeacher = new MutableLiveData<>();

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

        //findByStudentIdAndYearAndSemester(studentId, year,semester)
            studentClassService.findByStudentIdAndYearAndSemester(studentId,year,semester).enqueue(new Callback<ArrayList<Student_Class>>() {
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

    private ArrayList<Class> b;

    public MutableLiveData<ArrayList<Student_Class>> getStudentClassList(){return studentClassList;}

    public MutableLiveData<ArrayList<Student_Class>> findAllLiveData(){
        studentClassService.findAll().enqueue(new Callback<ArrayList<Student_Class>>() {
            @Override
            public void onResponse(Call<ArrayList<Student_Class>> call, Response<ArrayList<Student_Class>> response) {
                if(response.isSuccessful()){
                    ArrayList<Student_Class> t = response.body();
                    //Log.e("Xong", "t :" + t.toString());
                    allClass.postValue(t);
                    //Log.e("Xong", "all Class :" + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Student_Class>> call, Throwable t) {
                allClass= null;
                Log.e("Failure : ", t.toString() );
            }
        });
        Log.e("Xong", "Get class");
        return allClass;
    }

    public MutableLiveData<ArrayList<Class>> findAllClassOfTeacher(String teacherId) {
        studentClassService.findClassByIdTeacher(teacherId).enqueue(new Callback<ArrayList<Class>>() {
            @Override
            public void onResponse(Call<ArrayList<Class>> call, Response<ArrayList<Class>> response) {
                ArrayList<Class> temp = response.body();
                allClassOfTeacher.postValue(temp);
                Log.e("Test", "----------- findAllClassOfTeacher - top -----------");
                Log.e("Test All Class of teacher", String.valueOf(temp.size()));
            }

            @Override
            public void onFailure(Call<ArrayList<Class>> call, Throwable t) {
                allClassOfTeacher = null;
            }
        });

        return allClassOfTeacher;
    }

    public MutableLiveData<ArrayList<Student_Class>> findAllStudentOfTeacherInASemester(String teacherId, int year, int semester) {
        studentClassService.findStudentClassByIdTeacherAndYearAndSemester(teacherId, year, semester).enqueue(new Callback<ArrayList<Student_Class>>() {
            @Override
            public void onResponse(Call<ArrayList<Student_Class>> call, Response<ArrayList<Student_Class>> response) {
                ArrayList<Student_Class> temp = response.body();
                allStudentClassOfTeacher.postValue(temp);
                Log.e("Test", "----------- findAllClassOfTeacher - bottom -----------");
                Log.e("Test Teacher Statistic", String.valueOf(temp.size()));
            }

            @Override
            public void onFailure(Call<ArrayList<Student_Class>> call, Throwable t) {
                allStudentClassOfTeacher = null;
            }
        });

        return allStudentClassOfTeacher;
    }
}

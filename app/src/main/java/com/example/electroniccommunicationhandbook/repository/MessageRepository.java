package com.example.electroniccommunicationhandbook.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.electroniccommunicationhandbook.entity.Message;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Teacher;
import com.example.electroniccommunicationhandbook.service.MessageService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageRepository {
    private MessageService messageService;
    private MutableLiveData<ArrayList<Message>> messageList;
    private MutableLiveData<ArrayList<Student>> studentList;
    private MutableLiveData<ArrayList<Teacher>> teacherLiveDataList;
    private static MessageRepository instance;
    public String studentName;
    ArrayList<Student> studentArrayList;
    private Student student;
    private Message message;

    public MessageRepository(){
        messageList= new MutableLiveData<>();
        studentList = new MutableLiveData<>();
        teacherLiveDataList= new MutableLiveData<>();

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

        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson gson = gsonBuilder
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setPrettyPrinting()
                .setLenient()
                .create();

        messageService = new retrofit2.Retrofit.Builder()
                .baseUrl("https://apihandbookversion2.herokuapp.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(MessageService.class);
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public static MessageRepository getInstance(){
        if(instance== null){
            instance= new MessageRepository();
        }
        return instance;
    }

    /*
    get all message by person
     */
    public MutableLiveData<ArrayList<Message>> getAllMessageByAccountID(int accountID){
        messageService.getAllMessageByAccountId(accountID).enqueue(new Callback<ArrayList<Message>>() {
            @Override
            public void onResponse(Call<ArrayList<Message>> call, Response<ArrayList<Message>> response) {
                if(response.isSuccessful()){
                    messageList.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Message>> call, Throwable t) {
                messageList=null;
                Log.e("Mess", "Get all message: fail");
            }
        });
        return messageList;
    }

    /*
    get all message _ 2
     */
    public MutableLiveData<ArrayList<Message>> getMessagesBetweenUsers(int userAccountId1, int userAccountId2){
        messageService.getMessagesBetweenUser(userAccountId1, userAccountId1).enqueue(new Callback<ArrayList<Message>>() {
            @Override
            public void onResponse(Call<ArrayList<Message>> call, Response<ArrayList<Message>> response) {
                if(response.isSuccessful()){
                    messageList.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Message>> call, Throwable t) {
                messageList=null;
                Log.e("Mess", "Get list is fail");
            }
        });
        return messageList;
    }



    /*
    get students whose name is similar with specified name
     */
    public MutableLiveData<ArrayList<Student>> getAllStudentOfTeacherWithSimilarName(String teacherID, String studentName){
        messageService.getAllStudentOfTeacherWithSimilarName(teacherID, studentName)
                .enqueue(new Callback<ArrayList<Student>>() {
            @Override
            public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {
                if(response.isSuccessful()){
                    studentList.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Student>> call, Throwable t) {
                studentList=null;
                Log.e("Mess", "Get list is fail");
            }
        });
        return studentList;
    }

    /*
    get teahers whose name is similar with specified name
     */
    public MutableLiveData<ArrayList<Teacher>> getAllTeacherOfStudentWithSimilarName(String studentID, String teacherName){
        messageService.getAllTeacherOfStudentWithSimilarName(studentID, teacherName)
                .enqueue(new Callback<ArrayList<Teacher>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Teacher>> call, Response<ArrayList<Teacher>> response) {
                        if(response.isSuccessful()){
                            teacherLiveDataList.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Teacher>> call, Throwable t) {
                        teacherLiveDataList=null;
                        Log.e("search","Search teacher is fail");
                    }
                });
        return  teacherLiveDataList;
    }

    /*
    create a message
     */
    public void createMessage(Message mess){
        messageService.createMessage(mess).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Message message1 = new Message();
                message1 = response.body();
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.e("Mess", "Get all message: fail");
            }
        });
    }

    /*
    get student's name by account id
     */
    public String getStudentNameByAccountId( int accountid){
       studentArrayList = new ArrayList<Student>();
       studentName = "";
       messageService.getAllStudent()
             .enqueue(new Callback<ArrayList<Student>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {
                        if(response.isSuccessful()){
                            studentArrayList.addAll(response.body());
                            for (int i=0; i<studentArrayList.size(); i++){
                                if (studentArrayList.get(i).getAccount().getAccountID()==accountid){
                                    studentName = studentArrayList.get(i).getName();
                                    break;
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Student>> call, Throwable t) {
                        studentList=null;
                        Log.e("Mess", "Get list is fail");
                    }
                });
        return studentName;
    }

    /*
   get all student
    */
    public MutableLiveData<ArrayList<Student>>getAllStudents(){
        studentList = new MutableLiveData<ArrayList<Student>>();
        messageService.getAllStudent()
                .enqueue(new Callback<ArrayList<Student>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {
                        if(response.isSuccessful()){
                            studentList.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Student>> call, Throwable t) {
                        studentList=null;
                        Log.e("Mess", "Get list is fail");
                    }
                });
        return studentList;
    }

    /*
get all student
 */
    public ArrayList<Student> getAllArrayStudents(){
        studentArrayList = new ArrayList<Student>();
        messageService.getAllStudent()
                .enqueue(new Callback<ArrayList<Student>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {
                        if(response.isSuccessful()){
                            studentArrayList=response.body();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Student>> call, Throwable t) {
                        studentArrayList=null;
                        Log.e("Mess", "Get list is fail");
                    }
                });
        return studentArrayList;
    }

    /*
    get a student by account id
     */
    public Student getStudentByAccountID(Context context, int accountID){
       studentArrayList= new ArrayList<>();
        Student student=null;
        MutableLiveData<ArrayList<Student>> listMutableLiveData = new MutableLiveData<>();
        listMutableLiveData = getAllStudents();
        listMutableLiveData.observe((LifecycleOwner) context, new Observer<ArrayList<Student>>() {
            @Override
            public void onChanged(ArrayList<Student> students) {
                studentArrayList = students;
            }
        });

        for(int i=0; i<studentArrayList.size(); i++) {
            if (studentArrayList.get(i).getAccount().getAccountID() == accountID) {
                student = studentArrayList.get(i);
                break;
            }
        }
        return student;
//        messageService.getAllStudent().enqueue(new Callback<ArrayList<Student>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {
//                studentArrayList.addAll( response.body());
//                Log.e("arrr", ""+studentArrayList.size());
//                for(int i=0; i<studentArrayList.size(); i++){
//                    if(studentArrayList.get(i).getAccount().getAccountID()==accountID){
//                        student[0] = studentArrayList.get(i);
//                        break;
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Student>> call, Throwable t) {
//
//            }
//        });
    }

    /*
    get
     */
    public Student getStudentByAccountID(int accountid){
        student = new Student();
        messageService.getStudentByAccountId(accountid).enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if(response.isSuccessful()){
                    student = response.body();
                    Log.e("stu", student.getStudentId());
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                student= null;
            }
        });
        return student;
    }
}

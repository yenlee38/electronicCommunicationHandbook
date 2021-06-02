package com.example.electroniccommunicationhandbook.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.electroniccommunicationhandbook.entity.FeeInfor;
import com.example.electroniccommunicationhandbook.entity.FeeperCredit;
import com.example.electroniccommunicationhandbook.entity.Student_Class;
import com.example.electroniccommunicationhandbook.service.FeeService;
import com.example.electroniccommunicationhandbook.service.StudentClassService;
import com.example.electroniccommunicationhandbook.util.Comon;

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

public class FeeRepository extends AndroidViewModel {
    private static final String BASE_URL = Comon.API_LINK;

    private static FeeRepository instance;
    private FeeService feeService;
    private StudentClassService studentClassService;
    private MutableLiveData<ArrayList<FeeInfor>> feeInforMutableLiveData;

    private ArrayList<Student_Class> arrayList;
    private FeeperCredit feeperCredits;
    public static FeeRepository getInstance(Application application){
        if(instance== null){
            instance= new FeeRepository(application);
        }
        return instance;
    }

    public FeeRepository( Application application) {

        super(application);
        feeInforMutableLiveData= new MutableLiveData<>();
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

         feeService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FeeService.class);

        studentClassService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StudentClassService.class);
    }

    public void getFeeInfor(String studentId,int year,int semester  ){
        arrayList= new ArrayList<>();

        studentClassService.findByStudentIdAndYearAndSemester(studentId,year,semester).enqueue(new Callback<ArrayList<Student_Class>>() {
            @Override
            public void onResponse(Call<ArrayList<Student_Class>> call, Response<ArrayList<Student_Class>> response) {
                if(response.isSuccessful()){
                    arrayList= response.body();
                    if(arrayList!=null){

                        feeService.findByYear(year).enqueue(new Callback<FeeperCredit>() {
                            @Override
                            public void onResponse(Call<FeeperCredit> call, Response<FeeperCredit> response) {
                                if(response.isSuccessful()){
                                    feeperCredits= response.body();
                                    ArrayList<FeeInfor> feeInfors = new ArrayList<>();
                                    if(arrayList!=null && arrayList.size()>0 && feeperCredits!=null){
                                        for (Student_Class a:arrayList) {
                                            FeeInfor feeInfor= new FeeInfor(a.get_class().getSubject().getName(),a.get_class().getSubject().getNumberOfCredit(),a.get_class().getSubject().getNumberOfCredit()*feeperCredits.getFee());
                                            feeInfors.add(feeInfor);
                                        }
                                        feeInforMutableLiveData.postValue(feeInfors);
                                    }
                                    else {
                                        feeInforMutableLiveData.postValue(null);
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<FeeperCredit> call, Throwable t) {

                            }
                        });
                    }
                    // Log.e("XOng",""+ studentClassList.getValue().size());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Student_Class>> call, Throwable t) {
                feeInforMutableLiveData=null;
                Log.e("Failure : ", t.toString() );
            }
        });




    }

    public MutableLiveData<ArrayList<FeeInfor>> getFeeInforMutableLiveData() {
        return feeInforMutableLiveData;
    }
}

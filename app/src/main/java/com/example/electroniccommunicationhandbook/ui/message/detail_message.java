package com.example.electroniccommunicationhandbook.ui.message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.database.RoomDB;
import com.example.electroniccommunicationhandbook.entity.Account;
import com.example.electroniccommunicationhandbook.entity.Message;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Teacher;
import com.example.electroniccommunicationhandbook.repository.MessageRepository;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class detail_message extends AppCompatActivity {
    private RecyclerView recycMessage;
    private ShapeableImageView userImg;
    private TextView tvUserName;
    private EditText tvMessage;
    private Button btnBack;
    private Button btnSendMessage;
    private MutableLiveData<ArrayList<Message>> messageList;
    private ArrayList<Message> messageArrayList;
    private DetailMessageAdapter adapter;

    private UserLocalStore user;
    private MessageRepository messageRepository;
    private Account currentAccount;
    private int otherAccountId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_message);

        //get current user
        user = new UserLocalStore(getApplicationContext());
        int role = user.getRoleLocal();

        messageRepository = MessageRepository.getInstance();

        switch (role) {
            case 1://teacher
                currentAccount = user.getTeacherLocal().getAccount();
                break;
            case 2: //student
                currentAccount = user.getStudentLocal().getAccount();
                break;
            case 3: //parent
                currentAccount = user.getParentLocal().getAccount();
                break;
        }

        recycMessage= (RecyclerView)findViewById(R.id.recycDetailMessage);
        userImg = (ShapeableImageView)findViewById(R.id.imgViewPersonalImage_detailMessage);
        tvUserName = (TextView)findViewById(R.id.tvName_detail_messsage);
        tvMessage = (EditText)findViewById(R.id.tvMessage_detailMessage);

        //when click back button
        btnBack = (Button)findViewById(R.id.btnBack_detailMessage);
        btnBack.setOnClickListener((View v)->{
            Intent intent = new Intent(getApplicationContext(), MainMessage.class);
            startActivity(intent);
        });

        //send message
        btnSendMessage = findViewById(R.id.btnSendMessage);
        btnSendMessage.setOnClickListener((View view)->{
            sendMessage();
        });

        Bundle extras = getIntent().getExtras();

        if(extras!=null){
            tvUserName.setText(""+ extras.getString("otherName"));
            otherAccountId = extras.getInt("otherAccountID");
            Log.e("otherId", "otherID: "+ String.valueOf(otherAccountId));
        }

        //set user name
        tvUserName.setText("");
        setStudentName(otherAccountId, tvUserName);

        if(tvUserName.getText().toString().isEmpty()){
            setTeacherName(otherAccountId, tvUserName);
        }

        messageArrayList = new ArrayList<>();
//        messageList = new MutableLiveData<>();
//        messageList = messageRepository.getMessagesBetweenUsers(currentAccount.getAccountID(), otherAccountId);
//
//        messageList.observe(this, new Observer<ArrayList<Message>>() {
//            @Override
//            public void onChanged(ArrayList<Message> messages) {
//                if (messages != null) {
//                    messageArrayList = messages;
//                    messageArrayList=sortIncreasingMessageList(messages);
//                    adapter = new DetailMessageAdapter(messageArrayList, currentAccount.getAccountID());
////                    adapter.setMessageList(messageArrayList);
//
//                    recycMessage.setAdapter(adapter);
//                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
//                    recycMessage.setLayoutManager(linearLayoutManager);
//                    recycMessage.scrollToPosition(messageArrayList.size() - 1);
//                }
//            }
//        });
        Call<ArrayList<Message>> call = messageRepository.getMessageService()
                .getMessagesBetweenUser(currentAccount.getAccountID(), otherAccountId);
        call.enqueue(new Callback<ArrayList<Message>>() {
            @Override
            public void onResponse(Call<ArrayList<Message>> call, Response<ArrayList<Message>> response) {
                messageArrayList = response.body();
                messageArrayList= sortIncreasingMessageList(messageArrayList);
                    adapter = new DetailMessageAdapter(messageArrayList, currentAccount.getAccountID());
//                    adapter.setMessageList(messageArrayList);

                    recycMessage.setAdapter(adapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recycMessage.setLayoutManager(linearLayoutManager);
                    recycMessage.scrollToPosition(messageArrayList.size() - 1);
            }

            @Override
            public void onFailure(Call<ArrayList<Message>> call, Throwable t) {

            }
        });
//        loadMessage();
    }

    /*
    func to load messages
     */
    private void loadMessage(){
        messageArrayList= new ArrayList<>();
        Call<ArrayList<Message>> call = messageRepository.getMessageService()
                .getMessagesBetweenUser(currentAccount.getAccountID(), otherAccountId);
        call.enqueue(new Callback<ArrayList<Message>>() {
            @Override
            public void onResponse(Call<ArrayList<Message>> call, Response<ArrayList<Message>> response) {
                messageArrayList = response.body();
                messageArrayList= sortIncreasingMessageList(messageArrayList);
                adapter = new DetailMessageAdapter(messageArrayList, currentAccount.getAccountID());
//                    adapter.setMessageList(messageArrayList);

                recycMessage.setAdapter(adapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                recycMessage.setLayoutManager(linearLayoutManager);
                recycMessage.scrollToPosition(messageArrayList.size() - 1);
            }

            @Override
            public void onFailure(Call<ArrayList<Message>> call, Throwable t) {

            }
        });
    }

    /*
   get student's name
    */
    public void setStudentName(int accountid, TextView tvName){
        MutableLiveData<ArrayList<Student>> studentMultableList = messageRepository.getAllStudents();

        studentMultableList.observeForever( new Observer<ArrayList<Student>>() {
            @Override
            public void onChanged(ArrayList<Student> stuList) {
                if(stuList !=null){
                    ArrayList<Student> stdList = new ArrayList<>();
                    stdList=stuList;
                    for (int i=0; i<stdList.size(); i++){
                        if(stdList.get(i).getAccount().getAccountID()==accountid){
                            tvName.setText(stdList.get(i).getName());
                            break;
                        }
                    }
                }
            }
        });
    }

    /*
  get teacher's name
   */
    public void setTeacherName(int accountid, TextView tvName) {
        Call<ArrayList<Teacher>> call = messageRepository.getMessageService().getAllTeacher();
        call.enqueue(new Callback<ArrayList<Teacher>>() {
            @Override
            public void onResponse(Call<ArrayList<Teacher>> call, Response<ArrayList<Teacher>> response) {
                ArrayList<Teacher> teacherArrayList = new ArrayList<>();
                teacherArrayList=response.body();
                for (int i = 0; i < teacherArrayList.size(); i++) {
                    if (teacherArrayList.get(i).getAccount().getAccountID() == accountid) {
                        tvName.setText(teacherArrayList.get(i).getName());
                        break;
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Teacher>> call, Throwable t) {

            }
        });
    }

    /*
   func to send a messagemessage
    */
    public void sendMessage(){
        String text = this.tvMessage.getText().toString().trim();
        if (text == null || text.isEmpty() == true) {
            loadMessage();
        }

        if(text!=null && text.isEmpty()==false){
            Call<Account> accountCall = messageRepository.getMessageService().getAccountById(otherAccountId);
            accountCall.enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    Account account = response.body();
                    Message message = new Message(text, account, currentAccount);
                    messageRepository.createMessage(message);
//
//                    Call<Message> messcall= messageRepository.getMessageService()
//                            .createMessage(message);
//                    try{
//                        Response<Message> response1 = messcall.execute();
//                        Message mesage = response1.body();
////                        loadMessage();
//
//                    }
//                    catch(Exception ex){
//                        Log.e("ex", ex.getMessage());
//                    }

                    tvMessage.setText("");
                    loadMessage();
                }

                @Override
                public void onFailure(Call<Account> call, Throwable t) {

                }
            });
        }
    }

    /*
    arrange
     */
    public ArrayList<Message> sortIncreasingMessageList(ArrayList<Message> messsages){
        if(messageArrayList==null)
            return new ArrayList<>();

        for(int i=0; i<messsages.size()-1; i++) {
            int min = i;
            for (int j = i + 1; j < messsages.size(); j++) {
                if (messsages.get(j).getSentTime().before(messsages.get(min).getSentTime())) {
                    min = j;
                }
            }
            Collections.swap(messsages, i, min);
        }
        return messsages;
    }
}


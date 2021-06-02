package com.example.electroniccommunicationhandbook.ui.message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;


import com.example.electroniccommunicationhandbook.MainActivity;
import com.example.electroniccommunicationhandbook.MainActivity_teacher;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.database.RoomDB;
import com.example.electroniccommunicationhandbook.entity.Account;
import com.example.electroniccommunicationhandbook.entity.Message;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Teacher;
import com.example.electroniccommunicationhandbook.repository.MessageRepository;
import com.example.electroniccommunicationhandbook.service.MessageService;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.FormatFlagsConversionMismatchException;
import java.util.List;

import com.example.electroniccommunicationhandbook.entity.Class;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainMessage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MainMessageAdapter messageAdapter;
    private TextInputEditText tvSearch;
    private Account currentAccount;
    private MessageRepository messageRepository;
    private Button btnBackMessage;
    ArrayList<Message> messageArrayList;
    MutableLiveData<ArrayList<Message>> messageList;
    ArrayList<Student> studentArrayList;
    MutableLiveData<ArrayList<Student>> studentLiveData;
    ArrayList<Teacher> teacherArrayList;
    MutableLiveData<ArrayList<Teacher >> teachereLiveData;
    private MainMessageAdapter.recyclerViewClickListener clickListener;
    UserLocalStore user;


    private MessageService messageService;

    int role;

//    //set user (for testing func)
//    private Teacher user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_message);

        //get current user
        user = new UserLocalStore(getApplicationContext());
        role = user.getRoleLocal();

        messageRepository = MessageRepository.getInstance();

        switch (role) {
            case 1://teacher
                currentAccount = user.getTeacherLocal().getAccount();
//                user.getTeacherLocal().setAddress();
                break;
            case 2: //student
                currentAccount = user.getStudentLocal().getAccount();
                break;
            case 3: //parent
                currentAccount = user.getParentLocal().getAccount();
                break;
        }

        //back to main form
        btnBackMessage = findViewById(R.id.btnBack_mainMessage);
        btnBackMessage.setOnClickListener((View view) -> {
            Intent intent ;
            if(role==1){
                intent= new Intent(getApplicationContext(), MainActivity_teacher.class);
            }else {
                intent = new Intent(getApplicationContext(), MainActivity.class);
            }
            startActivity(intent);
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycleMessageList);

        messageArrayList = new ArrayList<>();

        messageList = new MutableLiveData<>();
        messageList = messageRepository.getAllMessageByAccountID(currentAccount.getAccountID());

        messageList.observe(this, new Observer<ArrayList<Message>>() {
            @Override
            public void onChanged(ArrayList<Message> messages) {
                if (messages != null) {
                    messageArrayList = getLatestMessage(messages);
                    messageArrayList = sortDescreasingMessageList(messageArrayList);
                    setOnClickListener(messageArrayList);
                    messageAdapter = new MainMessageAdapter(messageArrayList, null, null, clickListener,
                            getApplicationContext(), messageRepository, currentAccount);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

                    recyclerView.setAdapter(messageAdapter);
                    recyclerView.setLayoutManager(linearLayoutManager);

                }
            }
        });

//        //when the text in tvsearch changing
        tvSearch = (TextInputEditText) findViewById(R.id.tvSearch_Message);
        tvSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchingTextChange();
            }
        });

        //when focus searching tv
        tvSearch.setOnFocusChangeListener((View v, boolean hasFocus) -> {
            if (!hasFocus && tvSearch.getText().toString().trim().isEmpty()) {
                loadRecentMessage();
            }

            //list all user
            if(hasFocus && tvSearch.getText().toString().trim().isEmpty()){
                searchingTextChange();
            }
        });
    }

    /*
    load recent message
     */
    void loadRecentMessage() {
        messageList = messageRepository.getAllMessageByAccountID(currentAccount.getAccountID());

        messageList.observe(this, new Observer<ArrayList<Message>>() {
            @Override
            public void onChanged(ArrayList<Message> messages) {
                if (messages != null) {
                    messageArrayList = getLatestMessage(messages);
                    messageArrayList = sortDescreasingMessageList(messageArrayList);
                    messageAdapter = new MainMessageAdapter(messageArrayList, null, null, clickListener,
                            getApplicationContext(), messageRepository, currentAccount);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

                    recyclerView.setAdapter(messageAdapter);
                    recyclerView.setLayoutManager(linearLayoutManager);
                }
            }
        });
    }

    /*
when click an item in recyclerView
 */
    private void setOnClickListener(ArrayList<Message> messageList) {
        clickListener = new MainMessageAdapter.recyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), detail_message.class);
//                intent.putExtra("myName", user.getName());
//                intent.putExtra("myAccountID", user.getAccountID());
                //find other id
                int otherid = -1;
                if (messageList.get(position).getSenderAccount().getAccountID() != currentAccount.getAccountID()) {
                    otherid = messageList.get(position).getSenderAccount().getAccountID();
                } else {
                    otherid = messageList.get(position).getReceiverAccount().getAccountID();
                }
                intent.putExtra("otherAccountID", otherid);
                startActivity(intent);
            }
        };
    }

    /*
when click an item in recyclerView
*/
    private void setOnClickListenerStudentForSearching(ArrayList<Student> studentList) {
        clickListener = new MainMessageAdapter.recyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), detail_message.class);
//                intent.putExtra("myName", user.getName());
                intent.putExtra("otherName", studentList.get(position).getName());
                intent.putExtra("otherAccountID", studentList.get(position).getAccount().getAccountID());
                startActivity(intent);
            }
        };
    }

    /*
when click an item in recyclerView
*/
    private void setOnClickListenerTeacherForSearching(ArrayList<Teacher> teacherList) {
        clickListener = new MainMessageAdapter.recyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), detail_message.class);
//                intent.putExtra("myName", user.getName());
                intent.putExtra("otherName", teacherList.get(position).getName());
                intent.putExtra("otherAccountID", teacherList.get(position).getAccount().getAccountID());
                startActivity(intent);
            }
        };
    }

    /*
    The func will be to get messages sent latest
     */
    public ArrayList<Message> getLatestMessage(ArrayList<Message> originMessageList) {
        if (originMessageList.size() == 0) {
            return originMessageList;
        }

        ArrayList<Message> resultList = new ArrayList<>();

        //accountids are checked
        List<Integer> checkedList = new ArrayList<>();

        for (int i = 0; i < originMessageList.size() - 1; i++) {
            Message mess = originMessageList.get(i);

            //user checked
            if (checkedList.contains(mess.getReceiverAccount().getAccountID()) == true ||
                    checkedList.contains(mess.getSenderAccount().getAccountID()) == true) {
                continue;
            }

            //add accountid is checked
            int specifiedAccountID = -1;

            if (mess.getReceiverAccount().getAccountID() != currentAccount.getAccountID()) {
                checkedList.add(mess.getReceiverAccount().getAccountID());
                specifiedAccountID = mess.getReceiverAccount().getAccountID();
            } else {
                checkedList.add(mess.getSenderAccount().getAccountID());
                specifiedAccountID = mess.getSenderAccount().getAccountID();
            }

            for (int j = i + 1; j < originMessageList.size(); j++) {
                if ((originMessageList.get(j).getReceiverAccount().getAccountID() == specifiedAccountID
                        || originMessageList.get(j).getSenderAccount().getAccountID() == specifiedAccountID)
                        && originMessageList.get(j).getSentTime().after(mess.getSentTime()))
                    mess = originMessageList.get(j);
            }
            resultList.add(mess);
        }

        //check the last message
        if (checkedList.contains(originMessageList.get(originMessageList.size() - 1).getReceiverAccount().getAccountID()) == false
                && checkedList.contains(originMessageList.get(originMessageList.size() - 1).getSenderAccount().getAccountID()) == false) {
            resultList.add(originMessageList.get(originMessageList.size() - 1));
        }

        return resultList;
    }

    /*
    process the event when text to search is changed
     */
    public void searchingTextChange() {
        String name = tvSearch.getText().toString().trim();

        //used to list all user
        if(name==null || name.isEmpty()){
            name =" ";
        }

        //user is a teacher
        if (role == 1) {
            //show searching result, get student in teacher's class
            studentArrayList = new ArrayList<>();
            studentLiveData = new MutableLiveData<>();
            studentLiveData = messageRepository.getAllStudentOfTeacherWithSimilarName(user.getTeacherLocal().getTeacherID(),
                    name);

            studentLiveData.observe(this, new Observer<ArrayList<Student>>() {
                @Override
                public void onChanged(ArrayList<Student> students) {
                    if (students != null) {
                        studentArrayList = (ArrayList<Student>) students;
                        setOnClickListenerStudentForSearching(studentArrayList);
                        messageAdapter = new MainMessageAdapter(null, studentArrayList,null,
                                clickListener, getApplicationContext(), messageRepository, currentAccount);

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

                        recyclerView.setAdapter(messageAdapter);
                        recyclerView.setLayoutManager(linearLayoutManager);
                    }
                }
            });
        }
        else if(role==2){//student
            teacherArrayList = new ArrayList<>();
            teachereLiveData = new MutableLiveData<>();
            teachereLiveData=messageRepository.getAllTeacherOfStudentWithSimilarName(
                    user.getStudentLocal().getStudentId(), name);
            teachereLiveData.observe(this, new Observer<ArrayList<Teacher>>() {
                @Override
                public void onChanged(ArrayList<Teacher> teachers) {
                    if(teachers!=null){
                        teacherArrayList = teachers;
                        setOnClickListenerTeacherForSearching(teacherArrayList);
                        messageAdapter = new MainMessageAdapter(null, null,teacherArrayList,
                                clickListener, getApplicationContext(), messageRepository, currentAccount);

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

                        recyclerView.setAdapter(messageAdapter);
                        recyclerView.setLayoutManager(linearLayoutManager);
                    }
                }
            });
        }
    }

    /*
    arrange
     */
    public ArrayList<Message> sortDescreasingMessageList(ArrayList<Message> messsages){
        for(int i=0; i<messsages.size()-1; i++) {
            int max = i;
            for (int j = i + 1; j < messsages.size(); j++) {
                if (messsages.get(j).getSentTime().after(messsages.get(max).getSentTime())) {
                    max = j;
                }
            }
            Collections.swap(messsages, i, max);
        }
        return messsages;
    }
}
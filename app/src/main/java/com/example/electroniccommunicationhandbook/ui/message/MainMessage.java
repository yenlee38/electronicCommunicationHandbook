package com.example.electroniccommunicationhandbook.ui.message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.database.RoomDB;
import com.example.electroniccommunicationhandbook.entity.Account;
import com.example.electroniccommunicationhandbook.entity.Message;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Teacher;
import com.example.electroniccommunicationhandbook.repository.MessageRepository;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.FormatFlagsConversionMismatchException;
import java.util.List;
import com.example.electroniccommunicationhandbook.entity.Class;

public class MainMessage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MainMessageAdapter messageAdapter;
    private TextInputEditText tvSearch;
    private Account currentAccount;
    private MessageRepository messageRepository;
    ArrayList<Message> messageArrayList;
    MutableLiveData<ArrayList<Message>> messageList;

//    //set user (for testing func)
//    private Teacher user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_message);

        //get current user
        UserLocalStore user = new UserLocalStore(getApplicationContext());
        int role = user.getRoleLocal();

        messageRepository = MessageRepository.getInstance();

//        switch (role) {
//            case 1://teacher
//                currentAccount = user.getTeacherLocal().getAccount();
//                break;
//            case 2: //student
//                currentAccount = user.getStudentLocal().getAccount();
//                break;
//            case 3: //parent
//                currentAccount = user.getParentLocal().getAccount();
//                break;
//        }

        Log.e("Account", String.valueOf(user.getTeacherLocal().getName()));


        //set for testing
//        Account account = new Account(1, "user1", "user1");
//        user = new Teacher("1", "Hung", account);}

        recyclerView = (RecyclerView) findViewById(R.id.recycleMessageList);

//
////        Message message = new Message(1, 7, "new mess 7", Calendar.getInstance().getTime());
////        Date date = new Date(2022, 05, 10) ;
////
////        Class class1 = new Class("3", "2", "2", 1, 2021,
////                Calendar.getInstance().getTime(), date, 4, "A107", 1, 4);
//
//        //load messages

//            db.messageDAO().insert(message);
//            db.classDAO().insertNewClass(class1);
        messageArrayList = new ArrayList<>();

           messageList = new MutableLiveData<>();
            messageList = messageRepository.getMessageByAccountID(41);

            messageList.observe(this, new Observer<ArrayList<Message>>() {
                @Override
                public void onChanged(ArrayList<Message> messages) {
                    if(messages !=null){
                        messageArrayList= getLatestMessage(messages);
                        messageAdapter = new MainMessageAdapter(messageArrayList, null, getApplicationContext());

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

                        recyclerView.setAdapter(messageAdapter);
                        recyclerView.setLayoutManager(linearLayoutManager);

                    }
                }
            });

//            messageList = getLatestMessage(messageList);

//            List<Student> studentList = db.messageDAO().getAllStudentInClass("1");

//        //when the text in tvsearch changing
//        tvSearch = (TextInputEditText) findViewById(R.id.tvSearch_Message);
//        tvSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                searchingTextChange();
//            }
//        });
//
//        //when don't focus searching tv and text is empty
//        tvSearch.setOnFocusChangeListener((View v, boolean hasFocus)->{
//            if(!hasFocus && tvSearch.getText().toString().trim().isEmpty() ){
//                loadRecentMessage();
//            }
//        });
    }
//
    /*
    load recent message
     */
//    void loadRecentMessage() {
//        db.databaseWriteExecutor.execute(()->
//        {
//            List<Message> messageList = db.messageDAO().getAllMessageByAccount(1);
//            List<Message> messageList1 = getLatestMessage(messageList);
//
//            runOnUiThread(()->{
//                messageAdapter = new MainMessageAdapter(messageList1, null, this);
//                recyclerView.setAdapter(messageAdapter);
//            });
//
//        });
//    }
//
//
    /*
    The func will be to get messages sent latest
     */
    public ArrayList<Message> getLatestMessage(ArrayList<Message> originMessageList)
    {
        if (originMessageList.size()==0){
            return originMessageList;
        }

        ArrayList<Message> resultList= new ArrayList<>();

        //accountids are checked
        List<Integer> checkedList = new ArrayList<>();

        for(  int i=0; i<originMessageList.size()-1; i++){
            Message mess = originMessageList.get(i);

            //add accountid is checked
            if (checkedList.contains(mess.getReceiverAccount().getAccountID())){
                continue;
            }

            checkedList.add(mess.getReceiverAccount().getAccountID());

            for (int j=i+1; j <originMessageList.size(); j++){
                if (originMessageList.get(j).getReceiverAccount().getAccountID()==mess.getReceiverAccount().getAccountID()
                && originMessageList.get(j).getSentTime().after(mess.getSentTime()))
                    mess=originMessageList.get(j);
            }
            resultList.add(mess);
        }

        //check the last message
        if(checkedList.contains(originMessageList.get(originMessageList.size()-1).getReceiverAccount().getAccountID())== false){
            resultList.add(originMessageList.get(originMessageList.size()-1));
        }

        return resultList;
    }

//    /*
//    process the event when text to search is changed
//     */
//    public void searchingTextChange(){
//        String name = tvSearch.getText().toString().trim();
//
//        //user is a teacher
//        if(user instanceof Teacher) {
//            //show searching result
//            db.databaseWriteExecutor.execute(() -> {
//                List<Student> studentList = db.messageDAO().getAllStudentOfTeacherWithSimilarName(user.getTeacherId(), name);
//
//                runOnUiThread(() -> {
//                    messageAdapter = new MainMessageAdapter(null, studentList, this);
//                    recyclerView.setAdapter(messageAdapter);
//                });
//            });
//        }
//    }
}
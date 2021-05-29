package com.example.electroniccommunicationhandbook.ui.message;

import androidx.appcompat.app.AppCompatActivity;
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
import com.example.electroniccommunicationhandbook.repository.MessageRepository;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class detail_message extends AppCompatActivity {
    private RecyclerView recycMessage;
    private ShapeableImageView userImg;
    private TextView tvUserName;
    private EditText tvMessage;
    private Button btnBack;
    private DetailMessageAdapter adapter;
    List<Message> messageList;

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

        Bundle extras = getIntent().getExtras();

        if(extras!=null){
            otherAccountId = extras.getInt("otherAccountID");
//            tvMessage.setText(String.valueOf(extras.getInt("otherID")));
            Log.e("otherId", "otherID: "+ String.valueOf(otherAccountId));
        }

        loadMessage();
    }

    /*
    func to load messages
     */
    private void loadMessage(){
//        db.databaseWriteExecutor.execute(()->{
//            messageList = db.messageDAO().getMessageBetweenUsers(myAccountId, otherAccountId);
//            runOnUiThread(() -> {
//                adapter = new DetailMessageAdapter(messageList, myAccountId);
//                recycMessage.setAdapter(adapter);
//
//                LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
//                recycMessage.setLayoutManager(linearLayoutManager);
//                recycMessage.scrollToPosition(messageList.size()-1);
//            });
//        });
    }
}
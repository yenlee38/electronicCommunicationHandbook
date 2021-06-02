package com.example.electroniccommunicationhandbook.ui.student.card;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.electroniccommunicationhandbook.MainActivity;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.repository.StudentRepository;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;

public class CardActivity  extends AppCompatActivity {

    private Student student;
    private ImageView img_avatar;
    private TextView tv_student_id;
    private TextView tv_name;
    private TextView tv_major;
    private TextView tv_year_studying;
    private ImageView img_code_id;
    private TextView tv_bank_seri;
    private ImageView img_back;
    private StudentRepository studentRepository;
    UserLocalStore userLocalStore;
    private TextView tv_DoB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_student);
        Intent intent = getIntent();
        studentRepository = new StudentRepository();
        userLocalStore = new UserLocalStore(getApplicationContext());
        student = userLocalStore.getStudentLocal();
        initView();
        createImageViewCode();

    }

    private void setValueForCard(){
        tv_student_id.setText(student.getStudentId());
        if(student.getName() != null){ tv_name.setText(student.getName());}
        if(student.getMajor() != null){ tv_major.setText(student.getMajor());}
        if(student.getYear() != 0){ tv_year_studying.setText(student.getYear() + "");}
        if(student.getBankSeri() != null){tv_bank_seri.setText(student.getBankSeri());}

        try
        {SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        tv_DoB.setText(simpleFormat.format(student.getBirthday()));}catch (Exception e) {}

    }

    private void initView(){

        img_avatar = findViewById(R.id.img_avatar);
        tv_student_id = findViewById(R.id.tv_student_id);
        tv_name = findViewById(R.id.tv_name);
        tv_major = findViewById(R.id.tv_major);
        tv_year_studying = findViewById(R.id.tv_year_studying);
        tv_bank_seri = findViewById(R.id.tv_bank_seri);
        img_code_id = findViewById(R.id.img_code_id);
        img_back = findViewById(R.id.img_back);
        tv_DoB = findViewById(R.id.tv_DoB);

        setValueForCard();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void createImageViewCode() {
        try {
            String studentId = student.getStudentId();
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            Writer codeWriter;
            codeWriter = new Code128Writer();
            BitMatrix byteMatrix = codeWriter.encode(studentId, BarcodeFormat.CODE_128,314, 86, hintMap);
            int width = byteMatrix.getWidth();
            int height = byteMatrix.getHeight();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    bitmap.setPixel(i, j, byteMatrix.get(i, j) ? Color.BLACK : Color.WHITE);
                }
            }

            img_code_id.setImageBitmap(bitmap);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}

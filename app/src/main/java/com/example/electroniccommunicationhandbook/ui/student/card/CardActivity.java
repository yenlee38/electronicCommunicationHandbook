package com.example.electroniccommunicationhandbook.ui.student.card;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.electroniccommunicationhandbook.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

public class CardActivity  extends AppCompatActivity {

    private ImageView img_avatar;
    private TextView tv_student_id;
    private TextView tv_name;
    private TextView tv_major;
    private TextView tv_year_studying;
    private ImageView img_code_id;
    private TextView tv_bank_seri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_student);

        initView();
        createImageViewCode();

    }

    private void initView(){

        img_avatar = findViewById(R.id.img_avatar);
        tv_student_id = findViewById(R.id.tv_student_id);
        tv_name = findViewById(R.id.tv_name);
        tv_major = findViewById(R.id.tv_major);
        tv_year_studying = findViewById(R.id.tv_year_studying);
        tv_bank_seri = findViewById(R.id.tv_bank_seri);
        img_code_id = findViewById(R.id.img_code_id);
    }

    private void createImageViewCode() {
        try {
            String studentId = tv_student_id.getText().toString();
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

package com.example.appangicungduoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.appangicungduoc.AnGi.AngiActivity;
import com.example.appangicungduoc.DanhSach.DSMonActivity;
import com.example.appangicungduoc.ThongKe.ThongkeActivity;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 1;
    ViewFlipper vflper;
    ImageButton btAnGi,btDSMon,btThongKe;
    TextView txtTimMonAn,txtQLMA,txtThongKe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vflper=findViewById(R.id.vflper);
        vflper.setFlipInterval(3000);
        vflper.setAutoStart(true);
        txtTimMonAn=findViewById(R.id.txtTimMonAn);
        txtQLMA=findViewById(R.id.txtQLMA);
        txtThongKe=findViewById(R.id.txtThongKe);
        btAnGi=findViewById(R.id.btAnGi);
        btDSMon=findViewById(R.id.btDSMon);
        btThongKe=findViewById(R.id.btThongKe);

        btAnGi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,AngiActivity.class);
                startActivity(intent);
            }
        });
        btDSMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,DSMonActivity.class);
                startActivity(intent);
            }
        });
        btThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,ThongkeActivity.class);
                startActivity(intent);
            }
        });
    }

}
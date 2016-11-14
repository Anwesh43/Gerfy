package com.anwesome.games.gerfy;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.anwesome.games.gerfyview.GerfyView;

public class MainActivity extends AppCompatActivity {
    private GerfyView gerfyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gerfyView = (GerfyView)findViewById(R.id.hello);
        gerfyView.setText("Hello");
        gerfyView.setTextColor(Color.WHITE);

        //gerfyView.setLayoutParams(new LinearLayout.LayoutParams(100,100));
        gerfyView.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Hi",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

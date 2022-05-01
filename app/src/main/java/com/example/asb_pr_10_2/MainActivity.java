package com.example.asb_pr_10_2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("그림 투표");

        final int[] voteCount = new int[9];
        for(int i=0; i<voteCount.length; i++){
            voteCount[i] = 0;
        }

        ImageView[] image = new ImageView[9];
        Integer[] imageId = {R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5, R.id.iv6, R.id.iv7, R.id.iv8, R.id.iv9};

        final String[] imageName = {"독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀", "이레느깡 단 베르양", "잠자는 소녀"
        , "테라스의 두 자매", "피아노 레슨", "피아노 앞의 소녀들", "해변에서"};

        for(int i=0; i<imageId.length; i++){
            final int idx = i;
            image[idx] = (ImageView) findViewById(imageId[idx]);
            image[idx].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    voteCount[idx] ++;
                    Toast.makeText(MainActivity.this, imageName[idx]+"의 투표스 : "+voteCount[idx], Toast.LENGTH_SHORT).show();
                }
            });
        }

        Button btnEnd = (Button) findViewById(R.id.btnEnd);
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);

                intent.putExtra("voteCount", voteCount);
                intent.putExtra("imageName", imageName);

                //callback 없이 호출 : startActivity(internt);
                //startActivity(intent);

                // 다음 화면에서 finish() 후 콜백함수 사용 시 : startActivityForResult(intent, requestCode);
                // finish() 후 onActivityResult 에서 requstCode를 받아서 동작
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            Toast.makeText(MainActivity.this, "돌아옴", Toast.LENGTH_SHORT).show();
            Log.d("************", "돌아왔다구");
        }
    }
}
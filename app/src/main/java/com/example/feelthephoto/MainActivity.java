package com.example.feelthephoto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
Button button;
ImageButton imageButton;
ImageView imageView2;
Intent intent;
Bitmap bitmp;
final static int picbycamera=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);
        imageButton=findViewById(R.id.imageButton);
        imageView2=findViewById(R.id.imageView2);
        InputStream inputStream=getResources().openRawResource(R.drawable.image_two);
        bitmp= BitmapFactory.decodeStream(inputStream);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    getApplicationContext().setWallpaper(bitmp);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this,"Feel the moment",Toast.LENGTH_SHORT).show();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Click!!!",Toast.LENGTH_SHORT).show();
                intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // invokes the camera and its implicit intent
                startActivityForResult(intent,picbycamera);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK)
        {
            Bundle extras=data.getExtras();
            bitmp=(Bitmap) extras.get("data");
            imageView2.setImageBitmap(bitmp);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.item1:
                Toast.makeText(MainActivity.this,"Hii",Toast.LENGTH_SHORT).show();
                break;

            case R.id.item2:
                Toast.makeText(MainActivity.this,"Hello",Toast.LENGTH_SHORT).show();
                break;

            case R.id.item3:
                Toast.makeText(MainActivity.this,"How r u?",Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}

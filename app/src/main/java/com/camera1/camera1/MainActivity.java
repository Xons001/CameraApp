package com.camera1.camera1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        Button b = findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                 if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                     startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                 }
             }
         }

        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView nImageView = findViewById(R.id.foto);
            nImageView.setImageBitmap(imageBitmap);

            // guardar a disc
             /*try{
                FileOutputStream out = new FileOutputStream(IMAGE_FILENAME: MODE_APPEND)
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, quality: 100, out);
                Log.v(tag: "TAG OK", msg: "Arxiu ok")
            }catch (Exception e){
                Log.v(tag: "FILE ERROR", msg: "Error escrivint arxiu");
                e.printStackTrace();
            }*/

        }
    }
}

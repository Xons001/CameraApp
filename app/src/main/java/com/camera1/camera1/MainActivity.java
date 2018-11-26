package com.camera1.camera1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String mCurrentPhotoPath;
    ImageView nImageView;


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
        nImageView = findViewById(R.id.foto);
        File file = new File("/data/data/com.camera1.camera1/files/a.jpg");
        Uri uir = Uri.fromFile(file);
        nImageView.setImageURI(uir);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            nImageView = findViewById(R.id.foto);
            nImageView.setImageBitmap(imageBitmap);

            // guardar a disc
            try {
                FileOutputStream out = openFileOutput("a.jpg", MODE_PRIVATE);
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.close();
                Log.v("TAG OK", "Arxiu ok");
            } catch (Exception e) {
                Log.v("FILE ERROR", "Error escrivint arxiu");
                e.printStackTrace();
            }
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
}

package com.example.photoalbum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class UpdateImageActivity extends AppCompatActivity {

    private ImageView imageViewUpdateImage;
    private Button buttonUpdate;
    private EditText editTextUpdateTitle,editTextUpdateDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Update Image");
        setContentView(R.layout.activity_update_image);

        imageViewUpdateImage = findViewById(R.id.imageViewUpdateImage);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        editTextUpdateTitle=findViewById(R.id.editTextUpdateTitle);
        editTextUpdateDescription=findViewById(R.id.editTextUpdateDescription);

        imageViewUpdateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
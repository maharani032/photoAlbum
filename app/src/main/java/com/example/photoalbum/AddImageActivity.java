package com.example.photoalbum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddImageActivity extends AppCompatActivity {

    private ImageView imageViewAddImage;
    private Button buttonSave;
    private EditText editTextAddTitle,editTextAddDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Image");
        setContentView(R.layout.activity_add_image);

        imageViewAddImage = findViewById(R.id.imageViewAddImage);
        buttonSave = findViewById(R.id.buttonSave);
        editTextAddTitle=findViewById(R.id.editTextAddTitle);
        editTextAddDescription=findViewById(R.id.editTextAddDescription);

        imageViewAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
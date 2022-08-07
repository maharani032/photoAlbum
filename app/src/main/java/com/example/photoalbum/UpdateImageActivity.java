package com.example.photoalbum;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UpdateImageActivity extends AppCompatActivity {

    private ImageView imageViewUpdateImage;
    private Button buttonUpdate;
    private EditText editTextUpdateTitle,editTextUpdateDescription;

    private String title,description;
    private int id;
    private byte[] image;

    ActivityResultLauncher<Intent> activityResultLauncherForSelectImage;

    private Bitmap selectedImage;
    private Bitmap scaledImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Update Image");
        setContentView(R.layout.activity_update_image);

//        register activity
        registerActivityForSelectImage();



        imageViewUpdateImage = findViewById(R.id.imageViewUpdateImage);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        editTextUpdateTitle=findViewById(R.id.editTextUpdateTitle);
        editTextUpdateDescription=findViewById(R.id.editTextUpdateDescription);

        id=getIntent().getIntExtra("id",-1);
        title=getIntent().getStringExtra("title");
        description=getIntent().getStringExtra("description");
        image=getIntent().getByteArrayExtra("image");

        editTextUpdateTitle.setText(title);
        editTextUpdateDescription.setText(description);
        imageViewUpdateImage.setImageBitmap(BitmapFactory.decodeByteArray(image,0,image.length));

        imageViewUpdateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK
                        , MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    StartActivityForResult -->before api30
//                    ActivityResultLaucher
                activityResultLauncherForSelectImage.launch(intent);
            }
        });
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateData();
            }
        });
    }
    public void updateData() {
        if (id == -1) {
            Toast.makeText(UpdateImageActivity.this
                    , "there is a problem", Toast.LENGTH_SHORT).show();
            return;
        }
        String updateTitle = editTextUpdateTitle.getText().toString();
        String updateDescription = editTextUpdateDescription.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.putExtra("updateTitle", updateTitle);
        intent.putExtra("updateDescription", updateDescription);
        if (selectedImage == null) {
            intent.putExtra("image", image);
        }
        else{
            scaledImage = makeSmall(selectedImage, 300);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            scaledImage.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
            byte[] updateImage = outputStream.toByteArray();
            intent.putExtra("image", image);
        }
        setResult(RESULT_OK,intent);
        finish();
    }
    public void registerActivityForSelectImage(){

        activityResultLauncherForSelectImage
                =registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
                , new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        int resultCode=result.getResultCode();
                        Intent data=result.getData();

                        if(resultCode== RESULT_OK && data != null){
                            try {
                                selectedImage= MediaStore.Images.Media
                                        .getBitmap(getContentResolver(),data.getData());
                                imageViewUpdateImage.setImageBitmap(selectedImage);
                            }catch (IOException e){
                                e.printStackTrace();
                            }

                        }

                    }
                });

    }
    public Bitmap makeSmall(Bitmap image,int maxSize){

        int width=image.getWidth();
        int height=image.getHeight();

        float ratio=(float)width/(float)height;

        if(ratio>1){
//            width>height
            width=maxSize;
            height= (int) (width / ratio);
        }else{
            height=maxSize;
            width=(int)(height*ratio);
        }
        return Bitmap.createScaledBitmap(image,width,height,true);
    }

}
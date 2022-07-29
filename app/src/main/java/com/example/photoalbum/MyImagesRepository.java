package com.example.photoalbum;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyImagesRepository {

    private MyImagesDao myImagesDao;
    private LiveData<List<MyImages>> imageList;

    ExecutorService executorService=Executors.newSingleThreadExecutor();
    public MyImagesRepository(Application application){

        MyImagesDatabase database=MyImagesDatabase.getInstance(application);
        myImagesDao=database.myImagesDao();
        imageList = myImagesDao.getAllImages();
    }
    public void insert(MyImages myImages){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                myImagesDao.insert(myImages);
            }
        });
//        new InsertImageAsyncTask(myImagesDao).execute(myImages);
    }
    public void delete(MyImages myImages){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                myImagesDao.delete(myImages);
            }
        });
//        new DeleteImageAsyncTask(myImagesDao).execute(myImages);
    }
    public void update(MyImages myImages){
//        new UpdateImageAsyncTask(myImagesDao).execute(myImages);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                myImagesDao.update(myImages);
            }
        });
    }

    public LiveData<List<MyImages>> getAllImages() {
        return imageList;
    }



//    first parameter --> doInBackground()
//    second parameter --> onProgressUpdate()
//    third parameter --> return type of doInBackground()

//    private static class InsertImageAsyncTask extends AsyncTask<MyImages,Void,Void>{
//
//        MyImagesDao imagesDao;
//
//        public InsertImageAsyncTask(MyImagesDao imagesDao) {
//            this.imagesDao = imagesDao;
//        }
//
//        @Override
//        protected Void doInBackground(MyImages... myImages) {
//
//            imagesDao.insert(myImages[0]);
//            return null;
//        }
//    }
}

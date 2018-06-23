package com.example.chirag.booklisting;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageURL extends AsyncTask<String, Void, Bitmap> {
    @SuppressLint("StaticFieldLeak")
    private ImageView imageView;
    private Bitmap bitmap;

    public ImageURL(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String url = strings[0];
        try {
            URL bookUrl = new URL(url);
            InputStream inputStream = bookUrl.openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}

package com.cksrb.rebook;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Duck on 2016-12-16.
 */

public class DownLoadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownLoadImageTask(ImageView bmImage){
        this.bmImage = bmImage;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String urlDisplay = params[0];
        Bitmap bitmap = null;

        try{
            InputStream in = new java.net.URL(urlDisplay).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap result){
        bmImage.setImageBitmap(result);
    }
}

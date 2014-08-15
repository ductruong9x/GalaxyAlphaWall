package com.appfree.galaxyalpha.view;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.appfree.galaxyalpha.common.Constant;
import com.appfree.galaxyalpha.model.Wallpaper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by truongtvd on 8/6/14.
 */
public class OnSaveListener implements View.OnClickListener {
    private  Context context;
    private Wallpaper item;


    public OnSaveListener(Context context,Wallpaper item){
        this.context=context;
        this.item=item;

    }
    @Override
    public void onClick(View v) {
        Toast.makeText(context,"Saving...",Toast.LENGTH_LONG).show();
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), item.getWall());

        File file = new File(Constant.PATH, item.getName()+".jpg");
        try {
            FileOutputStream  outStream = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();
            String imageMimetype = "image/jpeg";
            ContentValues values = new ContentValues();
            values.put(MediaStore.MediaColumns.DATA, file.toString());
            values.put(MediaStore.MediaColumns.TITLE, item.getName()+".jpg");
            values.put(MediaStore.MediaColumns.DATE_ADDED,
                    System.currentTimeMillis());
            values.put(MediaStore.MediaColumns.MIME_TYPE, imageMimetype);
            Uri uri = context.getContentResolver()
                    .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            values);
            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "image/png");
            context.startActivity(intent);
            Toast.makeText(context,"Save to sdcard",Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

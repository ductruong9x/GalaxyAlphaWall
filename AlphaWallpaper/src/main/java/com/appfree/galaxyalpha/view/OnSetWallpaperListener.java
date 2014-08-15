package com.appfree.galaxyalpha.view;

import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Toast;

import com.appfree.galaxyalpha.model.Wallpaper;

import java.io.IOException;

/**
 * Created by truongtvd on 8/6/14.
 */
public class OnSetWallpaperListener implements View.OnClickListener {
    private Context context;
    private Wallpaper item;
    private Bitmap bitmap;
    private ProgressDialog dialog;
    public OnSetWallpaperListener(Context context,Wallpaper item,Bitmap bitmap){
        this.context=context;
        this.item=item;
        this.bitmap=bitmap;


    }


    @Override
    public void onClick(View v) {
        Toast.makeText(context,"Setting...",Toast.LENGTH_LONG).show();
        WallpaperManager myWallpaperManager
                = WallpaperManager.getInstance(context);
        try {

            myWallpaperManager.setBitmap(bitmap);

            Toast.makeText(context,"Set Wallpaper done",Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            dialog.dismiss();Toast.makeText(context,"Can not set wallpaper",Toast.LENGTH_SHORT).show();

        }
    }
}

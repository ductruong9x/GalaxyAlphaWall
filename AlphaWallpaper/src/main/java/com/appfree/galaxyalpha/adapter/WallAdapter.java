package com.appfree.galaxyalpha.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.appfree.galaxyalpha.R;
import com.appfree.galaxyalpha.model.Wallpaper;
import com.appfree.galaxyalpha.view.OnSaveListener;
import com.appfree.galaxyalpha.view.OnSetWallpaperListener;

import java.util.ArrayList;

/**
 * Created by truongtvd on 8/6/14.
 */
public class WallAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<Wallpaper> listNew = null;
    private LayoutInflater inflater;
    private View detailview;

    private Wallpaper item;

    public WallAdapter(Context context, ArrayList<Wallpaper> listNew) {
        this.context = context;
        this.listNew = listNew;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return listNew.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == (o);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewHolder viewHolder = null;

        viewHolder = new ViewHolder();
        detailview = inflater.inflate(R.layout.item_layout, null);
        viewHolder.btnSave = (Button) detailview.findViewById(R.id.btnSave);
        viewHolder.btnSet = (Button) detailview.findViewById(R.id.btnSet);
        viewHolder.imgWall = (ImageView) detailview.findViewById(R.id.imgWall);



        item = listNew.get(position);
        int THUMBSIZE = 512;
        DisplayMetrics displayMetrics=context.getResources().getDisplayMetrics();

        int wight=displayMetrics.widthPixels;
        int height=displayMetrics.heightPixels;
        final Bitmap ThumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeResource(context.getResources(),item.getWall()),
                wight, height);
        viewHolder.btnSet.setOnClickListener(new OnSetWallpaperListener(context,item,ThumbImage));
        viewHolder.btnSave.setOnClickListener(new OnSaveListener(context,item));
        //viewHolder.imgWall.setImageResource(item.getWall());
          viewHolder.imgWall.setImageBitmap(ThumbImage);
        ((ViewPager) container).addView(detailview, 0);
        return detailview;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    private class ViewHolder {
        Button btnSet, btnSave;
        ImageView imgWall;
    }
}

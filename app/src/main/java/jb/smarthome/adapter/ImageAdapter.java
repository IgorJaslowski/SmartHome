package jb.smarthome.adapter;

import android.content.Context;
import android.graphics.ColorFilter;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import jb.smarthome.R;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }
    @Override
    public int getCount() {
        return icons.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//
        ImageView imageView;

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(GridView.AUTO_FIT, 350));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(100, 75,100, 100);
            imageView.setColorFilter(mContext.getColor(R.color.colorGridIcon));
            imageView.setBackgroundColor(mContext.getColor(R.color.colorGridBackground));
            imageView.setElevation(5);



        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(icons[position]);



        return imageView;
    }

    // references to our images
    private static Integer[] icons = {
            R.drawable.ic_icon_alarm,
            R.drawable.ic_icon_cam,
            R.drawable.ic_icon_termometr,
            R.drawable.ic_icon_wifi,
            R.drawable.ic_icon_zarowka,
            R.drawable.ic_icon_ustawienia
};
//    private static String[] gridViewStrings = {
//            "NAME",
//            "NAME",
//            "NAME",
//            "NAME",
//    };

}

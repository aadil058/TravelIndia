package app.aadil.travelindia;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.lang.annotation.Target;

public class PlaceModal implements com.squareup.picasso.Target {
    private String mName;
    private Bitmap mImage;
    private Context context;
    private RecyclerView.Adapter adapter;

    public PlaceModal(Context context, RecyclerView.Adapter adapter, String mName, Bitmap mImage){
        this.mName = mName;
        this.mImage = mImage;
        this.context = context;
        this.adapter = adapter;
    }

    public String getName(){
        return mName;
    }

    public Bitmap getImage(){
        return mImage;
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        mImage = bitmap;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
        mImage = ((BitmapDrawable) errorDrawable).getBitmap();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {
        mImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.logo);
        adapter.notifyDataSetChanged();
    }
}

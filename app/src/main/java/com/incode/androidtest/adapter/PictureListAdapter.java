package com.incode.androidtest.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.incode.androidtest.DataManager;
import com.incode.androidtest.R;
import com.incode.androidtest.interfaces.FragmentInterface;
import com.incode.androidtest.models.Picture;

/**
 * Created by argi on 11/3/16.
 */

public class PictureListAdapter extends RecyclerView.Adapter<PictureListAdapter.ViewHolder> {

    private DataManager dm = DataManager.INSTANCE;
    private FragmentInterface fragmentInterface;

    private Context context;

    public static PictureListAdapter getInstance(FragmentInterface fragmentInterface, Context ctx){
        PictureListAdapter adapter = new PictureListAdapter();
        adapter.fragmentInterface = fragmentInterface;
        adapter.context = ctx;
        return adapter;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Picture picture = dm.data.get(position);

        holder.tvTitle.setText(picture.getTitle());
        holder.tvID.setText(picture.getId());
        holder.tvPublished.setText(picture.getPublishedAt());
        holder.tvComment.setText(picture.getComment());

        Glide.with(context)
                .load(picture.getPicture())
                .asBitmap()
                .skipMemoryCache(true)
                .diskCacheStrategy( DiskCacheStrategy.NONE )
                .thumbnail(0.1f)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                        holder.ivPicture.setImageBitmap(bitmap);
                    }
                });

    }

    @Override
    public int getItemCount() {
        if (dm.data != null)
        return dm.data.size();
        return 0;
    }



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvTitle, tvID, tvPublished, tvComment;
        ImageView ivPicture;


        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle     = (TextView) itemView.findViewById(R.id.tvTitle);
            tvID        = (TextView) itemView.findViewById(R.id.tvID);
            tvPublished = (TextView) itemView.findViewById(R.id.tvPublished);
            tvComment   = (TextView) itemView.findViewById(R.id.tvComment);

            ivPicture   = (ImageView) itemView.findViewById(R.id.imageView);

        }

        @Override
        public void onClick(View v) {
            String ID = tvID.getText()+"";
            if (fragmentInterface !=null) {
                fragmentInterface.onPictureClick(ID);
            }
        }
    }
}

package com.incode.androidtest.fragment;

import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
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

public class DetailsFragment extends BaseFragment <FragmentInterface> {

    Picture picture;

    ImageView ivFullSizePicture;

    FloatingActionButton fabPictureSharing;

    public static DetailsFragment getInstance(){
        DetailsFragment fragment = new DetailsFragment();
//        fragment.picture = DataManager.INSTANCE.picture;
        return  fragment;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_picture_details;
    }

    @Override
    protected void initWidgets(View fragmentView) {
        picture = DataManager.INSTANCE.picture;

        ivFullSizePicture = (ImageView) fragmentView.findViewById(R.id.ivFullSizePicture);

        ((TextView) fragmentView.findViewById(R.id.tvPictureID)).setText("Picture ID: " + picture.getId());
        fabPictureSharing = (FloatingActionButton) fragmentView.findViewById(R.id.fabPictureSharing);
        fabPictureSharing.setClickable(false);
        fabPictureSharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                log("fab");
                mListener.onPictureShare(picture.getId());
            }
        });

        Glide.with(getContext())
                .load(picture.getPicture())
                .asBitmap()
                .skipMemoryCache(true)
                .diskCacheStrategy( DiskCacheStrategy.NONE )
                .thumbnail(0.1f)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                        ivFullSizePicture.setImageBitmap(bitmap);
                        DataManager.INSTANCE.image = bitmap;
                        fabPictureSharing.setClickable(true);
                    }
                });
    }
}

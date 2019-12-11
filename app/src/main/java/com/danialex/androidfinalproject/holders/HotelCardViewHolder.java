/*
 * Copyright 2015 Danialex.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE­2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.danialex.androidfinalproject.holders;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.danialex.androidfinalproject.R;
import com.danialex.androidfinalproject.activities.MapActivity;
import com.danialex.androidfinalproject.managers.ModelDependencyManager;
import com.danialex.androidfinalproject.models.Hotel;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Daniel Sanchez Quirós
 * @author Manuel Alejandro Cabeza Romero
 */
public class HotelCardViewHolder extends RecyclerView.ViewHolder
{
    @Bind(R.id.cardview_hotel_item_main_imageview)
    ImageView mImageView;

    @Bind(R.id.cardview_hotel_item_main_name)
    TextView mName;

    @Bind(R.id.cardview_hotel_item_info_textview_hotel_rating)
    TextView mRating;

    @Bind(R.id.cardview_hotel_item_info_textview_hotel_distance)
    TextView mDistance;

    TypedArray mDefaultBgs;
    Context mContext;
    String mId;

    /**
     * Constructor of HotelCardViewHolder
     * @param itemView View to hold
     */
    public HotelCardViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);
        mDefaultBgs = mContext.getResources().obtainTypedArray(R.array.default_bgs);
        itemView.setOnClickListener(new OnHotelCardClickListener());
    }

    /**
     * Sets the image of the Card View if the Hotel has any, if not a default background is assigned
     * @param photos List of photos of the Hotel to select one from
     */
    public void setImage(ArrayList<Hotel.Photo> photos) {

        if (photos != null && photos.size() >= 0 ) {
            String base = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=%1$s&photoreference=%2$s&key=%3$s";
            String ref = photos.get(0).getPhotoReference();
            String max =  String.valueOf((int)photos.get(0).getWidth());
            String api = "*API-KEY*";
            String request = String.format(base, max, ref, api);
            Picasso.with(mContext).load(request).into(mImageView);
        } else {
            Random rng = new Random();
            int resourceId = mDefaultBgs.getResourceId(rng.nextInt(mDefaultBgs.length()), 0);
            mImageView.setImageBitmap(decodeSampledBitmapFromResource(mContext.getResources(), resourceId, 200, 200));
        }
    }

    public void setNameText(String name) {
        mName.setText(name);
    }

    public void setRatingText(float rating) {
        mRating.setText(String.format(mContext.getString(R.string.ratingText), rating));
    }

    public void setDistanceText(float distance) {
        mDistance.setText(String.format(mContext.getString(R.string.distanceText), ((int) distance)));
    }

    public void setId(String id) {
        mId = id;
    }

    private class OnHotelCardClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ModelDependencyManager.getHotelManager().setClickedHotelId(mId);
            Intent mapActivityIntent = new Intent(v.getContext(), MapActivity.class);
            v.getContext().startActivity(mapActivityIntent);
        }
    }

    private int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    private Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}

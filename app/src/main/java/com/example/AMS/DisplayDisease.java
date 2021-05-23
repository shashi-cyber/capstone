package com.example.AMS;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayDisease extends AppCompatActivity {

    ImageView mCropImage;
    TextView mCropTV,mDseaseTV,mRemedyTV;
    String mResult;
    byte[] mImageByteArray;
    Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_disease);

        mCropImage = findViewById(R.id.cropImage);
        mCropTV = findViewById(R.id.cropTV);
        mDseaseTV = findViewById(R.id.diseaseTV);
        mRemedyTV = findViewById(R.id.remedyTv);

        mImageByteArray = getIntent().getByteArrayExtra("bitmap");
        mResult = getIntent().getStringExtra("results");

        mBitmap = BitmapFactory.decodeByteArray(mImageByteArray, 0, mImageByteArray.length);

        mCropImage.setImageBitmap(mBitmap);

        Log.d("DisplayDiseases", "Results : "+mResult);
        mCropTV.setText(mResult.substring(mResult.indexOf("-")+2 ,mResult.indexOf(")")+1));
        mDseaseTV.setText(mResult.substring(mResult.indexOf("]")+2,mResult.indexOf("-")-1));
        mRemedyTV.setText(mResult.substring(mResult.indexOf(")")+1,mResult.length()-1).trim());

    }
}
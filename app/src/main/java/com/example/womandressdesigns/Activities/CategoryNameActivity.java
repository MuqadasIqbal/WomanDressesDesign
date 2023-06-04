package com.example.womandressdesigns.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.womandressdesigns.Adapters.CategoryAdapter;
import com.example.womandressdesigns.Adapters.SliderAdapterExample;
import com.example.womandressdesigns.ModelClass.SliderItem;
import com.example.womandressdesigns.ModelClass.categoryModel;
import com.example.womandressdesigns.R;
import com.example.womandressdesigns.databinding.ActivityCategoryNameBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class CategoryNameActivity extends AppCompatActivity {

    ActivityCategoryNameBinding binding;
    private SliderAdapterExample adapter;
    ArrayList<SliderItem> slidermodel;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryNameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        name = getIntent().getStringExtra("name");
        binding.categoryNameText.setText(name);
        slidermodel = new ArrayList<>();

        if (name.equals("Mandhi Dresses Designs")) {

            slidermodel.add(new SliderItem(R.drawable.mandhidres));
            slidermodel.add(new SliderItem(R.drawable.d));
            slidermodel.add(new SliderItem(R.drawable.greenmandhidress));
            slidermodel.add(new SliderItem(R.drawable.yeloowpinkmandhidress));
            slidermodel.add(new SliderItem(R.drawable.couplemandhidress));
            slidermodel.add(new SliderItem(R.drawable.greenmandhidress));
            slidermodel.add(new SliderItem(R.drawable.yeloowpinkmandhidress));
            slidermodel.add(new SliderItem(R.drawable.mandhidres));
            slidermodel.add(new SliderItem(R.drawable.couplemandhidress));
        } else if (name.equals("Bridal Dresses Designs")) {
            slidermodel.add(new SliderItem(R.drawable.mandhidres));
            slidermodel.add(new SliderItem(R.drawable.d));
            slidermodel.add(new SliderItem(R.drawable.bride));
        } else if (name.equals("Walima Dresses Designs")) {

            slidermodel.add(new SliderItem(R.drawable.trendingmuslimbridal));
            slidermodel.add(new SliderItem(R.drawable.couplewalimadress));
            slidermodel.add(new SliderItem(R.drawable.wilmapic));
            slidermodel.add(new SliderItem(R.drawable.valimabridaloutfitsaretrending));
            slidermodel.add(new SliderItem(R.drawable.valimabride));
            slidermodel.add(new SliderItem(R.drawable.valimabridedressinspo));
            slidermodel.add(new SliderItem(R.drawable.valimacouplegray));
            slidermodel.add(new SliderItem(R.drawable.valimadress));
            slidermodel.add(new SliderItem(R.drawable.valimaskfbridals));
            slidermodel.add(new SliderItem(R.drawable.valimmalightpinkdress));
            slidermodel.add(new SliderItem(R.drawable.walimaadresss));
            slidermodel.add(new SliderItem(R.drawable.walimaalightbluedrs));
            slidermodel.add(new SliderItem(R.drawable.walimaattractiveivory));
            slidermodel.add(new SliderItem(R.drawable.waliabridal));
            slidermodel.add(new SliderItem(R.drawable.));
            slidermodel.add(new SliderItem(R.drawable.));
            slidermodel.add(new SliderItem(R.drawable.));
            slidermodel.add(new SliderItem(R.drawable.));
            slidermodel.add(new SliderItem(R.drawable.));
            slidermodel.add(new SliderItem(R.drawable.));
            slidermodel.add(new SliderItem(R.drawable.));
            slidermodel.add(new SliderItem(R.drawable.));


        } else if (name.equals("Party Dresses Designs")) {
            slidermodel.add(new SliderItem(R.drawable.party));
            slidermodel.add(new SliderItem(R.drawable.yeloowpinkmandhidress));
            slidermodel.add(new SliderItem(R.drawable.bride));
        } else {
            slidermodel.add(new SliderItem(R.drawable.mandhidres));
            slidermodel.add(new SliderItem(R.drawable.d));
        }

        adapter = new SliderAdapterExample(slidermodel);
        binding.imageSlider.setSliderAdapter(adapter);

        //MoveSliderImageToRightSide
        binding.rightArrowimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = binding.imageSlider.getCurrentPagePosition();
                if (currentPosition < adapter.getCount() - 1) {
                    binding.imageSlider.setCurrentPagePosition(currentPosition + 1);
                }
            }
        });
        //MoveSliderImageToLeftSide
        binding.Arrowimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = binding.imageSlider.getCurrentPagePosition();
                if (currentPosition > 0) {
                    binding.imageSlider.setCurrentPagePosition(currentPosition - 1);
                }
            }
        });
        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //here we share image from image slider wo we pass image slider

                Bitmap image=getBitmapFromView(binding.imageSlider);
                shareImageAndText(image);
            }
        });


    }

    private void shareImageAndText(Bitmap image) {
        Uri uri=getImageToShare(image);
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM,uri);
        intent.putExtra(Intent.EXTRA_SUBJECT,"Dress design");
        intent.setType("images/jpg");
        startActivity(Intent.createChooser(intent,"Shared image Via:"));
    }

    private Uri getImageToShare(Bitmap image) {
        File imageFolder=new File(getCacheDir(),"images");
        Uri uri=null;
        try {
            imageFolder.mkdirs();
            File file=new File(imageFolder,"shared_image.jpg");
            FileOutputStream outputStream=new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            outputStream.flush();
            outputStream.close();
            uri= FileProvider.getUriForFile(this,"com.example.womandressdesigns.fileProvider",file);

        }catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return uri;

    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap=Bitmap.createBitmap(view.getWidth(),view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas convas=new Canvas(returnedBitmap);
        Drawable background=view.getBackground();
        if (background!=null){
            background.draw(convas);

        }else {
            convas.drawColor(Color.WHITE);
        }
        view.draw(convas);
        return returnedBitmap;

    }

    }




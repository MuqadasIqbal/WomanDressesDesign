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

    int[] imageResources = {R.drawable.mandhidres, R.drawable.yeloowpinkmandhidress, R.drawable.greenmandhidress};

    // Track the current image index
    int currentImageIndex = 0;
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
            slidermodel.add(new SliderItem(R.drawable.wilmapic));
            slidermodel.add(new SliderItem(R.drawable.greenmandhidress));
            slidermodel.add(new SliderItem(R.drawable.couplemandhidress));
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

        //SaveImageToGallery

       /* binding.savedimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToGallary();
            }
        });*/
        binding.savedimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToGallery();
            }
        });
    }

        private void saveToGallery() {

            int currentImageResource = imageResources[currentImageIndex];

            // Get the drawable corresponding to the current image resource
            Drawable drawable = getResources().getDrawable(currentImageResource);

            // Get the current image from the image slider
          /*  Drawable drawable = binding.imageSlider.getCurrentPagePosition().getDrawable();*/
           /* Drawable currentImage;
            currentImage = binding.imageSlider.setCurrentPagePosition(adapter);*/

            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap bitmap = bitmapDrawable.getBitmap();

                OutputStream outputStream;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    ContentResolver resolver = getContentResolver();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "image.jpg");
                    contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
                    contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/DressImages");

                    Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                    try {
                        outputStream = resolver.openOutputStream(imageUri);
                        if (outputStream != null) {
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                            outputStream.close();
                            Toast.makeText(CategoryNameActivity.this, "Image saved to gallery", Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    String imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/DressImages";
                    File dir = new File(imageDir);
                    dir.mkdirs();
                    String filename = String.format("%d.jpg", System.currentTimeMillis());
                    File outFile = new File(dir, filename);

                    try {
                        outputStream = new FileOutputStream(outFile);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        outputStream.close();
                        Toast.makeText(CategoryNameActivity.this, "Image saved to gallery", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                // Handle the case when the current image is not a BitmapDrawable
            }
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
  /*  private void saveToGallary() {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) binding.imageSlider.getContext().getDrawable(R.drawable.bride);
        Bitmap bitmap = bitmapDrawable.getBitmap();

        OutputStream outputStream;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentResolver resolver = getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "image.jpg");
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/DressImages");

            Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            try {
                outputStream = resolver.openOutputStream(imageUri);
                if (outputStream != null) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    outputStream.close();
                    Toast.makeText(CategoryNameActivity.this, "Image saved to gallery", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            String imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/DressImages";
            File dir = new File(imageDir);
            dir.mkdirs();
            String filename = String.format("%d.jpg", System.currentTimeMillis());
            File outFile = new File(dir, filename);

            try {
                outputStream = new FileOutputStream(outFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.close();
                Toast.makeText(CategoryNameActivity.this, "Image saved to gallery", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/



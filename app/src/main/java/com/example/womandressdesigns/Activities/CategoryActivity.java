package com.example.womandressdesigns.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.womandressdesigns.Adapters.PagerAdapter;
import com.example.womandressdesigns.ModelClass.SliderItem;
import com.example.womandressdesigns.R;
import com.example.womandressdesigns.databinding.ActivityCategoryBinding;
import com.example.womandressdesigns.databinding.ActivityCategoryNameBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
ActivityCategoryBinding binding;
String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        name = getIntent().getStringExtra("name");
        binding.categoryNameText.setText(name);

        /*  List<Drawable> images = getImages();*/ // Provide a list of Drawable images

     /*   PagerAdapter adapter=new PagerAdapter(images);
        binding.viewPager.setAdapter(adapter);*/
       /* binding.savedimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveImageToGallery();
            }
        });*/
    /*    binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Share image from image slider
                Bitmap image = getBitmapFromView(binding.viewPager);
                if (image != null) {
                    shareImageAndText(image);
                } else {
                    Log.e("ShareImage", "Failed to retrieve image from ViewPager");
                }
            }
        });*/


     /*  binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(CategoryActivity.this,SavedActivity.class);
               intent.putExtra("image",images);
               startActivity(intent);
                //here we share image from image slider wo we pass image slider

                Bitmap image=getBitmapFromView(binding.viewPager);
                shareImageAndText(image);
            }
        });

    }*//*
    private List<Drawable> getImages() {
        // Return a list of Drawable images to display in the ViewPager
        List<Drawable> images = new ArrayList<>();
        // Add your images to the list
        // Example:
        if (name.equals("Mandhi Dresses Designs")) {

            images.add(getResources().getDrawable(R.drawable.mandhidres));
            images.add(getResources().getDrawable(R.drawable.d));
            images.add(getResources().getDrawable(R.drawable.greenmandhidress));
            images.add(getResources().getDrawable(R.drawable.yeloowpinkmandhidress));
            images.add(getResources().getDrawable(R.drawable.couplemandhidress));
            images.add(getResources().getDrawable(R.drawable.greenmandhidress));
            images.add(getResources().getDrawable(R.drawable.yeloowpinkmandhidress));
            images.add(getResources().getDrawable(R.drawable.mandhidres));
            images.add(getResources().getDrawable(R.drawable.couplemandhidress));
        } else if (name.equals("Bridal Dresses Designs")) {
            images.add(getResources().getDrawable(R.drawable.mandhidres));
            images.add(getResources().getDrawable(R.drawable.d));
            images.add(getResources().getDrawable(R.drawable.bride));
        } else if (name.equals("Walima Dresses Designs")) {
            images.add(getResources().getDrawable(R.drawable.wilmapic));
            images.add(getResources().getDrawable(R.drawable.greenmandhidress));
            images.add(getResources().getDrawable(R.drawable.couplemandhidress));
        } else if (name.equals("Party Dresses Designs")) {
            images.add(getResources().getDrawable(R.drawable.party));
            images.add(getResources().getDrawable(R.drawable.yeloowpinkmandhidress));
            images.add(getResources().getDrawable(R.drawable.bride));
        } else {
            images.add(getResources().getDrawable(R.drawable.mandhidres));
            images.add(getResources().getDrawable(R.drawable.d));
        }

     *//*   images.add(getResources().getDrawable(R.drawable.greenmandhidress));
        images.add(getResources().getDrawable(R.drawable.mdressyellow));
        images.add(getResources().getDrawable(R.drawable.couplemandhidress));*//*
        return images;


    }
    private void saveImageToGallery() {
        int currentPosition =binding.viewPager.getCurrentItem();
        PagerAdapter adapter = (PagerAdapter)binding.viewPager.getAdapter();
        if (adapter != null) {
            Drawable drawable = adapter.getImageDrawable(currentPosition);
            if (drawable instanceof BitmapDrawable) {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

                OutputStream outputStream;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    ContentResolver resolver = getContentResolver();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "image.jpg");
                    contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
                    contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/ViewPagerImages");

                    Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                    try {
                        outputStream = resolver.openOutputStream(imageUri);
                        if (outputStream != null) {
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                            outputStream.close();
                            Toast.makeText(this, "Image saved to gallery", Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    String imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/ViewPagerImages";
                    File dir = new File(imageDir);
                    dir.mkdirs();

                    String filename = String.format("%d.jpg", System.currentTimeMillis());
                    File outFile = new File(dir, filename);

                    try {
                        outputStream = new FileOutputStream(outFile);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        outputStream.close();
                        Toast.makeText(this, "Image saved to gallery", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                // Handle the case when the drawable is not a BitmapDrawable
                Toast.makeText(this, "Unable to save image", Toast.LENGTH_SHORT).show();
            }
        }
    }*/
 /*   private void shareImageAndText(Bitmap image) {
        Uri uri = getImageToShare(image);
        if (uri != null) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Dress design");
            intent.setType("image/jpeg");
            startActivity(Intent.createChooser(intent, "Share image via:"));
        } else {
            Log.e("ShareImage", "Failed to get image URI");
        }
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


    }*/
/*private void shareImageAndText(Bitmap image) {
    Uri uri=getImageToShare(image);
    Intent intent=new Intent(Intent.ACTION_SEND);
    intent.putExtra(Intent.EXTRA_STREAM,uri);
    intent.putExtra(Intent.EXTRA_SUBJECT,"Dress design");
    intent.setType("images/jpg");
    startActivity(Intent.createChooser(intent,"Shared image Via:"));
}
    private Uri getImageToShare(Bitmap image) {
        File imageFolder = new File(getCacheDir(), "images");
        Uri uri = null;
        try {
            imageFolder.mkdirs();
            File file = new File(imageFolder, "shared_image.jpg");
            FileOutputStream outputStream = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            uri = FileProvider.getUriForFile(this, "com.example.womandressdesigns.fileProvider", file);
        } catch (Exception e) {
            Log.e("ShareImage", "Failed to create image file: " + e.getMessage());
        }
        return uri;
    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable background = view.getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }*/
    }
}
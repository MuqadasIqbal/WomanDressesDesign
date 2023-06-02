package com.example.womandressdesigns.Adapters;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.womandressdesigns.Activities.SavedActivity;
import com.example.womandressdesigns.ModelClass.SliderItem;
import com.example.womandressdesigns.R;
import com.example.womandressdesigns.databinding.ImagesliderSampleBinding;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;


public class SliderAdapterExample extends SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {

  ArrayList<SliderItem> imageslist;

    public SliderAdapterExample(ArrayList<SliderItem> imageslist) {
        this.imageslist = imageslist;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.imageslider_sample, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        viewHolder.binding.imageView1.setImageResource(imageslist.get(position).getImages());

        viewHolder.binding.savedimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewHolder.binding.imageView1.getContext(), SavedActivity.class);
                intent.putExtra("key",String.valueOf(imageslist.get(position).getImages()));
                viewHolder.binding.imageView1.getContext().startActivity(intent);
            }
        });

       /* viewHolder.imageViewBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), imageslist.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
*/

    }

    @Override
    public int getCount() {

        return imageslist.size();
    }

    class SliderAdapterVH extends ViewHolder {

        ImagesliderSampleBinding binding;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            binding=ImagesliderSampleBinding.bind(itemView);


        }
    }

}

package com.example.womandressdesigns.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.womandressdesigns.Activities.CategoryNameActivity;
import com.example.womandressdesigns.ModelClass.categoryModel;
import com.example.womandressdesigns.R;
import com.example.womandressdesigns.databinding.CategoriesSampleBinding;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {
    ArrayList<categoryModel>list;
    Context context;

    public CategoryAdapter(ArrayList<categoryModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_sample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        categoryModel model=list.get(position);
        holder.binding.image.setImageResource(model.getImage());
        holder.binding.textName.setText(model.getName());

        holder.binding.categoryArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*Intent intent=new Intent(context, CategoryNameActivity.class);
                intent.putExtra("name",model.getName());
                context.startActivity(intent);*/
                Intent intent=new Intent(context, CategoryNameActivity.class);
                intent.putExtra("name",model.getName());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        CategoriesSampleBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding=CategoriesSampleBinding.bind(itemView);
        }
    }
}

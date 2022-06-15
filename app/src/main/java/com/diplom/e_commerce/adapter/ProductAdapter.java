package com.diplom.e_commerce.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.diplom.e_commerce.ProductPage;
import com.diplom.e_commerce.R;
import com.diplom.e_commerce.model.Component;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context context;
    List<Component> components;

    public ProductAdapter(Context context, List<Component> components) {
        this.context = context;
        this.components = components;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productItems = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ProductAdapter.ProductViewHolder(productItems);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {

        int imageId = context.getResources().getIdentifier("ic_" + components.get(position).getImg(), "drawable", context.getPackageName());
        holder.productImage.setImageResource(imageId);

        holder.productTitle.setText(components.get(position).getName());
        holder.productCategory.setText(components.get(position).getType());
        holder.productProperties.setText(components.get(position).getProperties());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductPage.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        (Activity) context,
                        new Pair<View, String>(holder.productImage, "productImageAnimation")
                );

                intent.putExtra("productImage", imageId);
                intent.putExtra("productTitle", components.get(position).getName());
                intent.putExtra("productCategory", components.get(position).getType());
                intent.putExtra("productProperties", components.get(position).getProperties());
                intent.putExtra("productId", components.get(position).getId());

                context.startActivity(intent, options.toBundle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return components.size();
    }

    public static final class ProductViewHolder extends RecyclerView.ViewHolder {

        LinearLayout productBg;
        ImageView productImage;
        TextView productTitle, productCategory, productProperties;


        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productBg = itemView.findViewById(R.id.productBg);
            productImage = itemView.findViewById(R.id.productImage);
            productTitle = itemView.findViewById(R.id.productTitle);
            productCategory = itemView.findViewById(R.id.productCategory);
            productProperties = itemView.findViewById(R.id.productProperties);
        }
    }
}

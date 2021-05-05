package com.example.product_cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private List<Product> productList;
    private IAddToCart iAddToCart;

    public void SetData(List<Product> products, IAddToCart addToCartListener){
        productList = products;
        iAddToCart = addToCartListener;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_product, parent,false);

        ProductViewHolder productViewHolder = new ProductViewHolder(view);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        if(product == null) {
            return;
        }

        holder.imageViewProduct.setImageResource(product.getResourceId());
        holder.textViewProductName.setText(product.getName());
        holder.textViewProductDescription.setText(product.getDescription());

        // Change background khi user add cart
        if(product.isAddToCart()){
            holder.imageViewCart.setBackgroundResource(R.drawable.background_cart_disable);
        }
        else {
            holder.imageViewCart.setBackgroundResource(R.drawable.background_cart);
        }

        holder.imageViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Sản phẩm chưa add vào cart mới có thể thực hiện add
                // Sản phẩm đã add vào cart thì không thể add nữa
                if(!product.isAddToCart()){
                    iAddToCart.onClickAddToCart(holder.imageViewCart, product);
                }
            }
        });

    }

    @Override
    public int getItemCount() {

        if(productList != null){
            return productList.size();
        }

        return 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageViewProduct;
        private TextView textViewProductName;
        private TextView textViewProductDescription;
        private ImageView imageViewCart;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            InitView(itemView);
        }

        private void InitView(View view){
            imageViewProduct = view.findViewById(R.id.imageViewProduct);
            textViewProductName = view.findViewById(R.id.textViewProductName);
            textViewProductDescription = view.findViewById(R.id.textViewProductDescription);
            imageViewCart = view.findViewById(R.id.imageViewCart);
        }
    }
}

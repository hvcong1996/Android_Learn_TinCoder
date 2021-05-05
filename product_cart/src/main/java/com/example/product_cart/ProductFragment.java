package com.example.product_cart;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {

    private RecyclerView recyclerViewProduct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        InitView(view);
        ProductAdapter productAdapter = new ProductAdapter();

        MainActivity mainActivity = (MainActivity)getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity);
        recyclerViewProduct.setLayoutManager(linearLayoutManager);

        List<Product> products = GetProducts();

        productAdapter.SetData(products, new IAddToCart() {
            @Override
            public void onClickAddToCart(ImageView imageViewAddToCart, Product product) {
                AnimationUtil.translateAnimation(mainActivity.getImageViewAnimation(), imageViewAddToCart, mainActivity.getViewEndAnimation(), new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // Xử lý khi animation hoạt động xong(user add cart completed)
                        product.setAddToCart(true);
                        productAdapter.notifyDataSetChanged();
                        imageViewAddToCart.setBackgroundResource(R.drawable.background_cart_disable);

                        mainActivity.setCountProductInCart(mainActivity.getCountProduct() + 1);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });

        recyclerViewProduct.setAdapter(productAdapter);

        return view;
    }

    private void InitView(View view){
        recyclerViewProduct = view.findViewById(R.id.recyclerViewProduct);
    }

    private List<Product> GetProducts(){
        List<Product> products = new ArrayList<>();

        for (int i = 1; i <= 20; i++ ){
            products.add(new Product(R.drawable.product_item, "Product " + i, "Description " + i, 10));
        }

        return products;
    }
}
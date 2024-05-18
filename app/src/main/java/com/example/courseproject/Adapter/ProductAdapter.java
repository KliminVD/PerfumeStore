package com.example.courseproject.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.courseproject.Fragments.CartFragment;
import com.example.courseproject.Managers.CartManager;
import com.example.courseproject.Entities.Product;
import com.example.courseproject.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Product> mProducts;
    private FirebaseAuth mAuth;
    private CartManager manager;
    private StorageReference storageRef;
    private FirebaseFirestore db;
    private CartFragment frag;
    public ProductAdapter(Context context, ArrayList<Product> products) {
        mContext = context;
        mProducts = products;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser cUser = mAuth.getCurrentUser();
        manager = new CartManager(cUser.getUid());
        db = FirebaseFirestore.getInstance();
    }
    public void setFrag(CartFragment frag) {this.frag = frag;}
    @Override
    public int getCount() {
        return mProducts.size();
    }
    @Override
    public Object getItem(int position) {
        return mProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_product, parent, false);
        }

        Product p = getProduct(position);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference().child("images");
        StorageReference imageRef = storageRef.child(p.getImage());
        TextView quantity = convertView.findViewById(R.id.quantity);
        quantity.setText(p.getQuantity() + "");
        ((TextView) convertView.findViewById(R.id.tvName)).setText(p.getName());
        ((TextView) convertView.findViewById(R.id.tvPrice)).setText(p.getPrice() + "");
        ImageView img = (ImageView) convertView.findViewById(R.id.ivImage);
        imageRef.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(mContext)
                                .load(uri)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(img);
                    }
                });
        Button bt1 = (Button) convertView.findViewById(R.id.addButton);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity.setText((p.getQuantity() + 1) + "");
                manager.setProductQuantity(p, p.getQuantity() + 1);
                if (frag != null){
                    int sum = 0;
                    for (Product p : mProducts)
                        sum += p.getPrice() * p.getQuantity();
                    Log.d("MyLogs", sum + "");
                    frag.update(sum);
                }
            }
        });
        Button bt2 = (Button) convertView.findViewById(R.id.deleteButton);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p.getQuantity() > 0) {
                    quantity.setText((p.getQuantity() - 1) + "");
                    manager.setProductQuantity(p, p.getQuantity() - 1);
                    if (frag != null){
                        int sum = 0;
                        for (Product p : mProducts)
                            sum += p.getPrice() * p.getQuantity();
                        Log.d("MyLogs", sum + "");
                        frag.update(sum);
                    }
                }
            }
        });
        return convertView;
    }
    Product getProduct(int position){
        return ((Product) getItem(position));
    }
    public void setProducts(List<Product> mProducts){
        this.mProducts.clear();
        this.mProducts.addAll(mProducts);
        notifyDataSetChanged();
    }
}

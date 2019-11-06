package com.example.aewai_iou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LendActivity extends AppCompatActivity {

    Button btnBorrow,btnBuy,btnSell;
    DatabaseReference databaseArtists;

    ListView listView;
    List<Product> productList;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lend);

        databaseArtists = FirebaseDatabase.getInstance().getReference("Lend");

        listView = findViewById(R.id.listView);
        productList = new ArrayList<>();

        btnBuy = (Button) findViewById(R.id.Buy);
        btnBorrow = (Button) findViewById(R.id.Borrow);
        btnSell = (Button) findViewById(R.id.Sell);

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LendActivity.this, BuyActivity.class);
                startActivity(i);
            }
        });

        btnBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LendActivity.this, BorrowActivity.class);
                startActivity(i);
            }
        });

        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LendActivity.this, SellActivity.class);
                startActivity(i);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();

        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                productList.clear();

                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    Product product = artistSnapshot.getValue(Product.class);

                    productList.add(product);

                }

                ProductList adapter = new ProductList(LendActivity.this,productList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

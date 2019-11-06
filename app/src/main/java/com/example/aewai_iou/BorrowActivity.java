package com.example.aewai_iou;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BorrowActivity extends AppCompatActivity {


    Button btnUpload;
    Button btnLend,btnBuy,btnSell;
    EditText textName,textAbout,phoneNum;
    DatabaseReference databaseProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow);

        databaseProduct = FirebaseDatabase.getInstance().getReference("Lend");

        btnUpload = (Button) findViewById(R.id.upload);
        textName = (EditText) findViewById(R.id.textView);
        textAbout = (EditText) findViewById(R.id.editText3);
        btnLend = (Button) findViewById(R.id.Lend);
        btnBuy = (Button) findViewById(R.id.Buy);
        btnSell = (Button) findViewById(R.id.Sell);
        phoneNum = (EditText) findViewById(R.id.phone);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Upload();
            }
        });

        btnLend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BorrowActivity.this, LendActivity.class);
                startActivity(i);
            }
        });

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BorrowActivity.this, BuyActivity.class);
                startActivity(i);
            }
        });

        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BorrowActivity.this, SellActivity.class);
                startActivity(i);
            }
        });
    }
    public void Upload(){
        String name = textName.getText().toString().trim();
        String about = textAbout.getText().toString().trim();
        String number = phoneNum.getText().toString().trim();

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(about) && !TextUtils.isEmpty((number))){
            String id = databaseProduct.push().getKey();
            com.example.aewai_iou.Product product = new Product(id,name,about,number);
            databaseProduct.child(id).setValue(product);
            Toast.makeText(this, "Product you want to borrow is added", Toast.LENGTH_SHORT).show();
            Intent re = new Intent(BorrowActivity.this, BorrowActivity.class);
            startActivity(re);
        }

        else {
            Toast.makeText(this,"Empty fields",Toast.LENGTH_SHORT).show();
        }
    }
}



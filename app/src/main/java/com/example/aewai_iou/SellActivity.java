package com.example.aewai_iou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SellActivity extends AppCompatActivity {

    Button btnUpload;
    Button btnBorrow,btnBuy,btnLend;
    EditText textName,textAbout,phoneNum;
    DatabaseReference databaseProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        databaseProduct = FirebaseDatabase.getInstance().getReference("SellProduct");

        btnUpload = (Button) findViewById(R.id.upload);
        textName = (EditText) findViewById(R.id.textView);
        textAbout = (EditText) findViewById(R.id.editText3);
        btnBorrow = (Button) findViewById(R.id.Borrower);
        btnBuy = (Button) findViewById(R.id.Buy);
        btnLend = (Button) findViewById(R.id.Lend);
        phoneNum = (EditText) findViewById(R.id.phone2);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Upload();
            }
        });

        btnBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SellActivity.this, BorrowActivity.class);
                startActivity(i);
            }
        });

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SellActivity.this, BuyActivity.class);
                startActivity(i);
            }
        });

        btnLend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SellActivity.this, LendActivity.class);
                startActivity(i);
            }
        });
    }
    public void Upload(){
        String name = textName.getText().toString().trim();
        String about = textAbout.getText().toString().trim();
        String number = phoneNum.getText().toString().trim();

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(about) && !TextUtils.isEmpty(number)){
            String id = databaseProduct.push().getKey();

            Product product = new Product(id,name,about,number);
            databaseProduct.child(id).setValue(product);
            Toast.makeText(this, "Product you want to sell is Added", Toast.LENGTH_SHORT).show();

            Intent re = new Intent(SellActivity.this,SellActivity.class);
            startActivity(re);
        }

        else {
            Toast.makeText(this,"Empty fields",Toast.LENGTH_SHORT).show();
        }
    }
}
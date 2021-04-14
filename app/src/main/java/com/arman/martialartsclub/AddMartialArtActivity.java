package com.arman.martialartsclub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arman.martialartsclub.Model.DatabaseHandler;
import com.arman.martialartsclub.Model.MartialArt;
import com.shashank.sony.fancytoastlib.FancyToast;

public class AddMartialArtActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtName, edtPrice, edtColor;
    Button btnAdd, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_martial_art);

        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        edtColor = findViewById(R.id.edtColor);

        btnAdd = findViewById(R.id.btnAddMartialArt);
        btnBack = findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAddMartialArt){
            addMartialArtObjectToDatabase();
        } else if (v.getId() == R.id.btnBack){
            finish();
        }
    }

    private void addMartialArtObjectToDatabase() {
        String nameValue = edtName.getText().toString();
        String priceValue = edtPrice.getText().toString();
        String colorValue = edtColor.getText().toString();

        try {
            double priceDoubleValue = Double.parseDouble(priceValue);
            MartialArt mArt = new MartialArt(nameValue, priceDoubleValue, colorValue);
            DatabaseHandler mHandler = new DatabaseHandler(this);
            mHandler.addMartialArt(mArt);
            FancyToast.makeText(this, mArt+" Martial Art Object is added to Database", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
            mHandler.close();

        } catch (Exception e) {

        }

    }
}
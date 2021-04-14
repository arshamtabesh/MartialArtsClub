package com.arman.martialartsclub;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import com.arman.martialartsclub.Model.DatabaseHandler;
import com.arman.martialartsclub.Model.MartialArt;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.arman.martialartsclub.databinding.ActivityMainBinding;
import com.shashank.sony.fancytoastlib.FancyToast;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHandler mHandler;
    private double totalMartialArtsPrice;
    private ScrollView mScrollView;
    private int martialArtButtonWidth;

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        mHandler = new DatabaseHandler(this);
        totalMartialArtsPrice = 0.0;
        mScrollView = findViewById(R.id.scrollView);

        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        martialArtButtonWidth = screenSize.x / 2;

        modifyUserInterface();

    }

    private void modifyUserInterface() {
        ArrayList<MartialArt> martialArtObjects = mHandler.returnAllMartialArtObjects();
        mScrollView.removeAllViewsInLayout();
        if (martialArtObjects.size() > 0) {


            GridLayout mGridLayout = new GridLayout(this);
            mGridLayout.setRowCount((martialArtObjects.size() + 1) / 2);
            mGridLayout.setColumnCount(2);

            MartialArtButton[] mMartArtButtons = new MartialArtButton[martialArtObjects.size()];
            int index = 0;
            for (MartialArt mArt : martialArtObjects) {
                mMartArtButtons[index] = new MartialArtButton(this, mArt);
                mMartArtButtons[index].setText(mArt.getMartialArtID() + "\n" + mArt.getMartialArtName() + "\n" + mArt.getMartialArtPrice());
                switch (mArt.getMartialArtColor()) {
                    case "Red":
                        mMartArtButtons[index].setBackgroundColor(Color.RED);
                        break;
                    case "Orange":
                        mMartArtButtons[index].setBackgroundColor(Color.rgb(255, 69, 0));
                        break;
                    case "Blue":
                        mMartArtButtons[index].setBackgroundColor(Color.BLUE);
                        break;
                    case "Yellow":
                        mMartArtButtons[index].setBackgroundColor(Color.YELLOW);
                        break;
                    case "Black":
                        mMartArtButtons[index].setBackgroundColor(Color.BLACK);
                        break;
                    case "Purple":
                        mMartArtButtons[index].setBackgroundColor(Color.CYAN);
                        break;
                    case "Green":
                        mMartArtButtons[index].setBackgroundColor(Color.GREEN);
                        break;
                    case "White":
                        mMartArtButtons[index].setBackgroundColor(Color.WHITE);
                        break;
                    default:
                        mMartArtButtons[index].setBackgroundColor(Color.GRAY);
                }

                mMartArtButtons[index].setOnClickListener(this);
                mGridLayout.addView(mMartArtButtons[index],
                        martialArtButtonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);

            }

            mScrollView.addView(mGridLayout);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.add_martial_arts:
                Intent addMartialArtIntent = new Intent(MainActivity.this, AddMartialArtActivity.class);
                startActivity(addMartialArtIntent);
                break;
            case R.id.delete_martial_art:
                Intent deleteMartialArtIntent = new Intent(MainActivity.this, DeleteMartialArtActivity.class);
                startActivity(deleteMartialArtIntent);
                break;
            case R.id.update_martial_art:
                Intent updateMartialArtIntent = new Intent(MainActivity.this, UpdateMartialArtActivity.class);
                startActivity(updateMartialArtIntent);
                break;
            case R.id.martial_arts_prices_reset:
                totalMartialArtsPrice = 0.0;
                FancyToast.makeText(this, totalMartialArtsPrice+"", Toast.LENGTH_SHORT,
                        FancyToast.INFO, true).show();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View v) {

        MartialArtButton mMartialArtButton = (MartialArtButton) v;
        totalMartialArtsPrice = totalMartialArtsPrice + mMartialArtButton.getMartialArtPrice();
        String martialArtPriceFormatted = NumberFormat.getCurrencyInstance().format(totalMartialArtsPrice);

        FancyToast.makeText(this, martialArtPriceFormatted, Toast.LENGTH_SHORT,
                FancyToast.INFO, true).show();


    }

    @Override
    protected void onResume() {
        super.onResume();

        modifyUserInterface();
    }
}
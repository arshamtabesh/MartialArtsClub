package com.arman.martialartsclub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.arman.martialartsclub.Model.DatabaseHandler;
import com.arman.martialartsclub.Model.MartialArt;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.Formattable;

public class DeleteMartialArtActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private DatabaseHandler mHandler;
    RadioGroup mRadioGroup;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_martial_art);

        mHandler = new DatabaseHandler(this);

        updateTheUserInterface();

    }

    private void updateTheUserInterface() {

        ArrayList<MartialArt> allMartialArtObjects =
                mHandler.returnAllMartialArtObjects();

        RelativeLayout mRelativeLayout = new RelativeLayout(this);
        ScrollView mScrollView = new ScrollView(this);
        mRadioGroup = new RadioGroup(this);

        for (MartialArt mArt : allMartialArtObjects) {
            RadioButton currentRadioButton = new RadioButton(this);
            currentRadioButton.setId(mArt.getMartialArtID());
            currentRadioButton.setText(mArt.toString());
            mRadioGroup.addView(currentRadioButton);

        }
        mRadioGroup.setOnCheckedChangeListener(this);

        btnBack = new Button(this);
        btnBack.setText("Go Back");
        btnBack.setOnClickListener(this);

        mScrollView.addView(mRadioGroup);

        RelativeLayout.LayoutParams buttonParams =
                new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParams.setMargins(0,0,0,70);

        mRelativeLayout.addView(btnBack,buttonParams);
        ScrollView.LayoutParams scrollViewParams = new FrameLayout.LayoutParams(
                ScrollView.LayoutParams.MATCH_PARENT,
                ScrollView.LayoutParams.MATCH_PARENT
        );
        mRelativeLayout.addView(mScrollView,scrollViewParams);

        setContentView(mRelativeLayout);


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        mHandler.deleteMarschalArtObjectByID(checkedId);
        FancyToast.makeText(this, "The Material Art Object Is Deleted",
                Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
        updateTheUserInterface();
    }

    @Override
    public void onClick(View v) {
        finish();
    }


}
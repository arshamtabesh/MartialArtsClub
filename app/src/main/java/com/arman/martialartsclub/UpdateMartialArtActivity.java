package com.arman.martialartsclub;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.arman.martialartsclub.Model.DatabaseHandler;
import com.arman.martialartsclub.Model.MartialArt;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;

public class UpdateMartialArtActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_martial_art);

        mHandler = new DatabaseHandler(this);

        modifyUserInterface();
    }

    private void modifyUserInterface() {
        ArrayList<MartialArt> martialArtObjects = mHandler.returnAllMartialArtObjects();

        if (martialArtObjects.size() > 0) {

            ScrollView mScrollView = new ScrollView(this);
            GridLayout mGridLayout = new GridLayout(this);
            mGridLayout.setRowCount(martialArtObjects.size());
            mGridLayout.setColumnCount(5);

            TextView[] idTextViews = new TextView[martialArtObjects.size()];
            EditText[][] edtNamesPricesAndColors = new EditText[martialArtObjects.size()][3];

            Button[] modifyButtons = new Button[martialArtObjects.size()];

            Point screenSize = new Point();
            getWindowManager().getDefaultDisplay().getSize(screenSize);

            int screenWidth = screenSize.x;
            int index = 0;

            for (MartialArt martialArtObject : martialArtObjects) {
                idTextViews[index] = new TextView(this);
                idTextViews[index].setGravity(Gravity.CENTER);
                idTextViews[index].setText(martialArtObject.getMartialArtID() + "");
                edtNamesPricesAndColors[index][0] = new EditText(this);
                edtNamesPricesAndColors[index][1] = new EditText(this);
                edtNamesPricesAndColors[index][2] = new EditText(this);

                edtNamesPricesAndColors[index][0].setText(martialArtObject.getMartialArtName());
                edtNamesPricesAndColors[index][1].setText(martialArtObject.getMartialArtPrice() + "");
                edtNamesPricesAndColors[index][1].setInputType(InputType.TYPE_CLASS_NUMBER);
                edtNamesPricesAndColors[index][2].setText(martialArtObject.getMartialArtColor());

                edtNamesPricesAndColors[index][0].setId(martialArtObject.getMartialArtID() + 10);
                edtNamesPricesAndColors[index][1].setId(martialArtObject.getMartialArtID() + 20);
                edtNamesPricesAndColors[index][2].setId(martialArtObject.getMartialArtID() + 30);

                modifyButtons[index] = new Button(this);
                modifyButtons[index].setText("Modify");
                modifyButtons[index].setId(martialArtObject.getMartialArtID());
                modifyButtons[index].setOnClickListener(this);

                mGridLayout.addView(idTextViews[index], (int)(screenWidth * 0.05), ViewGroup.LayoutParams.WRAP_CONTENT);
                mGridLayout.addView(edtNamesPricesAndColors[index][0], (int) (screenWidth * 0.28), ViewGroup.LayoutParams.WRAP_CONTENT);
                mGridLayout.addView(edtNamesPricesAndColors[index][2], (int) (screenWidth * 0.20), ViewGroup.LayoutParams.WRAP_CONTENT);
                mGridLayout.addView(edtNamesPricesAndColors[index][1], (int) (screenWidth * 0.20), ViewGroup.LayoutParams.WRAP_CONTENT);
                mGridLayout.addView(modifyButtons[index], (int) (screenWidth * 0.25), ViewGroup.LayoutParams.WRAP_CONTENT);


                index++;
            }
            mScrollView.addView(mGridLayout);
            setContentView(mScrollView);
        }
    }

    @Override
    public void onClick(View v) {
        int martialArtObjectID = v.getId();
        EditText edtMartialArtName = (EditText) findViewById(martialArtObjectID + 10);
        EditText edtMartialArtPrice = (EditText) findViewById(martialArtObjectID + 20);
        EditText edtMartialArtColor = (EditText) findViewById(martialArtObjectID + 30);

        mHandler.modifyMarshalArtObject(martialArtObjectID, edtMartialArtName.getText().toString(),
                tryParseDouble(edtMartialArtPrice.getText().toString()), edtMartialArtColor.getText().toString());
        FancyToast.makeText(this, " Martial Art Object is Modified",
                FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();


    }

    public double tryParseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
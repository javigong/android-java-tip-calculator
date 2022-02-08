package com.example.tipcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity
                implements TextView.OnEditorActionListener, View.OnClickListener {
    private float tipPercent = .15f;

    //define variables for the user interface controls we want to interact with
    private EditText billAmountEditText;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;

    //define variable to show MainActivity life cycles
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "onCreate()");


        //get References to the UI controls
        billAmountEditText = findViewById(R.id.billAmountEditText);
        percentTextView = findViewById(R.id.percentTextView);
        Button percentUpButton = findViewById(R.id.percentUpButton);
        Button percentDownButton = findViewById(R.id.percentDownButton);
        tipTextView = findViewById(R.id.tipTextView);
        totalTextView = findViewById(R.id.totalTextView);

        //set the Listeners
        billAmountEditText.setOnEditorActionListener(this);
        percentDownButton.setOnClickListener(this);
        percentUpButton.setOnClickListener(this);
    }

    void calculateAndDisplay(){
        //get the bill amount
        String billAmountString = billAmountEditText.getText().toString();
        float billAmount;
        if (billAmountString.equals("")){
            billAmount = 0;
        }
        else {
            billAmount = Float.parseFloat(billAmountString);
        }

        //calculate tip and total
        float tipAmount = billAmount * tipPercent;
        float totalAmount = billAmount + tipAmount;

        //display the results with formatting
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipTextView.setText(currency.format(tipAmount));
        totalTextView.setText(currency.format(totalAmount));

        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTextView.setText(percent.format(tipPercent));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.percentDownButton:
                tipPercent = tipPercent - 0.01f;
                calculateAndDisplay();
                break;
            case R.id.percentUpButton:
                tipPercent = tipPercent + 0.01f;
                calculateAndDisplay();
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        calculateAndDisplay();
        return false;
    }

    //restore state when a saved state exists
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.i(TAG, "onRestoreInstanceState()");

        if (savedInstanceState != null) {
            percentTextView.setText(savedInstanceState.getString("percent"));
            tipTextView.setText(savedInstanceState.getString("tip"));
            totalTextView.setText(savedInstanceState.getString("total"));
        }
    }

    //get the state when save state
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.i(TAG, "onSaveInstanceState()");

        outState.putString("percent", percentTextView.getText().toString());
        outState.putString("tip", tipTextView.getText().toString());
        outState.putString("total", totalTextView.getText().toString());
    }

    //log each cycle to control the MainActivity life cycle

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }

}
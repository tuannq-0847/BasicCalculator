package com.example.basiccaculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterCaculator.OnItemClickListenner, View.OnClickListener {
    private RecyclerView mRcvCalculator;
    private ImageButton mIbDelete;
    private AdapterCaculator mAdapter;
    private String mTempRes1 = "";
    private int position;
    private ArrayList<String> mListOperators;
    private EditText mEdtRes;
    private double mTempRes = 0;
    private ArrayList<String> mListNumbers;
    private EditText mEdtInput;
    private String mKeyBoard;
    private List mListNumbers1;
    private char mOperators;
    private int mIndex = 0;
    private String mResult = "";
    private String mRevertResult = "";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponnets();
        addAll();
    }


    private void addAll() {
        String[] arrayNumbers = new String[]{"C", "()", "%", "/",
                "7", "8", "9", "X",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "T", "0", ".", "="};
        int i = 0;
        while (i < arrayNumbers.length) {

            mListNumbers.add(arrayNumbers[i]);
            i++;
        }
        Log.d("test", String.valueOf(mListNumbers.size()));
        for (int j = 0; j < mListNumbers.size(); j++) {
            Log.d("test", mListNumbers.get(j));
        }
        mAdapter = new AdapterCaculator(mListNumbers);
        mRcvCalculator.setAdapter(mAdapter);
        mRcvCalculator.setLayoutManager(new GridLayoutManager(this, 4));
        mAdapter.setOnItemClickListenner(this);

    }


    private void initComponnets() {
        mRcvCalculator = findViewById(R.id.rcvCaculator);
        mIbDelete = findViewById(R.id.ibDelete);
        mEdtInput = findViewById(R.id.edtInput);
        mEdtRes = findViewById(R.id.edtRes);
        mIbDelete.setOnClickListener(this);
        mListNumbers = new ArrayList<>();
        mListOperators = new ArrayList<>();
    }

    @Override
    public void onItemClicked(int position) {
        this.position = position;
        mKeyBoard = mListNumbers.get(position);
        switch (mKeyBoard) {
            case "C":
                mEdtInput.setText("");
                mResult = "";
                if (mListNumbers1 != null) {
                    mListNumbers1.clear();
                }
                break;
            case "+":
                mOperators = '+';
                mListOperators.add("+");
                pushToScreen();
                break;
            case "-":
                mOperators = '-';
                mListOperators.add("-");
                pushToScreen();
                break;
            case "/":
                mOperators = '/';
                mListOperators.add("/");
                pushToScreen();
                break;
            case "X":
                mOperators = 'X';
                mListOperators.add("X");
                pushToScreen();
                break;
            case "=":
                break;
            default:
                pushToScreen();
                break;
        }

    }


    private void pushToScreen() {
        mResult += mListNumbers.get(position);
        mEdtInput.setText(mResult);
        mTempRes1 = mResult.replaceAll("[^0-9]+", " ");
        ArrayList<String> listInput = new ArrayList<>(Arrays.asList(mTempRes1.trim().split(" ")));
        Log.d(TAG, "pushToScreen: " + Arrays.asList(mTempRes1.trim().split(" ")));
        for (int i = 0; i < listInput.size() - 1; i++) {
            switch (mListOperators.get(i)) {
                case "+":
                    if (i == 0) {
                        mTempRes = Double.parseDouble(String.valueOf(listInput.get(i)))
                                + Double.parseDouble(String.valueOf(listInput.get(i + 1)));
                    } else {
                        mTempRes = mTempRes
                                + Double.parseDouble(String.valueOf(listInput.get(i + 1)));
                    }
                    break;
                case "-":
                    if (i == 0) {
                        mTempRes = Double.parseDouble(String.valueOf(listInput.get(i)))
                                - Double.parseDouble(String.valueOf(listInput.get(i + 1)));
                    } else {
                        mTempRes = mTempRes - Double.parseDouble(String.valueOf(listInput.get(i + 1)));
                    }
                    break;
                case "/":
                    if (i == 0) {
                        mTempRes = Double.parseDouble(String.valueOf(listInput.get(i)))
                                / Double.parseDouble(String.valueOf(listInput.get(i + 1)));
                    } else {
                        mTempRes = mTempRes / Double.parseDouble(String.valueOf(listInput.get(i + 1)));
                    }
                    break;
                case "X":
                    if (i == 0) {
                        mTempRes = Double.parseDouble(String.valueOf(listInput.get(i)))
                                * Double.parseDouble(String.valueOf(listInput.get(i + 1)));
                    } else {
                        mTempRes = mTempRes * Double.parseDouble(String.valueOf(listInput.get(i + 1)));
                    }
                    break;
            }
        }
        mEdtRes.setText(String.valueOf(mTempRes));
        mTempRes = 0;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibDelete:
                if (mIndex > 0) {
                    deleteNumbers();
                } else if (mIndex == 0) {
                    mIndex = mResult.length();
                    deleteNumbers();
                }
                break;
        }
    }

    private String deleteNumbers() {
        mIndex--;
        for (int i = 0; i < mIndex; i++) {
            mRevertResult += mResult.charAt(i);
        }
        mEdtInput.setText(mRevertResult);
        mResult = mRevertResult;
        mRevertResult = "";
        return mRevertResult;
    }
}

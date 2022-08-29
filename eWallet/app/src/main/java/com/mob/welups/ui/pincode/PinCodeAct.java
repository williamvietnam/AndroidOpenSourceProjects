package com.mob.welups.ui.pincode;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mob.welups.ui.container.MainActivity;
import com.mob.welups.R;
import com.mob.welups.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

public class PinCodeAct extends BaseActivity {

    private final ArrayList<Integer> listPassCode = new ArrayList<>();
    private ArrayList<Integer> listPasswords = new ArrayList<>();

    @BindView(R.id.tvTitlePinCode)
    TextView tvTitlePinCode;
    @BindView(R.id.tvTitlePinCodeError)
    TextView tvTitlePinCodeError;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.circlePinCode1)
    ImageView imgCircle1;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.circlePinCode2)
    ImageView imgCircle2;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.circlePinCode3)
    ImageView imgCircle3;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.circlePinCode4)
    ImageView imgCircle4;

    @BindView(R.id.btnNumber0)
    RelativeLayout btnNumber0;
    @BindView(R.id.btnNumber1)
    RelativeLayout btnNumber1;
    @BindView(R.id.btnNumber2)
    RelativeLayout btnNumber2;
    @BindView(R.id.btnNumber3)
    RelativeLayout btnNumber3;
    @BindView(R.id.btnNumber4)
    RelativeLayout btnNumber4;
    @BindView(R.id.btnNumber5)
    RelativeLayout btnNumber5;
    @BindView(R.id.btnNumber6)
    RelativeLayout btnNumber6;
    @BindView(R.id.btnNumber7)
    RelativeLayout btnNumber7;
    @BindView(R.id.btnNumber8)
    RelativeLayout btnNumber8;
    @BindView(R.id.btnNumber9)
    RelativeLayout btnNumber9;
    @BindView(R.id.btnDeleteNumber)
    RelativeLayout btnDeleteNumber;

    @Override
    protected void initView(Bundle savedInstanceState) {
        hideItemActionBar();

        createData();
        registerListener();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pin_code;
    }

    private void registerListener() {
        btnNumber0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCircleActive(0);
                String number = listPassCode.toString();
                Log.d(PinCodeAct.class.getName(), number);
                passPinCode();
            }
        });
        btnNumber1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCircleActive(1);
                String number = listPassCode.toString();
                Log.d(PinCodeAct.class.getName(), number);
                passPinCode();
            }
        });
        btnNumber2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCircleActive(2);
                String number = listPassCode.toString();
                Log.d(PinCodeAct.class.getName(), number);
                passPinCode();
            }
        });
        btnNumber3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCircleActive(3);
                String number = listPassCode.toString();
                Log.d(PinCodeAct.class.getName(), number);
                passPinCode();
            }
        });
        btnNumber4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCircleActive(4);
                String number = listPassCode.toString();
                Log.d(PinCodeAct.class.getName(), number);
                passPinCode();
            }
        });
        btnNumber5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCircleActive(5);
                String number = listPassCode.toString();
                Log.d(PinCodeAct.class.getName(), number);
                passPinCode();
            }
        });
        btnNumber6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCircleActive(6);
                String number = listPassCode.toString();
                Log.d(PinCodeAct.class.getName(), number);
                passPinCode();
            }
        });
        btnNumber7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCircleActive(7);
                String number = listPassCode.toString();
                Log.d(PinCodeAct.class.getName(), number);
                passPinCode();
            }
        });
        btnNumber8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCircleActive(8);
                String number = listPassCode.toString();
                Log.d(PinCodeAct.class.getName(), number);
                passPinCode();
            }
        });
        btnNumber9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCircleActive(9);
                String number = listPassCode.toString();
                Log.d(PinCodeAct.class.getName(), number);
                passPinCode();
            }
        });
        btnDeleteNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listPassCode.size() > 5) {
                } else {
                    selectPositionCircleInactive();
                    if (listPassCode.size() <= 0) {
                        Log.d(PinCodeAct.class.getName(), "Sorry, you can't click continue!");
                        return;
                    }
                    listPassCode.remove(listPassCode.size() - 1);
                    String number = listPassCode.toString();
                    Log.d(PinCodeAct.class.getName(), number);
                    passPinCode();
                }
            }
        });
    }

    private void addCircleActive(int number) {
        if (listPassCode.size() < 4) {
            listPassCode.add(number);
            selectPositionCircleActive();
        }
    }

    private void selectPositionCircleActive() {
        if (listPassCode.size() > 4) {
            Log.d(PinCodeAct.class.getName(), "Sorry, you can't click continue!");
        }
        switch (listPassCode.size()) {
            case 1:
                imgCircle1.setImageResource(R.drawable.ic_circle_active);
                break;
            case 2:
                imgCircle2.setImageResource(R.drawable.ic_circle_active);
                break;
            case 3:
                imgCircle3.setImageResource(R.drawable.ic_circle_active);
                break;
            case 4:
                imgCircle4.setImageResource(R.drawable.ic_circle_active);
                break;
        }
    }


    private void selectPositionCircleInactive() {
        switch (listPassCode.size()) {
            case 1:
                imgCircle1.setImageResource(R.drawable.ic_circle_inactive);
                break;
            case 2:
                imgCircle2.setImageResource(R.drawable.ic_circle_inactive);
                break;
            case 3:
                imgCircle3.setImageResource(R.drawable.ic_circle_inactive);
                break;
            case 4:
                imgCircle4.setImageResource(R.drawable.ic_circle_inactive);
                break;
        }
    }

    private void passPinCode() {
        if (listPassCode.size() == 4) {
            if (listPassCode.equals(listPasswords)) {
                //show ra screen muon mo
            } else {
                // nhap lai pass:
                setTitlePinCode();
                Log.d(PinCodeAct.class.getName(), "Pass error!!!");
                resetImgCircle();
            }
        }
    }

    private void resetImgCircle() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //TODO
            }
        }, 1200);
        listPassCode.removeAll(listPassCode);
        Log.d(PinCodeAct.class.getName(), listPassCode.toString());
        imgCircle1.setImageResource(R.drawable.ic_circle_inactive);
        imgCircle2.setImageResource(R.drawable.ic_circle_inactive);
        imgCircle3.setImageResource(R.drawable.ic_circle_inactive);
        imgCircle4.setImageResource(R.drawable.ic_circle_inactive);
    }

    private void setTitlePinCode() {
        Handler handler = new Handler();
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                tvTitlePinCode.setVisibility(View.GONE);
                tvTitlePinCodeError.setVisibility(View.VISIBLE);
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                tvTitlePinCodeError.setVisibility(View.GONE);
                tvTitlePinCode.setVisibility(View.VISIBLE);
            }
        };
        handler.postDelayed(runnable1, 1000);
        handler.postDelayed(runnable2, 2000);
    }

    private void openMainAct() {
        Intent intent = new Intent(PinCodeAct.this, MainActivity.class);
        startActivity(intent);
    }

    private void createData() {
        listPasswords.add(1);
        listPasswords.add(2);
        listPasswords.add(3);
        listPasswords.add(4);
    }
}

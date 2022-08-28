package com.mob.welups.config;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.mob.welups.R;

public class CustomToast extends Toast {
    private Context context;

    public CustomToast(Context context) {
        super(context);
        this.context = context;
    }

    @SuppressLint("WrongConstant")
    public Toast makeCopySuccess(){
        Toast toast = new Toast(context);
        toast.setDuration(600);
        toast.setGravity(Gravity.BOTTOM, 0,0);
        View mView = LayoutInflater.from(context).inflate(R.layout.layout_copy_success_toast, null, false);
        toast.setView(mView);

        return toast;
    }

    @SuppressLint("WrongConstant")
    public Toast makeError(){
        Toast toast = new Toast(context);
        toast.setDuration(600);
        toast.setGravity(Gravity.BOTTOM, 0,0);
        View mView = LayoutInflater.from(context).inflate(R.layout.layout_error_toast, null, false);
        toast.setView(mView);

        return toast;
    }
}

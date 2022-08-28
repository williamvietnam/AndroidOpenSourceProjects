package com.mob.welups.view.pincode;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class PinCodeViewModel extends ViewModel {

    private ArrayList<Integer> passwords = new ArrayList<>();
    private ArrayList<Integer> listNumbers = new ArrayList<>();

    public void createValueForPassword() {
        passwords.add(1);
        passwords.add(2);
        passwords.add(3);
        passwords.add(4);
        Log.d(PinCodeAct.class.getName(),"Created Value For Password Success!");
    }

    public boolean isPassPinCode(ArrayList<Integer> passwords) {
        for(int i = 0;i<listNumbers.size();i++){
            if(listNumbers.get(i)!=passwords.get(i)){
                return false;
            }
        }
        return true;
    }

    public String convertArrayListIntToString(ArrayList<Integer> integerArrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = integerArrayList.size() - 1; i >= 0; i--) {
            int num = integerArrayList.get(i);
            stringBuilder.append(num);
        }
        return stringBuilder.reverse().toString();
    }

    private int convertArrayListIntToInt(ArrayList<Integer> integerArrayList) {
        //TODO
        return 1;
    }


}

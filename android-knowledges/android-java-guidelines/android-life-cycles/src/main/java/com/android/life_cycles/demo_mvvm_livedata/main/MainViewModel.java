package com.android.life_cycles.demo_mvvm_livedata.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<Integer> inputX = new MutableLiveData<>();
    private final MutableLiveData<Integer> inputY = new MutableLiveData<>();

    private final MutableLiveData<Integer> sum = new MutableLiveData<>();
    private final MutableLiveData<Integer> difference = new MutableLiveData<>();
    private final MutableLiveData<Integer> product = new MutableLiveData<>();
    private final MutableLiveData<Integer> division = new MutableLiveData<>();

    private MutableLiveData<Integer> result = new MutableLiveData<>();

    public MainViewModel() {
        inputX.setValue(0);
        inputY.setValue(0);
    }

    public MutableLiveData<Integer> getInputX() {
        return inputX;
    }

    public MutableLiveData<Integer> getInputY() {
        return inputY;
    }

    public MutableLiveData<Integer> getSum() {
        return sum;
    }

    public MutableLiveData<Integer> getDifference() {
        return difference;
    }

    public MutableLiveData<Integer> getProduct() {
        return product;
    }

    public MutableLiveData<Integer> getResult() {
        return result;
    }

    public void setInputX(int value) {
        inputX.setValue(value);
    }

    public void setInputY(int value) {
        inputY.setValue(value);
    }

    //tăng 1 với input x
    public void increaseInputX() {
        inputX.setValue(inputX.getValue() + 1);
    }

    //giảm 1 với input x
    public void reductionInputX() {
        inputX.setValue(inputX.getValue() - 1);
    }

    public void increaseInputY() {
        inputY.setValue(inputY.getValue() + 1);
    }

    public void reductionInputY() {
        inputY.setValue(inputY.getValue() - 1);
    }

    public void setSum() {
        sum.setValue(inputX.getValue() + inputY.getValue());
    }

    public void setDifference() {
        difference.setValue(inputX.getValue() - inputY.getValue());
    }

    public void setProduct() {
        product.setValue(inputX.getValue() * inputY.getValue());
    }

    public void setResult(MutableLiveData<Integer> result) {
        this.result = result;
    }
}

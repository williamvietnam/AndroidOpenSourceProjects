package com.android.life_cycles.demo_mvvm_databinding;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    private final User user = new User();

    private MutableLiveData<String> firstNameOutput = new MutableLiveData<>();
    private MutableLiveData<String> lastNameOutput = new MutableLiveData<>();

    public void onSubmit() {
        firstNameOutput.setValue(getFirstName());
        lastNameOutput.setValue(getLastName());
    }

    public MutableLiveData<String> getFirstNameOutput() {
        return firstNameOutput;
    }

    public MutableLiveData<String> getLastNameOutput() {
        return lastNameOutput;
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public void setFirstName(String firstName) {
        user.setFirstName(firstName);
    }

    public String getLastName() {
        return user.getLastName();
    }

    public void setLastName(String lastName) {
        user.setLastName(lastName);
    }

    public String getUserName() {
        return user.getUserName();
    }

    public void setUserName(String userName) {
        user.setUserName(userName);
    }

    public String getPassword() {
        return user.getPassword();
    }

    public void setPassword(String password) {
        user.setPassword(password);
    }

    public String getPhone() {
        return user.getPhone();
    }

    public void setPhone(String phone) {
        user.setPhone(phone);
    }

    public String getAddress() {
        return user.getAddress();
    }

    public void setAddress(String address) {
        user.setAddress(address);
    }
}

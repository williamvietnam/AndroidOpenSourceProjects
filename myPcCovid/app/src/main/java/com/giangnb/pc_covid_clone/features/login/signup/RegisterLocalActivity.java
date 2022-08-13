package com.giangnb.pc_covid_clone.features.login.signup;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.giangnb.pc_covid_clone.data.room.UserDao;
import com.giangnb.pc_covid_clone.data.room.UserDatabase;
import com.giangnb.pc_covid_clone.data.room.UserEntity;
import com.giangnb.pc_covid_clone.databinding.ActivityRegisterLocalBinding;
import com.giangnb.pc_covid_clone.features.login.signin.LoginLocalActivity;

public class RegisterLocalActivity extends AppCompatActivity
        implements RegisterContract.RegisterLocalView {
    private ActivityRegisterLocalBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterLocalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAccount();
            }
        });
    }


    @Override
    public void registerAccount() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(binding.userId.getText().toString());
        userEntity.setPassword(binding.password.getText().toString());
        if(validateInput(userEntity)){
            UserDatabase userDatabase = UserDatabase.getUserDatabase(this);
            final UserDao userDao = userDatabase.userDao();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    userDao.registerUser(userEntity);
                    Log.d("register", "Register success");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "User Register Success", Toast.LENGTH_SHORT).show();
                            openLoginLocalScreen();
                        }
                    });
                }
            }).start();
        }
    }

    private boolean validateInput(UserEntity userEntity) {
        if(userEntity.getUserId().isEmpty()||userEntity.getPassword().isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public void openLoginLocalScreen() {
        Intent intent = new Intent(this, LoginLocalActivity.class);
        startActivity(intent);
        finish();
    }

}

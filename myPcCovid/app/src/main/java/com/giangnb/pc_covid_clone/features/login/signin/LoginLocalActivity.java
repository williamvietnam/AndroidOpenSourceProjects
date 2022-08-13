package com.giangnb.pc_covid_clone.features.login.signin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.giangnb.pc_covid_clone.data.room.UserDao;
import com.giangnb.pc_covid_clone.data.room.UserDatabase;
import com.giangnb.pc_covid_clone.data.room.UserEntity;
import com.giangnb.pc_covid_clone.databinding.ActivityLoginLocalBinding;
import com.giangnb.pc_covid_clone.features.home.MainActivity;
import com.giangnb.pc_covid_clone.features.login.LoginContract;

public class LoginLocalActivity extends AppCompatActivity
        implements LoginContract.LoginLocalView {

    private ActivityLoginLocalBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginLocalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    @Override
    public void signIn() {
        String userIdText = binding.userId.getText().toString();
        String passText = binding.password.getText().toString();
        if (userIdText.isEmpty() || passText.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Fill All Fields", Toast.LENGTH_SHORT).show();
            Log.d("login", "Login Fail");
        }else{
            UserDatabase userDatabase = UserDatabase.getUserDatabase(this);
            UserDao userDao = userDatabase.userDao();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    UserEntity userEntity = userDao.login(userIdText,passText);
                    if(userEntity==null){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else{
                        openMainScreen();
                    }
                }
            }).start();
        }
    }

    @Override
    public void openMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

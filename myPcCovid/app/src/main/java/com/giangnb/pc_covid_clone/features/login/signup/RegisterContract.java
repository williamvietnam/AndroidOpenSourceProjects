package com.giangnb.pc_covid_clone.features.login.signup;

import com.giangnb.pc_covid_clone.data.room.UserEntity;

public interface RegisterContract {
    interface RegisterLocalView{
        void openLoginLocalScreen();
        void registerAccount();
//        boolean validateInput(UserEntity userEntity);
    }

    interface RegisterRemoteView{

    }
}

package com.giangnb.pc_covid_clone.features.login;

public interface LoginContract {
    interface LoginView {
        void initEventsClick();

        void openLoginLocalScreen();

        void openLoginRemoteScreen();

        void openRegisterLocalScreen();

        void openRegisterRemoteScreen();
    }

    interface LoginLocalView {
        void openMainScreen();
        void signIn();

    }

    interface LoginRemoteView {
        void openMainScreen();
    }
}

package com.giangnb.pc_covid_clone.features.menu;

public interface MenuContract {
    interface View{
        void initListenerOnClick();
    }

    interface ViewModel{
        void showDialogHotline();
        void onBackPressed();
    }
}

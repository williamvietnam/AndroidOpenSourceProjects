package com.giangnb.pc_covid_clone.features.home;

public interface HomeContract {
    interface View{
        void initListenerOnClick();
    }

    interface ViewModel{
        void scannerQrCode();
//        void requestPermissionCAM();
    }
}

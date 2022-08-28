package com.giangnb.pc_covid_clone.features.notify;

import com.giangnb.pc_covid_clone.data.model.Notify;

import java.util.ArrayList;

public interface NotifyContract {
    interface View{
        void showDataForNotifyList();
    }

    interface ViewModel{
        ArrayList<Notify> fakeUiForAdapter();
    }
}

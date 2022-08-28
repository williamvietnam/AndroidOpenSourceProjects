package com.giangnb.pc_covid_clone.features.menu.featuresOfMenu.checkin;

import com.giangnb.pc_covid_clone.data.model.CheckInHistory;

import java.util.ArrayList;

public interface CheckInHistoryContract {
    interface View {

    }

    interface ViewModel{
        ArrayList<CheckInHistory> mockDataUiForAdapter();
    }
}

package com.giangnb.pc_covid_clone.features.menu.featuresOfMenu.checkin;

import com.giangnb.pc_covid_clone.data.model.CheckInHistory;

import java.util.ArrayList;

/*
 * Xu ly logic, mock data,...
 * ben View chi can lay method o ViewModel de su dung
 * */
public class CheckInHistoryViewModel implements CheckInHistoryContract.ViewModel {

    @Override
    public ArrayList<CheckInHistory> mockDataUiForAdapter() {
        ArrayList<CheckInHistory> list = new ArrayList<>();

        CheckInHistory checkInHistory0 = new CheckInHistory("28/12/2021","12:00","VinHome - Hanoi","87/126 - Ba Trieu - Hoan Kiem");
        list.add(checkInHistory0);

        CheckInHistory checkInHistory1 = new CheckInHistory("29/12/2021","09:00","VinCom - Hanoi","92/34 - Pho Hue - Hoan Kiem");
        list.add(checkInHistory1);

        CheckInHistory checkInHistory2 = new CheckInHistory("30/12/2021","10:00","VinUni - HCM","So 1 - Dai Co Viet - Hai Ba Trung");
        list.add(checkInHistory2);

        CheckInHistory checkInHistory3 = new CheckInHistory("31/12/2021","09:00","VinMart - Saigon","56 - TT4 - KDT TPGL - Co Nhue 1 - Bac Tu Liem");
        list.add(checkInHistory3);

        CheckInHistory checkInHistory4 = new CheckInHistory("1/1/2022","08:30","VietTower - Hanoi","So 6 - duong Thien Than - phuong Thien Duong - quan Thien Dang");
        list.add(checkInHistory4);

        CheckInHistory checkInHistory5 = new CheckInHistory("2/1/2022","19:56","TTTM - Danang","So 9 - pho Den Do - xa Den Xanh - huyen Den Vang");
        list.add(checkInHistory5);

        return list;
    }
}

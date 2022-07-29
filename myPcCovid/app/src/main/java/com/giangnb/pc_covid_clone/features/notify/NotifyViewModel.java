package com.giangnb.pc_covid_clone.features.notify;

import com.giangnb.pc_covid_clone.data.model.Notify;

import java.util.ArrayList;

public class NotifyViewModel implements NotifyContract.ViewModel{


    @Override
    public ArrayList<Notify> fakeUiForAdapter() {
        ArrayList<Notify> notifyList = new ArrayList<>();

        Notify notify1 = new Notify("24/12/2021","Bạn đã cập nhật số điện thoại thành công");
        notifyList.add(notify1);
        Notify notify2 = new Notify("3/12/2021","Chúc mừng bạn đã hoàn thành công việc");
        notifyList.add(notify2);
        Notify notify3 = new Notify("15/12/2021","Bạn đã trúng một tỷ đồng cho lần cập nhật này");
        notifyList.add(notify3);
        Notify notify4 = new Notify("24/11/2021","Cảm ơn bạn đã cập nhật số điện thoại");
        notifyList.add(notify4);
        Notify notify5 = new Notify("11/10/2021","Bạn đã chuyển thành công 2 tỷ cho F1");
        notifyList.add(notify5);

        return notifyList;
    }
}

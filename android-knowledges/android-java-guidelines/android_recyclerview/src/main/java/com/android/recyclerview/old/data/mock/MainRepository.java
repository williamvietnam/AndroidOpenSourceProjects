package com.android.recyclerview.old.data.mock;

import com.android.recyclerview.R;
import com.android.recyclerview.old.models.SubItem;

import java.util.ArrayList;

public class MainRepository {
    public ArrayList<SubItem> dataMockItemChild1() {
        ArrayList<SubItem> listItemChildOne = new ArrayList<>();

        listItemChildOne.add(new SubItem(0, R.drawable.bg_music_2));
        listItemChildOne.add(new SubItem(1, R.drawable.bg_music_1));
        listItemChildOne.add(new SubItem(2, R.drawable.bg_music_3));
        listItemChildOne.add(new SubItem(3, R.drawable.ic_music_1));
        listItemChildOne.add(new SubItem(4, R.drawable.bg_music_2));
        listItemChildOne.add(new SubItem(5, R.drawable.ic_music2));

        return listItemChildOne;
    }

    public ArrayList<SubItem> dataMockItemChild2() {
        ArrayList<SubItem> listItemChildTwo = new ArrayList<>();

        listItemChildTwo.add(new SubItem(0, R.drawable.ic_music2, "Bài 1"));
        listItemChildTwo.add(new SubItem(1, R.drawable.ic_music_2, "Bài 2"));
        listItemChildTwo.add(new SubItem(2, R.drawable.ic_music2, "Bài 3"));
        listItemChildTwo.add(new SubItem(3, R.drawable.ic_music_2, "Bài 4"));
        listItemChildTwo.add(new SubItem(4, R.drawable.ic_music2, "Bài 5"));
        listItemChildTwo.add(new SubItem(5, R.drawable.ic_music_2, "Bài 6"));

        return listItemChildTwo;
    }

    public ArrayList<SubItem> dataMockItemChild3() {
        ArrayList<SubItem> listItemChildThree = new ArrayList<>();

        listItemChildThree.add(new SubItem(0, R.drawable.ic_music2, "Bài 1"));
        listItemChildThree.add(new SubItem(1, R.drawable.bg_music_1, "Bài 2"));
        listItemChildThree.add(new SubItem(2, R.drawable.bg_music_3, "Bài 3"));
        listItemChildThree.add(new SubItem(3, R.drawable.ic_album_songs, "Bài 4"));
        listItemChildThree.add(new SubItem(4, R.drawable.bg_music_2, "Bài 5"));
        listItemChildThree.add(new SubItem(5, R.drawable.ic_music3, "Bài 6"));

        return listItemChildThree;
    }

    public ArrayList<SubItem> dataMockItemChildOne() {
        ArrayList<SubItem> listItemChildOne = new ArrayList<>();

        listItemChildOne.add(new SubItem(R.drawable.ic_album_songs));
        listItemChildOne.add(new SubItem(R.drawable.ic_music_1));
        listItemChildOne.add(new SubItem(R.drawable.bg_music_1));
        listItemChildOne.add(new SubItem(R.drawable.ic_music3));
        listItemChildOne.add(new SubItem(R.drawable.ic_music_1));
        listItemChildOne.add(new SubItem(R.drawable.ic_music_1));

        return listItemChildOne;
    }

    public ArrayList<SubItem> dataMockItemChildTwo() {
        ArrayList<SubItem> listItemChildTwo = new ArrayList<>();

        listItemChildTwo.add(new SubItem(R.drawable.ic_music3, "Bài 1"));
        listItemChildTwo.add(new SubItem(R.drawable.ic_music2, "Bài 2"));
        listItemChildTwo.add(new SubItem(R.drawable.ic_music2, "Bài 3"));
        listItemChildTwo.add(new SubItem(R.drawable.ic_music3, "Bài 4"));
        listItemChildTwo.add(new SubItem(R.drawable.ic_music_2, "Bài 5"));
        listItemChildTwo.add(new SubItem(R.drawable.ic_music_2, "Bài 6"));

        return listItemChildTwo;
    }

    public ArrayList<SubItem> dataMockItemChildThree() {
        ArrayList<SubItem> listItemChildThree = new ArrayList<>();

        listItemChildThree.add(new SubItem(R.drawable.bg_music_3, "Bài 1"));
        listItemChildThree.add(new SubItem(R.drawable.ic_album_songs, "Bài 2"));
        listItemChildThree.add(new SubItem(R.drawable.bg_music_3, "Bài 3"));
        listItemChildThree.add(new SubItem(R.drawable.ic_album_songs, "Bài 4"));
        listItemChildThree.add(new SubItem(R.drawable.bg_music_3, "Bài 5"));
        listItemChildThree.add(new SubItem(R.drawable.ic_album_songs, "Bài 6"));

        return listItemChildThree;
    }
}

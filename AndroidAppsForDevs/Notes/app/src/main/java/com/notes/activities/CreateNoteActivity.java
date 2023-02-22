package com.notes.activities;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.notes.databinding.ActivityCreateNoteBinding;

public class CreateNoteActivity extends AppCompatActivity {
    private ActivityCreateNoteBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityCreateNoteBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}

package com.example.roonrxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewActivity extends AppCompatActivity {
    private TextView viewPersons;
    private LocalPersonDataSource localPersonDataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        viewPersons = findViewById(R.id.view_persons);
        localPersonDataSource = new LocalPersonDataSource(this);
        localPersonDataSource.getPersons(viewPersons);
    }
}
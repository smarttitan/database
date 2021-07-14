package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

//
import android.widget.Button;
import android.widget.Toast;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    // Variables
    protected Button b1;
    protected EditText e1;
    protected DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tittle
        setTitle("Add Account");

        setContentView(R.layout.activity_account1);

        // Initiate Views
        e1 = (EditText) findViewById(R.id.edit_text1);
        b1 = (Button) findViewById(R.id.button1);

        // Object dbManager
        dbManager = new DBManager(this);

        // On Click Listener Method
        b1.setOnClickListener(this);
    }

    // On Click Button
    @Override
    public void onClick(View v) {

        // Add Account Button
        switch (v.getId()) {
            case R.id.button1:

                // Open Database
                dbManager.open();

                // Insert Data to Database
                final String add = e1.getText().toString();
                dbManager.insert(add);

                // Fetch Data to Log
                dbManager.fetch();

                // Close Database
                dbManager.close();

                break;

        }

    }

}
package com.example.sqlite;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addrecordActivity extends Dialog implements View.OnClickListener {
    public Activity activity;
    //public Dialog d;
    //public Button btn;
    Button btn1;
    EditText txt1;
    EditText txt2;
    String t1,t2;
    public addrecordActivity(@NonNull Activity context) {
        super(context);
        this.activity=context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrecord);

        txt1=findViewById(R.id.txt_name);
        txt2=findViewById(R.id.txt_currency);
        btn1=findViewById(R.id.btn_Submit);
        btn1.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        t1 = String.valueOf(txt1.getText());
        t2 = String.valueOf(txt2.getText());
        DBManager manager = new DBManager(getContext());
        manager.open();
        manager.Insert(t1,t2);
        manager.Close();

        Intent intent = new Intent(activity,MainActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        //Intent intent= new Intent(addrecordActivity.this, MainActivity.class);
       // startActivity(intent);
       // dismiss();
    }
}
package com.example.sqlite;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab_add;
    List<String>  SimpleAdapter;
    ListView LV_country;
    //private String m_Text = "";
    DBManager dbManager;
    final String [] fromDatabase = new String[]{
            DBHelper._ID,
            DBHelper._NAME,
            DBHelper._CURRENCY
    };
    final int [] to = new int[]{
            R.id.tv_id,
            R.id.tv_country,
            R.id.tv_currency
    };
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DBManager(this);
        dbManager.open();
        FetchDatabase();
       addrecordActivity addrecord = new addrecordActivity(this);
//        SimpleAdapter= new ArrayList<>();
//        SimpleAdapter.add("India");
//        SimpleAdapter.add("USA");
//        SimpleAdapter.add("Japan");
//        SimpleAdapter.add("England");
//        SimpleAdapter.add("China");
//        SimpleAdapter.add("Denmark");
//        SimpleAdapter.add("Iceland");
//        SimpleAdapter.add("Greenland");
//        SimpleAdapter.add("Nepal");
//        LV_country=findViewById(R.id.country);
//        registerForContextMenu(LV_country);
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, SimpleAdapter);
//        LV_country.setAdapter(adapter);
        fab_add =(FloatingActionButton) findViewById(R.id.fab_add_Country);
        //registerForContextMenu(LV_country);


        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"FAB Clicked", Snackbar.LENGTH_LONG).show();
                addrecord.show();
            }
        });
    }
    private void FetchDatabase() {
        Toast.makeText(getApplicationContext(), "Database Fetch", Toast.LENGTH_SHORT).show();
        Cursor fetch = dbManager.fetch();
        dbManager.Close();

        adapter = new SimpleCursorAdapter(getApplicationContext(),R.layout.layout_view_countries,fetch,fromDatabase,to);
        ListView listView=(ListView) findViewById(R.id.country);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        registerForContextMenu(listView);
    }
    //menu creation code
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== R.id.add) {
            Toast.makeText(this, "New Data Entered", Toast.LENGTH_SHORT).show();
        }
            return super.onOptionsItemSelected(item);
    }
    //menu2 creation
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context,menu);
        super.onCreateContextMenu(menu,v,menuInfo);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case  R.id.options_menu_edit:

                Toast.makeText(getApplicationContext(), "Edited", Toast.LENGTH_SHORT).show();
                break;
            case R.id.options_menu_delete:
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
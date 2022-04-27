package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //UI
    EditText etxt_name, etxt_age;
    Switch sw_activeCustomer;
    Button btn_viewAll, btn_Add;

    //Listview and adapter
    ListView lv_customerList;
    ArrayAdapter customerArrayAdapter;

    //database elements
    DataBaseHelper dataBaseHelper;
    List<CustomerModel> everyone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etxt_name = findViewById(R.id.etxt_name);
        etxt_age = findViewById(R.id.etxt_age);
        sw_activeCustomer = findViewById(R.id.sw_activeCustomer);
        btn_viewAll = findViewById(R.id.btn_viewAll);
        btn_Add = findViewById(R.id.btn_add);
        lv_customerList = findViewById(R.id.lv_customerList);

        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        showCustomerOnListView(dataBaseHelper);

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CustomerModel customerModel;

                try {

                    customerModel = new CustomerModel(-1,
                            etxt_name.getText().toString(),
                            Integer.parseInt(etxt_age.getText().toString()),
                            sw_activeCustomer.isChecked());

                } catch (Exception e){

                    Toast.makeText(MainActivity.this, "Error creating customer.", Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel(-1,"Error",0,false);

                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                boolean success = dataBaseHelper.addOne(customerModel);

                showCustomerOnListView(dataBaseHelper);

                Toast.makeText(MainActivity.this, "Success = "+success, Toast.LENGTH_SHORT).show();

            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dataBaseHelper = new DataBaseHelper(MainActivity.this);

                showCustomerOnListView(dataBaseHelper);

            }
        });

        lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                CustomerModel clickedCustomer = (CustomerModel) adapterView.getItemAtPosition(i);
                dataBaseHelper.deleteOne(clickedCustomer);
                showCustomerOnListView(dataBaseHelper);
                Toast.makeText(MainActivity.this, "Deleted = "+clickedCustomer.toString(), Toast.LENGTH_SHORT).show();


            }
        });




    } // FINAL OF ONCREATE

    private void showCustomerOnListView(DataBaseHelper dataBaseHelper1) {

        customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                dataBaseHelper.getEveryone());
        lv_customerList.setAdapter(customerArrayAdapter);

    }
}
//MainActivity.java
package com.example.sheoran.mykeep2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText amount;
    TextView database;
    dbHandler dbhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=(EditText) findViewById(R.id.name);
        amount=(EditText) findViewById(R.id.amount);
        database=(TextView) findViewById(R.id.database);
        dbhandler=new dbHandler(this,null,null,1);
        printDatabase();
    }

    public void printDatabase()
    {
        String dbString=dbhandler.printBill();
        database.setText(dbString);
        name.setText("");
        amount.setText("");
    }

    public void AddButtonClicked(View view)
    {
        products product = new products(Integer.parseInt(amount.getText().toString()),name.getText().toString());
        dbhandler.addBill(product);
        printDatabase();
    }

    public void DeleteButtonClicked(View view)
    {

        dbhandler.deleteBill(name.getText().toString());
        printDatabase();
    }
}

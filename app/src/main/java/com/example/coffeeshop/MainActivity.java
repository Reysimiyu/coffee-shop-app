package com.example.coffeeshop;

import static java.lang.String.valueOf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.HttpCookie;

public class MainActivity extends AppCompatActivity {
    Button minus, plus, btn1, order_btn,confirm;
    TextView noOfCoffee,my_order;
    LinearLayout chan, toppings;
    int coffees = 1;
    CheckBox chapati, mandazi, pilau, ugali;
    String message;
    boolean pchapati = false, pmandazi = false, pugali = false, ppilau = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        noOfCoffee = findViewById(R.id.noOfCoffee);
        chapati = findViewById(R.id.chapati);
        mandazi = findViewById(R.id.mandazi);
        ugali = findViewById(R.id.ugali);
        chan = findViewById(R.id.clayout);
        toppings = findViewById(R.id.toppings_layout);
        order_btn=findViewById(R.id.order_button);
        my_order=findViewById(R.id.my_order);
        confirm=findViewById(R.id.confirm_button);
        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int coffee_price=billing();
                message="Your order: \n"+"number of coffee :" +coffees + "\nprice :" +coffee_price;
                my_order.setText(message);
                confirm.setVisibility(View.VISIBLE);


            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                emailing(message);
            }
        });
    }

    private void emailing(String message) {
        String[] emails={"reinhardsiminyu1@gmail.com"};
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("plain/Text");
        intent.putExtra(Intent.EXTRA_EMAIL,emails);
        intent.putExtra(Intent.EXTRA_SUBJECT,"coffee order");
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.gray:
                chan.setBackgroundResource(R.color.gray);
                return true;
            case R.id.maroon:
                chan.setBackgroundResource(R.color.maroon);
                return true;
            case R.id.orange:
                chan.setBackgroundResource(R.color.orange);
                return true;
            case R.id.green:
                chan.setBackgroundResource(R.color.palegreen);
                return true;
            case R.id.purple:
                chan.setBackgroundResource(R.color.purple);
                return true;


            case R.id.hide_Toppings:
                toppings.setVisibility(View.GONE);
                return true;

            case R.id.show_toppings:
                _Toppings:
                toppings.setVisibility(View.VISIBLE);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void plus(View view) {
        /* Toast.makeText(getApplicationContext(),"plus clicked",Toast.LENGTH_LONG).show();*/
        coffees++;
        Display1();

    }

    public void minus(View view) {
        coffees--;
        Display1();

        /*Toast.makeText(getApplicationContext(),"minus clicked",Toast.LENGTH_LONG).show();*/

    }

    public void Display1() {
        if (coffees >= 1 && coffees <= 10) {
            noOfCoffee.setText(valueOf(coffees));
        } else {
            Toast.makeText(getApplicationContext(), "invalid number of order", Toast.LENGTH_LONG).show();

        }
    }

    public void check() {
        if (chapati.isChecked() && mandazi.isChecked() && ugali.isChecked() && chapati.isChecked()) {
            pchapati = true;
            pmandazi = true;
            pugali = true;
            ppilau = true;
        } else if (chapati.isChecked() && mandazi.isChecked() && ugali.isChecked() && chapati.isChecked()) {
            pchapati = true;
            pmandazi = false;
            pugali = false;
            ppilau = false;
        }
        if (chapati.isChecked() && mandazi.isChecked() && ugali.isChecked() && chapati.isChecked()) {
            pchapati = true;
            pmandazi = false;
            pugali = false;
            ppilau = false;

        }
        if (chapati.isChecked() && mandazi.isChecked() && ugali.isChecked() && chapati.isChecked()) {
            pchapati = false;
            pmandazi = false;
            pugali = true;
            ppilau = false;

        }
        if (chapati.isChecked() && mandazi.isChecked() && ugali.isChecked() && chapati.isChecked()) {
            pchapati = false;
            pmandazi = false;
            pugali = false;
            ppilau = true;
        }
        check();
    }

    public void change(View view) {
        chan.setBackgroundResource(R.color.purple_500);
    }


    public int billing() {

                return coffees*15;

    }
}
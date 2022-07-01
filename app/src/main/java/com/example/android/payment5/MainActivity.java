package com.example.android.payment5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {
    CheckBox cb1,cb2,cb3;
    TextView pr1,pr2,pr3,fprice,pstatus;
    Button paybtn;
    int famount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb1=findViewById(R.id.checkBox1);
        cb2=findViewById(R.id.checkBox2);
        cb3=findViewById(R.id.checkBox3);
        pr1=findViewById(R.id.price1);
        pr2=findViewById(R.id.price2);
        pr3=findViewById(R.id.price3);
        pstatus=findViewById(R.id.pstatus);
        fprice=findViewById(R.id.totaltext);
        paybtn=findViewById(R.id.idBtnPay);

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cb1.isChecked())
                {
                    int price=Integer.parseInt(pr1.getText().toString());
                    famount=famount+price;
                    fprice.setText("Total :"+famount);
                }
                else
                {
                    int price=Integer.parseInt(pr1.getText().toString());
                    famount=famount-price;
                    fprice.setText("Total :"+famount);
                }
            }
        });

        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cb2.isChecked())
                {
                    int price=Integer.parseInt(pr2.getText().toString());
                    famount=famount+price;
                    fprice.setText("Total :"+famount);
                }
                else
                {
                    int price=Integer.parseInt(pr2.getText().toString());
                    famount=famount-price;
                    fprice.setText("Total :"+famount);
                }
            }
        });

        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cb3.isChecked())
                {
                    int price=Integer.parseInt(pr3.getText().toString());
                    famount=famount+price;
                    fprice.setText("Total :"+famount);
                }
                else
                {
                    int price=Integer.parseInt(pr3.getText().toString());
                    famount=famount-price;
                    fprice.setText("Total :"+famount);
                }
            }
        });

        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String samount = String.valueOf(famount);
                int amount = Math.round(Float.parseFloat(samount) * 100);

                Checkout checkout = new Checkout();
                checkout.setKeyID("rzp_test_bUcPHww52nISPv");
                checkout.setImage(R.drawable.brand);

                JSONObject object = new JSONObject();
                try {
                    object.put("name", "PizzaHut");
                    object.put("description", "Pizza Order Payment");
                    object.put("theme.color", "");
                    object.put("amount", amount);
                    object.put("prefill.contact", "9031799582");
                    object.put("prefill.email", "avinash@gmail.com");
                    checkout.open(MainActivity.this, object);
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        pstatus.setText("Order Successfully. Transaction No :"+s);
        fprice.setText("0.00");
        cb1.setChecked(false);
        cb2.setChecked(false);
        cb3.setChecked(false);
    }

    @Override
    public void onPaymentError(int i, String s) {
        pstatus.setText("Something went wrong"+s);
        fprice.setText("0.00");
        cb1.setChecked(false);
        cb2.setChecked(false);
        cb3.setChecked(false);
    }
}
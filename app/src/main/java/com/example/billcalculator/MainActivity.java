package com.example.billcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
TextView BilledAmount;
TextView PeopleBilled;
ToggleButton ServiceChargeEnabled;
ToggleButton GSTEnabled;
TextView Discount = findViewById(R.id.discount);
RadioGroup PaymentMethodRadioGroup;
RadioButton CashPaymentRadioButton;
RadioButton PayNowPaymentRadioButton;
Button Split;
Button Reset;
TextView TotalBilled;
TextView TotalBillPaidAmount;
TextView EachPersonToPay;
TextView EachPersonToPayAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TotalBilled=findViewById(R.id.totalbill);
        TotalBillPaidAmount=findViewById(R.id.totalbillamount);
        EachPersonToPay=findViewById(R.id.eachpersonpay);
        EachPersonToPayAmount=findViewById(R.id.eachpersonpayamount);
        BilledAmount=findViewById(R.id.billedAmount);
        PeopleBilled=findViewById(R.id.PeoplePaying);
        ServiceChargeEnabled=findViewById(R.id.serviceChargeCheck);
        GSTEnabled=findViewById(R.id.GSTCheck);
        PaymentMethodRadioGroup=findViewById(R.id.RadioGroupPaymentMethod);
        CashPaymentRadioButton=findViewById(R.id.radioButtonPaymentCash);
        PayNowPaymentRadioButton=findViewById(R.id.radioButtonPaymentPayNow);
        Split=findViewById(R.id.SplitButtonEnabled);
        Reset=findViewById(R.id.ResetButtonEnabled);

        Split.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
            double BaseBilledAmount=Double.parseDouble(String.valueOf(BilledAmount));
            int PeoplePayingBill= Integer.parseInt(String.valueOf(PeopleBilled));
            double Discountsineffect=Double.parseDouble(String.valueOf(Discount));
            double ServiceCharge=0;
            double GST=0;
            if(ServiceChargeEnabled.isChecked()){
                ServiceCharge=BaseBilledAmount*0.10;
            }
            if(GSTEnabled.isChecked()){
                GST=BaseBilledAmount*0.07;
            }
            double ValueSavedByDiscount=Discountsineffect*BaseBilledAmount;
            double FinalBilledAmount=(BaseBilledAmount-ValueSavedByDiscount)+GST+ServiceCharge;
            TotalBilled.setText("Total Bill:"+"$"+FinalBilledAmount);
            double EachPersonToPayAmount=FinalBilledAmount/PeoplePayingBill;
            if (PayNowPaymentRadioButton.isChecked()){
                EachPersonToPay.setText("Each Person Pays: " + EachPersonToPayAmount+ "Via PayNow to 912345678");
            }
            else{
                EachPersonToPay.setText("Each Person Pays: " + EachPersonToPayAmount);
            }

    }

    });
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BilledAmount.setText("");
                PeopleBilled.setText("");
                Discount.setText("");
                ServiceChargeEnabled.setChecked(false);
                GSTEnabled.setChecked(false);
                PaymentMethodRadioGroup.clearCheck();
                TotalBilled.setVisibility(View.INVISIBLE);
                TotalBillPaidAmount.setVisibility(View.INVISIBLE);
                EachPersonToPay.setVisibility(View.INVISIBLE);
                EachPersonToPayAmount.setVisibility(View.INVISIBLE);



            }
        });


}}
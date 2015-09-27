package com.blocksolid.income;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView totalTaxDeductionsText;
    TextView grossAnnualIncomeValueText;
    TaxCalculator taxCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taxCalculator = new TaxCalculator();
        grossAnnualIncomeValueText = (TextView)findViewById(R.id.main_txt_gross_value);
    }

    public void displayDeductions(View view) {
        // Display breakdown of all deductions
        EditText editText = (EditText) findViewById(R.id.main_edit_salary);
        if ((editText.getText()).length() > 0) {
            Integer enteredValue = Integer.valueOf(editText.getText().toString())*100;
            taxCalculator.setGrossAnnualIncome(enteredValue);
            grossAnnualIncomeValueText.setText(displayAsPoundsAndPence(taxCalculator.getGrossAnnualIncome()));
        }
        else {
            grossAnnualIncomeValueText.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static String displayAsPoundsAndPence(int value) {
        DecimalFormat currency = new DecimalFormat("£#,###,##0.00");
        Double valueDouble = value/100.0;
        return currency.format(valueDouble);
    }
}

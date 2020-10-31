package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import 	java.lang.Math;
public class MainActivity extends AppCompatActivity {
    private Spinner spinner1;
    private EditText editText;
    public static  final String name = "Name";
    public static  final String mass = "Mass";
    public static  final String height = "Height";
    public static  final String gender = "Gender";
    public static  final String bmiii = "BMI";
    private EditText nameE;
    private  EditText massE;
    private  EditText heightE;
    private EditText bmiE;
    private Spinner genderE;
    private SharedPreferences prefrence;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpSharedPrefrence();
        checkPref();
        ArrayList <String> genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");
         spinner1 = findViewById(R.id.genderSpinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genders);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter);


    }
public void setUpSharedPrefrence (){
        prefrence = PreferenceManager.getDefaultSharedPreferences(this);
        editor= prefrence.edit();
}

public void setUpViews(){

}
private void checkPref(){
      String name1 = prefrence.getString(name,"");
      String mass1 = prefrence.getString(mass,"");
    String height1 = prefrence.getString(height,"");
    String gender1 = prefrence.getString(gender,"");
    String bmi1 = prefrence.getString(bmiii,"");
    nameE = findViewById(R.id.namee);
    massE = findViewById(R.id.weightTextEdit);
    heightE= findViewById(R.id.heightTextEdit);
    bmiE =findViewById(R.id.BMITextEdit);
    nameE.setText(name1);
    massE.setText(mass1);
    heightE.setText(height1);
    bmiE.setText(bmi1);

}

    public void onClick(View view) {
        EditText name1 = (EditText) this.findViewById(R.id.namee);
        Editable name2 =name1.getText();
        EditText masss = (EditText) this.findViewById(R.id.weightTextEdit);
        Editable word =masss.getText();
        double mass1 = Double.parseDouble(String.valueOf(word));
        EditText heightt = (EditText) this.findViewById(R.id.heightTextEdit);
        Editable word2 =heightt.getText();
        double height1 = Double.parseDouble(String.valueOf(word));
        double height2 =Math.pow(height1,2);
        double bmi = mass1/height2;
        EditText res = (EditText) this.findViewById(R.id.BMITextEdit);
        bmi = Math.floor(bmi * 100) / 100;
        if(bmi<18.5 )
        res.setText(String.valueOf((double) bmi) + "  Underweight");
        else if (bmi>25)
            res.setText(String.valueOf((double) bmi) + "  Overweight");
        else
            res.setText(String.valueOf((double) bmi) + "  Normal");
        Editable bmi2 = res.getText();
        String spin = spinner1.getSelectedItem().toString();
        SharedPreferences.Editor editor = prefrence.edit();
        editor.putString(name, String.valueOf(name2));
        editor.putString(mass, String.valueOf(word));
        editor.putString(height, String.valueOf(word2));
        editor.putString(gender, spin);
        editor.putString(bmiii, String.valueOf(bmi2));
        editor.commit();
        Toast.makeText(MainActivity.this,"Thanks",Toast.LENGTH_LONG).show();
    }

    public void onClick2(View view) {
        Intent intent = new Intent(this, TimerActivity.class);
        startActivity(intent);
    }
}
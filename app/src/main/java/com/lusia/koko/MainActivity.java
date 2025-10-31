package com.lusia.koko;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private int liczba;
    private int liczbaDana1 = 50;
    private static final String KLUCZ_LICZBA = "liczba";
    private static final String KLUCZ_DANA1 = "liczbaDana1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button przyciskWylosuj = findViewById(R.id.przyciskWylosuj);
        TextView liczbaTekst = findViewById(R.id.liczbaTekst);
        ImageButton przyciskDodaj = findViewById(R.id.przyciskDodaj);
        ImageButton przyciskOdejmij = findViewById(R.id.przyciskOdejmij);
        Button przyciskZgadnij = findViewById(R.id.przyciskZgadnij);
        TextView liczbaDana = findViewById(R.id.liczbaDana);

        if (savedInstanceState != null) {
            liczba = savedInstanceState.getInt(KLUCZ_LICZBA, 0);
            liczbaDana1 = savedInstanceState.getInt(KLUCZ_DANA1, 50);
        } else {
            liczba = 0;
            liczbaDana1 = 50;
        }

        przyciskWylosuj.setOnClickListener(v -> {
            Random random = new Random();
            liczba = random.nextInt(100)+1;
            liczbaTekst.setText(String.valueOf(liczba));
        });
        przyciskDodaj.setOnClickListener(v -> {
            if (liczbaDana1 >=100){
                liczbaDana.setText("bez przesady.");
                liczbaDana1=99;
            }
            else {
                liczbaDana1++;
                liczbaDana.setText(String.valueOf(liczbaDana1));
            }
        });

        przyciskOdejmij.setOnClickListener(v -> {
            if (liczbaDana1<=0 ){
                liczbaDana.setText("bez przesady.");
                liczbaDana1=1;
            }
            else {
                liczbaDana1--;
                liczbaDana.setText(String.valueOf(liczbaDana1));
            }
        });
        przyciskZgadnij.setOnClickListener(v -> {
            if(liczba==liczbaDana1){
                liczbaDana.setText("brawo zgadłeś");
                liczbaDana1=50;
                Random random = new Random();
                liczba = random.nextInt(100)+1;
                liczbaTekst.setText(String.valueOf(liczba));
            } else if (liczba>liczbaDana1) {
                liczbaDana.setText("liczba za mała");

            }else if (liczba<liczbaDana1) {
                liczbaDana.setText("liczba za duża");

            }
            else {
                liczbaDana.setText("co rześ zrobił");
            }
        });

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KLUCZ_LICZBA, liczba);
        outState.putInt(KLUCZ_DANA1, liczbaDana1);
    }

}
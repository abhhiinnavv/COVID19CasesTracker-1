package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

public class CountryDetailedActivity extends AppCompatActivity {
    private TextView countryName,casesTv,todayCasesTv,deathsTv, todayDeathTv, recoveredTv, todayRecoveredTv;
    private AnyChartView anyChartView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detailed);

        countryName = findViewById(R.id.countryNameDisplay);
        casesTv = findViewById(R.id.casesTv);
        todayCasesTv = findViewById(R.id.todayCasesTv);
        deathsTv = findViewById(R.id.deathsTv);
        todayDeathTv = findViewById(R.id.todayDeathTv);
        recoveredTv = findViewById(R.id.recoveredTv);
        todayRecoveredTv = findViewById(R.id.todayRecoveredTv);
        anyChartView = findViewById(R.id.anyChartView);


        String country = getIntent().getStringExtra("countryName");
        String totalcases = getIntent().getStringExtra("totalCases");
        String totalDeaths = getIntent().getStringExtra("totalDeaths");
        String totalRecovered = getIntent().getStringExtra("totalRecovered");
        String todayCases = getIntent().getStringExtra("todayCases");
        String todayDeath = getIntent().getStringExtra("todayDeaths");
        String todayRecovered = getIntent().getStringExtra("todayRecovered");

        countryName.setText(country);
        casesTv.setText(totalcases);
        todayCasesTv.setText(todayCases);
        deathsTv.setText(totalDeaths);
        todayDeathTv.setText(todayDeath);
        recoveredTv.setText(totalRecovered);
        todayRecoveredTv.setText(todayRecovered);

        int deathCasesTotal = Integer.parseInt(totalDeaths);
        int recoveryCasesTotal = Integer.parseInt(totalRecovered);
        int totalCasesTotal= Integer.parseInt(totalcases);
        int activeCasesTotal = totalCasesTotal - (deathCasesTotal+recoveryCasesTotal);

        //Pie Chart
         String[] nameData = {"Death Rate","Recovery Rate", "Active Cases"};
        int[] values = {deathCasesTotal, recoveryCasesTotal, activeCasesTotal};

        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();

        for(int i=0; i<nameData.length; i++){
            dataEntries.add(new ValueDataEntry(nameData[i], values[i]));
        }
        pie.data(dataEntries);
        anyChartView.setChart(pie);

        }
}
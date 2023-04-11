package de.androidcrypto.barcodeexamples;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class LineChartActivity extends AppCompatActivity
{

    ArrayList lineArraylist;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        LineChart lineChart = findViewById(R.id.linechart);
        getData();
        LineDataSet lineDataSet = new LineDataSet(lineArraylist,"Cambo Tutorial");
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        //color bar data set
        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //text color
        lineDataSet.setValueTextColor(Color.BLACK);
        //settting text size
        lineDataSet.setValueTextSize(16f);
        lineChart.getDescription().setEnabled(true);
        //lineChart.set

    }

    private void getData()
    {
        lineArraylist = new ArrayList();
        lineArraylist.add(new BarEntry(2f,10));
        lineArraylist.add(new BarEntry(3f,20));
        lineArraylist.add(new BarEntry(4f,30));
        lineArraylist.add(new BarEntry(5f,40));
        lineArraylist.add(new BarEntry(6f,50));
        lineArraylist.add(new BarEntry(7f,25));
    }
}
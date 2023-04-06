package de.androidcrypto.barcodeexamples;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResultLauncher;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.mikephil.charting.data.LineDataSet;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class MainActivity extends AppCompatActivity {

    Button btnScan, btnGenerate, btnBiometrics, btnBarChart, btnLineChart;
    EditText edit_input;
    ImageView iv_qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScan = findViewById(R.id.btnScanBarcode);
        btnGenerate = findViewById(R.id.btnGenerateBarcode);
        btnBiometrics = findViewById(R.id.btnBiometrics);
        edit_input = findViewById(R.id.edit_input);
        iv_qr = findViewById(R.id.iv_qr);
        btnBarChart = findViewById(R.id.btnBarChart);
        btnLineChart = findViewById(R.id.btnLineChart);


        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanCode();
            }
        });

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edit_input.getText().toString().trim();
                MultiFormatWriter writer = new MultiFormatWriter();
                try
                {
                    //BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE,600,600); // qr-code
                    BitMatrix matrix = writer.encode(text, BarcodeFormat.EAN_13,800,200);
                    BarcodeEncoder encoder = new BarcodeEncoder();
                    Bitmap bitmap = encoder.createBitmap(matrix);
                    iv_qr.setImageBitmap(bitmap);

                    // just testing for cherck digit creation
                    String ean13WithoutCheckDigit = "995201946262";
                    String ean13CheckDigit = ean13CheckDigit(ean13WithoutCheckDigit);
                    System.out.println("*** ean13CheckDigit: " + ean13CheckDigit);

                } catch (WriterException e)
                {
                    e.printStackTrace();
                }

                /*
                Intent intent = new Intent("com.google.zxing.client.android.ENCODE");
                intent.putExtra("ENCODE_FORMAT", "EAN_13");
                intent.putExtra("ENCODE_DATA", "3800065711135");
                startActivity(intent);
                // Process: de.androidcrypto.barcodeexamples, PID: 24616
                //    android.content.ActivityNotFoundException: No Activity found to handle Intent { act=com.google.zxing.client.android.ENCODE (has extras) }
                */
            }
        });

        btnBiometrics.setOnClickListener(v->
        {
            Intent intent = new Intent(MainActivity.this, BiometricActivity.class);
            startActivity(intent);
        });

        btnBarChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BarChartActivity.class);
                startActivity(intent);
            }
        });

        btnLineChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LineChartActivity.class);
                startActivity(intent);
            }
        });

    }

    private void scanCode()
    {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(false);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        scanBarcodeLaucher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> scanBarcodeLaucher = registerForActivityResult(new ScanContract(), result->
    {
        if(result.getContents() !=null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            System.out.println("*** scanned barcode: " + result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    dialogInterface.dismiss();
                }
            }).show();
        }
    });

    /**
     * generates the complete EAN-13 code
     * @param barcode 12-digit barcode
     * @return 13-digit barcode
     */
    public static String ean13CheckDigit(String barcode) {
        int s = 0;
        for (int i = 0; i < 12; i++) {
            int c = Character.getNumericValue(barcode.charAt(i));
            s += c * ( i%2 == 0? 1: 3);
        }
        s = (10 - s % 10) % 10;
        barcode += s;
        return barcode;
    }

}
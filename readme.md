# Barcode Examples

https://www.youtube.com/watch?v=jtT60yFPelI

https://www.cambotutorial.com/article/10-minutes-build-bar-code-and-qr-code-scanner-in-android-app

https://drive.google.com/file/d/18fIO2uAuLgivSeUaCKy4aunZYV9lLkTx/view?usp=sharing

How to Generate QR Code in Android Studio With Short Code QRGenerator | Cambo Tutorial

https://www.youtube.com/watch?v=n8HdrLYL9DA

https://www.cambotutorial.com/article/android-how-to-generate-qr-code-in-android-studio-free-source-code

https://drive.google.com/file/d/14DnDfWbv6U8x64wLqdf5gJm0DcoVVHgH/view?usp=sharing

implementation 'com.journeyapps:zxing-android-embedded:4.3.0'

https://github.com/journeyapps/zxing-android-embedded

## Fingerprint

https://www.cambotutorial.com/article/android-show-a-biometric-fingerprint-authentication-dialog-example

https://drive.google.com/drive/folders/1Q1tK9PkOX_k5DsiXN52i3ftLfcFmtRlc

implementation "androidx.biometric:biometric:1.1.0"

## BarChart

https://www.cambotutorial.com/article/how-to-implement-awesome-chart-graph-in-your-android-app

implementation 'com.github.PhilJay:MPAndroidChart:v3.1.0'

## BarChart

https://www.cambotutorial.com/article/how-to-implement-awesome-chart-graph-in-your-android-app

implementation 'com.github.PhilJay:MPAndroidChart:v3.1.0'


```plaintext
9952019462625
EAN_13
Product

2
https://stackoverflow.com/a/11965553/8166854

This code made it possible:

Intent intent = new Intent("com.google.zxing.client.android.ENCODE");
intent.putExtra("ENCODE_FORMAT", "EAN_13");
intent.putExtra("ENCODE_DATA", "3800065711135");
startActivity(intent);

https://www.baeldung.com/java-generating-barcodes-qr-codes

https://stackoverflow.com/questions/38260601/barcode4j-generate-check-digit-in-an-ean13-barcode

In case you what to compute the check digit without importing a java library here's the code:

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


```


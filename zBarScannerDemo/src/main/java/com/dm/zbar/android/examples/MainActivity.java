package com.dm.zbar.android.examples;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.dtr.zbar.scan.CaptureActivity;


public class MainActivity extends Activity {

    private static final int REQUEST_CODE_SCAN = 0x0000;

    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final int ZBAR_SCANNER_REQUEST = 0;
    private static final int ZBAR_QR_SCANNER_REQUEST = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

//    public void launchScanner(View v) {
//        if (isCameraAvailable()) {
//            Intent intent = new Intent(this, ZBarScannerActivity.class);
//            startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
//        } else {
//            Toast.makeText(this, "Rear Facing Camera Unavailable", Toast.LENGTH_SHORT).show();
//        }
//    }

    public void QRScanner(View v) {
        if (isCameraAvailable()) {
            Intent intent = new Intent(MainActivity.this,
						CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_SCAN);
        } else {
            Toast.makeText(this, "Rear Facing Camera Unavailable", Toast.LENGTH_SHORT).show();
        }
    }

//    public void launchQRScanner(View v) {
//        if (isCameraAvailable()) {
//            Intent intent = new Intent(this, ZBarScannerActivity.class);
//            intent.putExtra(ZBarConstants.SCAN_MODES, new int[]{Symbol.QRCODE});
//            startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
//        } else {
//            Toast.makeText(this, "Rear Facing Camera Unavailable", Toast.LENGTH_SHORT).show();
//        }
//    }

    public boolean isCameraAvailable() {
        PackageManager pm = getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                Toast.makeText(MainActivity.this, "扫描结果： \n" + content, Toast.LENGTH_SHORT).show();
            }
        }
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            case ZBAR_SCANNER_REQUEST:
//            case ZBAR_QR_SCANNER_REQUEST:
//                if (resultCode == RESULT_OK) {
//                    Toast.makeText(this, "Scan Result = " + data.getStringExtra(ZBarConstants.SCAN_RESULT), Toast.LENGTH_SHORT).show();
//                } else if (resultCode == RESULT_CANCELED && data != null) {
//                    String error = data.getStringExtra(ZBarConstants.ERROR_INFO);
//                    if (!TextUtils.isEmpty(error)) {
//                        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
//                    }
//                }
//                break;
//        }
//    }
}

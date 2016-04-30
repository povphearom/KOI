package com.phearom.koi;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.zxing.WriterException;
import com.phearom.koi.databinding.ActivityMainBinding;
import com.phearom.koi.utils.BarcodeUtils;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mBinding.btnGenerateBarcode.setOnClickListener(this);
        mBinding.btnGenerateID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = UUID.randomUUID().toString();
                mBinding.edtSourceText.setText(str.substring(0, str.lastIndexOf("-")));
                BarcodeUtils.updateBrightness(MainActivity.this, 2F);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        try {
            Bitmap bitmap = BarcodeUtils.generateBarcode(mBinding.edtSourceText.getText().toString());
            mBinding.imvBarcodePreview.setImageBitmap(bitmap);
            BarcodeUtils.updateBrightness(MainActivity.this, 1F);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}

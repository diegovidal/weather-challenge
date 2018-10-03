package com.dvidal.ui.utils;

import android.content.Context;

import com.dvidal.ui.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import timber.log.Timber;

/**
 * @author diegovidal on 03/10/2018.
 */
public class Teste {

    public void readFile(Context context) {

        try {
            InputStream is = context.getResources().openRawResource(R.raw.citylist);
            int size = 0;
            size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            Timber.d("JSON AQUI OH "+ json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

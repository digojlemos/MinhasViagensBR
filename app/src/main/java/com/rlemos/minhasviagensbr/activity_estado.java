package com.rlemos.minhasviagensbr;

import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rlemos.minhasviagensbr.adapter.EstadoCursorAdapter;
import com.rlemos.minhasviagensbr.dados.ViagemContract;

import static android.R.attr.id;
import static android.os.Build.VERSION_CODES.M;
import static java.security.AccessController.getContext;

/**
 * Created by rlemos on 07/08/17.
 */

public class activity_estado extends AppCompatActivity {

        EstadoCursorAdapter cursorAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_estado);

            ListView listEstado = (ListView) findViewById(R.id.listEstado);

            View empty = findViewById(R.id.imagePais);
            listEstado.setEmptyView(empty);

            String[] projection = {
                    ViagemContract.EntryEstado._ID,
                    ViagemContract.EntryEstado.ESTADO,
                    ViagemContract.EntryEstado.ESTADO_SIGLA};

            Cursor c = getContentResolver().query(ViagemContract.EntryEstado.CONTENT_URI_ESTADOS,projection,null,null,null);







        }


}

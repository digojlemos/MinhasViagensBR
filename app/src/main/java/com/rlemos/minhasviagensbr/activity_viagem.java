package com.rlemos.minhasviagensbr;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.content.Loader;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.rlemos.minhasviagensbr.dados.ViagemContract.EntryViagem;

import static android.R.attr.data;

/**
 * Created by rlemos on 07/08/17.
 */

public class activity_viagem extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor>{

    private Uri mCurrentViagemUri;

    private TextView textviewLocal, textviewData, textviewDias, textviewHosp, textviewPasseios,
            textviewDesc,textviewLabelDesc, textviewLabelHosp, textviewLabelPass;

    private ImageView buttonBack;

    private static final int VIAGEM_LOADER = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_viagem);
        //setTitle("Viagem");

        //deixar o aplicativo em FullScreen ocupando toda a tela
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Esconde a Tool Bar
        getSupportActionBar().hide();

        Intent intent = getIntent();
        mCurrentViagemUri = intent.getData();

        textviewLocal = (TextView) findViewById(R.id.textViagem);
        textviewData = (TextView) findViewById(R.id.dataViagem);
        textviewHosp = (TextView) findViewById(R.id.localHospViagem);
        textviewPasseios = (TextView) findViewById(R.id.passeiosViagem);
        textviewDesc = (TextView) findViewById(R.id.descViagem);
        textviewDias = (TextView) findViewById(R.id.qtdDiasViagem);
        textviewLabelDesc = (TextView) findViewById(R.id.labelDescricao);
        textviewLabelHosp = (TextView) findViewById(R.id.labelHospedagem);
        textviewLabelPass = (TextView) findViewById(R.id.labelPasseios);

        buttonBack = (ImageView) findViewById(R.id.backButtonViagem);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });






        getLoaderManager().initLoader(VIAGEM_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                EntryViagem.ID_VIAGEM,
                EntryViagem.VIAGEM_LOCAL,
                EntryViagem.VIAGEM_DATA,
                EntryViagem.VIAGEM_QTD_DIAS,
                EntryViagem.VIAGEM_HOSPEDAGEM,
                EntryViagem.VIAGEM_PASSEIOS,
                EntryViagem.VIAGEM_DESC};
        String selection = EntryViagem.ID_VIAGEM + "=?";
        String[] selectionArgs = new String[] { String.valueOf(ContentUris.parseId(mCurrentViagemUri))};

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                EntryViagem.CONTENT_URI_VIAGENS,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                selection,                   // No selection clause
                selectionArgs,                   // No selection arguments
                null); // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }
        if (cursor.moveToFirst()) {
            // Find the columns of pet attributes that we're interested in
            int columLocalViagem = cursor.getColumnIndex(EntryViagem.VIAGEM_LOCAL);
            int columDataViagem = cursor.getColumnIndex(EntryViagem.VIAGEM_DATA);
            int columDiasViagem =cursor.getColumnIndex(EntryViagem.VIAGEM_QTD_DIAS);
            int columHospViagem = cursor.getColumnIndex(EntryViagem.VIAGEM_HOSPEDAGEM);
            int columPassViagem = cursor.getColumnIndex(EntryViagem.VIAGEM_PASSEIOS);
            int columDescViagem = cursor.getColumnIndex(EntryViagem.VIAGEM_DESC);

            // Extract out the value from the Cursor for the given column index
            String local = cursor.getString(columLocalViagem);
            String data = formataData(cursor.getString(columDataViagem));
            String dias = addDias(cursor.getString(columDiasViagem));
            String hospedagem = cursor.getString(columHospViagem);
            String passeios = cursor.getString(columPassViagem);
            String desc = cursor.getString(columDescViagem);

            if(local!=null){
                textviewLocal.setText(local);
            }


            if(data!=null){
                textviewData.setText(data);
            }


            if(dias!=null){
                textviewDias.setText(dias);
            }else{
                textviewDias.setVisibility(View.INVISIBLE);
            }

            if(hospedagem!=null){
                textviewHosp.setText(hospedagem);
            }else {
                textviewLabelHosp.setVisibility(View.INVISIBLE);
                textviewHosp.setVisibility(View.INVISIBLE);
            }

            if(passeios!=null){
                textviewPasseios.setText(passeios);
            }else {
                textviewPasseios.setVisibility(View.INVISIBLE);
                textviewLabelPass.setVisibility(View.INVISIBLE);
            }

            if(desc!=null){
                textviewDesc.setText(desc);
            }else{
                textviewDesc.setVisibility(View.INVISIBLE);
                textviewLabelDesc.setVisibility(View.INVISIBLE);
            }

        }

    }
    public String formataData(String data){
        if(data==null || data.isEmpty() || data.equals("")){
            return null;
        }
        String mes, dia, ano, dataFormated;
        ano = data.substring(0,4);
        mes = data.substring(4,6);
        dia = data.substring(6,8);
        dataFormated = dia+"/"+mes+"/"+ano;

        return dataFormated;
    }
    public String addDias(String dias){
        if(dias==null || dias.isEmpty() || dias.equals("")){
            return null;
        }
        dias = dias+" dias";
        return dias;
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        textviewLocal.setText("");
        textviewData.setText("");
        textviewHosp.setText("");
        textviewPasseios.setText("");
        textviewDesc.setText("");
    }
}

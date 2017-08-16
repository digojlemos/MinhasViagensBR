package com.rlemos.minhasviagensbr;


import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.content.Loader;

import com.rlemos.minhasviagensbr.adapter.ViagensCursorAdapter;
import com.rlemos.minhasviagensbr.dados.ViagemContract.EntryViagem;

import static com.rlemos.minhasviagensbr.R.id.listEstado;

/**
 * Created by rjlemos on 11/08/2017.
 */

public class activity_viagens extends AppCompatActivity  implements
        LoaderManager.LoaderCallbacks<Cursor>{

    private static final int VIAGENS_LOADER = 0;

    private Uri mCurrentPetUri;

    ViagensCursorAdapter viagensAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_viagens);


        Intent intent = getIntent();
        mCurrentPetUri = intent.getData();

        String estado = intent.getStringExtra("estado");
        setTitle("Viagens para "+estado);



        ListView listViagens = (ListView) findViewById(R.id.listViagens);
        viagensAdapter = new ViagensCursorAdapter(this,null);
        listViagens.setAdapter(viagensAdapter);
        listViagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Cria um novo Intent que será aberto quando clicado
                Intent intent = new Intent(activity_viagens.this, activity_viagem.class);
                //Cria um URI com o ID do estado clicado
                Uri currentPetUri = ContentUris.withAppendedId(EntryViagem.CONTENT_URI_LISTVIAGENS, id);
                //Inseri a URI no Intent
                intent.setData(currentPetUri);
                //Inicializa o Intent
                startActivity(intent);
            }
        });

        getLoaderManager().initLoader(VIAGENS_LOADER, null, this);


    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        String[] projection = {
                EntryViagem.ID_VIAGEM,
                EntryViagem.VIAGEM_LOCAL,
                EntryViagem.VIAGEM_DATA };
        String order = EntryViagem.VIAGEM_DATA+" DESC";
        String selection = EntryViagem.ID_ESTADO + "=?";
        String[] selectionArgs = new String[] { String.valueOf(ContentUris.parseId(mCurrentPetUri))};

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                EntryViagem.CONTENT_URI_VIAGENS,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                selection,                   // No selection clause
                selectionArgs,                   // No selection arguments
                order); // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        viagensAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        viagensAdapter.swapCursor(null);
    }
}

package com.rlemos.minhasviagensbr;


import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.content.CursorLoader;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.content.Loader;
import android.widget.TextView;

import com.rlemos.minhasviagensbr.adapter.ViagensCursorAdapter;
import com.rlemos.minhasviagensbr.dados.ViagemContract.EntryViagem;

import static com.rlemos.minhasviagensbr.R.id.listEstado;
import static com.rlemos.minhasviagensbr.R.id.listViagens;

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

        //deixar o aplicativo em FullScreen ocupando toda a tela
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Esconde a Tool Bar
        getSupportActionBar().hide();

        Intent intent = getIntent();
        mCurrentPetUri = intent.getData();

        String estado = intent.getStringExtra("estado");
        TextView tituloViagens = (TextView) findViewById(R.id.tituloViagens);
        tituloViagens.setText("Viagens - "+estado);

        ImageView buttonBack = (ImageView) findViewById(R.id.backButtonViagens);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


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

        FloatingActionButton buttonAdd = (FloatingActionButton) findViewById(R.id.buttonAddViagens);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(activity_viagens.this,  activity_edit_viagem.class);
                startActivity(addIntent);
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

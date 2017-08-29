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
import android.widget.ListView;
import android.widget.TextView;
import android.content.Loader;

import com.rlemos.minhasviagensbr.adapter.EstadoCursorAdapter;
import com.rlemos.minhasviagensbr.dados.ViagemContract.EntryViagem;
import com.rlemos.minhasviagensbr.dados.ViagemContract.EntryEstado;

/**
 * Created by rlemos on 07/08/17.
 */

public class activity_estado extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    EstadoCursorAdapter cursorAdapter;
    private static final int ESTADO_LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_estado);
        //Defini o titulo na toolBar
        setTitle("Viagens - Estados");

        //deixar o aplicativo em FullScreen ocupando toda a tela
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Esconde a Tool Bar
        getSupportActionBar().hide();

        ListView listEstado = (ListView) findViewById(R.id.listEstado);

        View empty = findViewById(R.id.empty);
        listEstado.setEmptyView(empty);

        cursorAdapter = new EstadoCursorAdapter(this,null);
        listEstado.setAdapter(cursorAdapter);

        FloatingActionButton buttonAdd = (FloatingActionButton) findViewById(R.id.buttonAddEstado);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(activity_estado.this,  activity_edit_viagem.class);
                startActivity(addIntent);
            }
        });

        listEstado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Cria um novo Intent que será aberto quando clicado
                Intent intent = new Intent(activity_estado.this, activity_viagens.class);
                //Cria um URI com o ID do estado clicado
                Uri currentPetUri = ContentUris.withAppendedId(EntryViagem.CONTENT_URI_LISTVIAGENS, id);


                TextView estado = (TextView) view.findViewById(R.id.textoEstados);

                intent.putExtra("estado",estado.getText());

                //Inseri a URI no Intent
                intent.setData(currentPetUri);

                //Inicializa o Intent
                startActivity(intent);

            }
        });
        //Inicia o Loader
        getLoaderManager().initLoader(ESTADO_LOADER, null, this);


    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        //Cria a Varial que contem os dados que será retorbadi da consulta ao banco
        String[] projection = {
                EntryEstado.ID_ESTADO,
                EntryEstado.ESTADO,
                EntryEstado.ESTADO_SIGLA };
        //Variavel com o argumento que será pesquisado no banco
        String selection = EntryEstado.ESTADO_ENABLED + "=?";
        //Defini o valor do argumento que será retornado
        String[] selectionArgs = new String[] { "1" };
        //Defini a ordenação da consulta que será retornada
        String order = EntryEstado.ESTADO_ULT_VIAGEM+" DESC";

        //Retorna o Cursor ao Loader para carregar na Tela
        return new CursorLoader(this,   // Parent activity context
                EntryEstado.CONTENT_URI_ESTADOS,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                selection,                   // No selection clause
                selectionArgs,                   // No selection arguments
                order); // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);

    }
}

package com.rlemos.minhasviagensbr;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by rjlemos on 11/08/2017.
 */

public class activity_viagens extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int EXISTING_PET_LOADER = 0;

    private Uri mCurrentPetUri;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_estado);
        setTitle("Viagens - Cidades");

        Intent intent = getIntent();
        mCurrentPetUri = intent.getData();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}

package com.rlemos.minhasviagensbr.dados;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.rlemos.minhasviagensbr.dados.ViagemContract.EntryPais;
import com.rlemos.minhasviagensbr.dados.ViagemContract.EntryEstado;
import com.rlemos.minhasviagensbr.dados.ViagemContract.EntryCidade;
import com.rlemos.minhasviagensbr.dados.ViagemContract.EntryViagem;

/**
 * Created by rlemos on 07/08/17.
 */

public class ViagemProvider extends ContentProvider {

    private static final int PAIS = 100;

    private static final int PAISES = 101;

    private static final int ESTADO = 102;

    private static final int ESTADOS= 103;

    private static final int CIDADE = 104;

    private static final int CIDADES = 105;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        sUriMatcher.addURI(ViagemContract.CONTENT_AUTHORITY, ViagemContract.PATH_PAISES, PAISES);

        sUriMatcher.addURI(ViagemContract.CONTENT_AUTHORITY, ViagemContract.PATH_PAIS + "/#", PAIS);

        sUriMatcher.addURI(ViagemContract.CONTENT_AUTHORITY, ViagemContract.PATH_ESTADOS, ESTADOS);

        sUriMatcher.addURI(ViagemContract.CONTENT_AUTHORITY, ViagemContract.PATH_ESTADO + "/#", ESTADO);

        sUriMatcher.addURI(ViagemContract.CONTENT_AUTHORITY, ViagemContract.PATH_CIDADES, CIDADES);

        sUriMatcher.addURI(ViagemContract.CONTENT_AUTHORITY, ViagemContract.PATH_CIDADE + "/#", CIDADE);
    }

    private ViagemDbHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new ViagemDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        SQLiteDatabase database = dbHelper.getReadableDatabase();

        // This cursor will hold the result of the query
        Cursor cursor;

        // Figure out if the URI matcher can match the URI to a specific code
        int match = sUriMatcher.match(uri);
        switch (match) {
            case PAISES:

                cursor = database.query(EntryPais.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case PAIS:

                selection = EntryPais._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                cursor = database.query(EntryPais.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case ESTADOS:

                cursor = database.query(EntryEstado.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case ESTADO:

                selection = EntryPais._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                cursor = database.query(EntryEstado.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case CIDADES:

                cursor = database.query(EntryCidade.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case CIDADE:

                selection = EntryPais._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                cursor = database.query(EntryCidade.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);

        }

        // Set notification URI on the Cursor,
        // so we know what content URI the Cursor was created for.
        // If the data at this URI changes, then we know we need to update the Cursor.
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        // Return the cursor
        return cursor;

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}


package com.rlemos.minhasviagensbr.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rlemos.minhasviagensbr.R;
import com.rlemos.minhasviagensbr.dados.ViagemContract;
import com.rlemos.minhasviagensbr.dados.ViagemContract.EntryViagem;

import java.util.zip.Inflater;

/**
 * Created by rjlemos on 11/08/2017.
 */

public class ViagemCursorAdapter extends CursorAdapter {
    public ViagemCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_viagens,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView local = (TextView) view.findViewById(R.id.textLocal);
        TextView dtViagem = (TextView) view.findViewById(R.id.dtViagem);

        int columLocal = cursor.getColumnIndex(EntryViagem.VIAGEM_LOCAL);
        int columDtViagem =  cursor.getColumnIndex(EntryViagem.VIAGEM_DATA);

        String resultLocal = cursor.getString(columLocal);
        String resultDtViagem = cursor.getString(columDtViagem);

        local.setText(resultLocal);
        dtViagem.setText(resultDtViagem);

    }
}

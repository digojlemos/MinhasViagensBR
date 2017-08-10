package com.rlemos.minhasviagensbr.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rlemos.minhasviagensbr.R;
import com.rlemos.minhasviagensbr.dados.ViagemContract.EntryEstado;

/**
 * Created by rlemos on 07/08/17.
 */

public class EstadoCursorAdapter extends CursorAdapter {


    public EstadoCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_estado,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView estado = (TextView) view.findViewById(R.id.textoEstados);
        ImageView bandeiraEstado = (ImageView) view.findViewById(R.id.bandeiraEstado);

        int columEstado = cursor.getColumnIndex(EntryEstado.ESTADO);
        int columSigla =  cursor.getColumnIndex(EntryEstado.ESTADO_SIGLA);

        String textEstado = cursor.getString(columEstado);
        String siglaEstado = cursor.getString(columSigla).toLowerCase();
        //int id = view.getResources().getIdentifier(siglaEstado, "drawable", view.getContext().getPackageName());


        Resources res = view.getResources();
        String mDrawableName = "bandeira_"+siglaEstado;
        int resID = res.getIdentifier(mDrawableName , "drawable", view.getContext().getPackageName());
        Drawable drawable = res.getDrawable(resID );

        bandeiraEstado.setImageDrawable(drawable );
        estado.setText(textEstado);


    }
}

package com.rlemos.minhasviagensbr.Class;

import android.database.Cursor;
import android.widget.ArrayAdapter;

import com.rlemos.minhasviagensbr.activity_edit_viagem;

import java.util.ArrayList;

/**
 * Created by rlemos on 26/08/17.
 */

public class Estado {

    private String mEstado;
    private int mId_estado;

    public Estado(String estado){
        mEstado=estado;
    }
    public Estado(){

    }

    public ArrayList<Estado> carregarItens(Cursor c){
        if(c==null) {
            return null;
        }
        ArrayList estados = new ArrayList();
        c.moveToFirst();
        do{
            String nome = c.getString(1);
            estados.add(nome);
        }while(c.moveToNext());
        return estados;
    }

}

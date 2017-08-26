package com.rlemos.minhasviagensbr;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.rlemos.minhasviagensbr.Class.Estado;
import com.rlemos.minhasviagensbr.dados.ViagemContract.EntryEstado;

/**
 * Created by rlemos on 16/08/17.
 */

public class activity_edit_viagem extends AppCompatActivity {
    Spinner estadoSpinner;
    SeekBar diasSeekBar;
    TextView qtdDias;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edit_viagem);

        estadoSpinner = (Spinner) findViewById(R.id.estadoSpinner);
        carregarSpinner();

        qtdDias = (TextView) findViewById(R.id.qtdEditDiasViagem) ;

        diasSeekBar = (SeekBar) findViewById(R.id.seekDias);
        diasSeekBar.setMax(50);
        diasSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub
                qtdDias.setText(String.valueOf(progress));
            }
        });

    }

    private void carregarSpinner(){
        ArrayAdapter adapter;
        String[] projection = {
                EntryEstado.ID_ESTADO,
                EntryEstado.ESTADO};
        Estado estado = new Estado();
        Cursor c = getContentResolver().query(EntryEstado.CONTENT_URI_ESTADOS,projection,null,null,null);
        if(c!=null) {
            adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, estado.carregarItens(c));
        }else {
            adapter=null;
        }
        estadoSpinner.setAdapter(adapter);
    }


}

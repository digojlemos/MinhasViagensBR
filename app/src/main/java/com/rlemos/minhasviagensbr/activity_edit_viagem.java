package com.rlemos.minhasviagensbr;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
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
    TextView qtdDias, data;
    CardView cardView;
    ImageView buttonBack;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edit_viagem);

        //deixar o aplicativo em FullScreen ocupando toda a tela
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Esconde a Tool Bar
        getSupportActionBar().hide();

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
        buttonBack = (ImageView) findViewById(R.id.backButtonEdit);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                onBackPressed();
            }
        });
        data = (EditText) findViewById(R.id.dataEditViagem);
        data.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length()==2){
                    data.setText(data.getText()+"/");
                }
                if(editable.length()==5){
                    data.setText(data.getText()+"/");
                }

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

package com.rlemos.minhasviagensbr.dados;

import android.provider.BaseColumns;
import android.net.Uri;

/**
 * Created by rlemos on 07/08/17.
 */

public class ViagemContract {

    public static final String CONTENT_AUTHORITY = "com.rlemos.minhasviagensbr.viagemProvider";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_PAIS = "pais";
    public static final String PATH_ESTADO = "estado";
    public static final String PATH_PAISES = "paises";
    public static final String PATH_ESTADOS = "estados";
    public static final String PATH_VIAGEM = "viagem";
    public static final String PATH_VIAGENS = "viagens";
    public static final String PATH_LIST_VIAGENS = "listviagens";


    public static final class EntryPais implements BaseColumns{

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PAIS);

        public final static String TABLE_NAME = "pais";

        public final static String ID_PAIS = BaseColumns._ID;

        public final static String PAIS_SIGLA = "pais_sigla";

        public final static String PAIS_ABREV = "pais_abrev";

        public final static String PAIS = "pais";

        public final static String PAIS_ENABLED = "pais_enabled";

        public final static String PAIS_ULT_VIAGEM = "pais_last_date";

    }

    //Campos específicos da tabela Estado
    public static final class EntryEstado implements BaseColumns{

        public static final Uri CONTENT_URI_ESTADO = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_ESTADO);
        public static final Uri CONTENT_URI_ESTADOS = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_ESTADOS);

        public final static String TABLE_NAME = "estado";

        public final static String ID_ESTADO = BaseColumns._ID;

        public final static String ESTADO = "estado";

        public final static  String ID_PAIS = "id_pais";

        public final static String ESTADO_ENABLED = "estado_enabled";

        public final static String ESTADO_ULT_VIAGEM = "estado_ult_viagem";

        public final static String ESTADO_SIGLA = "estado_sigla";

    }


    //Campos específicos da tabela Viagem
    public static final class EntryViagem implements BaseColumns{
        public static final Uri CONTENT_URI_LISTVIAGENS = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_LIST_VIAGENS);
        public static final Uri CONTENT_URI_VIAGENS = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_VIAGENS);
        public static final Uri CONTENT_URI_VIAGEM = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_VIAGEM);

        public final static String TABLE_NAME = "viagem";

        public final static String ID_VIAGEM = BaseColumns._ID;

        public final static String ID_ESTADO = "id_cidade";

        public final static String VIAGEM_DATA = "viagem_data";

        public final static String VIAGEM_QTD_DIAS = "viagem_qtd_dias";

        public final static String VIAGEM_HOSPEDAGEM = "viagem_hospedagem";

        public final static String VIAGEM_PASSEIOS = "viagem_passeios";

        public final static String VIAGEM_DESC = "viagem_desc";

        public final static String VIAGEM_LOCAL = "viagem_local";

    }

}

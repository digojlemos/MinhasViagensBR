package com.rlemos.minhasviagensbr.dados;

import android.provider.BaseColumns;

/**
 * Created by rlemos on 07/08/17.
 */

public class ViagemContract {


    public static final class EntryPais implements BaseColumns{

        public final static String TABLE_NAME = "pais";

        public final static String ID_PAIS = BaseColumns._ID;

        public final static String PAIS = "pais";

        public final static String PAIS_ENABLED = "pais_enabled";

        public final static String PAIS_ULT_VIAGEM = "pais_ult_viagem";

    }

    //Campos específicos da tabela Estado
    public static final class EntryEstado implements BaseColumns{

        public final static String TABLE_NAME = "estado";

        public final static String ID_ESTADO = BaseColumns._ID;

        public final static  String ID_PAIS = "id_pais";

        public final static String ESTADO_ENABLED = "estado_enabled";

        public final static String ESTADO_ULT_VIAGEM = "estado_ult_viagem";

        public final static String ESTADO_SIGLA = "estado_sigla";

    }

    //Campos específicos da tabela Viagem
    public static final class EntryCidade implements BaseColumns{

        public final static String TABLE_NAME = "cidade";

        public final static String ID_CIDADE = BaseColumns._ID;

        public final static String CIDADE = "cidade";

        public final static String ID_ESTADO = "id_estado";

        public final static String CIDADE_ENABLED = "cidade_enabled";

        public final static String CIDADE_ULT_VIAGEM = "cidade_ult_viagem";

    }

    //Campos específicos da tabela Viagem
    public static final class EntryViagem implements BaseColumns{

        public final static String TABLE_NAME = "viagem";

        public final static String ID_VIAGEM = BaseColumns._ID;

        public final static String ID_CIDADE = "id_cidade";

        public final static String VIAGEM_DATA = "viagem_data";

        public final static String VIAGEM_QTD_DIAS = "viagem_qtd_dias";

        public final static String VIAGEM_HOSPEDAGEM = "viagem_hospedagem";

        public final static String VIAGEM_PASSEIOS = "viagem_passeios";

        public final static String VIAGEM_DESC = "viagem_desc";

        public final static String VIAGEM_LOCAL = "viagem_local";

    }

}

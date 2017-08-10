package com.rlemos.minhasviagensbr.dados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rlemos.minhasviagensbr.dados.ViagemContract.EntryPais;
import com.rlemos.minhasviagensbr.dados.ViagemContract.EntryEstado;
import com.rlemos.minhasviagensbr.dados.ViagemContract.EntryCidade;
import com.rlemos.minhasviagensbr.dados.ViagemContract.EntryViagem;

/**
 * Created by rlemos on 07/08/17.
 */

public class ViagemDbHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "viagem.db";

    private final static int DB_VERSION = 1;

    //Inicializa o banco de dados
    public ViagemDbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    //Cria os códigos contidos na classe abaixo na primeira execução
    public void onCreate(SQLiteDatabase db) {


        String create_table_pais = "CREATE TABLE " + ViagemContract.EntryPais.TABLE_NAME + " (" +
                EntryPais.ID_PAIS + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EntryPais.PAIS_SIGLA + " TEXT NOT NULL, " +
                EntryPais.PAIS_ABREV + " TEXT NOT NULL, " +
                EntryPais.PAIS + " TEXT NOT NULL, " +
                EntryPais.PAIS_ENABLED + " INTEGER DEFAULT 0, " +
                EntryPais.PAIS_ULT_VIAGEM + " TEXT);";

        String create_table_estados = "CREATE TABLE " + EntryEstado.TABLE_NAME + " (" +
                EntryEstado.ID_ESTADO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EntryEstado.ESTADO + " TEXT NOT NULL, " +
                EntryEstado.ESTADO_SIGLA + " TEXT NOT NULL, " +
                EntryEstado.ESTADO_ENABLED + " INTEGER DEFAULT 0, " +
                EntryEstado.ESTADO_ENABLED + " TEXT, " +
                EntryEstado.ID_PAIS + " INTEGER, " +
                "FOREIGN KEY( " + EntryEstado.ID_PAIS + ") REFERENCES" + EntryPais.TABLE_NAME +
                "( " + EntryEstado.ID_PAIS + " ));";

        String create_table_cidades = "CREATE TABLE " + EntryCidade.TABLE_NAME + " ( " +
                EntryCidade.ID_CIDADE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EntryCidade.CIDADE + " TEXT NOT NULL, " +
                EntryCidade.CIDADE_ENABLED + " INTEGER DEFAULT 0, " +
                EntryCidade.CIDADE_ULT_VIAGEM + " TEXT, " +
                EntryCidade.ID_ESTADO + " id_estado INTEGER, " +
                "FOREIGN KEY(" + EntryCidade.ID_ESTADO + ") REFERENCES " + EntryEstado.TABLE_NAME +
                "(" + EntryCidade.ID_ESTADO + "));";

        String create_table_viagens = "CREATE TABLE " + EntryViagem.TABLE_NAME + "( " +
                EntryViagem.ID_VIAGEM + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EntryViagem.VIAGEM_LOCAL + " TEXT, " +
                EntryViagem.VIAGEM_DATA + " TEXT NOT NULL, " +
                EntryViagem.VIAGEM_QTD_DIAS + " INTEGER, " +
                EntryViagem.VIAGEM_HOSPEDAGEM + " TEXT, " +
                EntryViagem.VIAGEM_PASSEIOS + " TEXT, " +
                EntryViagem.VIAGEM_DESC + " TEXT, " +
                EntryViagem.ID_CIDADE + " INTEGER, " +
                "FOREIGN KEY(" + EntryViagem.ID_CIDADE + ") REFERENCES " + EntryCidade.TABLE_NAME +
                "(" + EntryViagem.ID_CIDADE + "));";

        String insert_table_pais = "INSERT INTO"+ EntryPais.TABLE_NAME+"("+EntryPais.PAIS_SIGLA+","+
        EntryPais.PAIS_ABREV+","+EntryPais.PAIS+") VALUES " +
                "('AF', 'AFG','Afeganistão')," +
                "('ZA', 'ZAF','África do Sul')," +
                "('AX', 'ALA','Åland, Ilhas')," +
                "('AL', 'ALB','Albânia')," +
                "('DE', 'DEU','Alemanha')," +
                "('AD', 'AND','Andorra')," +
                "('AO', 'AGO','Angola')," +
                "('AI', 'AIA','Anguilla')," +
                "('AQ', 'ATA','Antárctida')," +
                "('AG', 'ATG','Antigua e Barbuda')," +
                "('AN', 'ANT','Antilhas Holandesas')," +
                "('SA', 'SAU','Arábia Saudita')," +
                "('DZ', 'DZA','Argélia')," +
                "('AR', 'ARG','Argentina')," +
                "('AM', 'ARM','Arménia')," +
                "('AW', 'ABW','Aruba')," +
                "('AU', 'AUS','Austrália')," +
                "('AT', 'AUT','Áustria')," +
                "('AZ', 'AZE','Azerbeijão')," +
                "('BS', 'BHS','Bahamas')," +
                "('BH', 'BHR','Bahrain')," +
                "('BD', 'BGD','Bangladesh')," +
                "('BB', 'BRB','Barbados')," +
                "('BE', 'BEL','Bélgica')," +
                "('BZ', 'BLZ','Belize')," +
                "('BJ', 'BEN','Benin')," +
                "('BM', 'BMU','Bermuda')," +
                "('BY', 'BLR','Bielo-Rússia')," +
                "('BO', 'BOL','Bolívia')," +
                "('BA', 'BIH','Bósnia-Herzegovina')," +
                "('BW', 'BWA','Botswana')," +
                "('BV', 'BVT','Bouvet, Ilha')," +
                "('BR', 'BRA','Brasil')," +
                "('BN', 'BRN','Brunei')," +
                "('BG', 'BGR','Bulgária')," +
                "('BF', 'BFA','Burkina Faso')," +
                "('BI', 'BDI','Burundi')," +
                "('BT', 'BTN','Butão')," +
                "('CV', 'CPV','Cabo Verde')," +
                "('KH', 'KHM','Cambodja')," +
                "('CM', 'CMR','Camarões')," +
                "('CA', 'CAN','Canadá')," +
                "('KY', 'CYM','Cayman, Ilhas')," +
                "('KZ', 'KAZ','Cazaquistão')," +
                "('CF', 'CAF','Centro-africana, República')," +
                "('TD', 'TCD','Chade')," +
                "('CZ', 'CZE','Checa, República')," +
                "('CL', 'CHL','Chile')," +
                "('CN', 'CHN','China')," +
                "('CY', 'CYP','Chipre')," +
                "('CX', 'CXR','Christmas, Ilha')," +
                "('CC', 'CCK','Cocos, Ilhas')," +
                "('CO', 'COL','Colômbia')," +
                "('KM', 'COM','Comores')," +
                "('CG', 'COG','Congo, República do')," +
                "('CD', 'COD','Congo, República Democrática do (antigo Zaire)')," +
                "('CK', 'COK','Cook, Ilhas')," +
                "('KR', 'KOR','Coreia do Sul')," +
                "('KP', 'PRK','Coreia, República Democrática da (Coreia do Norte)')," +
                "('CI', 'CIV','Costa do Marfim')," +
                "('CR', 'CRI','Costa Rica')," +
                "('HR', 'HRV','Croácia')," +
                "('CU', 'CUB','Cuba')," +
                "('DK', 'DNK','Dinamarca')," +
                "('DJ', 'DJI','Djibouti')," +
                "('DM', 'DMA','Dominica')," +
                "('DO', 'DOM','Dominicana, República')," +
                "('EG', 'EGY','Egipto')," +
                "('SV', 'SLV','El Salvador')," +
                "('AE', 'ARE','Emiratos Árabes Unidos')," +
                "('EC', 'ECU','Equador')," +
                "('ER', 'ERI','Eritreia')," +
                "('SK', 'SVK','Eslováquia')," +
                "('SI', 'SVN','Eslovénia')," +
                "('ES', 'ESP','Espanha')," +
                "('US', 'USA','Estados Unidos da América')," +
                "('EE', 'EST','Estónia')," +
                "('ET', 'ETH','Etiópia')," +
                "('FO', 'FRO','Faroe, Ilhas')," +
                "('FJ', 'FJI','Fiji')," +
                "('PH', 'PHL','Filipinas')," +
                "('FI', 'FIN','Finlândia')," +
                "('FR', 'FRA','França')," +
                "('GA', 'GAB','Gabão')," +
                "('GM', 'GMB','Gâmbia')," +
                "('GH', 'GHA','Gana')," +
                "('GE', 'GEO','Geórgia')," +
                "('GS', 'SGS','Geórgia do Sul e Sandwich do Sul, Ilhas')," +
                "('GI', 'GIB','Gibraltar')," +
                "('GR', 'GRC','Grécia')," +
                "('GD', 'GRD','Grenada')," +
                "('GL', 'GRL','Gronelândia')," +
                "('GP', 'GLP','Guadeloupe')," +
                "('GU', 'GUM','Guam')," +
                "('GT', 'GTM','Guatemala')," +
                "('GG', 'GGY','Guernsey')," +
                "('GY', 'GUY','Guiana')," +
                "('GF', 'GUF','Guiana Francesa')," +
                "('GW', 'GNB','Guiné-Bissau')," +
                "('GN', 'GIN','Guiné-Conacri')," +
                "('GQ', 'GNQ','Guiné Equatorial')," +
                "('HT', 'HTI','Haiti')," +
                "('HM', 'HMD','Heard e Ilhas McDonald, Ilha')," +
                "('HN', 'HND','Honduras')," +
                "('HK', 'HKG','Hong Kong')," +
                "('HU', 'HUN','Hungria')," +
                "('YE', 'YEM','Iémen')," +
                "('IN', 'IND','Índia')," +
                "('ID', 'IDN','Indonésia')," +
                "('IQ', 'IRQ','Iraque')," +
                "('IR', 'IRN','Irão')," +
                "('IE', 'IRL','Irlanda')," +
                "('IS', 'ISL','Islândia')," +
                "('IL', 'ISR','Israel')," +
                "('IT', 'ITA','Itália')," +
                "('JM', 'JAM','Jamaica')," +
                "('JP', 'JPN','Japão')," +
                "('JE', 'JEY','Jersey')," +
                "('JO', 'JOR','Jordânia')," +
                "('KI', 'KIR','Kiribati')," +
                "('KW', 'KWT','Kuwait')," +
                "('LA', 'LAO','Laos')," +
                "('LS', 'LSO','Lesoto')," +
                "('LV', 'LVA','Letónia')," +
                "('LB', 'LBN','Líbano')," +
                "('LR', 'LBR','Libéria')," +
                "('LY', 'LBY','Líbia')," +
                "('LI', 'LIE','Liechtenstein')," +
                "('LT', 'LTU','Lituânia')," +
                "('LU', 'LUX','Luxemburgo')," +
                "('MO', 'MAC','Macau')," +
                "('MK', 'MKD','Macedónia, República da')," +
                "('MG', 'MDG','Madagáscar')," +
                "('MY', 'MYS','Malásia')," +
                "('MW', 'MWI','Malawi')," +
                "('MV', 'MDV','Maldivas')," +
                "('ML', 'MLI','Mali')," +
                "('MT', 'MLT','Malta')," +
                "('FK', 'FLK','Malvinas, Ilhas (Falkland)')," +
                "('IM', 'IMN','Man, Ilha de')," +
                "('MP', 'MNP','Marianas Setentrionais')," +
                "('MA', 'MAR','Marrocos')," +
                "('MH', 'MHL','Marshall, Ilhas')," +
                "('MQ', 'MTQ','Martinica')," +
                "('MU', 'MUS','Maurícia')," +
                "('MR', 'MRT','Mauritânia')," +
                "('YT', 'MYT','Mayotte')," +
                "('UM', 'UMI','Menores Distantes dos Estados Unidos, Ilhas')," +
                "('MX', 'MEX','México')," +
                "('MM', 'MMR','Myanmar (antiga Birmânia)')," +
                "('FM', 'FSM','Micronésia, Estados Federados da')," +
                "('MZ', 'MOZ','Moçambique')," +
                "('MD', 'MDA','Moldávia')," +
                "('MC', 'MCO','Mónaco')," +
                "('MN', 'MNG','Mongólia')," +
                "('ME', 'MNE','Montenegro')," +
                "('MS', 'MSR','Montserrat')," +
                "('NA', 'NAM','Namíbia')," +
                "('NR', 'NRU','Nauru')," +
                "('NP', 'NPL','Nepal')," +
                "('NI', 'NIC','Nicarágua')," +
                "('NE', 'NER','Níger')," +
                "('NG', 'NGA','Nigéria')," +
                "('NU', 'NIU','Niue')," +
                "('NF', 'NFK','Norfolk, Ilha')," +
                "('NO', 'NOR','Noruega')," +
                "('NC', 'NCL','Nova Caledónia')," +
                "('NZ', 'NZL','Nova Zelândia (Aotearoa)')," +
                "('OM', 'OMN','Oman')," +
                "('NL', 'NLD','Países Baixos (Holanda)')," +
                "('PW', 'PLW','Palau')," +
                "('PS', 'PSE','Palestina')," +
                "('PA', 'PAN','Panamá')," +
                "('PG', 'PNG','Papua-Nova Guiné')," +
                "('PK', 'PAK','Paquistão')," +
                "('PY', 'PRY','Paraguai')," +
                "('PE', 'PER','Peru')," +
                "('PN', 'PCN','Pitcairn')," +
                "('PF', 'PYF','Polinésia Francesa')," +
                "('PL', 'POL','Polónia')," +
                "('PR', 'PRI','Porto Rico')," +
                "('PT', 'PRT','Portugal')," +
                "('QA', 'QAT','Qatar')," +
                "('KE', 'KEN','Quénia')," +
                "('KG', 'KGZ','Quirguistão')," +
                "('GB', 'GBR','Reino Unido da Grã-Bretanha e Irlanda do Norte')," +
                "('RE', 'REU','Reunião')," +
                "('RO', 'ROU','Roménia')," +
                "('RW', 'RWA','Ruanda')," +
                "('RU', 'RUS','Rússia')," +
                "('EH', 'ESH','Saara Ocidental')," +
                "('AS', 'ASM','Samoa Americana')," +
                "('WS', 'WSM','Samoa (Samoa Ocidental)')," +
                "('PM', 'SPM','Saint Pierre et Miquelon')," +
                "('SB', 'SLB','Salomão, Ilhas')," +
                "('KN', 'KNA','São Cristóvão e Névis (Saint Kitts e Nevis)')," +
                "('SM', 'SMR','San Marino')," +
                "('ST', 'STP','São Tomé e Príncipe')," +
                "('VC', 'VCT','São Vicente e Granadinas')," +
                "('SH', 'SHN','Santa Helena')," +
                "('LC', 'LCA','Santa Lúcia')," +
                "('SN', 'SEN','Senegal')," +
                "('SL', 'SLE','Serra Leoa')," +
                "('RS', 'SRB','Sérvia')," +
                "('SC', 'SYC','Seychelles')," +
                "('SG', 'SGP','Singapura')," +
                "('SY', 'SYR','Síria')," +
                "('SO', 'SOM','Somália')," +
                "('LK', 'LKA','Sri Lanka')," +
                "('SZ', 'SWZ','Suazilândia')," +
                "('SD', 'SDN','Sudão')," +
                "('SE', 'SWE','Suécia')," +
                "('CH', 'CHE','Suíça')," +
                "('SR', 'SUR','Suriname')," +
                "('SJ', 'SJM','Svalbard e Jan Mayen')," +
                "('TH', 'THA','Tailândia')," +
                "('TW', 'TWN','Taiwan')," +
                "('TJ', 'TJK','Tajiquistão')," +
                "('TZ', 'TZA','Tanzânia')," +
                "('TF', 'ATF','Terras Austrais e Antárticas Francesas (TAAF)')," +
                "('IO', 'IOT','Território Britânico do Oceano Índico')," +
                "('TL', 'TLS','Timor-Leste')," +
                "('TG', 'TGO','Togo')," +
                "('TK', 'TKL','Toquelau')," +
                "('TO', 'TON','Tonga')," +
                "('TT', 'TTO','Trindade e Tobago')," +
                "('TN', 'TUN','Tunísia')," +
                "('TC', 'TCA','Turks e Caicos')," +
                "('TM', 'TKM','Turquemenistão')," +
                "('TR', 'TUR','Turquia')," +
                "('TV', 'TUV','Tuvalu')," +
                "('UA', 'UKR','Ucrânia')," +
                "('UG', 'UGA','Uganda')," +
                "('UY', 'URY','Uruguai')," +
                "('UZ', 'UZB','Usbequistão')," +
                "('VU', 'VUT','Vanuatu')," +
                "('VA', 'VAT','Vaticano')," +
                "('VE', 'VEN','Venezuela')," +
                "('VN', 'VNM','Vietname')," +
                "('VI', 'VIR','Virgens Americanas, Ilhas')," +
                "('VG', 'VGB','Virgens Britânicas, Ilhas')," +
                "('WF', 'WLF','Wallis e Futuna')," +
                "('ZM', 'ZMB','Zâmbia')," +
                "('ZW', 'ZWE','Zimbabwe');";

        String insert_table_estados = "INSERT INTO "+ EntryEstado.TABLE_NAME+" ("+EntryEstado.ESTADO+ ","+
        EntryEstado.ESTADO_SIGLA+","+EntryEstado.ID_PAIS+") VALUES " +
                "('Acre', 'AC', 33)," +
                "('Alagoas', 'AL', 33)," +
                "('Amazonas', 'AM', 33)," +
                "('Amapá', 'AP', 33)," +
                "('Bahia', 'BA', 33)," +
                "('Ceará', 'CE', 33)," +
                "('Distrito Federal', 'DF', 33)," +
                "('Espírito Santo', 'ES', 33)," +
                "('Goiás', 'GO', 33)," +
                "('Maranhão', 'MA', 33)," +
                "('Minas Gerais', 'MG', 33)," +
                "('Mato Grosso do Sul', 'MS', 33)," +
                "('Mato Grosso', 'MT', 33)," +
                "('Pará', 'PA', 33)," +
                "('Paraíba', 'PB', 33)," +
                "('Pernambuco', 'PE', 33)," +
                "('Piauí', 'PI', 33)," +
                "('Paraná', 'PR', 33)," +
                "('Rio de Janeiro', 'RJ', 33)," +
                "('Rio Grande do Norte', 'RN', 33)," +
                "('Rondônia', 'RO', 33)," +
                "('Roraima', 'RR', 33)," +
                "('Rio Grande do Sul', 'RS', 33)," +
                "('Santa Catarina', 'SC', 33)," +
                "('Sergipe', 'SE', 33)," +
                "('São Paulo', 'SP', 33)," +
                "('Tocantins', 'TO', 33);";

        db.execSQL(create_table_pais);
        db.execSQL(create_table_estados);
        db.execSQL(create_table_cidades);
        db.execSQL(create_table_viagens);
        db.execSQL(insert_table_pais);
        db.execSQL(insert_table_estados);
    }


    @Override
    //Em caso Upgrade do Banco de Dados
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}


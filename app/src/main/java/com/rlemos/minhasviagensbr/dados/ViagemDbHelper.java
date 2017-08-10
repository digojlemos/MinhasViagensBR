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
                EntryEstado.ESTADO_ULT_VIAGEM+ " TEXT, " +
                EntryEstado.ID_PAIS + " INTEGER, " +
                "FOREIGN KEY( " + EntryEstado.ID_PAIS + ") REFERENCES " + EntryPais.TABLE_NAME +
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

        String insert_table_pais = "INSERT INTO "+ EntryPais.TABLE_NAME+"("+EntryPais.PAIS_SIGLA+","+
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
        
        String insert_table_cidades_1 = "INSERT INTO "+EntryCidade.TABLE_NAME +" ( "+EntryCidade.CIDADE+","+EntryCidade.ID_ESTADO+") VALUES "+
                "('Abadia de Goiás', 9)," +
                "('Abadia dos Dourados', 11)," +
                "('Abadiânia', 9)," +
                "('Abaeté', 11)," +
                "('Abaetetuba', 14)," +
                "('Abaiara', 6)," +
                "('Abaíra', 5)," +
                "('Abaré', 5)," +
                "('Abatiá', 18)," +
                "('Abdon Batista', 24)," +
                "('Abel Figueiredo', 14)," +
                "('Abelardo Luz', 24)," +
                "('Abre Campo', 11)," +
                "('Abreu e Lima', 16)," +
                "('Abreulândia', 27)," +
                "('Acaiaca', 11)," +
                "('Açailândia', 10)," +
                "('Acajutiba', 5)," +
                "('Acará', 14)," +
                "('Acarape', 6)," +
                "('Acaraú', 6)," +
                "('Acari', 20)," +
                "('Acauã', 17)," +
                "('Aceguá', 23)," +
                "('Acopiara', 6)," +
                "('Acorizal', 13)," +
                "('Acrelândia', 1)," +
                "('Acreúna', 9)," +
                "('Açu', 20)," +
                "('Açucena', 11)," +
                "('Adamantina', 26)," +
                "('Adelândia', 9)," +
                "('Adolfo', 26)," +
                "('Adrianópolis', 18)," +
                "('Adustina', 5)," +
                "('Afogados da Ingazeira', 16)," +
                "('Afonso Bezerra', 20)," +
                "('Afonso Cláudio', 8)," +
                "('Afonso Cunha', 10)," +
                "('Afrânio', 16)," +
                "('Afuá', 14)," +
                "('Agrestina', 16)," +
                "('Agricolândia', 17)," +
                "('Agrolândia', 24)," +
                "('Agronômica', 24)," +
                "('Água Azul do Norte', 14)," +
                "('Água Boa', 11)," +
                "('Água Boa', 13)," +
                "('Água Branca', 15)," +
                "('Água Branca', 17)," +
                "('Água Branca', 2)," +
                "('Água Clara', 12)," +
                "('Água Comprida', 11)," +
                "('Água Doce do Maranhão', 10)," +
                "('Água Doce do Norte', 8)," +
                "('Água Doce', 24)," +
                "('Água Fria de Goiás', 9)," +
                "('Água Fria', 5)," +
                "('Água Limpa', 9)," +
                "('Água Nova', 20)," +
                "('Água Preta', 16)," +
                "('Água Santa', 23)," +
                "('Aguaí', 26)," +
                "('Aguanil', 11)," +
                "('Águas Belas', 16)," +
                "('Águas da Prata', 26)," +
                "('Águas de Chapecó', 24)," +
                "('Águas de Lindóia', 26)," +
                "('Águas de Santa Bárbara', 26)," +
                "('Águas de São Pedro', 26)," +
                "('Águas Formosas', 11)," +
                "('Águas Frias', 24)," +
                "('Águas Lindas de Goiás', 9)," +
                "('Águas Mornas', 24)," +
                "('Águas Vermelhas', 11)," +
                "('Agudo', 23)," +
                "('Agudos do Sul', 18)," +
                "('Agudos', 26)," +
                "('Águia Branca', 8)," +
                "('Aguiar', 15)," +
                "('Aguiarnópolis', 27)," +
                "('Aimorés', 11)," +
                "('Aiquara', 5)," +
                "('Aiuaba', 6)," +
                "('Aiuruoca', 11)," +
                "('Ajuricaba', 23)," +
                "('Alagoa Grande', 15)," +
                "('Alagoa Nova', 15)," +
                "('Alagoa', 11)," +
                "('Alagoinha do Piauí', 17)," +
                "('Alagoinha', 15)," +
                "('Alagoinha', 16)," +
                "('Alagoinhas', 5)," +
                "('Alambari', 26)," +
                "('Albertina', 11)," +
                "('Alcântara', 10)," +
                "('Alcântaras', 6)," +
                "('Alcantil', 15)," +
                "('Alcinópolis', 12)," +
                "('Alcobaça', 5)," +
                "('Aldeias Altas', 10)," +
                "('Alecrim', 23)," +
                "('Alegre', 8)," +
                "('Alegrete do Piauí', 17)," +
                "('Alegrete', 23)," +
                "('Alegria', 23)," +
                "('Além Paraíba', 11)," +
                "('Alenquer', 14)," +
                "('Alexandria', 20)," +
                "('Alexânia', 9)," +
                "('Alfenas', 11)," +
                "('Alfredo Chaves', 8)," +
                "('Alfredo Marcondes', 26)," +
                "('Alfredo Vasconcelos', 11)," +
                "('Alfredo Wagner', 24)," +
                "('Algodão de Jandaíra', 15)," +
                "('Alhandra', 15)," +
                "('Aliança do Tocantins', 27)," +
                "('Aliança', 16)," +
                "('Almadina', 5)," +
                "('Almas', 27)," +
                "('Almeirim', 14)," +
                "('Almenara', 11)," +
                "('Almino Afonso', 20)," +
                "('Almirante Tamandaré do Sul', 23)," +
                "('Almirante Tamandaré', 18)," +
                "('Aloândia', 9)," +
                "('Alpercata', 11)," +
                "('Alpestre', 23)," +
                "('Alpinópolis', 11)," +
                "('Alta Floresta d`Oeste', 21)," +
                "('Alta Floresta', 13)," +
                "('Altair', 26)," +
                "('Altamira do Maranhão', 10)," +
                "('Altamira do Paraná', 18)," +
                "('Altamira', 14)," +
                "('Altaneira', 6)," +
                "('Alterosa', 11)," +
                "('Altinho', 16)," +
                "('Altinópolis', 26)," +
                "('Alto Alegre do Maranhão', 10)," +
                "('Alto Alegre do Pindaré', 10)," +
                "('Alto Alegre dos Parecis', 21)," +
                "('Alto Alegre', 22)," +
                "('Alto Alegre', 23)," +
                "('Alto Alegre', 26)," +
                "('Alto Araguaia', 13)," +
                "('Alto Bela Vista', 24)," +
                "('Alto Boa Vista', 13)," +
                "('Alto Caparaó', 11)," +
                "('Alto do Rodrigues', 20)," +
                "('Alto Feliz', 23)," +
                "('Alto Garças', 13)," +
                "('Alto Horizonte', 9)," +
                "('Alto Jequitibá', 11)," +
                "('Alto Longá', 17)," +
                "('Alto Paraguai', 13)," +
                "('Alto Paraíso de Goiás', 9)," +
                "('Alto Paraíso', 18)," +
                "('Alto Paraíso', 21)," +
                "('Alto Paraná', 18)," +
                "('Alto Parnaíba', 10)," +
                "('Alto Piquiri', 18)," +
                "('Alto Rio Doce', 11)," +
                "('Alto Rio Novo', 8)," +
                "('Alto Santo', 6)," +
                "('Alto Taquari', 13)," +
                "('Altônia', 18)," +
                "('Altos', 17)," +
                "('Alumínio', 26)," +
                "('Alvarães', 3)," +
                "('Alvarenga', 11)," +
                "('Álvares Florence', 26)," +
                "('Álvares Machado', 26)," +
                "('Álvaro de Carvalho', 26)," +
                "('Alvinlândia', 26)," +
                "('Alvinópolis', 11)," +
                "('Alvorada d`Oeste', 21)," +
                "('Alvorada de Minas', 11)," +
                "('Alvorada do Gurguéia', 17)," +
                "('Alvorada do Norte', 9)," +
                "('Alvorada do Sul', 18)," +
                "('Alvorada', 23)," +
                "('Alvorada', 27)," +
                "('Amajari', 22)," +
                "('Amambaí', 12)," +
                "('Amapá do Maranhão', 10)," +
                "('Amapá', 4)," +
                "('Amaporã', 18)," +
                "('Amaraji', 16)," +
                "('Amaral Ferrador', 23)," +
                "('Amaralina', 9)," +
                "('Amarante do Maranhão', 10)," +
                "('Amarante', 17)," +
                "('Amargosa', 5)," +
                "('Amaturá', 3)," +
                "('Amélia Rodrigues', 5)," +
                "('América Dourada', 5)," +
                "('Americana', 26)," +
                "('Americano do Brasil', 9)," +
                "('Américo Brasiliense', 26)," +
                "('Américo de Campos', 26)," +
                "('Ametista do Sul', 23)," +
                "('Amontada', 6)," +
                "('Amorinópolis', 9)," +
                "('Amparo de São Francisco', 25)," +
                "('Amparo do Serra', 11)," +
                "('Amparo', 15)," +
                "('Amparo', 26)," +
                "('Ampére', 18)," +
                "('Anadia', 2)," +
                "('Anagé', 5)," +
                "('Anahy', 18)," +
                "('Anajás', 14)," +
                "('Anajatuba', 10)," +
                "('Analândia', 26)," +
                "('Anamã', 3)," +
                "('Ananás', 27)," +
                "('Ananindeua', 14)," +
                "('Anápolis', 9)," +
                "('Anapu', 14)," +
                "('Anapurus', 10)," +
                "('Anastácio', 12)," +
                "('Anaurilândia', 12)," +
                "('Anchieta', 24)," +
                "('Anchieta', 8)," +
                "('Andaraí', 5)," +
                "('Andirá', 18)," +
                "('Andorinha', 5)," +
                "('Andradas', 11)," +
                "('Andradina', 26)," +
                "('André da Rocha', 23)," +
                "('Andrelândia', 11)," +
                "('Angatuba', 26)," +
                "('Angelândia', 11)," +
                "('Angélica', 12)," +
                "('Angelim', 16)," +
                "('Angelina', 24)," +
                "('Angical do Piauí', 17)," +
                "('Angical', 5)," +
                "('Angico', 27)," +
                "('Angicos', 20)," +
                "('Angra dos Reis', 19)," +
                "('Anguera', 5)," +
                "('Ângulo', 18)," +
                "('Anhanguera', 9)," +
                "('Anhembi', 26)," +
                "('Anhumas', 26)," +
                "('Anicuns', 9)," +
                "('Anísio de Abreu', 17)," +
                "('Anita Garibaldi', 24)," +
                "('Anitápolis', 24)," +
                "('Anori', 3)," +
                "('Anta Gorda', 23)," +
                "('Antas', 5)," +
                "('Antonina do Norte', 6)," +
                "('Antonina', 18)," +
                "('Antônio Almeida', 17)," +
                "('Antônio Cardoso', 5)," +
                "('Antônio Carlos', 11)," +
                "('Antônio Carlos', 24)," +
                "('Antônio Dias', 11)," +
                "('Antônio Gonçalves', 5)," +
                "('Antônio João', 12)," +
                "('Antônio Martins', 20)," +
                "('Antônio Olinto', 18)," +
                "('Antônio Prado de Minas', 11)," +
                "('Antônio Prado', 23)," +
                "('Aparecida d`Oeste', 26)," +
                "('Aparecida de Goiânia', 9)," +
                "('Aparecida do Rio Doce', 9)," +
                "('Aparecida do Rio Negro', 27)," +
                "('Aparecida do Taboado', 12)," +
                "('Aparecida', 15)," +
                "('Aparecida', 26)," +
                "('Aperibé', 19)," +
                "('Apiacá', 8)," +
                "('Apiacás', 13)," +
                "('Apiaí', 26)," +
                "('Apicum-Açu', 10)," +
                "('Apiúna', 24)," +
                "('Apodi', 20)," +
                "('Aporá', 5)," +
                "('Aporé', 9)," +
                "('Apuarema', 5)," +
                "('Apucarana', 18)," +
                "('Apuí', 3)," +
                "('Apuiarés', 6)," +
                "('Aquidabã', 25)," +
                "('Aquidauana', 12)," +
                "('Aquiraz', 6)," +
                "('Arabutã', 24)," +
                "('Araçagi', 15)," +
                "('Araçaí', 11)," +
                "('Aracaju', 25)," +
                "('Araçariguama', 26)," +
                "('Araças', 5)," +
                "('Aracati', 6)," +
                "('Aracatu', 5)," +
                "('Araçatuba', 26)," +
                "('Araci', 5)," +
                "('Aracitaba', 11)," +
                "('Araçoiaba da Serra', 26)," +
                "('Araçoiaba', 16)," +
                "('Aracoiaba', 6)," +
                "('Aracruz', 8)," +
                "('Araçu', 9)," +
                "('Araçuaí', 11)," +
                "('Aragarças', 9)," +
                "('Aragoiânia', 9)," +
                "('Aragominas', 27)," +
                "('Araguacema', 27)," +
                "('Araguaçu', 27)," +
                "('Araguaiana', 13)," +
                "('Araguaína', 27)," +
                "('Araguainha', 13)," +
                "('Araguanã', 10)," +
                "('Araguanã', 27)," +
                "('Araguapaz', 9)," +
                "('Araguari', 11)," +
                "('Araguatins', 27)," +
                "('Araioses', 10)," +
                "('Aral Moreira', 12)," +
                "('Aramari', 5)," +
                "('Arambaré', 23)," +
                "('Arame', 10)," +
                "('Aramina', 26)," +
                "('Arandu', 26)," +
                "('Arantina', 11)," +
                "('Arapeí', 26)," +
                "('Arapiraca', 2)," +
                "('Arapoema', 27)," +
                "('Araponga', 11)," +
                "('Arapongas', 18)," +
                "('Araporã', 11)," +
                "('Arapoti', 18)," +
                "('Arapuá', 11)," +
                "('Arapuã', 18)," +
                "('Araputanga', 13)," +
                "('Araquari', 24)," +
                "('Arara', 15)," +
                "('Araranguá', 24)," +
                "('Araraquara', 26)," +
                "('Araras', 26)," +
                "('Ararendá', 6)," +
                "('Arari', 10)," +
                "('Araricá', 23)," +
                "('Araripe', 6)," +
                "('Araripina', 16)," +
                "('Araruama', 19)," +
                "('Araruna', 15)," +
                "('Araruna', 18)," +
                "('Arataca', 5)," +
                "('Aratiba', 23)," +
                "('Aratuba', 6)," +
                "('Aratuípe', 5)," +
                "('Arauá', 25)," +
                "('Araucária', 18)," +
                "('Araújos', 11)," +
                "('Araxá', 11)," +
                "('Arceburgo', 11)," +
                "('Arco-Íris', 26)," +
                "('Arcos', 11)," +
                "('Arcoverde', 16)," +
                "('Areado', 11)," +
                "('Areal', 19)," +
                "('Arealva', 26)," +
                "('Areia Branca', 20)," +
                "('Areia Branca', 25)," +
                "('Areia de Baraúnas', 15)," +
                "('Areia', 15)," +
                "('Areial', 15)," +
                "('Areias', 26)," +
                "('Areiópolis', 26)," +
                "('Arenápolis', 13)," +
                "('Arenópolis', 9)," +
                "('Arês', 20)," +
                "('Argirita', 11)," +
                "('Aricanduva', 11)," +
                "('Arinos', 11)," +
                "('Aripuanã', 13)," +
                "('Ariquemes', 21)," +
                "('Ariranha do Ivaí', 18)," +
                "('Ariranha', 26)," +
                "('Armação dos Búzios', 19)," +
                "('Armazém', 24)," +
                "('Arneiroz', 6)," +
                "('Aroazes', 17)," +
                "('Aroeiras do Itaim', 17)," +
                "('Aroeiras', 15)," +
                "('Arraial do Cabo', 19)," +
                "('Arraial', 17)," +
                "('Arraias', 27)," +
                "('Arroio do Meio', 23)," +
                "('Arroio do Padre', 23)," +
                "('Arroio do Sal', 23)," +
                "('Arroio do Tigre', 23)," +
                "('Arroio dos Ratos', 23)," +
                "('Arroio Grande', 23)," +
                "('Arroio Trinta', 24)," +
                "('Artur Nogueira', 26)," +
                "('Aruanã', 9)," +
                "('Arujá', 26)," +
                "('Arvoredo', 24)," +
                "('Arvorezinha', 23)," +
                "('Ascurra', 24)," +
                "('Aspásia', 26)," +
                "('Assaí', 18)," +
                "('Assaré', 6)," +
                "('Assis Brasil', 1)," +
                "('Assis Chateaubriand', 18)," +
                "('Assis', 26)," +
                "('Assunção do Piauí', 17)," +
                "('Assunção', 15)," +
                "('Astolfo Dutra', 11)," +
                "('Astorga', 18)," +
                "('Atalaia do Norte', 3)," +
                "('Atalaia', 18)," +
                "('Atalaia', 2)," +
                "('Atalanta', 24)," +
                "('Ataléia', 11)," +
                "('Atibaia', 26)," +
                "('Atilio Vivacqua', 8)," +
                "('Augustinópolis', 27)," +
                "('Augusto Corrêa', 14)," +
                "('Augusto de Lima', 11)," +
                "('Augusto Pestana', 23)," +
                "('Augusto Severo', 20)," +
                "('Áurea', 23)," +
                "('Aurelino Leal', 5)," +
                "('Auriflama', 26)," +
                "('Aurilândia', 9)," +
                "('Aurora do Pará', 14)," +
                "('Aurora do Tocantins', 27)," +
                "('Aurora', 24)," +
                "('Aurora', 6)," +
                "('Autazes', 3)," +
                "('Avaí', 26)," +
                "('Avanhandava', 26)," +
                "('Avaré', 26)," +
                "('Aveiro', 14)," +
                "('Avelino Lopes', 17)," +
                "('Avelinópolis', 9)," +
                "('Axixá do Tocantins', 27)," +
                "('Axixá', 10)," +
                "('Babaçulândia', 27)," +
                "('Bacabal', 10)," +
                "('Bacabeira', 10)," +
                "('Bacuri', 10)," +
                "('Bacurituba', 10)," +
                "('Bady Bassitt', 26)," +
                "('Baependi', 11)," +
                "('Bagé', 23)," +
                "('Bagre', 14)," +
                "('Baía da Traição', 15)," +
                "('Baía Formosa', 20)," +
                "('Baianópolis', 5)," +
                "('Baião', 14)," +
                "('Baixa Grande do Ribeiro', 17)," +
                "('Baixa Grande', 5)," +
                "('Baixio', 6)," +
                "('Baixo Guandu', 8)," +
                "('Balbinos', 26)," +
                "('Baldim', 11)," +
                "('Baliza', 9)," +
                "('Balneário Arroio do Silva', 24)," +
                "('Balneário Barra do Sul', 24)," +
                "('Balneário Camboriú', 24)," +
                "('Balneário Gaivota', 24)," +
                "('Balneário Pinhal', 23)," +
                "('Balsa Nova', 18)," +
                "('Bálsamo', 26)," +
                "('Balsas', 10)," +
                "('Bambuí', 11)," +
                "('Banabuiú', 6)," +
                "('Bananal', 26)," +
                "('Bananeiras', 15)," +
                "('Bandeira do Sul', 11)," +
                "('Bandeira', 11)," +
                "('Bandeirante', 24)," +
                "('Bandeirantes do Tocantins', 27)," +
                "('Bandeirantes', 12)," +
                "('Bandeirantes', 18)," +
                "('Bannach', 14)," +
                "('Banzaê', 5)," +
                "('Barão de Antonina', 26)," +
                "('Barão de Cocais', 11)," +
                "('Barão de Cotegipe', 23)," +
                "('Barão de Grajaú', 10)," +
                "('Barão de Melgaço', 13)," +
                "('Barão de Monte Alto', 11)," +
                "('Barão do Triunfo', 23)," +
                "('Barão', 23)," +
                "('Baraúna', 15)," +
                "('Baraúna', 20)," +
                "('Barbacena', 11)," +
                "('Barbalha', 6)," +
                "('Barbosa Ferraz', 18)," +
                "('Barbosa', 26)," +
                "('Barcarena', 14)," +
                "('Barcelona', 20)," +
                "('Barcelos', 3)," +
                "('Bariri', 26)," +
                "('Barra Bonita', 24)," +
                "('Barra Bonita', 26)," +
                "('Barra d`Alcântara', 17)," +
                "('Barra da Estiva', 5)," +
                "('Barra de Guabiraba', 16)," +
                "('Barra de Santa Rosa', 15)," +
                "('Barra de Santana', 15)," +
                "('Barra de Santo Antônio', 2)," +
                "('Barra de São Francisco', 8)," +
                "('Barra de São Miguel', 15)," +
                "('Barra de São Miguel', 2)," +
                "('Barra do Bugres', 13)," +
                "('Barra do Chapéu', 26)," +
                "('Barra do Choça', 5)," +
                "('Barra do Corda', 10)," +
                "('Barra do Garças', 13)," +
                "('Barra do Guarita', 23)," +
                "('Barra do Jacaré', 18)," +
                "('Barra do Mendes', 5)," +
                "('Barra do Ouro', 27)," +
                "('Barra do Piraí', 19)," +
                "('Barra do Quaraí', 23)," +
                "('Barra do Ribeiro', 23)," +
                "('Barra do Rio Azul', 23)," +
                "('Barra do Rocha', 5)," +
                "('Barra do Turvo', 26)," +
                "('Barra dos Coqueiros', 25)," +
                "('Barra Funda', 23)," +
                "('Barra Longa', 11)," +
                "('Barra Mansa', 19)," +
                "('Barra Velha', 24)," +
                "('Barra', 5)," +
                "('Barracão', 18)," +
                "('Barracão', 23)," +
                "('Barras', 17)," +
                "('Barreira', 6)," +
                "('Barreiras do Piauí', 17)," +
                "('Barreiras', 5)," +
                "('Barreirinha', 3)," +
                "('Barreirinhas', 10)," +
                "('Barreiros', 16)," +
                "('Barretos', 26)," +
                "('Barrinha', 26)," +
                "('Barro Alto', 5)," +
                "('Barro Alto', 9)," +
                "('Barro Duro', 17)," +
                "('Barro Preto (antigo Gov. Lomanto Jr.)', 5)," +
                "('Barro', 6)," +
                "('Barrocas', 5)," +
                "('Barrolândia', 27)," +
                "('Barroquinha', 6)," +
                "('Barros Cassal', 23)," +
                "('Barroso', 11)," +
                "('Barueri', 26)," +
                "('Bastos', 26)," +
                "('Bataguassu', 12)," +
                "('Bataiporã', 12)," +
                "('Batalha', 17)," +
                "('Batalha', 2)," +
                "('Batatais', 26)," +
                "('Baturité', 6)," +
                "('Bauru', 26)," +
                "('Bayeux', 15)," +
                "('Bebedouro', 26)," +
                "('Beberibe', 6)," +
                "('Bela Cruz', 6)," +
                "('Bela Vista da Caroba', 18)," +
                "('Bela Vista de Goiás', 9)," +
                "('Bela Vista de Minas', 11)," +
                "('Bela Vista do Maranhão', 10)," +
                "('Bela Vista do Paraíso', 18)," +
                "('Bela Vista do Piauí', 17)," +
                "('Bela Vista do Toldo', 24)," +
                "('Bela Vista', 12)," +
                "('Belágua', 10)," +
                "('Belém de Maria', 16)," +
                "('Belém de São Francisco', 16)," +
                "('Belém do Brejo do Cruz', 15)," +
                "('Belém do Piauí', 17)," +
                "('Belém', 14)," +
                "('Belém', 15)," +
                "('Belém', 2)," +
                "('Belford Roxo', 19)," +
                "('Belmiro Braga', 11)," +
                "('Belmonte', 24)," +
                "('Belmonte', 5)," +
                "('Belo Campo', 5)," +
                "('Belo Horizonte', 11)," +
                "('Belo Jardim', 16)," +
                "('Belo Monte', 2)," +
                "('Belo Oriente', 11)," +
                "('Belo Vale', 11)," +
                "('Belterra', 14)," +
                "('Beneditinos', 17)," +
                "('Benedito Leite', 10)," +
                "('Benedito Novo', 24)," +
                "('Benevides', 14)," +
                "('Benjamin Constant do Sul', 23)," +
                "('Benjamin Constant', 3)," +
                "('Bento de Abreu', 26)," +
                "('Bento Fernandes', 20)," +
                "('Bento Gonçalves', 23)," +
                "('Bequimão', 10)," +
                "('Berilo', 11)," +
                "('Berizal', 11)," +
                "('Bernardino Batista', 15)," +
                "('Bernardino de Campos', 26)," +
                "('Bernardo do Mearim', 10)," +
                "('Bernardo Sayão', 27)," +
                "('Bertioga', 26)," +
                "('Bertolínia', 17)," +
                "('Bertópolis', 11)," +
                "('Beruri', 3)," +
                "('Betânia do Piauí', 17)," +
                "('Betânia', 16)," +
                "('Betim', 11)," +
                "('Bezerros', 16)," +
                "('Bias Fortes', 11)," +
                "('Bicas', 11)," +
                "('Biguaçu', 24)," +
                "('Bilac', 26)," +
                "('Biquinhas', 11)," +
                "('Birigui', 26)," +
                "('Biritiba-Mirim', 26)," +
                "('Biritinga', 5)," +
                "('Bituruna', 18)," +
                "('Blumenau', 24)," +
                "('Boa Esperança do Iguaçu', 18)," +
                "('Boa Esperança do Sul', 26)," +
                "('Boa Esperança', 11)," +
                "('Boa Esperança', 18)," +
                "('Boa Esperança', 8)," +
                "('Boa Hora', 17)," +
                "('Boa Nova', 5)," +
                "('Boa Ventura de São Roque', 18)," +
                "('Boa Ventura', 15)," +
                "('Boa Viagem', 6)," +
                "('Boa Vista da Aparecida', 18)," +
                "('Boa Vista das Missões', 23)," +
                "('Boa Vista do Buricá', 23)," +
                "('Boa Vista do Cadeado', 23)," +
                "('Boa Vista do Gurupi', 10)," +
                "('Boa Vista do Incra', 23)," +
                "('Boa Vista do Ramos', 3)," +
                "('Boa Vista do Sul', 23)," +
                "('Boa Vista do Tupim', 5)," +
                "('Boa Vista', 15)," +
                "('Boa Vista', 22)," +
                "('Boca da Mata', 2)," +
                "('Boca do Acre', 3)," +
                "('Bocaina de Minas', 11)," +
                "('Bocaina do Sul', 24)," +
                "('Bocaina', 17)," +
                "('Bocaina', 26)," +
                "('Bocaiúva do Sul', 18)," +
                "('Bocaiúva', 11)," +
                "('Bodó', 20)," +
                "('Bodocó', 16)," +
                "('Bodoquena', 12)," +
                "('Bofete', 26)," +
                "('Boituva', 26)," +
                "('Bom Conselho', 16)," +
                "('Bom Despacho', 11)," +
                "('Bom Jardim da Serra', 24)," +
                "('Bom Jardim de Goiás', 9)," +
                "('Bom Jardim de Minas', 11)," +
                "('Bom Jardim', 10)," +
                "('Bom Jardim', 16)," +
                "('Bom Jardim', 19)," +
                "('Bom Jesus da Lapa', 5)," +
                "('Bom Jesus da Penha', 11)," +
                "('Bom Jesus da Serra', 5)," +
                "('Bom Jesus das Selvas', 10)," +
                "('Bom Jesus de Goiás', 9)," +
                "('Bom Jesus do Amparo', 11)," +
                "('Bom Jesus do Araguaia', 13)," +
                "('Bom Jesus do Galho', 11)," +
                "('Bom Jesus do Itabapoana', 19)," +
                "('Bom Jesus do Norte', 8)," +
                "('Bom Jesus do Oeste', 24)," +
                "('Bom Jesus do Sul', 18)," +
                "('Bom Jesus do Tocantins', 14)," +
                "('Bom Jesus do Tocantins', 27)," +
                "('Bom Jesus dos Perdões', 26)," +
                "('Bom Jesus', 15)," +
                "('Bom Jesus', 17)," +
                "('Bom Jesus', 20)," +
                "('Bom Jesus', 23)," +
                "('Bom Jesus', 24)," +
                "('Bom Lugar', 10)," +
                "('Bom Princípio do Piauí', 17)," +
                "('Bom Princípio', 23)," +
                "('Bom Progresso', 23)," +
                "('Bom Repouso', 11)," +
                "('Bom Retiro do Sul', 23)," +
                "('Bom Retiro', 24)," +
                "('Bom Sucesso de Itararé', 26)," +
                "('Bom Sucesso do Sul', 18)," +
                "('Bom Sucesso', 11)," +
                "('Bom Sucesso', 15)," +
                "('Bom Sucesso', 18)," +
                "('Bombinhas', 24)," +
                "('Bonfim do Piauí', 17)," +
                "('Bonfim', 11)," +
                "('Bonfim', 22)," +
                "('Bonfinópolis de Minas', 11)," +
                "('Bonfinópolis', 9)," +
                "('Boninal', 5)," +
                "('Bonito de Minas', 11)," +
                "('Bonito de Santa Fé', 15)," +
                "('Bonito', 12)," +
                "('Bonito', 14)," +
                "('Bonito', 16)," +
                "('Bonito', 5)," +
                "('Bonópolis', 9)," +
                "('Boqueirão do Leão', 23)," +
                "('Boqueirão do Piauí', 17)," +
                "('Boqueirão', 15)," +
                "('Boquim', 25)," +
                "('Boquira', 5)," +
                "('Borá', 26)," +
                "('Boracéia', 26)," +
                "('Borba', 3)," +
                "('Borborema', 15)," +
                "('Borborema', 26)," +
                "('Borda da Mata', 11)," +
                "('Borebi', 26)," +
                "('Borrazópolis', 18)," +
                "('Bossoroca', 23)," +
                "('Botelhos', 11)," +
                "('Botucatu', 26)," +
                "('Botumirim', 11)," +
                "('Botuporã', 5)," +
                "('Botuverá', 24)," +
                "('Bozano', 23)," +
                "('Braço do Norte', 24)," +
                "('Braço do Trombudo', 24)," +
                "('Braga', 23)," +
                "('Bragança Paulista', 26)," +
                "('Bragança', 14)," +
                "('Braganey', 18)," +
                "('Branquinha', 2)," +
                "('Brás Pires', 11)," +
                "('Brasil Novo', 14)," +
                "('Brasilândia de Minas', 11)," +
                "('Brasilândia do Sul', 18)," +
                "('Brasilândia do Tocantins', 27)," +
                "('Brasilândia', 12)," +
                "('Brasiléia', 1)," +
                "('Brasileira', 17)," +
                "('Brasília de Minas', 11)," +
                "('Brasília', 7)," +
                "('Brasnorte', 13)," +
                "('Brasópolis', 11)," +
                "('Braúna', 26)," +
                "('Braúnas', 11)," +
                "('Brazabrantes', 9)," +
                "('Brejão', 16)," +
                "('Brejetuba', 8)," +
                "('Brejinho de Nazaré', 27)," +
                "('Brejinho', 16)," +
                "('Brejinho', 20)," +
                "('Brejo Alegre', 26)," +
                "('Brejo da Madre de Deus', 16)," +
                "('Brejo de Areia', 10)," +
                "('Brejo do Cruz', 15)," +
                "('Brejo do Piauí', 17)," +
                "('Brejo dos Santos', 15)," +
                "('Brejo Grande do Araguaia', 14)," +
                "('Brejo Grande', 25)," +
                "('Brejo Santo', 6)," +
                "('Brejo', 10)," +
                "('Brejões', 5)," +
                "('Brejolândia', 5)," +
                "('Breu Branco', 14)," +
                "('Breves', 14)," +
                "('Britânia', 9)," +
                "('Brochier', 23)," +
                "('Brodowski', 26)," +
                "('Brotas de Macaúbas', 5)," +
                "('Brotas', 26)," +
                "('Brumadinho', 11)," +
                "('Brumado', 5)," +
                "('Brunópolis', 24)," +
                "('Brusque', 24)," +
                "('Bueno Brandão', 11)," +
                "('Buenópolis', 11)," +
                "('Buenos Aires', 16)," +
                "('Buerarema', 5)," +
                "('Bugre', 11)," +
                "('Buíque', 16)," +
                "('Bujari', 1)," +
                "('Bujaru', 14)," +
                "('Buri', 26)," +
                "('Buritama', 26)," +
                "('Buriti Alegre', 9)," +
                "('Buriti Bravo', 10)," +
                "('Buriti de Goiás', 9)," +
                "('Buriti do Tocantins', 27)," +
                "('Buriti dos Lopes', 17)," +
                "('Buriti dos Montes', 17)," +
                "('Buriti', 10)," +
                "('Buriticupu', 10)," +
                "('Buritinópolis', 9)," +
                "('Buritirama', 5)," +
                "('Buritirana', 10)," +
                "('Buritis', 11)," +
                "('Buritis', 21)," +
                "('Buritizal', 26)," +
                "('Buritizeiro', 11)," +
                "('Butiá', 23)," +
                "('Caapiranga', 3)," +
                "('Caaporã', 15)," +
                "('Caarapó', 12)," +
                "('Caatiba', 5)," +
                "('Cabaceiras do Paraguaçu', 5)," +
                "('Cabaceiras', 15)," +
                "('Cabeceira Grande', 11)," +
                "('Cabeceiras do Piauí', 17)," +
                "('Cabeceiras', 9)," +
                "('Cabedelo', 15)," +
                "('Cabixi', 21)," +
                "('Cabo de Santo Agostinho', 16)," +
                "('Cabo Frio', 19)," +
                "('Cabo Verde', 11)," +
                "('Cabrália Paulista', 26)," +
                "('Cabreúva', 26)," +
                "('Cabrobó', 16)," +
                "('Caçador', 24)," +
                "('Caçapava do Sul', 23)," +
                "('Caçapava', 26)," +
                "('Cacaulândia', 21)," +
                "('Cacequi', 23)," +
                "('Cáceres', 13)," +
                "('Cachoeira Alta', 9)," +
                "('Cachoeira da Prata', 11)," +
                "('Cachoeira de Goiás', 9)," +
                "('Cachoeira de Minas', 11)," +
                "('Cachoeira de Pajeú', 11)," +
                "('Cachoeira do Arari', 14)," +
                "('Cachoeira do Piriá', 14)," +
                "('Cachoeira do Sul', 23)," +
                "('Cachoeira dos Índios', 15)," +
                "('Cachoeira Dourada', 11)," +
                "('Cachoeira Dourada', 9)," +
                "('Cachoeira Grande', 10)," +
                "('Cachoeira Paulista', 26)," +
                "('Cachoeira', 5)," +
                "('Cachoeiras de Macacu', 19)," +
                "('Cachoeirinha', 16)," +
                "('Cachoeirinha', 23)," +
                "('Cachoeirinha', 27)," +
                "('Cachoeiro de Itapemirim', 8)," +
                "('Cacimba de Areia', 15)," +
                "('Cacimba de Dentro', 15)," +
                "('Cacimbas', 15)," +
                "('Cacimbinhas', 2)," +
                "('Cacique Doble', 23)," +
                "('Cacoal', 21)," +
                "('Caconde', 26)," +
                "('Caçu', 9)," +
                "('Caculé', 5)," +
                "('Caém', 5)," +
                "('Caetanópolis', 11)," +
                "('Caetanos', 5)," +
                "('Caeté', 11)," +
                "('Caetés', 16)," +
                "('Caetité', 5)," +
                "('Cafarnaum', 5)," +
                "('Cafeara', 18)," +
                "('Cafelândia', 18)," +
                "('Cafelândia', 26)," +
                "('Cafezal do Sul', 18)," +
                "('Caiabu', 26)," +
                "('Caiana', 11)," +
                "('Caiapônia', 9)," +
                "('Caibaté', 23)," +
                "('Caibi', 24)," +
                "('Caiçara do Norte', 20)," +
                "('Caiçara do Rio do Vento', 20)," +
                "('Caiçara', 15)," +
                "('Caiçara', 23)," +
                "('Caicó', 20)," +
                "('Caieiras', 26)," +
                "('Cairu', 5)," +
                "('Caiuá', 26)," +
                "('Cajamar', 26)," +
                "('Cajapió', 10)," +
                "('Cajari', 10)," +
                "('Cajati', 26)," +
                "('Cajazeiras do Piauí', 17)," +
                "('Cajazeiras', 15)," +
                "('Cajazeirinhas', 15)," +
                "('Cajobi', 26)," +
                "('Cajueiro da Praia', 17)," +
                "('Cajueiro', 2)," +
                "('Cajuri', 11)," +
                "('Cajuru', 26)," +
                "('Calçado', 16)," +
                "('Calçoene', 4)," +
                "('Caldas Brandão', 15)," +
                "('Caldas Novas', 9)," +
                "('Caldas', 11)," +
                "('Caldazinha', 9)," +
                "('Caldeirão Grande do Piauí', 17)," +
                "('Caldeirão Grande', 5)," +
                "('Califórnia', 18)," +
                "('Calmon', 24)," +
                "('Calumbi', 16)," +
                "('Camacan', 5)," +
                "('Camaçari', 5)," +
                "('Camacho', 11)," +
                "('Camalaú', 15)," +
                "('Camamu', 5)," +
                "('Camanducaia', 11)," +
                "('Camapuã', 12)," +
                "('Camaquã', 23)," +
                "('Camaragibe', 16)," +
                "('Camargo', 23)," +
                "('Cambará do Sul', 23)," +
                "('Cambará', 18)," +
                "('Cambé', 18)," +
                "('Cambira', 18)," +
                "('Camboriú', 24)," +
                "('Cambuci', 19)," +
                "('Cambuí', 11)," +
                "('Cambuquira', 11)," +
                "('Cametá', 14)," +
                "('Camocim de São Félix', 16)," +
                "('Camocim', 6)," +
                "('Campanário', 11)," +
                "('Campanha', 11)," +
                "('Campestre da Serra', 23)," +
                "('Campestre de Goiás', 9)," +
                "('Campestre do Maranhão', 10)," +
                "('Campestre', 11)," +
                "('Campestre', 2)," +
                "('Campina da Lagoa', 18)," +
                "('Campina das Missões', 23)," +
                "('Campina do Monte Alegre', 26)," +
                "('Campina do Simão', 18)," +
                "('Campina Grande do Sul', 18)," +
                "('Campina Grande', 15)," +
                "('Campina Verde', 11)," +
                "('Campinaçu', 9)," +
                "('Campinápolis', 13)," +
                "('Campinas do Piauí', 17)," +
                "('Campinas do Sul', 23)," +
                "('Campinas', 26)," +
                "('Campinorte', 9)," +
                "('Campo Alegre de Goiás', 9)," +
                "('Campo Alegre de Lourdes', 5)," +
                "('Campo Alegre do Fidalgo', 17)," +
                "('Campo Alegre', 2)," +
                "('Campo Alegre', 24)," +
                "('Campo Azul', 11)," +
                "('Campo Belo do Sul', 24)," +
                "('Campo Belo', 11)," +
                "('Campo Bom', 23)," +
                "('Campo Bonito', 18)," +
                "('Campo de Santana', 15)," +
                "('Campo do Brito', 25)," +
                "('Campo do Meio', 11)," +
                "('Campo do Tenente', 18)," +
                "('Campo Erê', 24)," +
                "('Campo Florido', 11)," +
                "('Campo Formoso', 5)," +
                "('Campo Grande do Piauí', 17)," +
                "('Campo Grande', 12)," +
                "('Campo Grande', 2)," +
                "('Campo Largo do Piauí', 17)," +
                "('Campo Largo', 18)," +
                "('Campo Limpo de Goiás', 9)," +
                "('Campo Limpo Paulista', 26)," +
                "('Campo Magro', 18)," +
                "('Campo Maior', 17)," +
                "('Campo Mourão', 18)," +
                "('Campo Novo de Rondônia', 21)," +
                "('Campo Novo do Parecis', 13)," +
                "('Campo Novo', 23)," +
                "('Campo Redondo', 20)," +
                "('Campo Verde', 13)," +
                "('Campos Altos', 11)," +
                "('Campos Belos', 9)," +
                "('Campos Borges', 23)," +
                "('Campos de Júlio', 13)," +
                "('Campos do Jordão', 26)," +
                "('Campos dos Goytacazes', 19)," +
                "('Campos Gerais', 11)," +
                "('Campos Lindos', 27)," +
                "('Campos Novos Paulista', 26)," +
                "('Campos Novos', 24)," +
                "('Campos Sales', 6)," +
                "('Campos Verdes', 9)," +
                "('Camutanga', 16)," +
                "('Cana Verde', 11)," +
                "('Canaã dos Carajás', 14)," +
                "('Canaã', 11)," +
                "('Canabrava do Norte', 13)," +
                "('Cananéia', 26)," +
                "('Canapi', 2)," +
                "('Canápolis', 11)," +
                "('Canápolis', 5)," +
                "('Canarana', 13)," +
                "('Canarana', 5)," +
                "('Canas', 26)," +
                "('Canavieira', 17)," +
                "('Canavieiras', 5)," +
                "('Candeal', 5)," +
                "('Candeias do Jamari', 21)," +
                "('Candeias', 11)," +
                "('Candeias', 5)," +
                "('Candelária', 23)," +
                "('Candiba', 5)," +
                "('Cândido de Abreu', 18)," +
                "('Cândido Godói', 23)," +
                "('Cândido Mendes', 10)," +
                "('Cândido Mota', 26)," +
                "('Cândido Rodrigues', 26)," +
                "('Cândido Sales', 5)," +
                "('Candiota', 23)," +
                "('Candói', 18)," +
                "('Canela', 23)," +
                "('Canelinha', 24)," +
                "('Canguaretama', 20)," +
                "('Canguçu', 23)," +
                "('Canhoba', 25)," +
                "('Canhotinho', 16)," +
                "('Canindé de São Francisco', 25)," +
                "('Canindé', 6)," +
                "('Canitar', 26)," +
                "('Canoas', 23)," +
                "('Canoinhas', 24)," +
                "('Cansanção', 5)," +
                "('Cantá', 22)," +
                "('Cantagalo', 11)," +
                "('Cantagalo', 18)," +
                "('Cantagalo', 19)," +
                "('Cantanhede', 10)," +
                "('Canto do Buriti', 17)," +
                "('Canudos do Vale', 23)," +
                "('Canudos', 5)," +
                "('Canutama', 3)," +
                "('Capanema', 14)," +
                "('Capanema', 18)," +
                "('Capão Alto', 24)," +
                "('Capão Bonito do Sul', 23)," +
                "('Capão Bonito', 26)," +
                "('Capão da Canoa', 23)," +
                "('Capão do Cipó', 23)," +
                "('Capão do Leão', 23)," +
                "('Caparaó', 11)," +
                "('Capela de Santana', 23)," +
                "('Capela do Alto Alegre', 5)," +
                "('Capela do Alto', 26)," +
                "('Capela Nova', 11)," +
                "('Capela', 2)," +
                "('Capela', 25)," +
                "('Capelinha', 11)," +
                "('Capetinga', 11)," +
                "('Capim Branco', 11)," +
                "('Capim Grosso', 5)," +
                "('Capim', 15)," +
                "('Capinópolis', 11)," +
                "('Capinzal do Norte', 10)," +
                "('Capinzal', 24)," +
                "('Capistrano', 6)," +
                "('Capitão Andrade', 11)," +
                "('Capitão de Campos', 17)," +
                "('Capitão Enéas', 11)," +
                "('Capitão Gervásio Oliveira', 17)," +
                "('Capitão Leônidas Marques', 18)," +
                "('Capitão Poço', 14)," +
                "('Capitão', 23)," +
                "('Capitólio', 11)," +
                "('Capivari de Baixo', 24)," +
                "('Capivari do Sul', 23)," +
                "('Capivari', 26)," +
                "('Capixaba', 1)," +
                "('Capoeiras', 16)," +
                "('Caputira', 11)," +
                "('Caraá', 23)," +
                "('Caracaraí', 22)," +
                "('Caracol', 12)," +
                "('Caracol', 17)," +
                "('Caraguatatuba', 26)," +
                "('Caraí', 11)," +
                "('Caraíbas', 5)," +
                "('Carambeí', 18)," +
                "('Caranaíba', 11)," +
                "('Carandaí', 11)," +
                "('Carangola', 11)," +
                "('Carapebus', 19)," +
                "('Carapicuíba', 26)," +
                "('Caratinga', 11)," +
                "('Carauari', 3)," +
                "('Caraúbas do Piauí', 17)," +
                "('Caraúbas', 15)," +
                "('Caraúbas', 20)," +
                "('Caravelas', 5)," +
                "('Carazinho', 23)," +
                "('Carbonita', 11)," +
                "('Cardeal da Silva', 5)," +
                "('Cardoso Moreira', 19)," +
                "('Cardoso', 26)," +
                "('Careaçu', 11)," +
                "('Careiro da Várzea', 3)," +
                "('Careiro', 3)," +
                "('Cariacica', 8)," +
                "('Caridade do Piauí', 17)," +
                "('Caridade', 6)," +
                "('Carinhanha', 5)," +
                "('Carira', 25)," +
                "('Cariré', 6)," +
                "('Cariri do Tocantins', 27)," +
                "('Caririaçu', 6)," +
                "('Cariús', 6)," +
                "('Carlinda', 13)," +
                "('Carlópolis', 18)," +
                "('Carlos Barbosa', 23)," +
                "('Carlos Chagas', 11)," +
                "('Carlos Gomes', 23)," +
                "('Carmésia', 11)," +
                "('Carmo da Cachoeira', 11)," +
                "('Carmo da Mata', 11)," +
                "('Carmo de Minas', 11)," +
                "('Carmo do Cajuru', 11)," +
                "('Carmo do Paranaíba', 11)," +
                "('Carmo do Rio Claro', 11)," +
                "('Carmo do Rio Verde', 9)," +
                "('Carmo', 19)," +
                "('Carmolândia', 27)," +
                "('Carmópolis de Minas', 11)," +
                "('Carmópolis', 25)," +
                "('Carnaíba', 16)," +
                "('Carnaúba dos Dantas', 20)," +
                "('Carnaubais', 20)," +
                "('Carnaubal', 6)," +
                "('Carnaubeira da Penha', 16)," +
                "('Carneirinho', 11)," +
                "('Carneiros', 2)," +
                "('Caroebe', 22)," +
                "('Carolina', 10)," +
                "('Carpina', 16)," +
                "('Carrancas', 11)," +
                "('Carrapateira', 15)," +
                "('Carrasco Bonito', 27)," +
                "('Caruaru', 16)," +
                "('Carutapera', 10)," +
                "('Carvalhópolis', 11)," +
                "('Carvalhos', 11)," +
                "('Casa Branca', 26)," +
                "('Casa Grande', 11)," +
                "('Casa Nova', 5)," +
                "('Casca', 23)," +
                "('Cascalho Rico', 11)," +
                "('Cascavel', 18)," +
                "('Cascavel', 6)," +
                "('Caseara', 27)," +
                "('Caseiros', 23)," +
                "('Casimiro de Abreu', 19)," +
                "('Casinhas', 16)," +
                "('Casserengue', 15)," +
                "('Cássia dos Coqueiros', 26)," +
                "('Cássia', 11)," +
                "('Cassilândia', 12)," +
                "('Castanhal', 14)," +
                "('Castanheira', 13)," +
                "('Castanheiras', 21)," +
                "('Castelândia', 9)," +
                "('Castelo do Piauí', 17)," +
                "('Castelo', 8)," +
                "('Castilho', 26)," +
                "('Castro Alves', 5)," +
                "('Castro', 18)," +
                "('Cataguases', 11)," +
                "('Catalão', 9)," +
                "('Catanduva', 26)," +
                "('Catanduvas', 18)," +
                "('Catanduvas', 24)," +
                "('Catarina', 6)," +
                "('Catas Altas da Noruega', 11)," +
                "('Catas Altas', 11)," +
                "('Catende', 16)," +
                "('Catiguá', 26)," +
                "('Catingueira', 15)," +
                "('Catolândia', 5)," +
                "('Catolé do Rocha', 15)," +
                "('Catu', 5)," +
                "('Catuípe', 23)," +
                "('Catuji', 11)," +
                "('Catunda', 6)," +
                "('Caturaí', 9)," +
                "('Caturama', 5)," +
                "('Caturité', 15)," +
                "('Catuti', 11)," +
                "('Caucaia', 6)," +
                "('Cavalcante', 9)," +
                "('Caxambu do Sul', 24)," +
                "('Caxambu', 11)," +
                "('Caxias do Sul', 23)," +
                "('Caxias', 10)," +
                "('Caxingó', 17)," +
                "('Ceará-Mirim', 20)," +
                "('Cedral', 10)," +
                "('Cedral', 26)," +
                "('Cedro de São João', 25)," +
                "('Cedro do Abaeté', 11)," +
                "('Cedro', 16)," +
                "('Cedro', 6)," +
                "('Celso Ramos', 24)," +
                "('Centenário do Sul', 18)," +
                "('Centenário', 23)," +
                "('Centenário', 27)," +
                "('Central de Minas', 11)," +
                "('Central do Maranhão', 10)," +
                "('Central', 5)," +
                "('Centralina', 11)," +
                "('Centro do Guilherme', 10)," +
                "('Centro Novo do Maranhão', 10)," +
                "('Cerejeiras', 21)," +
                "('Ceres', 9)," +
                "('Cerqueira César', 26)," +
                "('Cerquilho', 26)," +
                "('Cerrito', 23)," +
                "('Cerro Azul', 18)," +
                "('Cerro Branco', 23)," +
                "('Cerro Corá', 20)," +
                "('Cerro Grande do Sul', 23)," +
                "('Cerro Grande', 23)," +
                "('Cerro Largo', 23)," +
                "('Cerro Negro', 24)," +
                "('Cesário Lange', 26)," +
                "('Céu Azul', 18)," +
                "('Cezarina', 9)," +
                "('Chã de Alegria', 16)," +
                "('Chã Grande', 16)," +
                "('Chã Preta', 2)," +
                "('Chácara', 11)," +
                "('Chalé', 11)," +
                "('Chapada da Natividade', 27)," +
                "('Chapada de Areia', 27)," +
                "('Chapada do Norte', 11)," +
                "('Chapada dos Guimarães', 13)," +
                "('Chapada Gaúcha', 11)," +
                "('Chapada', 23)," +
                "('Chapadão do Céu', 9)," +
                "('Chapadão do Lageado', 24)," +
                "('Chapadão do Sul', 12)," +
                "('Chapadinha', 10)," +
                "('Chapecó', 24)," +
                "('Charqueada', 26)," +
                "('Charqueadas', 23)," +
                "('Charrua', 23)," +
                "('Chaval', 6)," +
                "('Chavantes', 26)," +
                "('Chaves', 14)," +
                "('Chiador', 11)," +
                "('Chiapeta', 23)," +
                "('Chopinzinho', 18)," +
                "('Choró', 6)," +
                "('Chorozinho', 6)," +
                "('Chorrochó', 5)," +
                "('Chuí', 23)," +
                "('Chupinguaia', 21)," +
                "('Chuvisca', 23)," +
                "('Cianorte', 18)," +
                "('Cícero Dantas', 5)," +
                "('Cidade Gaúcha', 18)," +
                "('Cidade Ocidental', 9)," +
                "('Cidelândia', 10)," +
                "('Cidreira', 23)," +
                "('Cipó', 5)," +
                "('Cipotânea', 11)," +
                "('Ciríaco', 23)," +
                "('Claraval', 11)," +
                "('Claro dos Poções', 11)," +
                "('Cláudia', 13)," +
                "('Cláudio', 11)," +
                "('Clementina', 26)," +
                "('Clevelândia', 18)," +
                "('Coaraci', 5)," +
                "('Coari', 3)," +
                "('Cocal de Telha', 17)," +
                "('Cocal do Sul', 24)," +
                "('Cocal dos Alves', 17)," +
                "('Cocal', 17)," +
                "('Cocalinho', 13)," +
                "('Cocalzinho de Goiás', 9)," +
                "('Cocos', 5)," +
                "('Codajás', 3)," +
                "('Codó', 10)," +
                "('Coelho Neto', 10)," +
                "('Coimbra', 11)," +
                "('Coité do Nóia', 2)," +
                "('Coivaras', 17)," +
                "('Colares', 14)," +
                "('Colatina', 8)," +
                "('Colíder', 13)," +
                "('Colina', 26)," +
                "('Colinas do Sul', 9)," +
                "('Colinas do Tocantins', 27)," +
                "('Colinas', 10)," +
                "('Colinas', 23)," +
                "('Colméia', 27)," +
                "('Colniza', 13)," +
                "('Colômbia', 26)," +
                "('Colombo', 18)," +
                "('Colônia do Gurguéia', 17)," +
                "('Colônia do Piauí', 17)," +
                "('Colônia Leopoldina', 2)," +
                "('Colorado do Oeste', 21)," +
                "('Colorado', 18)," +
                "('Colorado', 23)," +
                "('Coluna', 11)," +
                "('Combinado', 27)," +
                "('Comendador Gomes', 11)," +
                "('Comendador Levy Gasparian', 19)," +
                "('Comercinho', 11)," +
                "('Comodoro', 13)," +
                "('Conceição da Aparecida', 11)," +
                "('Conceição da Barra de Minas', 11)," +
                "('Conceição da Barra', 8)," +
                "('Conceição da Feira', 5)," +
                "('Conceição das Alagoas', 11)," +
                "('Conceição das Pedras', 11)," +
                "('Conceição de Ipanema', 11)," +
                "('Conceição de Macabu', 19)," +
                "('Conceição do Almeida', 5)," +
                "('Conceição do Araguaia', 14)," +
                "('Conceição do Canindé', 17)," +
                "('Conceição do Castelo', 8)," +
                "('Conceição do Coité', 5)," +
                "('Conceição do Jacuípe', 5)," +
                "('Conceição do Lago-Açu', 10)," +
                "('Conceição do Mato Dentro', 11)," +
                "('Conceição do Pará', 11)," +
                "('Conceição do Rio Verde', 11)," +
                "('Conceição do Tocantins', 27)," +
                "('Conceição dos Ouros', 11)," +
                "('Conceição', 15)," +
                "('Conchal', 26)," +
                "('Conchas', 26)," +
                "('Concórdia do Pará', 14)," +
                "('Concórdia', 24)," +
                "('Condado', 15)," +
                "('Condado', 16)," +
                "('Conde', 15)," +
                "('Conde', 5)," +
                "('Condeúba', 5)," +
                "('Condor', 23)," +
                "('Cônego Marinho', 11)," +
                "('Confins', 11)," +
                "('Confresa', 13)," +
                "('Congo', 15)," +
                "('Congonhal', 11)," +
                "('Congonhas do Norte', 11)," +
                "('Congonhas', 11)," +
                "('Congonhinhas', 18)," +
                "('Conquista d`Oeste', 13)," +
                "('Conquista', 11)," +
                "('Conselheiro Lafaiete', 11)," +
                "('Conselheiro Mairinck', 18)," +
                "('Conselheiro Pena', 11)," +
                "('Consolação', 11)," +
                "('Constantina', 23)," +
                "('Contagem', 11)," +
                "('Contenda', 18)," +
                "('Contendas do Sincorá', 5)," +
                "('Coqueiral', 11)," +
                "('Coqueiro Baixo', 23)," +
                "('Coqueiro Seco', 2)," +
                "('Coqueiros do Sul', 23)," +
                "('Coração de Jesus', 11)," +
                "('Coração de Maria', 5)," +
                "('Corbélia', 18)," +
                "('Cordeiro', 19)," +
                "('Cordeirópolis', 26)," +
                "('Cordeiros', 5)," +
                "('Cordilheira Alta', 24)," +
                "('Cordisburgo', 11)," +
                "('Cordislândia', 11)," +
                "('Coreaú', 6)," +
                "('Coremas', 15)," +
                "('Corguinho', 12)," +
                "('Coribe', 5)," +
                "('Corinto', 11)," +
                "('Cornélio Procópio', 18)," +
                "('Coroaci', 11)," +
                "('Coroados', 26)," +
                "('Coroatá', 10)," +
                "('Coromandel', 11)," +
                "('Coronel Barros', 23)," +
                "('Coronel Bicaco', 23)," +
                "('Coronel Domingos Soares', 18)," +
                "('Coronel Ezequiel', 20)," +
                "('Coronel Fabriciano', 11)," +
                "('Coronel Freitas', 24)," +
                "('Coronel João Pessoa', 20)," +
                "('Coronel João Sá', 5)," +
                "('Coronel José Dias', 17)," +
                "('Coronel Macedo', 26)," +
                "('Coronel Martins', 24)," +
                "('Coronel Murta', 11)," +
                "('Coronel Pacheco', 11)," +
                "('Coronel Pilar', 23)," +
                "('Coronel Sapucaia', 12)," +
                "('Coronel Vivida', 18)," +
                "('Coronel Xavier Chaves', 11)," +
                "('Córrego Danta', 11)," +
                "('Córrego do Bom Jesus', 11)," +
                "('Córrego do Ouro', 9)," +
                "('Córrego Fundo', 11)," +
                "('Córrego Novo', 11)," +
                "('Correia Pinto', 24)," +
                "('Corrente', 17)," +
                "('Correntes', 16)," +
                "('Correntina', 5)," +
                "('Cortês', 16)," +
                "('Corumbá de Goiás', 9)," +
                "('Corumbá', 12)," +
                "('Corumbaíba', 9)," +
                "('Corumbataí do Sul', 18)," +
                "('Corumbataí', 26)," +
                "('Corumbiara', 21)," +
                "('Corupá', 24)," +
                "('Coruripe', 2)," +
                "('Cosmópolis', 26)," +
                "('Cosmorama', 26)," +
                "('Costa Marques', 21)," +
                "('Costa Rica', 12)," +
                "('Cotegipe', 5)," +
                "('Cotia', 26)," +
                "('Cotiporã', 23)," +
                "('Cotriguaçu', 13)," +
                "('Couto de Magalhães de Minas', 11)," +
                "('Couto de Magalhães', 27)," +
                "('Coxilha', 23)," +
                "('Coxim', 12)," +
                "('Coxixola', 15)," +
                "('Craíbas', 2)," +
                "('Crateús', 6)," +
                "('Crato', 6)," +
                "('Cravinhos', 26)," +
                "('Cravolândia', 5)," +
                "('Criciúma', 24)," +
                "('Crisólita', 11)," +
                "('Crisópolis', 5)," +
                "('Crissiumal', 23)," +
                "('Cristais Paulista', 26)," +
                "('Cristais', 11)," +
                "('Cristal do Sul', 23)," +
                "('Cristal', 23)," +
                "('Cristalândia do Piauí', 17)," +
                "('Cristalândia', 27)," +
                "('Cristália', 11)," +
                "('Cristalina', 9)," +
                "('Cristiano Otoni', 11)," +
                "('Cristianópolis', 9)," +
                "('Cristina', 11)," +
                "('Cristinápolis', 25)," +
                "('Cristino Castro', 17)," +
                "('Cristópolis', 5)," +
                "('Crixás do Tocantins', 27)," +
                "('Crixás', 9)," +
                "('Croatá', 6)," +
                "('Cromínia', 9)," +
                "('Crucilândia', 11)," +
                "('Cruz Alta', 23)," +
                "('Cruz das Almas', 5)," +
                "('Cruz do Espírito Santo', 15)," +
                "('Cruz Machado', 18)," +
                "('Cruz', 6)," +
                "('Cruzália', 26)," +
                "('Cruzaltense', 23)," +
                "('Cruzeiro da Fortaleza', 11)," +
                "('Cruzeiro do Iguaçu', 18)," +
                "('Cruzeiro do Oeste', 18)," +
                "('Cruzeiro do Sul', 1)," +
                "('Cruzeiro do Sul', 18)," +
                "('Cruzeiro do Sul', 23)," +
                "('Cruzeiro', 26)," +
                "('Cruzeta', 20)," +
                "('Cruzília', 11)," +
                "('Cruzmaltina', 18)," +
                "('Cubatão', 26)," +
                "('Cubati', 15)," +
                "('Cuiabá', 13)," +
                "('Cuité de Mamanguape', 15)," +
                "('Cuité', 15)," +
                "('Cuitegi', 15)," +
                "('Cujubim', 21)," +
                "('Cumari', 9)," +
                "('Cumaru do Norte', 14)," +
                "('Cumaru', 16)," +
                "('Cumbe', 25)," +
                "('Cunha Porã', 24)," +
                "('Cunha', 26)," +
                "('Cunhataí', 24)," +
                "('Cuparaque', 11)," +
                "('Cupira', 16)," +
                "('Curaçá', 5)," +
                "('Curimatá', 17)," +
                "('Curionópolis', 14)," +
                "('Curitiba', 18)," +
                "('Curitibanos', 24)," +
                "('Curiúva', 18)," +
                "('Currais Novos', 20)," +
                "('Currais', 17)," +
                "('Curral de Cima', 15)," +
                "('Curral de Dentro', 11)," +
                "('Curral Novo do Piauí', 17)," +
                "('Curral Velho', 15)," +
                "('Curralinho', 14)," +
                "('Curralinhos', 17)," +
                "('Curuá', 14)," +
                "('Curuçá', 14)," +
                "('Cururupu', 10)," +
                "('Curvelândia', 13)," +
                "('Curvelândia', 13)," +
                "('Curvelo', 11)," +
                "('Custódia', 16)," +
                "('Cutias', 4);";
        String  insert_table_cidades_2 ="INSERT INTO "+EntryCidade.TABLE_NAME +" ( "+EntryCidade.CIDADE+","+EntryCidade.ID_ESTADO+") VALUES "+
                "('Damianópolis', 9)," +
                "('Damião', 15)," +
                "('Damolândia', 9)," +
                "('Darcinópolis', 27)," +
                "('Dário Meira', 5)," +
                "('Datas', 11)," +
                "('David Canabarro', 23)," +
                "('Davinópolis', 10)," +
                "('Davinópolis', 9)," +
                "('Delfim Moreira', 11)," +
                "('Delfinópolis', 11)," +
                "('Delmiro Gouveia', 2)," +
                "('Delta', 11)," +
                "('Demerval Lobão', 17)," +
                "('Denise', 13)," +
                "('Deodápolis', 12)," +
                "('Deputado Irapuan Pinheiro', 6)," +
                "('Derrubadas', 23)," +
                "('Descalvado', 26)," +
                "('Descanso', 24)," +
                "('Descoberto', 11)," +
                "('Desterro de Entre Rios', 11)," +
                "('Desterro do Melo', 11)," +
                "('Desterro', 15)," +
                "('Dezesseis de Novembro', 23)," +
                "('Diadema', 26)," +
                "('Diamante d`Oeste', 18)," +
                "('Diamante do Norte', 18)," +
                "('Diamante do Sul', 18)," +
                "('Diamante', 15)," +
                "('Diamantina', 11)," +
                "('Diamantino', 13)," +
                "('Dianópolis', 27)," +
                "('Dias d`Ávila', 5)," +
                "('Dilermando de Aguiar', 23)," +
                "('Diogo de Vasconcelos', 11)," +
                "('Dionísio Cerqueira', 24)," +
                "('Dionísio', 11)," +
                "('Diorama', 9)," +
                "('Dirce Reis', 26)," +
                "('Dirceu Arcoverde', 17)," +
                "('Divina Pastora', 25)," +
                "('Divinésia', 11)," +
                "('Divino das Laranjeiras', 11)," +
                "('Divino de São Lourenço', 8)," +
                "('Divino', 11)," +
                "('Divinolândia de Minas', 11)," +
                "('Divinolândia', 26)," +
                "('Divinópolis de Goiás', 9)," +
                "('Divinópolis do Tocantins', 27)," +
                "('Divinópolis', 11)," +
                "('Divisa Alegre', 11)," +
                "('Divisa Nova', 11)," +
                "('Divisópolis', 11)," +
                "('Dobrada', 26)," +
                "('Dois Córregos', 26)," +
                "('Dois Irmãos das Missões', 23)," +
                "('Dois Irmãos do Buriti', 12)," +
                "('Dois Irmãos do Tocantins', 27)," +
                "('Dois Irmãos', 23)," +
                "('Dois Lajeados', 23)," +
                "('Dois Riachos', 2)," +
                "('Dois Vizinhos', 18)," +
                "('Dolcinópolis', 26)," +
                "('Dom Aquino', 13)," +
                "('Dom Basílio', 5)," +
                "('Dom Bosco', 11)," +
                "('Dom Cavati', 11)," +
                "('Dom Eliseu', 14)," +
                "('Dom Expedito Lopes', 17)," +
                "('Dom Feliciano', 23)," +
                "('Dom Inocêncio', 17)," +
                "('Dom Joaquim', 11)," +
                "('Dom Macedo Costa', 5)," +
                "('Dom Pedrito', 23)," +
                "('Dom Pedro de Alcântara', 23)," +
                "('Dom Pedro', 10)," +
                "('Dom Silvério', 11)," +
                "('Dom Viçoso', 11)," +
                "('Domingos Martins', 8)," +
                "('Domingos Mourão', 17)," +
                "('Dona Emma', 24)," +
                "('Dona Eusébia', 11)," +
                "('Dona Francisca', 23)," +
                "('Dona Inês', 15)," +
                "('Dores de Campos', 11)," +
                "('Dores de Guanhães', 11)," +
                "('Dores do Indaiá', 11)," +
                "('Dores do Rio Preto', 8)," +
                "('Dores do Turvo', 11)," +
                "('Doresópolis', 11)," +
                "('Dormentes', 16)," +
                "('Douradina', 12)," +
                "('Douradina', 18)," +
                "('Dourado', 26)," +
                "('Douradoquara', 11)," +
                "('Dourados', 12)," +
                "('Doutor Camargo', 18)," +
                "('Doutor Maurício Cardoso', 23)," +
                "('Doutor Pedrinho', 24)," +
                "('Doutor Ricardo', 23)," +
                "('Doutor Severiano', 20)," +
                "('Doutor Ulysses', 18)," +
                "('Doverlândia', 9)," +
                "('Dracena', 26)," +
                "('Duartina', 26)," +
                "('Duas Barras', 19)," +
                "('Duas Estradas', 15)," +
                "('Dueré', 27)," +
                "('Dumont', 26)," +
                "('Duque Bacelar', 10)," +
                "('Duque de Caxias', 19)," +
                "('Durandé', 11)," +
                "('Echaporã', 26)," +
                "('Ecoporanga', 8)," +
                "('Edealina', 9)," +
                "('Edéia', 9)," +
                "('Eirunepé', 3)," +
                "('Eldorado do Sul', 23)," +
                "('Eldorado dos Carajás', 14)," +
                "('Eldorado', 12)," +
                "('Eldorado', 26)," +
                "('Elesbão Veloso', 17)," +
                "('Elias Fausto', 26)," +
                "('Eliseu Martins', 17)," +
                "('Elisiário', 26)," +
                "('Elísio Medrado', 5)," +
                "('Elói Mendes', 11)," +
                "('Emas', 15)," +
                "('Embaúba', 26)," +
                "('Embu', 26)," +
                "('Embu-Guaçu', 26)," +
                "('Emilianópolis', 26)," +
                "('Encantado', 23)," +
                "('Encanto', 20)," +
                "('Encruzilhada do Sul', 23)," +
                "('Encruzilhada', 5)," +
                "('Enéas Marques', 18)," +
                "('Engenheiro Beltrão', 18)," +
                "('Engenheiro Caldas', 11)," +
                "('Engenheiro Coelho', 26)," +
                "('Engenheiro Navarro', 11)," +
                "('Engenheiro Paulo de Frontin', 19)," +
                "('Engenho Velho', 23)," +
                "('Entre Folhas', 11)," +
                "('Entre Rios de Minas', 11)," +
                "('Entre Rios do Oeste', 18)," +
                "('Entre Rios do Sul', 23)," +
                "('Entre Rios', 24)," +
                "('Entre Rios', 5)," +
                "('Entre-Ijuís', 23)," +
                "('Envira', 3)," +
                "('Epitaciolândia', 1)," +
                "('Equador', 20)," +
                "('Erebango', 23)," +
                "('Erechim', 23)," +
                "('Ererê', 6)," +
                "('Érico Cardoso', 5)," +
                "('Ermo', 24)," +
                "('Ernestina', 23)," +
                "('Erval Grande', 23)," +
                "('Erval Seco', 23)," +
                "('Erval Velho', 24)," +
                "('Ervália', 11)," +
                "('Escada', 16)," +
                "('Esmeralda', 23)," +
                "('Esmeraldas', 11)," +
                "('Espera Feliz', 11)," +
                "('Esperança do Sul', 23)," +
                "('Esperança Nova', 18)," +
                "('Esperança', 15)," +
                "('Esperantina', 17)," +
                "('Esperantina', 27)," +
                "('Esperantinópolis', 10)," +
                "('Espigão Alto do Iguaçu', 18)," +
                "('Espigão d`Oeste', 21)," +
                "('Espinosa', 11)," +
                "('Espírito Santo do Dourado', 11)," +
                "('Espírito Santo do Pinhal', 26)," +
                "('Espírito Santo do Turvo', 26)," +
                "('Espírito Santo', 20)," +
                "('Esplanada', 5)," +
                "('Espumoso', 23)," +
                "('Estação', 23)," +
                "('Estância Velha', 23)," +
                "('Estância', 25)," +
                "('Esteio', 23)," +
                "('Estiva Gerbi', 26)," +
                "('Estiva', 11)," +
                "('Estreito', 10)," +
                "('Estrela d`Oeste', 26)," +
                "('Estrela Dalva', 11)," +
                "('Estrela de Alagoas', 2)," +
                "('Estrela do Indaiá', 11)," +
                "('Estrela do Norte', 26)," +
                "('Estrela do Norte', 9)," +
                "('Estrela do Sul', 11)," +
                "('Estrela Velha', 23)," +
                "('Estrela', 23)," +
                "('Euclides da Cunha Paulista', 26)," +
                "('Euclides da Cunha', 5)," +
                "('Eugênio de Castro', 23)," +
                "('Eugenópolis', 11)," +
                "('Eunápolis', 5)," +
                "('Eusébio', 6)," +
                "('Ewbank da Câmara', 11)," +
                "('Extrema', 11)," +
                "('Extremoz', 20)," +
                "('Exu', 16)," +
                "('Fagundes Varela', 23)," +
                "('Fagundes', 15)," +
                "('Faina', 9)," +
                "('Fama', 11)," +
                "('Faria Lemos', 11)," +
                "('Farias Brito', 6)," +
                "('Faro', 14)," +
                "('Farol', 18)," +
                "('Farroupilha', 23)," +
                "('Fartura do Piauí', 17)," +
                "('Fartura', 26)," +
                "('Fátima do Sul', 12)," +
                "('Fátima', 27)," +
                "('Fátima', 5)," +
                "('Faxinal do Soturno', 23)," +
                "('Faxinal dos Guedes', 24)," +
                "('Faxinal', 18)," +
                "('Faxinalzinho', 23)," +
                "('Fazenda Nova', 9)," +
                "('Fazenda Rio Grande', 18)," +
                "('Fazenda Vilanova', 23)," +
                "('Feijó', 1)," +
                "('Feira da Mata', 5)," +
                "('Feira de Santana', 5)," +
                "('Feira Grande', 2)," +
                "('Feira Nova do Maranhão', 10)," +
                "('Feira Nova', 16)," +
                "('Feira Nova', 25)," +
                "('Felício dos Santos', 11)," +
                "('Felipe Guerra', 20)," +
                "('Felisburgo', 11)," +
                "('Felixlândia', 11)," +
                "('Feliz Deserto', 2)," +
                "('Feliz Natal', 13)," +
                "('Feliz', 23)," +
                "('Fênix', 18)," +
                "('Fernandes Pinheiro', 18)," +
                "('Fernandes Tourinho', 11)," +
                "('Fernando de Noronha', 16)," +
                "('Fernando Falcão', 10)," +
                "('Fernando Pedroza', 20)," +
                "('Fernando Prestes', 26)," +
                "('Fernandópolis', 26)," +
                "('Fernão', 26)," +
                "('Ferraz de Vasconcelos', 26)," +
                "('Ferreira Gomes', 4)," +
                "('Ferreiros', 16)," +
                "('Ferros', 11)," +
                "('Fervedouro', 11)," +
                "('Figueira', 18)," +
                "('Figueirão', 12)," +
                "('Figueirópolis d`Oeste', 13)," +
                "('Figueirópolis', 27)," +
                "('Filadélfia', 27)," +
                "('Filadélfia', 5)," +
                "('Firmino Alves', 5)," +
                "('Firminópolis', 9)," +
                "('Flexeiras', 2)," +
                "('Flor da Serra do Sul', 18)," +
                "('Flor do Sertão', 24)," +
                "('Flora Rica', 26)," +
                "('Floraí', 18)," +
                "('Florânia', 20)," +
                "('Floreal', 26)," +
                "('Flores da Cunha', 23)," +
                "('Flores de Goiás', 9)," +
                "('Flores do Piauí', 17)," +
                "('Flores', 16)," +
                "('Floresta Azul', 5)," +
                "('Floresta do Araguaia', 14)," +
                "('Floresta do Piauí', 17)," +
                "('Floresta', 16)," +
                "('Floresta', 18)," +
                "('Florestal', 11)," +
                "('Florestópolis', 18)," +
                "('Floriano Peixoto', 23)," +
                "('Floriano', 17)," +
                "('Florianópolis', 24)," +
                "('Flórida Paulista', 26)," +
                "('Flórida', 18)," +
                "('Florínia', 26)," +
                "('Fonte Boa', 3)," +
                "('Fontoura Xavier', 23)," +
                "('Formiga', 11)," +
                "('Formigueiro', 23)," +
                "('Formosa da Serra Negra', 10)," +
                "('Formosa do Oeste', 18)," +
                "('Formosa do Rio Preto', 5)," +
                "('Formosa do Sul', 24)," +
                "('Formosa', 9)," +
                "('Formoso do Araguaia', 27)," +
                "('Formoso', 11)," +
                "('Formoso', 9)," +
                "('Forquetinha', 23)," +
                "('Forquilha', 6)," +
                "('Forquilhinha', 24)," +
                "('Fortaleza de Minas', 11)," +
                "('Fortaleza do Tabocão', 27)," +
                "('Fortaleza dos Nogueiras', 10)," +
                "('Fortaleza dos Valos', 23)," +
                "('Fortaleza', 6)," +
                "('Fortim', 6)," +
                "('Fortuna de Minas', 11)," +
                "('Fortuna', 10)," +
                "('Foz do Iguaçu', 18)," +
                "('Foz do Jordão', 18)," +
                "('Fraiburgo', 24)," +
                "('Franca', 26)," +
                "('Francinópolis', 17)," +
                "('Francisco Alves', 18)," +
                "('Francisco Ayres', 17)," +
                "('Francisco Badaró', 11)," +
                "('Francisco Beltrão', 18)," +
                "('Francisco Dantas', 20)," +
                "('Francisco Dumont', 11)," +
                "('Francisco Macedo', 17)," +
                "('Francisco Morato', 26)," +
                "('Francisco Sá', 11)," +
                "('Francisco Santos', 17)," +
                "('Franciscópolis', 11)," +
                "('Franco da Rocha', 26)," +
                "('Frecheirinha', 6)," +
                "('Frederico Westphalen', 23)," +
                "('Frei Gaspar', 11)," +
                "('Frei Inocêncio', 11)," +
                "('Frei Lagonegro', 11)," +
                "('Frei Martinho', 15)," +
                "('Frei Miguelinho', 16)," +
                "('Frei Paulo', 25)," +
                "('Frei Rogério', 24)," +
                "('Fronteira dos Vales', 11)," +
                "('Fronteira', 11)," +
                "('Fronteiras', 17)," +
                "('Fruta de Leite', 11)," +
                "('Frutal', 11)," +
                "('Frutuoso Gomes', 20)," +
                "('Fundão', 8)," +
                "('Funilândia', 11)," +
                "('Gabriel Monteiro', 26)," +
                "('Gado Bravo', 15)," +
                "('Gália', 26)," +
                "('Galiléia', 11)," +
                "('Galinhos', 20)," +
                "('Galvão', 24)," +
                "('Gameleira de Goiás', 9)," +
                "('Gameleira', 16)," +
                "('Gameleiras', 11)," +
                "('Gandu', 5)," +
                "('Garanhuns', 16)," +
                "('Gararu', 25)," +
                "('Garça', 26)," +
                "('Garibaldi', 23)," +
                "('Garopaba', 24)," +
                "('Garrafão do Norte', 14)," +
                "('Garruchos', 23)," +
                "('Garuva', 24)," +
                "('Gaspar', 24)," +
                "('Gastão Vidigal', 26)," +
                "('Gaúcha do Norte', 13)," +
                "('Gaurama', 23)," +
                "('Gavião Peixoto', 26)," +
                "('Gavião', 5)," +
                "('Geminiano', 17)," +
                "('General Câmara', 23)," +
                "('General Carneiro', 13)," +
                "('General Carneiro', 18)," +
                "('General Maynard', 25)," +
                "('General Salgado', 26)," +
                "('General Sampaio', 6)," +
                "('Gentil', 23)," +
                "('Gentio do Ouro', 5)," +
                "('Getulina', 26)," +
                "('Getúlio Vargas', 23)," +
                "('Gilbués', 17)," +
                "('Girau do Ponciano', 2)," +
                "('Giruá', 23)," +
                "('Glaucilândia', 11)," +
                "('Glicério', 26)," +
                "('Glória d`Oeste', 13)," +
                "('Glória de Dourados', 12)," +
                "('Glória do Goitá', 16)," +
                "('Glória', 5)," +
                "('Glorinha', 23)," +
                "('Godofredo Viana', 10)," +
                "('Godoy Moreira', 18)," +
                "('Goiabeira', 11)," +
                "('Goianá', 11)," +
                "('Goiana', 16)," +
                "('Goianápolis', 9)," +
                "('Goiandira', 9)," +
                "('Goianésia do Pará', 14)," +
                "('Goianésia', 9)," +
                "('Goiânia', 9)," +
                "('Goianinha', 20)," +
                "('Goianira', 9)," +
                "('Goianorte', 27)," +
                "('Goiás', 9)," +
                "('Goiatins', 27)," +
                "('Goiatuba', 9)," +
                "('Goioerê', 18)," +
                "('Goioxim', 18)," +
                "('Gonçalves Dias', 10)," +
                "('Gonçalves', 11)," +
                "('Gongogi', 5)," +
                "('Gonzaga', 11)," +
                "('Gouveia', 11)," +
                "('Gouvelândia', 9)," +
                "('Governador Archer', 10)," +
                "('Governador Celso Ramos', 24)," +
                "('Governador Dix-Sept Rosado', 20)," +
                "('Governador Edison Lobão', 10)," +
                "('Governador Eugênio Barros', 10)," +
                "('Governador Jorge Teixeira', 21)," +
                "('Governador Lindenberg', 8)," +
                "('Governador Luiz Rocha', 10)," +
                "('Governador Mangabeira', 5)," +
                "('Governador Newton Bello', 10)," +
                "('Governador Nunes Freire', 10)," +
                "('Governador Valadares', 11)," +
                "('Graça Aranha', 10)," +
                "('Graça', 6)," +
                "('Gracho Cardoso', 25)," +
                "('Grajaú', 10)," +
                "('Gramado dos Loureiros', 23)," +
                "('Gramado Xavier', 23)," +
                "('Gramado', 23)," +
                "('Grandes Rios', 18)," +
                "('Granito', 16)," +
                "('Granja', 6)," +
                "('Granjeiro', 6)," +
                "('Grão Mogol', 11)," +
                "('Grão Pará', 24)," +
                "('Gravatá', 16)," +
                "('Gravataí', 23)," +
                "('Gravatal', 24)," +
                "('Groaíras', 6)," +
                "('Grossos', 20)," +
                "('Grupiara', 11)," +
                "('Guabiju', 23)," +
                "('Guabiruba', 24)," +
                "('Guaçuí', 8)," +
                "('Guadalupe', 17)," +
                "('Guaíba', 23)," +
                "('Guaiçara', 26)," +
                "('Guaimbê', 26)," +
                "('Guaíra', 18)," +
                "('Guaíra', 26)," +
                "('Guairaçá', 18)," +
                "('Guaiúba', 6)," +
                "('Guajará', 3)," +
                "('Guajará-Mirim', 21)," +
                "('Guajeru', 5)," +
                "('Guamaré', 20)," +
                "('Guamiranga', 18)," +
                "('Guanambi', 5)," +
                "('Guanhães', 11)," +
                "('Guapé', 11)," +
                "('Guapiaçu', 26)," +
                "('Guapiara', 26)," +
                "('Guapimirim', 19)," +
                "('Guapirama', 18)," +
                "('Guapó', 9)," +
                "('Guaporé', 23)," +
                "('Guaporema', 18)," +
                "('Guará', 26)," +
                "('Guarabira', 15)," +
                "('Guaraçaí', 26)," +
                "('Guaraci', 18)," +
                "('Guaraci', 26)," +
                "('Guaraciaba do Norte', 6)," +
                "('Guaraciaba', 11)," +
                "('Guaraciaba', 24)," +
                "('Guaraciama', 11)," +
                "('Guaraí', 27)," +
                "('Guaraíta', 9)," +
                "('Guaramiranga', 6)," +
                "('Guaramirim', 24)," +
                "('Guaranésia', 11)," +
                "('Guarani d`Oeste', 26)," +
                "('Guarani das Missões', 23)," +
                "('Guarani de Goiás', 9)," +
                "('Guarani', 11)," +
                "('Guaraniaçu', 18)," +
                "('Guarantã do Norte', 13)," +
                "('Guarantã', 26)," +
                "('Guarapari', 8)," +
                "('Guarapuava', 18)," +
                "('Guaraqueçaba', 18)," +
                "('Guarará', 11)," +
                "('Guararapes', 26)," +
                "('Guararema', 26)," +
                "('Guaratinga', 5)," +
                "('Guaratinguetá', 26)," +
                "('Guaratuba', 18)," +
                "('Guarda-Mor', 11)," +
                "('Guareí', 26)," +
                "('Guariba', 26)," +
                "('Guaribas', 17)," +
                "('Guarinos', 9)," +
                "('Guarujá do Sul', 24)," +
                "('Guarujá', 26)," +
                "('Guarulhos', 26)," +
                "('Guatambú', 24)," +
                "('Guatapará', 26)," +
                "('Guaxupé', 11)," +
                "('Guia Lopes da Laguna', 12)," +
                "('Guidoval', 11)," +
                "('Guimarães', 10)," +
                "('Guimarânia', 11)," +
                "('Guiratinga', 13)," +
                "('Guiricema', 11)," +
                "('Gurinhatã', 11)," +
                "('Gurinhém', 15)," +
                "('Gurjão', 15)," +
                "('Gurupá', 14)," +
                "('Gurupi', 27)," +
                "('Guzolândia', 26)," +
                "('Harmonia', 23)," +
                "('Heitoraí', 9)," +
                "('Heliodora', 11)," +
                "('Heliópolis', 5)," +
                "('Herculândia', 26)," +
                "('Herval d`Oeste', 24)," +
                "('Herval', 23)," +
                "('Herveiras', 23)," +
                "('Hidrolândia', 6)," +
                "('Hidrolândia', 9)," +
                "('Hidrolina', 9)," +
                "('Holambra', 26)," +
                "('Honório Serpa', 18)," +
                "('Horizonte', 6)," +
                "('Horizontina', 23)," +
                "('Hortolândia', 26)," +
                "('Hugo Napoleão', 17)," +
                "('Hulha Negra', 23)," +
                "('Humaitá', 23)," +
                "('Humaitá', 3)," +
                "('Humberto de Campos', 10)," +
                "('Iacanga', 26)," +
                "('Iaciara', 9)," +
                "('Iacri', 26)," +
                "('Iaçu', 5)," +
                "('Iapu', 11)," +
                "('Iaras', 26)," +
                "('Iati', 16)," +
                "('Ibaiti', 18)," +
                "('Ibarama', 23)," +
                "('Ibaretama', 6)," +
                "('Ibaté', 26)," +
                "('Ibateguara', 2)," +
                "('Ibatiba', 8)," +
                "('Ibema', 18)," +
                "('Ibertioga', 11)," +
                "('Ibiá', 11)," +
                "('Ibiaçá', 23)," +
                "('Ibiaí', 11)," +
                "('Ibiam', 24)," +
                "('Ibiapina', 6)," +
                "('Ibiara', 15)," +
                "('Ibiassucê', 5)," +
                "('Ibicaraí', 5)," +
                "('Ibicaré', 24)," +
                "('Ibicoara', 5)," +
                "('Ibicuí', 5)," +
                "('Ibicuitinga', 6)," +
                "('Ibimirim', 16)," +
                "('Ibipeba', 5)," +
                "('Ibipitanga', 5)," +
                "('Ibiporã', 18)," +
                "('Ibiquera', 5)," +
                "('Ibirá', 26)," +
                "('Ibiracatu', 11)," +
                "('Ibiraci', 11)," +
                "('Ibiraçu', 8)," +
                "('Ibiraiaras', 23)," +
                "('Ibirajuba', 16)," +
                "('Ibirama', 24)," +
                "('Ibirapitanga', 5)," +
                "('Ibirapuã', 5)," +
                "('Ibirapuitã', 23)," +
                "('Ibirarema', 26)," +
                "('Ibirataia', 5)," +
                "('Ibirité', 11)," +
                "('Ibirubá', 23)," +
                "('Ibitiara', 5)," +
                "('Ibitinga', 26)," +
                "('Ibitirama', 8)," +
                "('Ibititá', 5)," +
                "('Ibitiúra de Minas', 11)," +
                "('Ibituruna', 11)," +
                "('Ibiúna', 26)," +
                "('Ibotirama', 5)," +
                "('Icapuí', 6)," +
                "('Içara', 24)," +
                "('Icaraí de Minas', 11)," +
                "('Icaraíma', 18)," +
                "('Icatu', 10)," +
                "('Icém', 26)," +
                "('Ichu', 5)," +
                "('Icó', 6)," +
                "('Iconha', 8)," +
                "('Ielmo Marinho', 20)," +
                "('Iepê', 26)," +
                "('Igaci', 2)," +
                "('Igaporã', 5)," +
                "('Igaraçu do Tietê', 26)," +
                "('Igaracy', 15)," +
                "('Igarapava', 26)," +
                "('Igarapé do Meio', 10)," +
                "('Igarapé Grande', 10)," +
                "('Igarapé', 11)," +
                "('Igarapé-Açu', 14)," +
                "('Igarapé-Miri', 14)," +
                "('Igarassu', 16)," +
                "('Igaratá', 26)," +
                "('Igaratinga', 11)," +
                "('Igrapiúna', 5)," +
                "('Igreja Nova', 2)," +
                "('Igrejinha', 23)," +
                "('Iguaba Grande', 19)," +
                "('Iguaí', 5)," +
                "('Iguape', 26)," +
                "('Iguaraci', 16)," +
                "('Iguaraçu', 18)," +
                "('Iguatama', 11)," +
                "('Iguatemi', 12)," +
                "('Iguatu', 18)," +
                "('Iguatu', 6)," +
                "('Ijaci', 11)," +
                "('Ijuí', 23)," +
                "('Ilha Comprida', 26)," +
                "('Ilha das Flores', 25)," +
                "('Ilha de Itamaracá', 16)," +
                "('Ilha Grande', 17)," +
                "('Ilha Solteira', 26)," +
                "('Ilhabela', 26)," +
                "('Ilhéus', 5)," +
                "('Ilhota', 24)," +
                "('Ilicínea', 11)," +
                "('Ilópolis', 23)," +
                "('Imaculada', 15)," +
                "('Imaruí', 24)," +
                "('Imbaú', 18)," +
                "('Imbé de Minas', 11)," +
                "('Imbé', 23)," +
                "('Imbituba', 24)," +
                "('Imbituva', 18)," +
                "('Imbuia', 24)," +
                "('Imigrante', 23)," +
                "('Imperatriz', 10)," +
                "('Inácio Martins', 18)," +
                "('Inaciolândia', 9)," +
                "('Inajá', 16)," +
                "('Inajá', 18)," +
                "('Inconfidentes', 11)," +
                "('Indaiabira', 11)," +
                "('Indaial', 24)," +
                "('Indaiatuba', 26)," +
                "('Independência', 23)," +
                "('Independência', 6)," +
                "('Indiana', 26)," +
                "('Indianópolis', 11)," +
                "('Indianópolis', 18)," +
                "('Indiaporã', 26)," +
                "('Indiara', 9)," +
                "('Indiaroba', 25)," +
                "('Indiavaí', 13)," +
                "('Ingá', 15)," +
                "('Ingaí', 11)," +
                "('Ingazeira', 16)," +
                "('Inhacorá', 23)," +
                "('Inhambupe', 5)," +
                "('Inhangapi', 14)," +
                "('Inhapi', 2)," +
                "('Inhapim', 11)," +
                "('Inhaúma', 11)," +
                "('Inhuma', 17)," +
                "('Inhumas', 9)," +
                "('Inimutaba', 11)," +
                "('Inocência', 12)," +
                "('Inúbia Paulista', 26)," +
                "('Iomerê', 24)," +
                "('Ipaba', 11)," +
                "('Ipameri', 9)," +
                "('Ipanema', 11)," +
                "('Ipanguaçu', 20)," +
                "('Ipaporanga', 6)," +
                "('Ipatinga', 11)," +
                "('Ipaumirim', 6)," +
                "('Ipaussu', 26)," +
                "('Ipê', 23)," +
                "('Ipecaetá', 5)," +
                "('Iperó', 26)," +
                "('Ipeúna', 26)," +
                "('Ipiaçu', 11)," +
                "('Ipiaú', 5)," +
                "('Ipiguá', 26)," +
                "('Ipira', 24)," +
                "('Ipirá', 5)," +
                "('Ipiranga de Goiás', 9)," +
                "('Ipiranga do Norte', 13)," +
                "('Ipiranga do Piauí', 17)," +
                "('Ipiranga do Sul', 23)," +
                "('Ipiranga', 18)," +
                "('Ipixuna do Pará', 14)," +
                "('Ipixuna', 3)," +
                "('Ipojuca', 16)," +
                "('Iporã do Oeste', 24)," +
                "('Iporã', 18)," +
                "('Iporá', 9)," +
                "('Iporanga', 26)," +
                "('Ipu', 6)," +
                "('Ipuã', 26)," +
                "('Ipuaçu', 24)," +
                "('Ipubi', 16)," +
                "('Ipueira', 20)," +
                "('Ipueiras', 27)," +
                "('Ipueiras', 6)," +
                "('Ipuiúna', 11)," +
                "('Ipumirim', 24)," +
                "('Ipupiara', 5)," +
                "('Iracema do Oeste', 18)," +
                "('Iracema', 22)," +
                "('Iracema', 6)," +
                "('Iracemápolis', 26)," +
                "('Iraceminha', 24)," +
                "('Iraí de Minas', 11)," +
                "('Iraí', 23)," +
                "('Irajuba', 5)," +
                "('Iramaia', 5)," +
                "('Iranduba', 3)," +
                "('Irani', 24)," +
                "('Irapuã', 26)," +
                "('Irapuru', 26)," +
                "('Iraquara', 5)," +
                "('Irará', 5)," +
                "('Irati', 18)," +
                "('Irati', 24)," +
                "('Irauçuba', 6)," +
                "('Irecê', 5)," +
                "('Iretama', 18)," +
                "('Irineópolis', 24)," +
                "('Irituia', 14)," +
                "('Irupi', 8)," +
                "('Isaías Coelho', 17)," +
                "('Israelândia', 9)," +
                "('Itá', 24)," +
                "('Itaara', 23)," +
                "('Itabaiana', 15)," +
                "('Itabaiana', 25)," +
                "('Itabaianinha', 25)," +
                "('Itabela', 5)," +
                "('Itaberá', 26)," +
                "('Itaberaba', 5)," +
                "('Itaberaí', 9)," +
                "('Itabi', 25)," +
                "('Itabira', 11)," +
                "('Itabirinha de Mantena', 11)," +
                "('Itabirito', 11)," +
                "('Itaboraí', 19)," +
                "('Itabuna', 5)," +
                "('Itacajá', 27)," +
                "('Itacambira', 11)," +
                "('Itacarambi', 11)," +
                "('Itacaré', 5)," +
                "('Itacoatiara', 3)," +
                "('Itacuruba', 16)," +
                "('Itacurubi', 23)," +
                "('Itaeté', 5)," +
                "('Itagi', 5)," +
                "('Itagibá', 5)," +
                "('Itagimirim', 5)," +
                "('Itaguaçu da Bahia', 5)," +
                "('Itaguaçu', 8)," +
                "('Itaguaí', 19)," +
                "('Itaguajé', 18)," +
                "('Itaguara', 11)," +
                "('Itaguari', 9)," +
                "('Itaguaru', 9)," +
                "('Itaguatins', 27)," +
                "('Itaí', 26)," +
                "('Itaíba', 16)," +
                "('Itaiçaba', 6)," +
                "('Itainópolis', 17)," +
                "('Itaiópolis', 24)," +
                "('Itaipava do Grajaú', 10)," +
                "('Itaipé', 11)," +
                "('Itaipulândia', 18)," +
                "('Itaitinga', 6)," +
                "('Itaituba', 14)," +
                "('Itajá', 20)," +
                "('Itajá', 9)," +
                "('Itajaí', 24)," +
                "('Itajobi', 26)," +
                "('Itaju do Colônia', 5)," +
                "('Itaju', 26)," +
                "('Itajubá', 11)," +
                "('Itajuípe', 5)," +
                "('Italva', 19)," +
                "('Itamaraju', 5)," +
                "('Itamarandiba', 11)," +
                "('Itamarati de Minas', 11)," +
                "('Itamarati', 3)," +
                "('Itamari', 5)," +
                "('Itambacuri', 11)," +
                "('Itambaracá', 18)," +
                "('Itambé do Mato Dentro', 11)," +
                "('Itambé', 16)," +
                "('Itambé', 18)," +
                "('Itambé', 5)," +
                "('Itamogi', 11)," +
                "('Itamonte', 11)," +
                "('Itanagra', 5)," +
                "('Itanhaém', 26)," +
                "('Itanhandu', 11)," +
                "('Itanhangá', 13)," +
                "('Itanhém', 5)," +
                "('Itanhomi', 11)," +
                "('Itaobim', 11)," +
                "('Itaóca', 26)," +
                "('Itaocara', 19)," +
                "('Itapaci', 9)," +
                "('Itapagé', 6)," +
                "('Itapagipe', 11)," +
                "('Itaparica', 5)," +
                "('Itapé', 5)," +
                "('Itapebi', 5)," +
                "('Itapecerica da Serra', 26)," +
                "('Itapecerica', 11)," +
                "('Itapecuru Mirim', 10)," +
                "('Itapejara d`Oeste', 18)," +
                "('Itapema', 24)," +
                "('Itapemirim', 8)," +
                "('Itaperuçu', 18)," +
                "('Itaperuna', 19)," +
                "('Itapetim', 16)," +
                "('Itapetinga', 5)," +
                "('Itapetininga', 26)," +
                "('Itapeva', 11)," +
                "('Itapeva', 26)," +
                "('Itapevi', 26)," +
                "('Itapicuru', 5)," +
                "('Itapipoca', 6)," +
                "('Itapira', 26)," +
                "('Itapiranga', 24)," +
                "('Itapiranga', 3)," +
                "('Itapirapuã Paulista', 26)," +
                "('Itapirapuã', 9)," +
                "('Itapiratins', 27)," +
                "('Itapissuma', 16)," +
                "('Itapitanga', 5)," +
                "('Itapiúna', 6)," +
                "('Itapoá', 24)," +
                "('Itápolis', 26)," +
                "('Itaporã do Tocantins', 27)," +
                "('Itaporã', 12)," +
                "('Itaporanga d`Ajuda', 25)," +
                "('Itaporanga', 15)," +
                "('Itaporanga', 26)," +
                "('Itapororoca', 15)," +
                "('Itapuã do Oeste', 21)," +
                "('Itapuca', 23)," +
                "('Itapuí', 26)," +
                "('Itapura', 26)," +
                "('Itapuranga', 9)," +
                "('Itaquaquecetuba', 26)," +
                "('Itaquara', 5)," +
                "('Itaqui', 23)," +
                "('Itaquiraí', 12)," +
                "('Itaquitinga', 16)," +
                "('Itarana', 8)," +
                "('Itarantim', 5)," +
                "('Itararé', 26)," +
                "('Itarema', 6)," +
                "('Itariri', 26)," +
                "('Itarumã', 9)," +
                "('Itati', 23)," +
                "('Itatiaia', 19)," +
                "('Itatiaiuçu', 11)," +
                "('Itatiba do Sul', 23)," +
                "('Itatiba', 26)," +
                "('Itatim', 5)," +
                "('Itatinga', 26)," +
                "('Itatira', 6)," +
                "('Itatuba', 15)," +
                "('Itaú de Minas', 11)," +
                "('Itaú', 20)," +
                "('Itaúba', 13)," +
                "('Itaubal', 4)," +
                "('Itauçu', 9)," +
                "('Itaueira', 17)," +
                "('Itaúna do Sul', 18)," +
                "('Itaúna', 11)," +
                "('Itaverava', 11)," +
                "('Itinga do Maranhão', 10)," +
                "('Itinga', 11)," +
                "('Itiquira', 13)," +
                "('Itirapina', 26)," +
                "('Itirapuã', 26)," +
                "('Itiruçu', 5)," +
                "('Itiúba', 5)," +
                "('Itobi', 26)," +
                "('Itororó', 5)," +
                "('Itu', 26)," +
                "('Ituaçu', 5)," +
                "('Ituberá', 5)," +
                "('Itueta', 11)," +
                "('Ituiutaba', 11)," +
                "('Itumbiara', 9)," +
                "('Itumirim', 11)," +
                "('Itupeva', 26)," +
                "('Itupiranga', 14)," +
                "('Ituporanga', 24)," +
                "('Iturama', 11)," +
                "('Itutinga', 11)," +
                "('Ituverava', 26)," +
                "('Iuiú', 5)," +
                "('Iúna', 8)," +
                "('Ivaí', 18)," +
                "('Ivaiporã', 18)," +
                "('Ivaté', 18)," +
                "('Ivatuba', 18)," +
                "('Ivinhema', 12)," +
                "('Ivolândia', 9)," +
                "('Ivorá', 23)," +
                "('Ivoti', 23)," +
                "('Jaboatão dos Guararapes', 16)," +
                "('Jaborá', 24)," +
                "('Jaborandi', 26)," +
                "('Jaborandi', 5)," +
                "('Jaboti', 18)," +
                "('Jaboticaba', 23)," +
                "('Jaboticabal', 26)," +
                "('Jaboticatubas', 11)," +
                "('Jaçanã', 20)," +
                "('Jacaraci', 5)," +
                "('Jacaraú', 15)," +
                "('Jacaré dos Homens', 2)," +
                "('Jacareacanga', 14)," +
                "('Jacareí', 26)," +
                "('Jacarezinho', 18)," +
                "('Jaci', 26)," +
                "('Jaciara', 13)," +
                "('Jacinto Machado', 24)," +
                "('Jacinto', 11)," +
                "('Jacobina do Piauí', 17)," +
                "('Jacobina', 5)," +
                "('Jacuí', 11)," +
                "('Jacuípe', 2)," +
                "('Jacuizinho', 23)," +
                "('Jacundá', 14)," +
                "('Jacupiranga', 26)," +
                "('Jacutinga', 11)," +
                "('Jacutinga', 23)," +
                "('Jaguapitã', 18)," +
                "('Jaguaquara', 5)," +
                "('Jaguaraçu', 11)," +
                "('Jaguarão', 23)," +
                "('Jaguarari', 5)," +
                "('Jaguaré', 8)," +
                "('Jaguaretama', 6)," +
                "('Jaguari', 23)," +
                "('Jaguariaíva', 18)," +
                "('Jaguaribara', 6)," +
                "('Jaguaribe', 6)," +
                "('Jaguaripe', 5)," +
                "('Jaguariúna', 26)," +
                "('Jaguaruana', 6)," +
                "('Jaguaruna', 24)," +
                "('Jaíba', 11)," +
                "('Jaicós', 17)," +
                "('Jales', 26)," +
                "('Jambeiro', 26)," +
                "('Jampruca', 11)," +
                "('Janaúba', 11)," +
                "('Jandaia do Sul', 18)," +
                "('Jandaia', 9)," +
                "('Jandaíra', 20)," +
                "('Jandaíra', 5)," +
                "('Jandira', 26)," +
                "('Janduís', 20)," +
                "('Jangada', 13)," +
                "('Janiópolis', 18)," +
                "('Januária', 11)," +
                "('Januário Cicco', 20)," +
                "('Japaraíba', 11)," +
                "('Japaratinga', 2)," +
                "('Japaratuba', 25)," +
                "('Japeri', 19)," +
                "('Japi', 20)," +
                "('Japira', 18)," +
                "('Japoatã', 25)," +
                "('Japonvar', 11)," +
                "('Japorã', 12)," +
                "('Japurá', 18)," +
                "('Japurá', 3)," +
                "('Jaqueira', 16)," +
                "('Jaquirana', 23)," +
                "('Jaraguá do Sul', 24)," +
                "('Jaraguá', 9)," +
                "('Jaraguari', 12)," +
                "('Jaramataia', 2)," +
                "('Jardim Alegre', 18)," +
                "('Jardim de Angicos', 20)," +
                "('Jardim de Piranhas', 20)," +
                "('Jardim do Mulato', 17)," +
                "('Jardim do Seridó', 20)," +
                "('Jardim Olinda', 18)," +
                "('Jardim', 12)," +
                "('Jardim', 6)," +
                "('Jardinópolis', 24)," +
                "('Jardinópolis', 26)," +
                "('Jari', 23)," +
                "('Jarinu', 26)," +
                "('Jaru', 21)," +
                "('Jataí', 9)," +
                "('Jataizinho', 18)," +
                "('Jataúba', 16)," +
                "('Jateí', 12)," +
                "('Jati', 6)," +
                "('Jatobá do Piauí', 17)," +
                "('Jatobá', 10)," +
                "('Jatobá', 16)," +
                "('Jaú do Tocantins', 27)," +
                "('Jaú', 26)," +
                "('Jaupaci', 9)," +
                "('Jauru', 13)," +
                "('Jeceaba', 11)," +
                "('Jenipapo de Minas', 11)," +
                "('Jenipapo dos Vieiras', 10)," +
                "('Jequeri', 11)," +
                "('Jequiá da Praia', 2)," +
                "('Jequié', 5)," +
                "('Jequitaí', 11)," +
                "('Jequitibá', 11)," +
                "('Jequitinhonha', 11)," +
                "('Jeremoabo', 5)," +
                "('Jericó', 15)," +
                "('Jeriquara', 26)," +
                "('Jerônimo Monteiro', 8)," +
                "('Jerumenha', 17)," +
                "('Jesuânia', 11)," +
                "('Jesuítas', 18)," +
                "('Jesúpolis', 9)," +
                "('Jijoca de Jericoacoara', 6)," +
                "('Ji-Paraná', 21)," +
                "('Jiquiriçá', 5)," +
                "('Jitaúna', 5)," +
                "('Joaçaba', 24)," +
                "('Joaíma', 11)," +
                "('Joanésia', 11)," +
                "('Joanópolis', 26)," +
                "('João Alfredo', 16)," +
                "('João Câmara', 20)," +
                "('João Costa', 17)," +
                "('João Dias', 20)," +
                "('João Dourado', 5)," +
                "('João Lisboa', 10)," +
                "('João Monlevade', 11)," +
                "('João Neiva', 8)," +
                "('João Pessoa', 15)," +
                "('João Pinheiro', 11)," +
                "('João Ramalho', 26)," +
                "('Joaquim Felício', 11)," +
                "('Joaquim Gomes', 2)," +
                "('Joaquim Nabuco', 16)," +
                "('Joaquim Pires', 17)," +
                "('Joaquim Távora', 18)," +
                "('Joca Marques', 17)," +
                "('Jóia', 23)," +
                "('Joinville', 24)," +
                "('Jordânia', 11)," +
                "('Jordão', 1)," +
                "('José Boiteux', 24)," +
                "('José Bonifácio', 26)," +
                "('José da Penha', 20)," +
                "('José de Freitas', 17)," +
                "('José Gonçalves de Minas', 11)," +
                "('José Raydan', 11)," +
                "('Joselândia', 10)," +
                "('Josenópolis', 11)," +
                "('Joviânia', 9)," +
                "('Juara', 13)," +
                "('Juarez Távora', 15)," +
                "('Juarina', 27)," +
                "('Juatuba', 11)," +
                "('Juazeirinho', 15)," +
                "('Juazeiro do Norte', 6)," +
                "('Juazeiro do Piauí', 17)," +
                "('Juazeiro', 5)," +
                "('Jucás', 6)," +
                "('Jucati', 16)," +
                "('Jucuruçu', 5)," +
                "('Jucurutu', 20)," +
                "('Juína', 13)," +
                "('Juiz de Fora', 11)," +
                "('Júlio Borges', 17)," +
                "('Júlio de Castilhos', 23)," +
                "('Júlio Mesquita', 26)," +
                "('Jumirim', 26)," +
                "('Junco do Maranhão', 10)," +
                "('Junco do Seridó', 15)," +
                "('Jundiá', 2)," +
                "('Jundiá', 20)," +
                "('Jundiaí do Sul', 18)," +
                "('Jundiaí', 26)," +
                "('Junqueiro', 2)," +
                "('Junqueirópolis', 26)," +
                "('Jupi', 16)," +
                "('Jupiá', 24)," +
                "('Juquiá', 26)," +
                "('Juquitiba', 26)," +
                "('Juramento', 11)," +
                "('Juranda', 18)," +
                "('Jurema', 16)," +
                "('Jurema', 17)," +
                "('Juripiranga', 15)," +
                "('Juru', 15)," +
                "('Juruá', 3)," +
                "('Juruaia', 11)," +
                "('Juruena', 13)," +
                "('Juruti', 14)," +
                "('Juscimeira', 13)," +
                "('Jussara', 18)," +
                "('Jussara', 5)," +
                "('Jussara', 9)," +
                "('Jussari', 5)," +
                "('Jussiape', 5)," +
                "('Jutaí', 3)," +
                "('Juti', 12)," +
                "('Juvenília', 11)," +
                "('Kaloré', 18)," +
                "('Lábrea', 3)," +
                "('Lacerdópolis', 24)," +
                "('Ladainha', 11)," +
                "('Ladário', 12)," +
                "('Lafaiete Coutinho', 5)," +
                "('Lagamar', 11)," +
                "('Lagarto', 25)," +
                "('Lages', 24)," +
                "('Lago da Pedra', 10)," +
                "('Lago do Junco', 10)," +
                "('Lago dos Rodrigues', 10)," +
                "('Lago Verde', 10)," +
                "('Lagoa Alegre', 17)," +
                "('Lagoa Bonita do Sul', 23)," +
                "('Lagoa d`Anta', 20)," +
                "('Lagoa da Canoa', 2)," +
                "('Lagoa da Confusão', 27)," +
                "('Lagoa da Prata', 11)," +
                "('Lagoa de Dentro', 15)," +
                "('Lagoa de Pedras', 20)," +
                "('Lagoa de São Francisco', 17)," +
                "('Lagoa de Velhos', 20)," +
                "('Lagoa do Barro do Piauí', 17)," +
                "('Lagoa do Carro', 16)," +
                "('Lagoa do Itaenga', 16)," +
                "('Lagoa do Mato', 10)," +
                "('Lagoa do Ouro', 16)," +
                "('Lagoa do Piauí', 17)," +
                "('Lagoa do Sítio', 17)," +
                "('Lagoa do Tocantins', 27)," +
                "('Lagoa dos Gatos', 16)," +
                "('Lagoa dos Patos', 11)," +
                "('Lagoa dos Três Cantos', 23)," +
                "('Lagoa Dourada', 11)," +
                "('Lagoa Formosa', 11)," +
                "('Lagoa Grande do Maranhão', 10)," +
                "('Lagoa Grande', 11)," +
                "('Lagoa Grande', 16)," +
                "('Lagoa Nova', 20)," +
                "('Lagoa Real', 5)," +
                "('Lagoa Salgada', 20)," +
                "('Lagoa Santa', 11)," +
                "('Lagoa Santa', 9)," +
                "('Lagoa Seca', 15)," +
                "('Lagoa Vermelha', 23)," +
                "('Lagoa', 15)," +
                "('Lagoão', 23)," +
                "('Lagoinha do Piauí', 17)," +
                "('Lagoinha', 26)," +
                "('Laguna Carapã', 12)," +
                "('Laguna', 24)," +
                "('Laje do Muriaé', 19)," +
                "('Laje', 5)," +
                "('Lajeado do Bugre', 23)," +
                "('Lajeado Grande', 24)," +
                "('Lajeado Novo', 10)," +
                "('Lajeado', 23)," +
                "('Lajeado', 27)," +
                "('Lajedão', 5)," +
                "('Lajedinho', 5)," +
                "('Lajedo do Tabocal', 5)," +
                "('Lajedo', 16)," +
                "('Lajes Pintadas', 20)," +
                "('Lajes', 20)," +
                "('Lajinha', 11)," +
                "('Lamarão', 5)," +
                "('Lambari d`Oeste', 13)," +
                "('Lambari', 11)," +
                "('Lamim', 11)," +
                "('Landri Sales', 17)," +
                "('Lapa', 18)," +
                "('Lapão', 5)," +
                "('Laranja da Terra', 8)," +
                "('Laranjal do Jari', 4)," +
                "('Laranjal Paulista', 26)," +
                "('Laranjal', 11)," +
                "('Laranjal', 18)," +
                "('Laranjeiras do Sul', 18)," +
                "('Laranjeiras', 25)," +
                "('Lassance', 11)," +
                "('Lastro', 15)," +
                "('Laurentino', 24)," +
                "('Lauro de Freitas', 5)," +
                "('Lauro Muller', 24)," +
                "('Lavandeira', 27)," +
                "('Lavínia', 26)," +
                "('Lavras da Mangabeira', 6)," +
                "('Lavras do Sul', 23)," +
                "('Lavras', 11)," +
                "('Lavrinhas', 26)," +
                "('Leandro Ferreira', 11)," +
                "('Lebon Régis', 24)," +
                "('Leme do Prado', 11)," +
                "('Leme', 26)," +
                "('Lençóis Paulista', 26)," +
                "('Lençóis', 5)," +
                "('Leoberto Leal', 24)," +
                "('Leopoldina', 11)," +
                "('Leopoldo de Bulhões', 9)," +
                "('Leópolis', 18)," +
                "('Liberato Salzano', 23)," +
                "('Liberdade', 11)," +
                "('Licínio de Almeida', 5)," +
                "('Lidianópolis', 18)," +
                "('Lima Campos', 10)," +
                "('Lima Duarte', 11)," +
                "('Limeira do Oeste', 11)," +
                "('Limeira', 26)," +
                "('Limoeiro de Anadia', 2)," +
                "('Limoeiro do Ajuru', 14)," +
                "('Limoeiro do Norte', 6)," +
                "('Limoeiro', 16)," +
                "('Lindoeste', 18)," +
                "('Lindóia do Sul', 24)," +
                "('Lindóia', 26)," +
                "('Lindolfo Collor', 23)," +
                "('Linha Nova', 23)," +
                "('Linhares', 8)," +
                "('Lins', 26)," +
                "('Livramento de Nossa Senhora', 5)," +
                "('Livramento', 15)," +
                "('Lizarda', 27)," +
                "('Loanda', 18)," +
                "('Lobato', 18)," +
                "('Logradouro', 15)," +
                "('Londrina', 18)," +
                "('Lontra', 11)," +
                "('Lontras', 24)," +
                "('Lorena', 26)," +
                "('Loreto', 10)," +
                "('Lourdes', 26)," +
                "('Louveira', 26)," +
                "('Lucas do Rio Verde', 13)," +
                "('Lucélia', 26)," +
                "('Lucena', 15)," +
                "('Lucianópolis', 26)," +
                "('Luciára', 13)," +
                "('Lucrécia', 20)," +
                "('Luís Antônio', 26)," +
                "('Luís Correia', 17)," +
                "('Luís Domingues', 10)," +
                "('Luís Eduardo Magalhães', 5)," +
                "('Luís Gomes', 20)," +
                "('Luisburgo', 11)," +
                "('Luislândia', 11)," +
                "('Luiz Alves', 24)," +
                "('Luiziana', 18)," +
                "('Luiziânia', 26)," +
                "('Luminárias', 11)," +
                "('Lunardelli', 18)," +
                "('Lupércio', 26)," +
                "('Lupionópolis', 18)," +
                "('Lutécia', 26)," +
                "('Luz', 11)," +
                "('Luzerna', 24)," +
                "('Luziânia', 9)," +
                "('Luzilândia', 17)," +
                "('Luzinópolis', 27)," +
                "('Macaé', 19)," +
                "('Macaíba', 20)," +
                "('Macajuba', 5)," +
                "('Maçambara', 23)," +
                "('Macambira', 25)," +
                "('Macapá', 4)," +
                "('Macaparana', 16)," +
                "('Macarani', 5)," +
                "('Macatuba', 26)," +
                "('Macau', 20)," +
                "('Macaubal', 26)," +
                "('Macaúbas', 5)," +
                "('Macedônia', 26)," +
                "('Maceió', 2)," +
                "('Machacalis', 11)," +
                "('Machadinho d`Oeste', 21)," +
                "('Machadinho', 23)," +
                "('Machado', 11)," +
                "('Machados', 16)," +
                "('Macieira', 24)," +
                "('Macuco', 19)," +
                "('Macururé', 5)," +
                "('Madalena', 6)," +
                "('Madeiro', 17)," +
                "('Madre de Deus de Minas', 11)," +
                "('Madre de Deus', 5)," +
                "('Mãe d`Água', 15)," +
                "('Mãe do Rio', 14)," +
                "('Maetinga', 5)," +
                "('Mafra', 24)," +
                "('Magalhães Barata', 14)," +
                "('Magalhães de Almeida', 10)," +
                "('Magda', 26)," +
                "('Magé', 19)," +
                "('Maiquinique', 5)," +
                "('Mairi', 5)," +
                "('Mairinque', 26)," +
                "('Mairiporã', 26)," +
                "('Mairipotaba', 9)," +
                "('Major Gercino', 24)," +
                "('Major Isidoro', 2)," +
                "('Major Sales', 20)," +
                "('Major Vieira', 24)," +
                "('Malacacheta', 11)," +
                "('Malhada de Pedras', 5)," +
                "('Malhada dos Bois', 25)," +
                "('Malhada', 5)," +
                "('Malhador', 25)," +
                "('Mallet', 18)," +
                "('Malta', 15)," +
                "('Mamanguape', 15)," +
                "('Mambaí', 9)," +
                "('Mamborê', 18)," +
                "('Mamonas', 11)," +
                "('Mampituba', 23)," +
                "('Manacapuru', 3)," +
                "('Manaíra', 15)," +
                "('Manaquiri', 3)," +
                "('Manari', 16)," +
                "('Manaus', 3)," +
                "('Mâncio Lima', 1)," +
                "('Mandaguaçu', 18)," +
                "('Mandaguari', 18)," +
                "('Mandirituba', 18)," +
                "('Manduri', 26)," +
                "('Manfrinópolis', 18)," +
                "('Manga', 11)," +
                "('Mangaratiba', 19)," +
                "('Mangueirinha', 18)," +
                "('Manhuaçu', 11)," +
                "('Manhumirim', 11)," +
                "('Manicoré', 3)," +
                "('Manoel Emídio', 17)," +
                "('Manoel Ribas', 18)," +
                "('Manoel Urbano', 1)," +
                "('Manoel Viana', 23)," +
                "('Manoel Vitorino', 5)," +
                "('Mansidão', 5)," +
                "('Mantena', 11)," +
                "('Mantenópolis', 8)," +
                "('Maquiné', 23)," +
                "('Mar de Espanha', 11)," +
                "('Mar Vermelho', 2)," +
                "('Mara Rosa', 9)," +
                "('Maraã', 3)," +
                "('Marabá Paulista', 26)," +
                "('Marabá', 14)," +
                "('Maracaçumé', 10)," +
                "('Maracaí', 26)," +
                "('Maracajá', 24)," +
                "('Maracaju', 12)," +
                "('Maracanã', 14)," +
                "('Maracanaú', 6)," +
                "('Maracás', 5)," +
                "('Maragogi', 2)," +
                "('Maragogipe', 5)," +
                "('Maraial', 16)," +
                "('Marajá do Sena', 10)," +
                "('Maranguape', 6)," +
                "('Maranhãozinho', 10)," +
                "('Marapanim', 14)," +
                "('Marapoama', 26)," +
                "('Maratá', 23)," +
                "('Marataízes', 8)," +
                "('Marau', 23)," +
                "('Maraú', 5)," +
                "('Maravilha', 2)," +
                "('Maravilha', 24)," +
                "('Maravilhas', 11)," +
                "('Marcação', 15)," +
                "('Marcelândia', 13)," +
                "('Marcelino Ramos', 23)," +
                "('Marcelino Vieira', 20)," +
                "('Marcionílio Souza', 5)," +
                "('Marco', 6)," +
                "('Marcolândia', 17)," +
                "('Marcos Parente', 17)," +
                "('Marechal Cândido Rondon', 18)," +
                "('Marechal Deodoro', 2)," +
                "('Marechal Floriano', 8)," +
                "('Marechal Thaumaturgo', 1)," +
                "('Marema', 24)," +
                "('Mari', 15)," +
                "('Maria da Fé', 11)," +
                "('Maria Helena', 18)," +
                "('Marialva', 18)," +
                "('Mariana Pimentel', 23)," +
                "('Mariana', 11)," +
                "('Mariano Moro', 23)," +
                "('Marianópolis do Tocantins', 27)," +
                "('Mariápolis', 26)," +
                "('Maribondo', 2)," +
                "('Maricá', 19)," +
                "('Marilac', 11)," +
                "('Marilândia do Sul', 18)," +
                "('Marilândia', 8)," +
                "('Marilena', 18)," +
                "('Marília', 26)," +
                "('Mariluz', 18)," +
                "('Maringá', 18)," +
                "('Marinópolis', 26)," +
                "('Mário Campos', 11)," +
                "('Mariópolis', 18)," +
                "('Maripá de Minas', 11)," +
                "('Maripá', 18)," +
                "('Marituba', 14)," +
                "('Marizópolis', 15)," +
                "('Marliéria', 11)," +
                "('Marmeleiro', 18)," +
                "('Marmelópolis', 11)," +
                "('Marques de Souza', 23)," +
                "('Marquinho', 18)," +
                "('Martinho Campos', 11)," +
                "('Martinópole', 6)," +
                "('Martinópolis', 26)," +
                "('Martins Soares', 11)," +
                "('Martins', 20)," +
                "('Maruim', 25)," +
                "('Marumbi', 18)," +
                "('Marzagão', 9)," +
                "('Mascote', 5)," +
                "('Massapê do Piauí', 17)," +
                "('Massapê', 6)," +
                "('Massaranduba', 15)," +
                "('Massaranduba', 24)," +
                "('Mata de São João', 5)," +
                "('Mata Grande', 2)," +
                "('Mata Roma', 10)," +
                "('Mata Verde', 11)," +
                "('Mata', 23)," +
                "('Matão', 26)," +
                "('Mataraca', 15)," +
                "('Mateiros', 27)," +
                "('Matelândia', 18)," +
                "('Materlândia', 11)," +
                "('Mateus Leme', 11)," +
                "('Mathias Lobato', 11)," +
                "('Matias Barbosa', 11)," +
                "('Matias Cardoso', 11)," +
                "('Matias Olímpio', 17)," +
                "('Matina', 5)," +
                "('Matinha', 10)," +
                "('Matinhas', 15)," +
                "('Matinhos', 18)," +
                "('Matipó', 11)," +
                "('Mato Castelhano', 23)," +
                "('Mato Grosso', 15)," +
                "('Mato Leitão', 23)," +
                "('Mato Queimado', 23)," +
                "('Mato Rico', 18)," +
                "('Mato Verde', 11)," +
                "('Matões do Norte', 10)," +
                "('Matões', 10)," +
                "('Matos Costa', 24)," +
                "('Matozinhos', 11)," +
                "('Matrinchã', 9)," +
                "('Matriz de Camaragibe', 2)," +
                "('Matupá', 13)," +
                "('Maturéia', 15)," +
                "('Matutina', 11)," +
                "('Mauá da Serra', 18)," +
                "('Mauá', 26)," +
                "('Maués', 3)," +
                "('Maurilândia do Tocantins', 27)," +
                "('Maurilândia', 9)," +
                "('Mauriti', 6)," +
                "('Maxaranguape', 20)," +
                "('Maximiliano de Almeida', 23)," +
                "('Mazagão', 4)," +
                "('Medeiros Neto', 5)," +
                "('Medeiros', 11)," +
                "('Medianeira', 18)," +
                "('Medicilândia', 14)," +
                "('Medina', 11)," +
                "('Meleiro', 24)," +
                "('Melgaço', 14)," +
                "('Mendes Pimentel', 11)," +
                "('Mendes', 19)," +
                "('Mendonça', 26)," +
                "('Mercedes', 18)," +
                "('Mercês', 11)," +
                "('Meridiano', 26)," +
                "('Meruoca', 6)," +
                "('Mesópolis', 26)," +
                "('Mesquita', 11)," +
                "('Mesquita', 19)," +
                "('Messias Targino', 20)," +
                "('Messias', 2)," +
                "('Miguel Alves', 17)," +
                "('Miguel Calmon', 5)," +
                "('Miguel Leão', 17)," +
                "('Miguel Pereira', 19)," +
                "('Miguelópolis', 26)," +
                "('Milagres do Maranhão', 10)," +
                "('Milagres', 5)," +
                "('Milagres', 6)," +
                "('Milhã', 6)," +
                "('Milton Brandão', 17)," +
                "('Mimoso de Goiás', 9)," +
                "('Mimoso do Sul', 8)," +
                "('Minaçu', 9)," +
                "('Minador do Negrão', 2)," +
                "('Minas do Leão', 23)," +
                "('Minas Novas', 11)," +
                "('Minduri', 11)," +
                "('Mineiros do Tietê', 26)," +
                "('Mineiros', 9)," +
                "('Ministro Andreazza', 21)," +
                "('Mira Estrela', 26)," +
                "('Mirabela', 11)," +
                "('Miracatu', 26)," +
                "('Miracema do Tocantins', 27)," +
                "('Miracema', 19)," +
                "('Mirador', 10)," +
                "('Mirador', 18)," +
                "('Miradouro', 11)," +
                "('Miraguaí', 23)," +
                "('Miraí', 11)," +
                "('Miraíma', 6)," +
                "('Miranda do Norte', 10)," +
                "('Miranda', 12)," +
                "('Mirandiba', 16)," +
                "('Mirandópolis', 26)," +
                "('Mirangaba', 5)," +
                "('Miranorte', 27)," +
                "('Mirante da Serra', 21)," +
                "('Mirante do Paranapanema', 26)," +
                "('Mirante', 5)," +
                "('Miraselva', 18)," +
                "('Mirassol d`Oeste', 13)," +
                "('Mirassol', 26)," +
                "('Mirassolândia', 26)," +
                "('Miravânia', 11)," +
                "('Mirim Doce', 24)," +
                "('Mirinzal', 10)," +
                "('Missal', 18)," +
                "('Missão Velha', 6)," +
                "('Mocajuba', 14)," +
                "('Mococa', 26)," +
                "('Modelo', 24)," +
                "('Moeda', 11)," +
                "('Moema', 11)," +
                "('Mogeiro', 15)," +
                "('Mogi das Cruzes', 26)," +
                "('Mogi Guaçu', 26)," +
                "('Moiporá', 9)," +
                "('Moita Bonita', 25)," +
                "('Moji Mirim', 26)," +
                "('Moju', 14)," +
                "('Mombaça', 6)," +
                "('Mombuca', 26)," +
                "('Monção', 10)," +
                "('Monções', 26)," +
                "('Mondaí', 24)," +
                "('Mongaguá', 26)," +
                "('Monjolos', 11)," +
                "('Monsenhor Gil', 17)," +
                "('Monsenhor Hipólito', 17)," +
                "('Monsenhor Paulo', 11)," +
                "('Monsenhor Tabosa', 6)," +
                "('Montadas', 15)," +
                "('Montalvânia', 11)," +
                "('Montanha', 8)," +
                "('Montanhas', 20)," +
                "('Montauri', 23)," +
                "('Monte Alegre de Goiás', 9)," +
                "('Monte Alegre de Minas', 11)," +
                "('Monte Alegre de Sergipe', 25)," +
                "('Monte Alegre do Piauí', 17)," +
                "('Monte Alegre do Sul', 26)," +
                "('Monte Alegre dos Campos', 23)," +
                "('Monte Alegre', 14)," +
                "('Monte Alegre', 20)," +
                "('Monte Alto', 26)," +
                "('Monte Aprazível', 26)," +
                "('Monte Azul Paulista', 26)," +
                "('Monte Azul', 11)," +
                "('Monte Belo do Sul', 23)," +
                "('Monte Belo', 11)," +
                "('Monte Carlo', 24)," +
                "('Monte Carmelo', 11)," +
                "('Monte Castelo', 24)," +
                "('Monte Castelo', 26)," +
                "('Monte das Gameleiras', 20)," +
                "('Monte do Carmo', 27)," +
                "('Monte Formoso', 11)," +
                "('Monte Horebe', 15)," +
                "('Monte Mor', 26)," +
                "('Monte Negro', 21)," +
                "('Monte Santo de Minas', 11)," +
                "('Monte Santo do Tocantins', 27)," +
                "('Monte Santo', 5)," +
                "('Monte Sião', 11)," +
                "('Monteiro Lobato', 26)," +
                "('Monteiro', 15)," +
                "('Monteirópolis', 2)," +
                "('Montenegro', 23)," +
                "('Montes Altos', 10)," +
                "('Montes Claros de Goiás', 9)," +
                "('Montes Claros', 11)," +
                "('Montezuma', 11)," +
                "('Montividiu do Norte', 9)," +
                "('Montividiu', 9)," +
                "('Morada Nova de Minas', 11)," +
                "('Morada Nova', 6)," +
                "('Moraújo', 6)," +
                "('Moreilândia', 16)," +
                "('Moreira Sales', 18)," +
                "('Moreno', 16)," +
                "('Mormaço', 23)," +
                "('Morpará', 5)," +
                "('Morretes', 18)," +
                "('Morrinhos do Sul', 23)," +
                "('Morrinhos', 6)," +
                "('Morrinhos', 9)," +
                "('Morro Agudo de Goiás', 9)," +
                "('Morro Agudo', 26)," +
                "('Morro Cabeça no Tempo', 17)," +
                "('Morro da Fumaça', 24)," +
                "('Morro da Garça', 11)," +
                "('Morro do Chapéu do Piauí', 17)," +
                "('Morro do Chapéu', 5)," +
                "('Morro do Pilar', 11)," +
                "('Morro Grande', 24)," +
                "('Morro Redondo', 23)," +
                "('Morro Reuter', 23)," +
                "('Morros', 10)," +
                "('Mortugaba', 5)," +
                "('Morungaba', 26)," +
                "('Mossâmedes', 9)," +
                "('Mossoró', 20)," +
                "('Mostardas', 23)," +
                "('Motuca', 26)," +
                "('Mozarlândia', 9)," +
                "('Muaná', 14)," +
                "('Mucajaí', 22)," +
                "('Mucambo', 6)," +
                "('Mucugê', 5)," +
                "('Muçum', 23)," +
                "('Mucuri', 5)," +
                "('Mucurici', 8)," +
                "('Muitos Capões', 23)," +
                "('Muliterno', 23)," +
                "('Mulungu do Morro', 5)," +
                "('Mulungu', 15)," +
                "('Mulungu', 6)," +
                "('Mundo Novo', 12)," +
                "('Mundo Novo', 5)," +
                "('Mundo Novo', 9)," +
                "('Munhoz de Melo', 18)," +
                "('Munhoz', 11)," +
                "('Muniz Ferreira', 5)," +
                "('Muniz Freire', 8)," +
                "('Muquém de São Francisco', 5)," +
                "('Muqui', 8)," +
                "('Muriaé', 11)," +
                "('Muribeca', 25)," +
                "('Murici dos Portelas', 17)," +
                "('Murici', 2)," +
                "('Muricilândia', 27)," +
                "('Muritiba', 5)," +
                "('Murutinga do Sul', 26)," +
                "('Mutuípe', 5)," +
                "('Mutum', 11)," +
                "('Mutunópolis', 9)," +
                "('Muzambinho', 11);";
        String insert_table_cidades_3 ="INSERT INTO "+EntryCidade.TABLE_NAME +" ( "+EntryCidade.CIDADE+","+EntryCidade.ID_ESTADO+") VALUES "+ 
                "('Nacip Raydan', 11)," +
                "('Nantes', 26)," +
                "('Nanuque', 11)," +
                "('Não-Me-Toque', 23)," +
                "('Naque', 11)," +
                "('Narandiba', 26)," +
                "('Natal', 20)," +
                "('Natalândia', 11)," +
                "('Natércia', 11)," +
                "('Natividade da Serra', 26)," +
                "('Natividade', 19)," +
                "('Natividade', 27)," +
                "('Natuba', 15)," +
                "('Navegantes', 24)," +
                "('Naviraí', 12)," +
                "('Nazaré da Mata', 16)," +
                "('Nazaré do Piauí', 17)," +
                "('Nazaré Paulista', 26)," +
                "('Nazaré', 27)," +
                "('Nazaré', 5)," +
                "('Nazareno', 11)," +
                "('Nazarezinho', 15)," +
                "('Nazário', 9)," +
                "('Neópolis', 25)," +
                "('Nepomuceno', 11)," +
                "('Nerópolis', 9)," +
                "('Neves Paulista', 26)," +
                "('Nhamundá', 3)," +
                "('Nhandeara', 26)," +
                "('Nicolau Vergueiro', 23)," +
                "('Nilo Peçanha', 5)," +
                "('Nilópolis', 19)," +
                "('Nina Rodrigues', 10)," +
                "('Ninheira', 11)," +
                "('Nioaque', 12)," +
                "('Nipoã', 26)," +
                "('Niquelândia', 9)," +
                "('Nísia Floresta', 20)," +
                "('Niterói', 19)," +
                "('Nobres', 13)," +
                "('Nonoai', 23)," +
                "('Nordestina', 5)," +
                "('Normandia', 22)," +
                "('Nortelândia', 13)," +
                "('Nossa Senhora Aparecida', 25)," +
                "('Nossa Senhora da Glória', 25)," +
                "('Nossa Senhora das Dores', 25)," +
                "('Nossa Senhora das Graças', 18)," +
                "('Nossa Senhora de Lourdes', 25)," +
                "('Nossa Senhora de Nazaré', 17)," +
                "('Nossa Senhora do Livramento', 13)," +
                "('Nossa Senhora do Socorro', 25)," +
                "('Nossa Senhora dos Remédios', 17)," +
                "('Nova Aliança do Ivaí', 18)," +
                "('Nova Aliança', 26)," +
                "('Nova Alvorada do Sul', 12)," +
                "('Nova Alvorada', 23)," +
                "('Nova América da Colina', 18)," +
                "('Nova América', 9)," +
                "('Nova Andradina', 12)," +
                "('Nova Araçá', 23)," +
                "('Nova Aurora', 18)," +
                "('Nova Aurora', 9)," +
                "('Nova Bandeirantes', 13)," +
                "('Nova Bassano', 23)," +
                "('Nova Belém', 11)," +
                "('Nova Boa Vista', 23)," +
                "('Nova Brasilândia d`Oeste', 21)," +
                "('Nova Brasilândia', 13)," +
                "('Nova Bréscia', 23)," +
                "('Nova Campina', 26)," +
                "('Nova Canaã do Norte', 13)," +
                "('Nova Canaã Paulista', 26)," +
                "('Nova Canaã', 5)," +
                "('Nova Candelária', 23)," +
                "('Nova Cantu', 18)," +
                "('Nova Castilho', 26)," +
                "('Nova Colinas', 10)," +
                "('Nova Crixás', 9)," +
                "('Nova Cruz', 20)," +
                "('Nova Era', 11)," +
                "('Nova Erechim', 24)," +
                "('Nova Esperança do Piriá', 14)," +
                "('Nova Esperança do Sudoeste', 18)," +
                "('Nova Esperança do Sul', 23)," +
                "('Nova Esperança', 18)," +
                "('Nova Europa', 26)," +
                "('Nova Fátima', 18)," +
                "('Nova Fátima', 5)," +
                "('Nova Floresta', 15)," +
                "('Nova Friburgo', 19)," +
                "('Nova Glória', 9)," +
                "('Nova Granada', 26)," +
                "('Nova Guarita', 13)," +
                "('Nova Guataporanga', 26)," +
                "('Nova Hartz', 23)," +
                "('Nova Ibiá', 5)," +
                "('Nova Iguaçu de Goiás', 9)," +
                "('Nova Iguaçu', 19)," +
                "('Nova Independência', 26)," +
                "('Nova Iorque', 10)," +
                "('Nova Ipixuna', 14)," +
                "('Nova Itaberaba', 24)," +
                "('Nova Itarana', 5)," +
                "('Nova Lacerda', 13)," +
                "('Nova Laranjeiras', 18)," +
                "('Nova Lima', 11)," +
                "('Nova Londrina', 18)," +
                "('Nova Luzitânia', 26)," +
                "('Nova Mamoré', 21)," +
                "('Nova Marilândia', 13)," +
                "('Nova Maringá', 13)," +
                "('Nova Módica', 11)," +
                "('Nova Monte verde', 13)," +
                "('Nova Mutum', 13)," +
                "('Nova Odessa', 26)," +
                "('Nova Olímpia', 13)," +
                "('Nova Olímpia', 18)," +
                "('Nova Olinda do Maranhão', 10)," +
                "('Nova Olinda do Norte', 3)," +
                "('Nova Olinda', 15)," +
                "('Nova Olinda', 27)," +
                "('Nova Olinda', 6)," +
                "('Nova Pádua', 23)," +
                "('Nova Palma', 23)," +
                "('Nova Palmeira', 15)," +
                "('Nova Petrópolis', 23)," +
                "('Nova Ponte', 11)," +
                "('Nova Porteirinha', 11)," +
                "('Nova Prata do Iguaçu', 18)," +
                "('Nova Prata', 23)," +
                "('Nova Ramada', 23)," +
                "('Nova Redenção', 5)," +
                "('Nova Resende', 11)," +
                "('Nova Roma do Sul', 23)," +
                "('Nova Roma', 9)," +
                "('Nova Rosalândia', 27)," +
                "('Nova Russas', 6)," +
                "('Nova Santa Bárbara', 18)," +
                "('Nova Santa Helena', 13)," +
                "('Nova Santa Rita', 17)," +
                "('Nova Santa Rita', 23)," +
                "('Nova Santa Rosa', 18)," +
                "('Nova Serrana', 11)," +
                "('Nova Soure', 5)," +
                "('Nova Tebas', 18)," +
                "('Nova Timboteua', 14)," +
                "('Nova Trento', 24)," +
                "('Nova Ubiratã', 13)," +
                "('Nova União', 11)," +
                "('Nova União', 21)," +
                "('Nova Venécia', 8)," +
                "('Nova Veneza', 24)," +
                "('Nova Veneza', 9)," +
                "('Nova Viçosa', 5)," +
                "('Nova Xavantina', 13)," +
                "('Novais', 26)," +
                "('Novo Acordo', 27)," +
                "('Novo Airão', 3)," +
                "('Novo Alegre', 27)," +
                "('Novo Aripuanã', 3)," +
                "('Novo Barreiro', 23)," +
                "('Novo Brasil', 9)," +
                "('Novo Cabrais', 23)," +
                "('Novo Cruzeiro', 11)," +
                "('Novo Gama', 9)," +
                "('Novo Hamburgo', 23)," +
                "('Novo Horizonte do Norte', 13)," +
                "('Novo Horizonte do Oeste', 21)," +
                "('Novo Horizonte do Sul', 12)," +
                "('Novo Horizonte', 24)," +
                "('Novo Horizonte', 26)," +
                "('Novo Horizonte', 5)," +
                "('Novo Itacolomi', 18)," +
                "('Novo Jardim', 27)," +
                "('Novo Lino', 2)," +
                "('Novo Machado', 23)," +
                "('Novo Mundo', 13)," +
                "('Novo Oriente de Minas', 11)," +
                "('Novo Oriente do Piauí', 17)," +
                "('Novo Oriente', 6)," +
                "('Novo Planalto', 9)," +
                "('Novo Progresso', 14)," +
                "('Novo Repartimento', 14)," +
                "('Novo Santo Antônio', 13)," +
                "('Novo Santo Antônio', 17)," +
                "('Novo São Joaquim', 13)," +
                "('Novo Tiradentes', 23)," +
                "('Novo Triunfo', 5)," +
                "('Novo Xingu', 23)," +
                "('Novorizonte', 11)," +
                "('Nuporanga', 26)," +
                "('Óbidos', 14)," +
                "('Ocara', 6)," +
                "('Ocauçu', 26)," +
                "('Oeiras do Pará', 14)," +
                "('Oeiras', 17)," +
                "('Oiapoque', 4)," +
                "('Olaria', 11)," +
                "('Óleo', 26)," +
                "('Olho d`Água das Cunhãs', 10)," +
                "('Olho d`Água das Flores', 2)," +
                "('Olho d`Água do Casado', 2)," +
                "('Olho d`Água do Piauí', 17)," +
                "('Olho d`Água Grande', 2)," +
                "('Olho d`Água', 15)," +
                "('Olho-d`Água do Borges', 20)," +
                "('Olhos-d`Água', 11)," +
                "('Olímpia', 26)," +
                "('Olímpio Noronha', 11)," +
                "('Olinda Nova do Maranhão', 10)," +
                "('Olinda', 16)," +
                "('Olindina', 5)," +
                "('Olivedos', 15)," +
                "('Oliveira de Fátima', 27)," +
                "('Oliveira dos Brejinhos', 5)," +
                "('Oliveira Fortes', 11)," +
                "('Oliveira', 11)," +
                "('Olivença', 2)," +
                "('Onça de Pitangui', 11)," +
                "('Onda Verde', 26)," +
                "('Oratórios', 11)," +
                "('Oriente', 26)," +
                "('Orindiúva', 26)," +
                "('Oriximiná', 14)," +
                "('Orizânia', 11)," +
                "('Orizona', 9)," +
                "('Orlândia', 26)," +
                "('Orleans', 24)," +
                "('Orobó', 16)," +
                "('Orocó', 16)," +
                "('Orós', 6)," +
                "('Ortigueira', 18)," +
                "('Osasco', 26)," +
                "('Oscar Bressane', 26)," +
                "('Osório', 23)," +
                "('Osvaldo Cruz', 26)," +
                "('Otacílio Costa', 24)," +
                "('Ourém', 14)," +
                "('Ouriçangas', 5)," +
                "('Ouricuri', 16)," +
                "('Ourilândia do Norte', 14)," +
                "('Ourinhos', 26)," +
                "('Ourizona', 18)," +
                "('Ouro Branco', 11)," +
                "('Ouro Branco', 2)," +
                "('Ouro Branco', 20)," +
                "('Ouro Fino', 11)," +
                "('Ouro Preto do Oeste', 21)," +
                "('Ouro Preto', 11)," +
                "('Ouro Velho', 15)," +
                "('Ouro Verde de Goiás', 9)," +
                "('Ouro Verde de Minas', 11)," +
                "('Ouro Verde do Oeste', 18)," +
                "('Ouro Verde', 24)," +
                "('Ouro Verde', 26)," +
                "('Ouro', 24)," +
                "('Ouroeste', 26)," +
                "('Ourolândia', 5)," +
                "('Ouvidor', 9)," +
                "('Pacaembu', 26)," +
                "('Pacajá', 14)," +
                "('Pacajus', 6)," +
                "('Pacaraima', 22)," +
                "('Pacatuba', 25)," +
                "('Pacatuba', 6)," +
                "('Paço do Lumiar', 10)," +
                "('Pacoti', 6)," +
                "('Pacujá', 6)," +
                "('Padre Bernardo', 9)," +
                "('Padre Carvalho', 11)," +
                "('Padre Marcos', 17)," +
                "('Padre Paraíso', 11)," +
                "('Paes Landim', 17)," +
                "('Pai Pedro', 11)," +
                "('Paial', 24)," +
                "('Paiçandu', 18)," +
                "('Paim Filho', 23)," +
                "('Paineiras', 11)," +
                "('Painel', 24)," +
                "('Pains', 11)," +
                "('Paiva', 11)," +
                "('Pajeú do Piauí', 17)," +
                "('Palestina de Goiás', 9)," +
                "('Palestina do Pará', 14)," +
                "('Palestina', 2)," +
                "('Palestina', 26)," +
                "('Palhano', 6)," +
                "('Palhoça', 24)," +
                "('Palma Sola', 24)," +
                "('Palma', 11)," +
                "('Palmácia', 6)," +
                "('Palmares do Sul', 23)," +
                "('Palmares Paulista', 26)," +
                "('Palmares', 16)," +
                "('Palmas de Monte Alto', 5)," +
                "('Palmas', 18)," +
                "('Palmas', 27)," +
                "('Palmeira d`Oeste', 26)," +
                "('Palmeira das Missões', 23)," +
                "('Palmeira do Piauí', 17)," +
                "('Palmeira dos Índios', 2)," +
                "('Palmeira', 18)," +
                "('Palmeira', 24)," +
                "('Palmeirais', 17)," +
                "('Palmeirândia', 10)," +
                "('Palmeirante', 27)," +
                "('Palmeiras de Goiás', 9)," +
                "('Palmeiras do Tocantins', 27)," +
                "('Palmeiras', 5)," +
                "('Palmeirina', 16)," +
                "('Palmeirópolis', 27)," +
                "('Palmelo', 9)," +
                "('Palminópolis', 9)," +
                "('Palmital', 18)," +
                "('Palmital', 26)," +
                "('Palmitinho', 23)," +
                "('Palmitos', 24)," +
                "('Palmópolis', 11)," +
                "('Palotina', 18)," +
                "('Panamá', 9)," +
                "('Panambi', 23)," +
                "('Pancas', 8)," +
                "('Panelas', 16)," +
                "('Panorama', 26)," +
                "('Pantano Grande', 23)," +
                "('Pão de Açúcar', 2)," +
                "('Papagaios', 11)," +
                "('Papanduva', 24)," +
                "('Paquetá', 17)," +
                "('Pará de Minas', 11)," +
                "('Paracambi', 19)," +
                "('Paracatu', 11)," +
                "('Paracuru', 6)," +
                "('Paragominas', 14)," +
                "('Paraguaçu Paulista', 26)," +
                "('Paraguaçu', 11)," +
                "('Paraí', 23)," +
                "('Paraíba do Sul', 19)," +
                "('Paraibano', 10)," +
                "('Paraibuna', 26)," +
                "('Paraipaba', 6)," +
                "('Paraíso do Norte', 18)," +
                "('Paraíso do Sul', 23)," +
                "('Paraíso do Tocantins', 27)," +
                "('Paraíso', 24)," +
                "('Paraíso', 26)," +
                "('Paraisópolis', 11)," +
                "('Parambu', 6)," +
                "('Paramirim', 5)," +
                "('Paramoti', 6)," +
                "('Paraná', 20)," +
                "('Paranã', 27)," +
                "('Paranacity', 18)," +
                "('Paranaguá', 18)," +
                "('Paranaíba', 12)," +
                "('Paranaiguara', 9)," +
                "('Paranaíta', 13)," +
                "('Paranapanema', 26)," +
                "('Paranapoema', 18)," +
                "('Paranapuã', 26)," +
                "('Paranatama', 16)," +
                "('Paranatinga', 13)," +
                "('Paranavaí', 18)," +
                "('Paranhos', 12)," +
                "('Paraopeba', 11)," +
                "('Parapuã', 26)," +
                "('Parari', 15)," +
                "('Parati', 19)," +
                "('Paratinga', 5)," +
                "('Paraú', 20)," +
                "('Parauapebas', 14)," +
                "('Paraúna', 9)," +
                "('Parazinho', 20)," +
                "('Pardinho', 26)," +
                "('Pareci Novo', 23)," +
                "('Parecis', 21)," +
                "('Parelhas', 20)," +
                "('Pariconha', 2)," +
                "('Parintins', 3)," +
                "('Paripiranga', 5)," +
                "('Paripueira', 2)," +
                "('Pariquera-Açu', 26)," +
                "('Parisi', 26)," +
                "('Parnaguá', 17)," +
                "('Parnaíba', 17)," +
                "('Parnamirim', 16)," +
                "('Parnamirim', 20)," +
                "('Parnarama', 10)," +
                "('Parobé', 23)," +
                "('Passa e Fica', 20)," +
                "('Passa Quatro', 11)," +
                "('Passa Sete', 23)," +
                "('Passa Tempo', 11)," +
                "('Passabém', 11)," +
                "('Passagem Franca do Piauí', 17)," +
                "('Passagem Franca', 10)," +
                "('Passagem', 15)," +
                "('Passagem', 20)," +
                "('Passa-Vinte', 11)," +
                "('Passira', 16)," +
                "('Passo de Camaragibe', 2)," +
                "('Passo de Torres', 24)," +
                "('Passo do Sobrado', 23)," +
                "('Passo Fundo', 23)," +
                "('Passos Maia', 24)," +
                "('Passos', 11)," +
                "('Pastos Bons', 10)," +
                "('Patis', 11)," +
                "('Pato Bragado', 18)," +
                "('Pato Branco', 18)," +
                "('Patos de Minas', 11)," +
                "('Patos do Piauí', 17)," +
                "('Patos', 15)," +
                "('Patrocínio do Muriaé', 11)," +
                "('Patrocínio Paulista', 26)," +
                "('Patrocínio', 11)," +
                "('Patu', 20)," +
                "('Paty do Alferes', 19)," +
                "('Pau Brasil', 5)," +
                "('Pau d`Arco do Piauí', 17)," +
                "('Pau d`Arco', 14)," +
                "('Pau d`Arco', 27)," +
                "('Pau dos Ferros', 20)," +
                "('Paudalho', 16)," +
                "('Pauini', 3)," +
                "('Paula Cândido', 11)," +
                "('Paula Freitas', 18)," +
                "('Paulicéia', 26)," +
                "('Paulínia', 26)," +
                "('Paulino Neves', 10)," +
                "('Paulista', 15)," +
                "('Paulista', 16)," +
                "('Paulistana', 17)," +
                "('Paulistânia', 26)," +
                "('Paulistas', 11)," +
                "('Paulo Afonso', 5)," +
                "('Paulo Bento', 23)," +
                "('Paulo de Faria', 26)," +
                "('Paulo Frontin', 18)," +
                "('Paulo Jacinto', 2)," +
                "('Paulo Lopes', 24)," +
                "('Paulo Ramos', 10)," +
                "('Pavão', 11)," +
                "('Paverama', 23)," +
                "('Pavussu', 17)," +
                "('Pé de Serra', 5)," +
                "('Peabiru', 18)," +
                "('Peçanha', 11)," +
                "('Pederneiras', 26)," +
                "('Pedra Azul', 11)," +
                "('Pedra Bela', 26)," +
                "('Pedra Bonita', 11)," +
                "('Pedra Branca do Amaparí', 4)," +
                "('Pedra Branca', 15)," +
                "('Pedra Branca', 6)," +
                "('Pedra do Anta', 11)," +
                "('Pedra do Indaiá', 11)," +
                "('Pedra Dourada', 11)," +
                "('Pedra Grande', 20)," +
                "('Pedra Lavrada', 15)," +
                "('Pedra Mole', 25)," +
                "('Pedra Preta', 13)," +
                "('Pedra Preta', 20)," +
                "('Pedra', 16)," +
                "('Pedralva', 11)," +
                "('Pedranópolis', 26)," +
                "('Pedrão', 5)," +
                "('Pedras Altas', 23)," +
                "('Pedras de Fogo', 15)," +
                "('Pedras de Maria da Cruz', 11)," +
                "('Pedras Grandes', 24)," +
                "('Pedregulho', 26)," +
                "('Pedreira', 26)," +
                "('Pedreiras', 10)," +
                "('Pedrinhas Paulista', 26)," +
                "('Pedrinhas', 25)," +
                "('Pedrinópolis', 11)," +
                "('Pedro Afonso', 27)," +
                "('Pedro Alexandre', 5)," +
                "('Pedro Avelino', 20)," +
                "('Pedro Canário', 8)," +
                "('Pedro de Toledo', 26)," +
                "('Pedro do Rosário', 10)," +
                "('Pedro Gomes', 12)," +
                "('Pedro II', 17)," +
                "('Pedro Laurentino', 17)," +
                "('Pedro Leopoldo', 11)," +
                "('Pedro Osório', 23)," +
                "('Pedro Régis', 15)," +
                "('Pedro Teixeira', 11)," +
                "('Pedro Velho', 20)," +
                "('Peixe', 27)," +
                "('Peixe-Boi', 14)," +
                "('Peixoto de Azevedo', 13)," +
                "('Pejuçara', 23)," +
                "('Pelotas', 23)," +
                "('Penaforte', 6)," +
                "('Penalva', 10)," +
                "('Penápolis', 26)," +
                "('Pendências', 20)," +
                "('Penedo', 2)," +
                "('Penha', 24)," +
                "('Pentecoste', 6)," +
                "('Pequeri', 11)," +
                "('Pequi', 11)," +
                "('Pequizeiro', 27)," +
                "('Perdigão', 11)," +
                "('Perdizes', 11)," +
                "('Perdões', 11)," +
                "('Pereira Barreto', 26)," +
                "('Pereiras', 26)," +
                "('Pereiro', 6)," +
                "('Peri Mirim', 10)," +
                "('Periquito', 11)," +
                "('Peritiba', 24)," +
                "('Peritoró', 10)," +
                "('Perobal', 18)," +
                "('Pérola d`Oeste', 18)," +
                "('Pérola', 18)," +
                "('Perolândia', 9)," +
                "('Peruíbe', 26)," +
                "('Pescador', 11)," +
                "('Pesqueira', 16)," +
                "('Petrolândia', 16)," +
                "('Petrolândia', 24)," +
                "('Petrolina de Goiás', 9)," +
                "('Petrolina', 16)," +
                "('Petrópolis', 19)," +
                "('Piaçabuçu', 2)," +
                "('Piacatu', 26)," +
                "('Piancó', 15)," +
                "('Piatã', 5)," +
                "('Piau', 11)," +
                "('Picada Café', 23)," +
                "('Piçarra', 14)," +
                "('Piçarras', 24)," +
                "('Picos', 17)," +
                "('Picuí', 15)," +
                "('Piedade de Caratinga', 11)," +
                "('Piedade de Ponte Nova', 11)," +
                "('Piedade do Rio Grande', 11)," +
                "('Piedade dos Gerais', 11)," +
                "('Piedade', 26)," +
                "('Piên', 18)," +
                "('Pilão Arcado', 5)," +
                "('Pilar de Goiás', 9)," +
                "('Pilar do Sul', 26)," +
                "('Pilar', 15)," +
                "('Pilar', 2)," +
                "('Pilões', 15)," +
                "('Pilões', 20)," +
                "('Pilõezinhos', 15)," +
                "('Pimenta Bueno', 21)," +
                "('Pimenta', 11)," +
                "('Pimenteiras do Oeste', 21)," +
                "('Pimenteiras', 17)," +
                "('Pindaí', 5)," +
                "('Pindamonhangaba', 26)," +
                "('Pindaré-Mirim', 10)," +
                "('Pindoba', 2)," +
                "('Pindobaçu', 5)," +
                "('Pindorama do Tocantins', 27)," +
                "('Pindorama', 26)," +
                "('Pindoretama', 6)," +
                "('Pingo-d`Água', 11)," +
                "('Pinhais', 18)," +
                "('Pinhal da Serra', 23)," +
                "('Pinhal de São Bento', 18)," +
                "('Pinhal Grande', 23)," +
                "('Pinhal', 23)," +
                "('Pinhalão', 18)," +
                "('Pinhalzinho', 24)," +
                "('Pinhalzinho', 26)," +
                "('Pinhão', 18)," +
                "('Pinhão', 25)," +
                "('Pinheiral', 19)," +
                "('Pinheirinho do Vale', 23)," +
                "('Pinheiro Machado', 23)," +
                "('Pinheiro Preto', 24)," +
                "('Pinheiro', 10)," +
                "('Pinheiros', 8)," +
                "('Pintadas', 5)," +
                "('Pintópolis', 11)," +
                "('Pio IX', 17)," +
                "('Pio XII', 10)," +
                "('Piquerobi', 26)," +
                "('Piquet Carneiro', 6)," +
                "('Piquete', 26)," +
                "('Piracaia', 26)," +
                "('Piracanjuba', 9)," +
                "('Piracema', 11)," +
                "('Piracicaba', 26)," +
                "('Piracuruca', 17)," +
                "('Piraí do Norte', 5)," +
                "('Piraí do Sul', 18)," +
                "('Piraí', 19)," +
                "('Piraju', 26)," +
                "('Pirajuba', 11)," +
                "('Pirajuí', 26)," +
                "('Pirambu', 25)," +
                "('Piranga', 11)," +
                "('Pirangi', 26)," +
                "('Piranguçu', 11)," +
                "('Piranguinho', 11)," +
                "('Piranhas', 2)," +
                "('Piranhas', 9)," +
                "('Pirapemas', 10)," +
                "('Pirapetinga', 11)," +
                "('Pirapó', 23)," +
                "('Pirapora do Bom Jesus', 26)," +
                "('Pirapora', 11)," +
                "('Pirapozinho', 26)," +
                "('Piraquara', 18)," +
                "('Piraquê', 27)," +
                "('Pirassununga', 26)," +
                "('Piratini', 23)," +
                "('Piratininga', 26)," +
                "('Piratuba', 24)," +
                "('Piraúba', 11)," +
                "('Pirenópolis', 9)," +
                "('Pires do Rio', 9)," +
                "('Pires Ferreira', 6)," +
                "('Piripá', 5)," +
                "('Piripiri', 17)," +
                "('Piritiba', 5)," +
                "('Pirpirituba', 15)," +
                "('Pitanga', 18)," +
                "('Pitangueiras', 18)," +
                "('Pitangueiras', 26)," +
                "('Pitangui', 11)," +
                "('Pitimbu', 15)," +
                "('Pium', 27)," +
                "('Piúma', 8)," +
                "('Piumhi', 11)," +
                "('Placas', 14)," +
                "('Plácido de Castro', 1)," +
                "('Planaltina do Paraná', 18)," +
                "('Planaltina', 9)," +
                "('Planaltino', 5)," +
                "('Planalto Alegre', 24)," +
                "('Planalto da Serra', 13)," +
                "('Planalto', 18)," +
                "('Planalto', 23)," +
                "('Planalto', 26)," +
                "('Planalto', 5)," +
                "('Planura', 11)," +
                "('Platina', 26)," +
                "('Poá', 26)," +
                "('Poção de Pedras', 10)," +
                "('Poção', 16)," +
                "('Pocinhos', 15)," +
                "('Poço Branco', 20)," +
                "('Poço Dantas', 15)," +
                "('Poço das Antas', 23)," +
                "('Poço das Trincheiras', 2)," +
                "('Poço de José de Moura', 15)," +
                "('Poço Fundo', 11)," +
                "('Poço Redondo', 25)," +
                "('Poço Verde', 25)," +
                "('Poções', 5)," +
                "('Poconé', 13)," +
                "('Poços de Caldas', 11)," +
                "('Pocrane', 11)," +
                "('Pojuca', 5)," +
                "('Poloni', 26)," +
                "('Pombal', 15)," +
                "('Pombos', 16)," +
                "('Pomerode', 24)," +
                "('Pompéia', 26)," +
                "('Pompéu', 11)," +
                "('Pongaí', 26)," +
                "('Ponta de Pedras', 14)," +
                "('Ponta Grossa', 18)," +
                "('Ponta Porã', 12)," +
                "('Pontal do Araguaia', 13)," +
                "('Pontal do Paraná', 18)," +
                "('Pontal', 26)," +
                "('Pontalina', 9)," +
                "('Pontalinda', 26)," +
                "('Pontão', 23)," +
                "('Ponte Alta do Bom Jesus', 27)," +
                "('Ponte Alta do Norte', 24)," +
                "('Ponte Alta do Tocantins', 27)," +
                "('Ponte Alta', 24)," +
                "('Ponte Branca', 13)," +
                "('Ponte Nova', 11)," +
                "('Ponte Preta', 23)," +
                "('Ponte Serrada', 24)," +
                "('Pontes e Lacerda', 13)," +
                "('Pontes Gestal', 26)," +
                "('Ponto Belo', 8)," +
                "('Ponto Chique', 11)," +
                "('Ponto dos Volantes', 11)," +
                "('Ponto Novo', 5)," +
                "('Populina', 26)," +
                "('Poranga', 6)," +
                "('Porangaba', 26)," +
                "('Porangatu', 9)," +
                "('Porciúncula', 19)," +
                "('Porecatu', 18)," +
                "('Portalegre', 20)," +
                "('Portão', 23)," +
                "('Porteirão', 9)," +
                "('Porteiras', 6)," +
                "('Porteirinha', 11)," +
                "('Portel', 14)," +
                "('Portelândia', 9)," +
                "('Porto Acre', 1)," +
                "('Porto Alegre do Norte', 13)," +
                "('Porto Alegre do Piauí', 17)," +
                "('Porto Alegre do Tocantins', 27)," +
                "('Porto Alegre', 23)," +
                "('Porto Amazonas', 18)," +
                "('Porto Barreiro', 18)," +
                "('Porto Belo', 24)," +
                "('Porto Calvo', 2)," +
                "('Porto da Folha', 25)," +
                "('Porto de Moz', 14)," +
                "('Porto de Pedras', 2)," +
                "('Porto do Mangue', 20)," +
                "('Porto dos Gaúchos', 13)," +
                "('Porto Esperidião', 13)," +
                "('Porto Estrela', 13)," +
                "('Porto Feliz', 26)," +
                "('Porto Ferreira', 26)," +
                "('Porto Firme', 11)," +
                "('Porto Franco', 10)," +
                "('Porto Grande', 4)," +
                "('Porto Lucena', 23)," +
                "('Porto Mauá', 23)," +
                "('Porto Murtinho', 12)," +
                "('Porto Nacional', 27)," +
                "('Porto Real do Colégio', 2)," +
                "('Porto Real', 19)," +
                "('Porto Rico do Maranhão', 10)," +
                "('Porto Rico', 18)," +
                "('Porto Seguro', 5)," +
                "('Porto União', 24)," +
                "('Porto Velho', 21)," +
                "('Porto Vera Cruz', 23)," +
                "('Porto Vitória', 18)," +
                "('Porto Walter', 1)," +
                "('Porto Xavier', 23)," +
                "('Porto', 17)," +
                "('Posse', 9)," +
                "('Poté', 11)," +
                "('Potengi', 6)," +
                "('Potim', 26)," +
                "('Potiraguá', 5)," +
                "('Potirendaba', 26)," +
                "('Potiretama', 6)," +
                "('Pouso Alegre', 11)," +
                "('Pouso Alto', 11)," +
                "('Pouso Novo', 23)," +
                "('Pouso Redondo', 24)," +
                "('Poxoréo', 13)," +
                "('Pracinha', 26)," +
                "('Pracuúba', 4)," +
                "('Prado Ferreira', 18)," +
                "('Prado', 5)," +
                "('Pradópolis', 26)," +
                "('Prados', 11)," +
                "('Praia Grande', 24)," +
                "('Praia Grande', 26)," +
                "('Praia Norte', 27)," +
                "('Prainha', 14)," +
                "('Pranchita', 18)," +
                "('Prata do Piauí', 17)," +
                "('Prata', 11)," +
                "('Prata', 15)," +
                "('Pratânia', 26)," +
                "('Pratápolis', 11)," +
                "('Pratinha', 11)," +
                "('Presidente Alves', 26)," +
                "('Presidente Bernardes', 11)," +
                "('Presidente Bernardes', 26)," +
                "('Presidente Castelo Branco', 18)," +
                "('Presidente Castelo Branco', 24)," +
                "('Presidente Dutra', 10)," +
                "('Presidente Dutra', 5)," +
                "('Presidente Epitácio', 26)," +
                "('Presidente Figueiredo', 3)," +
                "('Presidente Getúlio', 24)," +
                "('Presidente Jânio Quadros', 5)," +
                "('Presidente Juscelino', 10)," +
                "('Presidente Juscelino', 11)," +
                "('Presidente Juscelino', 20)," +
                "('Presidente Kennedy', 27)," +
                "('Presidente Kennedy', 8)," +
                "('Presidente Kubitschek', 11)," +
                "('Presidente Lucena', 23)," +
                "('Presidente Médici', 10)," +
                "('Presidente Médici', 21)," +
                "('Presidente Nereu', 24)," +
                "('Presidente Olegário', 11)," +
                "('Presidente Prudente', 26)," +
                "('Presidente Sarney', 10)," +
                "('Presidente Tancredo Neves', 5)," +
                "('Presidente Vargas', 10)," +
                "('Presidente Venceslau', 26)," +
                "('Primavera de Rondônia', 21)," +
                "('Primavera do Leste', 13)," +
                "('Primavera', 14)," +
                "('Primavera', 16)," +
                "('Primeira Cruz', 10)," +
                "('Primeiro de Maio', 18)," +
                "('Princesa Isabel', 15)," +
                "('Princesa', 24)," +
                "('Professor Jamil', 9)," +
                "('Progresso', 23)," +
                "('Promissão', 26)," +
                "('Propriá', 25)," +
                "('Protásio Alves', 23)," +
                "('Prudente de Morais', 11)," +
                "('Prudentópolis', 18)," +
                "('Pugmil', 27)," +
                "('Pureza', 20)," +
                "('Putinga', 23)," +
                "('Puxinanã', 15)," +
                "('Quadra', 26)," +
                "('Quaraí', 23)," +
                "('Quartel Geral', 11)," +
                "('Quarto Centenário', 18)," +
                "('Quatá', 26)," +
                "('Quatiguá', 18)," +
                "('Quatipuru', 14)," +
                "('Quatis', 19)," +
                "('Quatro Barras', 18)," +
                "('Quatro Irmãos', 23)," +
                "('Quatro Pontes', 18)," +
                "('Quebrangulo', 2)," +
                "('Quedas do Iguaçu', 18)," +
                "('Queimada Nova', 17)," +
                "('Queimadas', 15)," +
                "('Queimadas', 5)," +
                "('Queimados', 19)," +
                "('Queiroz', 26)," +
                "('Queluz', 26)," +
                "('Queluzito', 11)," +
                "('Querência do Norte', 18)," +
                "('Querência', 13)," +
                "('Quevedos', 23)," +
                "('Quijingue', 5)," +
                "('Quilombo', 24)," +
                "('Quinta do Sol', 18)," +
                "('Quintana', 26)," +
                "('Quinze de Novembro', 23)," +
                "('Quipapá', 16)," +
                "('Quirinópolis', 9)," +
                "('Quissamã', 19)," +
                "('Quitandinha', 18)," +
                "('Quiterianópolis', 6)," +
                "('Quixabá', 15)," +
                "('Quixaba', 16)," +
                "('Quixabeira', 5)," +
                "('Quixadá', 6)," +
                "('Quixelô', 6)," +
                "('Quixeramobim', 6)," +
                "('Quixeré', 6)," +
                "('Rafael Fernandes', 20)," +
                "('Rafael Godeiro', 20)," +
                "('Rafael Jambeiro', 5)," +
                "('Rafard', 26)," +
                "('Ramilândia', 18)," +
                "('Rancharia', 26)," +
                "('Rancho Alegre d`Oeste', 18)," +
                "('Rancho Alegre', 18)," +
                "('Rancho Queimado', 24)," +
                "('Raposa', 10)," +
                "('Raposos', 11)," +
                "('Raul Soares', 11)," +
                "('Realeza', 18)," +
                "('Rebouças', 18)," +
                "('Recife', 16)," +
                "('Recreio', 11)," +
                "('Recursolândia', 27)," +
                "('Redenção da Serra', 26)," +
                "('Redenção do Gurguéia', 17)," +
                "('Redenção', 14)," +
                "('Redenção', 6)," +
                "('Redentora', 23)," +
                "('Reduto', 11)," +
                "('Regeneração', 17)," +
                "('Regente Feijó', 26)," +
                "('Reginópolis', 26)," +
                "('Registro', 26)," +
                "('Relvado', 23)," +
                "('Remanso', 5)," +
                "('Remígio', 15)," +
                "('Renascença', 18)," +
                "('Reriutaba', 6)," +
                "('Resende Costa', 11)," +
                "('Resende', 19)," +
                "('Reserva do Cabaçal', 13)," +
                "('Reserva do Iguaçu', 18)," +
                "('Reserva', 18)," +
                "('Resplendor', 11)," +
                "('Ressaquinha', 11)," +
                "('Restinga Seca', 23)," +
                "('Restinga', 26)," +
                "('Retirolândia', 5)," +
                "('Riachão das Neves', 5)," +
                "('Riachão do Bacamarte', 15)," +
                "('Riachão do Dantas', 25)," +
                "('Riachão do Jacuípe', 5)," +
                "('Riachão do Poço', 15)," +
                "('Riachão', 10)," +
                "('Riachão', 15)," +
                "('Riachinho', 11)," +
                "('Riachinho', 27)," +
                "('Riacho da Cruz', 20)," +
                "('Riacho das Almas', 16)," +
                "('Riacho de Santana', 20)," +
                "('Riacho de Santana', 5)," +
                "('Riacho de Santo Antônio', 15)," +
                "('Riacho dos Cavalos', 15)," +
                "('Riacho dos Machados', 11)," +
                "('Riacho Frio', 17)," +
                "('Riachuelo', 20)," +
                "('Riachuelo', 25)," +
                "('Rialma', 9)," +
                "('Rianápolis', 9)," +
                "('Ribamar Fiquene', 10)," +
                "('Ribas do Rio Pardo', 12)," +
                "('Ribeira do Amparo', 5)," +
                "('Ribeira do Piauí', 17)," +
                "('Ribeira do Pombal', 5)," +
                "('Ribeira', 26)," +
                "('Ribeirão Bonito', 26)," +
                "('Ribeirão Branco', 26)," +
                "('Ribeirão Cascalheira', 13)," +
                "('Ribeirão Claro', 18)," +
                "('Ribeirão Corrente', 26)," +
                "('Ribeirão das Neves', 11)," +
                "('Ribeirão do Largo', 5)," +
                "('Ribeirão do Pinhal', 18)," +
                "('Ribeirão do Sul', 26)," +
                "('Ribeirão dos Índios', 26)," +
                "('Ribeirão Grande', 26)," +
                "('Ribeirão Pires', 26)," +
                "('Ribeirão Preto', 26)," +
                "('Ribeirão Vermelho', 11)," +
                "('Ribeirão', 16)," +
                "('Ribeirãozinho', 13)," +
                "('Ribeiro Gonçalves', 17)," +
                "('Ribeirópolis', 25)," +
                "('Rifaina', 26)," +
                "('Rincão', 26)," +
                "('Rinópolis', 26)," +
                "('Rio Acima', 11)," +
                "('Rio Azul', 18)," +
                "('Rio Bananal', 8)," +
                "('Rio Bom', 18)," +
                "('Rio Bonito do Iguaçu', 18)," +
                "('Rio Bonito', 19)," +
                "('Rio Branco do Ivaí', 18)," +
                "('Rio Branco do Sul', 18)," +
                "('Rio Branco', 1)," +
                "('Rio Branco', 13)," +
                "('Rio Brilhante', 12)," +
                "('Rio Casca', 11)," +
                "('Rio Claro', 19)," +
                "('Rio Claro', 26)," +
                "('Rio Crespo', 21)," +
                "('Rio da Conceição', 27)," +
                "('Rio das Antas', 24)," +
                "('Rio das Flores', 19)," +
                "('Rio das Ostras', 19)," +
                "('Rio das Pedras', 26)," +
                "('Rio de Contas', 5)," +
                "('Rio de Janeiro', 19)," +
                "('Rio do Antônio', 5)," +
                "('Rio do Campo', 24)," +
                "('Rio do Fogo', 20)," +
                "('Rio do Oeste', 24)," +
                "('Rio do Pires', 5)," +
                "('Rio do Prado', 11)," +
                "('Rio do Sul', 24)," +
                "('Rio Doce', 11)," +
                "('Rio dos Bois', 27)," +
                "('Rio dos Cedros', 24)," +
                "('Rio dos Índios', 23)," +
                "('Rio Espera', 11)," +
                "('Rio Formoso', 16)," +
                "('Rio Fortuna', 24)," +
                "('Rio Grande da Serra', 26)," +
                "('Rio Grande do Piauí', 17)," +
                "('Rio Grande', 23)," +
                "('Rio Largo', 2)," +
                "('Rio Manso', 11)," +
                "('Rio Maria', 14)," +
                "('Rio Negrinho', 24)," +
                "('Rio Negro', 12)," +
                "('Rio Negro', 18)," +
                "('Rio Novo do Sul', 8)," +
                "('Rio Novo', 11)," +
                "('Rio Paranaíba', 11)," +
                "('Rio Pardo de Minas', 11)," +
                "('Rio Pardo', 23)," +
                "('Rio Piracicaba', 11)," +
                "('Rio Pomba', 11)," +
                "('Rio Preto da Eva', 3)," +
                "('Rio Preto', 11)," +
                "('Rio Quente', 9)," +
                "('Rio Real', 5)," +
                "('Rio Rufino', 24)," +
                "('Rio Sono', 27)," +
                "('Rio Tinto', 15)," +
                "('Rio Verde de Mato Grosso', 12)," +
                "('Rio Verde', 9)," +
                "('Rio Vermelho', 11)," +
                "('Riolândia', 26)," +
                "('Riozinho', 23)," +
                "('Riqueza', 24)," +
                "('Ritápolis', 11)," +
                "('Riversul', 26)," +
                "('Roca Sales', 23)," +
                "('Rochedo de Minas', 11)," +
                "('Rochedo', 12)," +
                "('Rodeio Bonito', 23)," +
                "('Rodeio', 24)," +
                "('Rodeiro', 11)," +
                "('Rodelas', 5)," +
                "('Rodolfo Fernandes', 20)," +
                "('Rodrigues Alves', 1)," +
                "('Rolador', 23)," +
                "('Rolândia', 18)," +
                "('Rolante', 23)," +
                "('Rolim de Moura', 21)," +
                "('Romaria', 11)," +
                "('Romelândia', 24)," +
                "('Roncador', 18)," +
                "('Ronda Alta', 23)," +
                "('Rondinha', 23)," +
                "('Rondolândia', 13)," +
                "('Rondon do Pará', 14)," +
                "('Rondon', 18)," +
                "('Rondonópolis', 13)," +
                "('Roque Gonzales', 23)," +
                "('Rorainópolis', 22)," +
                "('Rosana', 26)," +
                "('Rosário da Limeira', 11)," +
                "('Rosário do Catete', 25)," +
                "('Rosário do Ivaí', 18)," +
                "('Rosário do Sul', 23)," +
                "('Rosário Oeste', 13)," +
                "('Rosário', 10)," +
                "('Roseira', 26)," +
                "('Roteiro', 2)," +
                "('Rubelita', 11)," +
                "('Rubiácea', 26)," +
                "('Rubiataba', 9)," +
                "('Rubim', 11)," +
                "('Rubinéia', 26)," +
                "('Rurópolis', 14)," +
                "('Russas', 6)," +
                "('Ruy Barbosa', 20)," +
                "('Ruy Barbosa', 5)," +
                "('Sabará', 11)," +
                "('Sabáudia', 18)," +
                "('Sabino', 26)," +
                "('Sabinópolis', 11)," +
                "('Saboeiro', 6)," +
                "('Sacramento', 11)," +
                "('Sagrada Família', 23)," +
                "('Sagres', 26)," +
                "('Sairé', 16)," +
                "('Saldanha Marinho', 23)," +
                "('Sales Oliveira', 26)," +
                "('Sales', 26)," +
                "('Salesópolis', 26)," +
                "('Salete', 24)," +
                "('Salgadinho', 15)," +
                "('Salgadinho', 16)," +
                "('Salgado de São Félix', 15)," +
                "('Salgado Filho', 18)," +
                "('Salgado', 25)," +
                "('Salgueiro', 16)," +
                "('Salinas da Margarida', 5)," +
                "('Salinas', 11)," +
                "('Salinópolis', 14)," +
                "('Salitre', 6)," +
                "('Salmourão', 26)," +
                "('Saloá', 16)," +
                "('Saltinho', 24)," +
                "('Saltinho', 26)," +
                "('Salto da Divisa', 11)," +
                "('Salto de Pirapora', 26)," +
                "('Salto do Céu', 13)," +
                "('Salto do Itararé', 18)," +
                "('Salto do Jacuí', 23)," +
                "('Salto do Lontra', 18)," +
                "('Salto Grande', 26)," +
                "('Salto Veloso', 24)," +
                "('Salto', 26)," +
                "('Salvador das Missões', 23)," +
                "('Salvador do Sul', 23)," +
                "('Salvador', 5)," +
                "('Salvaterra', 14)," +
                "('Sambaíba', 10)," +
                "('Sampaio', 27)," +
                "('Sananduva', 23)," +
                "('Sanclerlândia', 9)," +
                "('Sandolândia', 27)," +
                "('Sandovalina', 26)," +
                "('Sangão', 24)," +
                "('Sanharó', 16)," +
                "('Santa Adélia', 26)," +
                "('Santa Albertina', 26)," +
                "('Santa Amélia', 18)," +
                "('Santa Bárbara d`Oeste', 26)," +
                "('Santa Bárbara de Goiás', 9)," +
                "('Santa Bárbara do Leste', 11)," +
                "('Santa Bárbara do Monte Verde', 11)," +
                "('Santa Bárbara do Pará', 14)," +
                "('Santa Bárbara do Sul', 23)," +
                "('Santa Bárbara do Tugúrio', 11)," +
                "('Santa Bárbara', 11)," +
                "('Santa Bárbara', 5)," +
                "('Santa Branca', 26)," +
                "('Santa Brígida', 5)," +
                "('Santa Carmem', 13)," +
                "('Santa Cecília do Pavão', 18)," +
                "('Santa Cecília do Sul', 23)," +
                "('Santa Cecília', 15)," +
                "('Santa Cecília', 24)," +
                "('Santa Clara d`Oeste', 26)," +
                "('Santa Clara do Sul', 23)," +
                "('Santa Cruz Cabrália', 5)," +
                "('Santa Cruz da Baixa Verde', 16)," +
                "('Santa Cruz da Conceição', 26)," +
                "('Santa Cruz da Esperança', 26)," +
                "('Santa Cruz da Vitória', 5)," +
                "('Santa Cruz das Palmeiras', 26)," +
                "('Santa Cruz de Goiás', 9)," +
                "('Santa Cruz de Minas', 11)," +
                "('Santa Cruz de Monte Castelo', 18)," +
                "('Santa Cruz de Salinas', 11)," +
                "('Santa Cruz do Arari', 14)," +
                "('Santa Cruz do Capibaribe', 16)," +
                "('Santa Cruz do Escalvado', 11)," +
                "('Santa Cruz do Piauí', 17)," +
                "('Santa Cruz do Rio Pardo', 26)," +
                "('Santa Cruz do Sul', 23)," +
                "('Santa Cruz do Xingu', 13)," +
                "('Santa Cruz dos Milagres', 17)," +
                "('Santa Cruz', 15)," +
                "('Santa Cruz', 16)," +
                "('Santa Cruz', 20)," +
                "('Santa Efigênia de Minas', 11)," +
                "('Santa Ernestina', 26)," +
                "('Santa Fé de Goiás', 9)," +
                "('Santa Fé de Minas', 11)," +
                "('Santa Fé do Araguaia', 27)," +
                "('Santa Fé do Sul', 26)," +
                "('Santa Fé', 18)," +
                "('Santa Filomena do Maranhão', 10)," +
                "('Santa Filomena', 16)," +
                "('Santa Filomena', 17)," +
                "('Santa Gertrudes', 26)," +
                "('Santa Helena de Goiás', 9)," +
                "('Santa Helena de Minas', 11)," +
                "('Santa Helena', 10)," +
                "('Santa Helena', 15)," +
                "('Santa Helena', 18)," +
                "('Santa Helena', 24)," +
                "('Santa Inês', 10)," +
                "('Santa Inês', 15)," +
                "('Santa Inês', 18)," +
                "('Santa Inês', 5)," +
                "('Santa Isabel do Ivaí', 18)," +
                "('Santa Isabel do Pará', 14)," +
                "('Santa Isabel do Rio Negro', 3)," +
                "('Santa Isabel', 26)," +
                "('Santa Isabel', 9)," +
                "('Santa Izabel do Oeste', 18)," +
                "('Santa Juliana', 11)," +
                "('Santa Leopoldina', 8)," +
                "('Santa Lúcia', 18)," +
                "('Santa Lúcia', 26)," +
                "('Santa Luz', 17)," +
                "('Santa Luzia d`Oeste', 21)," +
                "('Santa Luzia do Itanhy', 25)," +
                "('Santa Luzia do Norte', 2)," +
                "('Santa Luzia do Pará', 14)," +
                "('Santa Luzia do Paruá', 10)," +
                "('Santa Luzia', 10)," +
                "('Santa Luzia', 11)," +
                "('Santa Luzia', 15)," +
                "('Santa Luzia', 5)," +
                "('Santa Margarida do Sul', 23)," +
                "('Santa Margarida', 11)," +
                "('Santa Maria da Boa Vista', 16)," +
                "('Santa Maria da Serra', 26)," +
                "('Santa Maria da Vitória', 5)," +
                "('Santa Maria das Barreiras', 14)," +
                "('Santa Maria de Itabira', 11)," +
                "('Santa Maria de Jetibá', 8)," +
                "('Santa Maria do Cambucá', 16)," +
                "('Santa Maria do Herval', 23)," +
                "('Santa Maria do Oeste', 18)," +
                "('Santa Maria do Pará', 14)," +
                "('Santa Maria do Salto', 11)," +
                "('Santa Maria do Suaçuí', 11)," +
                "('Santa Maria do Tocantins', 27)," +
                "('Santa Maria Madalena', 19)," +
                "('Santa Maria', 20)," +
                "('Santa Maria', 23)," +
                "('Santa Mariana', 18)," +
                "('Santa Mercedes', 26)," +
                "('Santa Mônica', 18)," +
                "('Santa Quitéria do Maranhão', 10)," +
                "('Santa Quitéria', 6)," +
                "('Santa Rita d`Oeste', 26)," +
                "('Santa Rita de Caldas', 11)," +
                "('Santa Rita de Cássia', 5)," +
                "('Santa Rita de Ibitipoca', 11)," +
                "('Santa Rita de Jacutinga', 11)," +
                "('Santa Rita de Minas', 11)," +
                "('Santa Rita do Araguaia', 9)," +
                "('Santa Rita do Itueto', 11)," +
                "('Santa Rita do Novo Destino', 9)," +
                "('Santa Rita do Pardo', 12)," +
                "('Santa Rita do Passa Quatro', 26)," +
                "('Santa Rita do Sapucaí', 11)," +
                "('Santa Rita do Tocantins', 27)," +
                "('Santa Rita do Trivelato', 13)," +
                "('Santa Rita', 10)," +
                "('Santa Rita', 15)," +
                "('Santa Rosa da Serra', 11)," +
                "('Santa Rosa de Goiás', 9)," +
                "('Santa Rosa de Lima', 24)," +
                "('Santa Rosa de Lima', 25)," +
                "('Santa Rosa de Viterbo', 26)," +
                "('Santa Rosa do Piauí', 17)," +
                "('Santa Rosa do Purus', 1)," +
                "('Santa Rosa do Sul', 24)," +
                "('Santa Rosa do Tocantins', 27)," +
                "('Santa Rosa', 23)," +
                "('Santa Salete', 26)," +
                "('Santa Teresa', 8)," +
                "('Santa Teresinha', 15)," +
                "('Santa Teresinha', 5)," +
                "('Santa Tereza de Goiás', 9)," +
                "('Santa Tereza do Oeste', 18)," +
                "('Santa Tereza do Tocantins', 27)," +
                "('Santa Tereza', 23)," +
                "('Santa Terezinha de Goiás', 9)," +
                "('Santa Terezinha de Itaipu', 18)," +
                "('Santa Terezinha do Progresso', 24)," +
                "('Santa Terezinha do Tocantins', 27)," +
                "('Santa Terezinha', 13)," +
                "('Santa Terezinha', 16)," +
                "('Santa Terezinha', 24)," +
                "('Santa Vitória do Palmar', 23)," +
                "('Santa Vitória', 11)," +
                "('Santaluz', 5)," +
                "('Santana da Boa Vista', 23)," +
                "('Santana da Ponte Pensa', 26)," +
                "('Santana da Vargem', 11)," +
                "('Santana de Cataguases', 11)," +
                "('Santana de Mangueira', 15)," +
                "('Santana de Parnaíba', 26)," +
                "('Santana de Pirapama', 11)," +
                "('Santana do Acaraú', 6)," +
                "('Santana do Araguaia', 14)," +
                "('Santana do Cariri', 6)," +
                "('Santana do Deserto', 11)," +
                "('Santana do Garambéu', 11)," +
                "('Santana do Ipanema', 2)," +
                "('Santana do Itararé', 18)," +
                "('Santana do Jacaré', 11)," +
                "('Santana do Livramento', 23)," +
                "('Santana do Manhuaçu', 11)," +
                "('Santana do Maranhão', 10)," +
                "('Santana do Matos', 20)," +
                "('Santana do Mundaú', 2)," +
                "('Santana do Paraíso', 11)," +
                "('Santana do Piauí', 17)," +
                "('Santana do Riacho', 11)," +
                "('Santana do São Francisco', 25)," +
                "('Santana do Seridó', 20)," +
                "('Santana dos Garrotes', 15)," +
                "('Santana dos Montes', 11)," +
                "('Santana', 4)," +
                "('Santana', 5)," +
                "('Santanópolis', 5)," +
                "('Santarém Novo', 14)," +
                "('Santarém', 14)," +
                "('Santarém', 15)," +
                "('Santiago do Sul', 24)," +
                "('Santiago', 23)," +
                "('Santo Afonso', 13)," +
                "('Santo Amaro da Imperatriz', 24)," +
                "('Santo Amaro das Brotas', 25)," +
                "('Santo Amaro do Maranhão', 10)," +
                "('Santo Amaro', 5)," +
                "('Santo Anastácio', 26)," +
                "('Santo André', 15)," +
                "('Santo André', 26)," +
                "('Santo Ângelo', 23)," +
                "('Santo Antônio da Alegria', 26)," +
                "('Santo Antônio da Barra', 9)," +
                "('Santo Antônio da Patrulha', 23)," +
                "('Santo Antônio da Platina', 18)," +
                "('Santo Antônio das Missões', 23)," +
                "('Santo Antônio de Goiás', 9)," +
                "('Santo Antônio de Jesus', 5)," +
                "('Santo Antônio de Lisboa', 17)," +
                "('Santo Antônio de Pádua', 19)," +
                "('Santo Antônio de Posse', 26)," +
                "('Santo Antônio do Amparo', 11)," +
                "('Santo Antônio do Aracanguá', 26)," +
                "('Santo Antônio do Aventureiro', 11)," +
                "('Santo Antônio do Caiuá', 18)," +
                "('Santo Antônio do Descoberto', 9)," +
                "('Santo Antônio do Grama', 11)," +
                "('Santo Antônio do Içá', 3)," +
                "('Santo Antônio do Itambé', 11)," +
                "('Santo Antônio do Jacinto', 11)," +
                "('Santo Antônio do Jardim', 26)," +
                "('Santo Antônio do Leste', 13)," +
                "('Santo Antônio do Leverger', 13)," +
                "('Santo Antônio do Monte', 11)," +
                "('Santo Antônio do Palma', 23)," +
                "('Santo Antônio do Paraíso', 18)," +
                "('Santo Antônio do Pinhal', 26)," +
                "('Santo Antônio do Planalto', 23)," +
                "('Santo Antônio do Retiro', 11)," +
                "('Santo Antônio do Rio Abaixo', 11)," +
                "('Santo Antônio do Sudoeste', 18)," +
                "('Santo Antônio do Tauá', 14)," +
                "('Santo Antônio dos Lopes', 10)," +
                "('Santo Antônio dos Milagres', 17)," +
                "('Santo Antônio', 20)," +
                "('Santo Augusto', 23)," +
                "('Santo Cristo', 23)," +
                "('Santo Estêvão', 5)," +
                "('Santo Expedito do Sul', 23)," +
                "('Santo Expedito', 26)," +
                "('Santo Hipólito', 11)," +
                "('Santo Inácio do Piauí', 17)," +
                "('Santo Inácio', 18)," +
                "('Santópolis do Aguapeí', 26)," +
                "('Santos Dumont', 11)," +
                "('Santos', 26)," +
                "('São Benedito do Rio Preto', 10)," +
                "('São Benedito do Sul', 16)," +
                "('São Benedito', 6)," +
                "('São Bentinho', 15)," +
                "('São Bento Abade', 11)," +
                "('São Bento do Norte', 20)," +
                "('São Bento do Sapucaí', 26)," +
                "('São Bento do Sul', 24)," +
                "('São Bento do Tocantins', 27)," +
                "('São Bento do Trairí', 20)," +
                "('São Bento do Una', 16)," +
                "('São Bento', 10)," +
                "('São Bento', 15)," +
                "('São Bernardino', 24)," +
                "('São Bernardo do Campo', 26)," +
                "('São Bernardo', 10)," +
                "('São Bonifácio', 24)," +
                "('São Borja', 23)," +
                "('São Brás do Suaçuí', 11)," +
                "('São Brás', 2)," +
                "('São Braz do Piauí', 17)," +
                "('São Caetano de Odivelas', 14)," +
                "('São Caetano do Sul', 26)," +
                "('São Caitano', 16)," +
                "('São Carlos do Ivaí', 18)," +
                "('São Carlos', 24)," +
                "('São Carlos', 26)," +
                "('São Cristovão do Sul', 24)," +
                "('São Cristóvão', 25)," +
                "('São Desidério', 5)," +
                "('São Domingos das Dores', 11)," +
                "('São Domingos de Pombal', 15)," +
                "('São Domingos do Araguaia', 14)," +
                "('São Domingos do Azeitão', 10)," +
                "('São Domingos do Capim', 14)," +
                "('São Domingos do Cariri', 15)," +
                "('São Domingos do Maranhão', 10)," +
                "('São Domingos do Norte', 8)," +
                "('São Domingos do Prata', 11)," +
                "('São Domingos do Sul', 23)," +
                "('São Domingos', 24)," +
                "('São Domingos', 25)," +
                "('São Domingos', 5)," +
                "('São Domingos', 9)," +
                "('São Felipe d`Oeste', 21)," +
                "('São Felipe', 5)," +
                "('São Félix de Balsas', 10)," +
                "('São Félix de Minas', 11)," +
                "('São Félix do Araguaia', 13)," +
                "('São Félix do Coribe', 5)," +
                "('São Félix do Piauí', 17)," +
                "('São Félix do Tocantins', 27)," +
                "('São Félix do Xingu', 14)," +
                "('São Félix', 5)," +
                "('São Fernando', 20)," +
                "('São Fidélis', 19)," +
                "('São Francisco de Assis do Piauí', 17)," +
                "('São Francisco de Assis', 23)," +
                "('São Francisco de Goiás', 9)," +
                "('São Francisco de Itabapoana', 19)," +
                "('São Francisco de Paula', 11)," +
                "('São Francisco de Paula', 23)," +
                "('São Francisco de Sales', 11)," +
                "('São Francisco do Brejão', 10)," +
                "('São Francisco do Conde', 5)," +
                "('São Francisco do Glória', 11)," +
                "('São Francisco do Guaporé', 21)," +
                "('São Francisco do Maranhão', 10)," +
                "('São Francisco do Oeste', 20)," +
                "('São Francisco do Pará', 14)," +
                "('São Francisco do Piauí', 17)," +
                "('São Francisco do Sul', 24)," +
                "('São Francisco', 11)," +
                "('São Francisco', 15)," +
                "('São Francisco', 25)," +
                "('São Francisco', 26)," +
                "('São Gabriel da Cachoeira', 3)," +
                "('São Gabriel da Palha', 8)," +
                "('São Gabriel do Oeste', 12)," +
                "('São Gabriel', 23)," +
                "('São Gabriel', 5)," +
                "('São Geraldo da Piedade', 11)," +
                "('São Geraldo do Araguaia', 14)," +
                "('São Geraldo do Baixio', 11)," +
                "('São Geraldo', 11)," +
                "('São Gonçalo do Abaeté', 11)," +
                "('São Gonçalo do Amarante', 20)," +
                "('São Gonçalo do Amarante', 6)," +
                "('São Gonçalo do Gurguéia', 17)," +
                "('São Gonçalo do Pará', 11)," +
                "('São Gonçalo do Piauí', 17)," +
                "('São Gonçalo do Rio Abaixo', 11)," +
                "('São Gonçalo do Rio Preto', 11)," +
                "('São Gonçalo do Sapucaí', 11)," +
                "('São Gonçalo dos Campos', 5)," +
                "('São Gonçalo', 19)," +
                "('São Gotardo', 11)," +
                "('São Jerônimo da Serra', 18)," +
                "('São Jerônimo', 23)," +
                "('São João Batista do Glória', 11)," +
                "('São João Batista', 10)," +
                "('São João Batista', 24)," +
                "('São João d`Aliança', 9)," +
                "('São João da Baliza', 22)," +
                "('São João da Barra', 19)," +
                "('São João da Boa Vista', 26)," +
                "('São João da Canabrava', 17)," +
                "('São João da Fronteira', 17)," +
                "('São João da Lagoa', 11)," +
                "('São João da Mata', 11)," +
                "('São João da Paraúna', 9)," +
                "('São João da Ponta', 14)," +
                "('São João da Ponte', 11)," +
                "('São João da Serra', 17)," +
                "('São João da Urtiga', 23)," +
                "('São João da Varjota', 17)," +
                "('São João das Duas Pontes', 26)," +
                "('São João das Missões', 11)," +
                "('São João de Iracema', 26)," +
                "('São João de Meriti', 19)," +
                "('São João de Pirabas', 14)," +
                "('São João del Rei', 11)," +
                "('São João do Araguaia', 14)," +
                "('São João do Arraial', 17)," +
                "('São João do Caiuá', 18)," +
                "('São João do Cariri', 15)," +
                "('São João do Carú', 10)," +
                "('São João do Itaperiú', 24)," +
                "('São João do Ivaí', 18)," +
                "('São João do Jaguaribe', 6)," +
                "('São João do Manhuaçu', 11)," +
                "('São João do Manteninha', 11)," +
                "('São João do Oeste', 24)," +
                "('São João do Oriente', 11)," +
                "('São João do Pacuí', 11)," +
                "('São João do Paraíso', 10)," +
                "('São João do Paraíso', 11)," +
                "('São João do Pau d`Alho', 26)," +
                "('São João do Piauí', 17)," +
                "('São João do Polêsine', 23)," +
                "('São João do Rio do Peixe', 15)," +
                "('São João do Sabugi', 20)," +
                "('São João do Soter', 10)," +
                "('São João do Sul', 24)," +
                "('São João do Tigre', 15)," +
                "('São João do Triunfo', 18)," +
                "('São João dos Patos', 10)," +
                "('São João Evangelista', 11)," +
                "('São João Nepomuceno', 11)," +
                "('São João', 16)," +
                "('São João', 18)," +
                "('São Joaquim da Barra', 26)," +
                "('São Joaquim de Bicas', 11)," +
                "('São Joaquim do Monte', 16)," +
                "('São Joaquim', 24)," +
                "('São Jorge d`Oeste', 18)," +
                "('São Jorge do Ivaí', 18)," +
                "('São Jorge do Patrocínio', 18)," +
                "('São Jorge', 23)," +
                "('São José da Barra', 11)," +
                "('São José da Bela Vista', 26)," +
                "('São José da Boa Vista', 18)," +
                "('São José da Coroa Grande', 16)," +
                "('São José da Lagoa Tapada', 15)," +
                "('São José da Laje', 2)," +
                "('São José da Lapa', 11)," +
                "('São José da Safira', 11)," +
                "('São José da Tapera', 2)," +
                "('São José da Varginha', 11)," +
                "('São José da Vitória', 5)," +
                "('São José das Missões', 23)," +
                "('São José das Palmeiras', 18)," +
                "('São José de Caiana', 15)," +
                "('São José de Espinharas', 15)," +
                "('São José de Mipibu', 20)," +
                "('São José de Piranhas', 15)," +
                "('São José de Princesa', 15)," +
                "('São José de Ribamar', 10)," +
                "('São José de Ubá', 19)," +
                "('São José do Alegre', 11)," +
                "('São José do Barreiro', 26)," +
                "('São José do Belmonte', 16)," +
                "('São José do Bonfim', 15)," +
                "('São José do Brejo do Cruz', 15)," +
                "('São José do Calçado', 8)," +
                "('São José do Campestre', 20)," +
                "('São José do Cedro', 24)," +
                "('São José do Cerrito', 24)," +
                "('São José do Divino', 11)," +
                "('São José do Divino', 17)," +
                "('São José do Egito', 16)," +
                "('São José do Goiabal', 11)," +
                "('São José do Herval', 23)," +
                "('São José do Hortêncio', 23)," +
                "('São José do Inhacorá', 23)," +
                "('São José do Jacuípe', 5)," +
                "('São José do Jacuri', 11)," +
                "('São José do Mantimento', 11)," +
                "('São José do Norte', 23)," +
                "('São José do Ouro', 23)," +
                "('São José do Peixe', 17)," +
                "('São José do Piauí', 17)," +
                "('São José do Povo', 13)," +
                "('São José do Rio Claro', 13)," +
                "('São José do Rio Pardo', 26)," +
                "('São José do Rio Preto', 26)," +
                "('São José do Sabugi', 15)," +
                "('São José do Seridó', 20)," +
                "('São José do Sul', 23)," +
                "('São José do Vale do Rio Pret', 19)," +
                "('São José do Xingu', 13)," +
                "('São José dos Ausentes', 23)," +
                "('São José dos Basílios', 10)," +
                "('São José dos Campos', 26)," +
                "('São José dos Cordeiros', 15)," +
                "('São José dos Pinhais', 18)," +
                "('São José dos Quatro Marcos', 13)," +
                "('São José dos Ramos', 15)," +
                "('São José', 24)," +
                "('São Julião', 17)," +
                "('São Leopoldo', 23)," +
                "('São Lourenço da Mata', 16)," +
                "('São Lourenço da Serra', 26)," +
                "('São Lourenço do Oeste', 24)," +
                "('São Lourenço do Piauí', 17)," +
                "('São Lourenço do Sul', 23)," +
                "('São Lourenço', 11)," +
                "('São Ludgero', 24)," +
                "('São Luís de Montes Belos', 9)," +
                "('São Luís do Curu', 6)," +
                "('São Luís do Paraitinga', 26)," +
                "('São Luis do Piauí', 17)," +
                "('São Luís do Quitunde', 2)," +
                "('São Luís Gonzaga do Maranhão', 10)," +
                "('São Luís', 10)," +
                "('São Luíz do Norte', 9)," +
                "('São Luiz Gonzaga', 23)," +
                "('São Luiz', 22)," +
                "('São Mamede', 15)," +
                "('São Manoel do Paraná', 18)," +
                "('São Manuel', 26)," +
                "('São Marcos', 23)," +
                "('São Martinho da Serra', 23)," +
                "('São Martinho', 23)," +
                "('São Martinho', 24)," +
                "('São Mateus do Maranhão', 10)," +
                "('São Mateus do Sul', 18)," +
                "('São Mateus', 8)," +
                "('São Miguel Arcanjo', 26)," +
                "('São Miguel da Baixa Grande', 17)," +
                "('São Miguel da Boa Vista', 24)," +
                "('São Miguel das Matas', 5)," +
                "('São Miguel das Missões', 23)," +
                "('São Miguel de Taipu', 15)," +
                "('São Miguel do Aleixo', 25)," +
                "('São Miguel do Anta', 11)," +
                "('São Miguel do Araguaia', 9)," +
                "('São Miguel do Fidalgo', 17)," +
                "('São Miguel do Gostoso', 20)," +
                "('São Miguel do Guamá', 14)," +
                "('São Miguel do Guaporé', 21)," +
                "('São Miguel do Iguaçu', 18)," +
                "('São Miguel do Oeste', 24)," +
                "('São Miguel do Passa Quatro', 9)," +
                "('São Miguel do Tapuio', 17)," +
                "('São Miguel do Tocantins', 27)," +
                "('São Miguel dos Campos', 2)," +
                "('São Miguel dos Milagres', 2)," +
                "('São Miguel', 20)," +
                "('São Nicolau', 23)," +
                "('São Patrício', 9)," +
                "('São Paulo das Missões', 23)," +
                "('São Paulo de Olivença', 3)," +
                "('São Paulo do Potengi', 20)," +
                "('São Paulo', 26)," +
                "('São Pedro da Água Branca', 10)," +
                "('São Pedro da Aldeia', 19)," +
                "('São Pedro da Cipa', 13)," +
                "('São Pedro da Serra', 23)," +
                "('São Pedro da União', 11)," +
                "('São Pedro das Missões', 23)," +
                "('São Pedro de Alcântara', 24)," +
                "('São Pedro do Butiá', 23)," +
                "('São Pedro do Iguaçu', 18)," +
                "('São Pedro do Ivaí', 18)," +
                "('São Pedro do Paraná', 18)," +
                "('São Pedro do Piauí', 17)," +
                "('São Pedro do Suaçuí', 11)," +
                "('São Pedro do Sul', 23)," +
                "('São Pedro do Turvo', 26)," +
                "('São Pedro dos Crentes', 10)," +
                "('São Pedro dos Ferros', 11)," +
                "('São Pedro', 20)," +
                "('São Pedro', 26)," +
                "('São Rafael', 20)," +
                "('São Raimundo das Mangabeiras', 10)," +
                "('São Raimundo do Doca Bezerra', 10)," +
                "('São Raimundo Nonato', 17)," +
                "('São Roberto', 10)," +
                "('São Romão', 11)," +
                "('São Roque de Minas', 11)," +
                "('São Roque do Canaã', 8)," +
                "('São Roque', 26)," +
                "('São Salvador do Tocantins', 27)," +
                "('São Sebastião da Amoreira', 18)," +
                "('São Sebastião da Bela Vista', 11)," +
                "('São Sebastião da Boa Vista', 14)," +
                "('São Sebastião da Grama', 26)," +
                "('São Sebastião da Vargem Alegre', 11)," +
                "('São Sebastião de Lagoa de Roça', 15)," +
                "('São Sebastião do Alto', 19)," +
                "('São Sebastião do Anta', 11)," +
                "('São Sebastião do Caí', 23)," +
                "('São Sebastião do Maranhão', 11)," +
                "('São Sebastião do Oeste', 11)," +
                "('São Sebastião do Paraíso', 11)," +
                "('São Sebastião do Passé', 5)," +
                "('São Sebastião do Rio Preto', 11)," +
                "('São Sebastião do Rio Verde', 11)," +
                "('São Sebastião do Tocantins', 27)," +
                "('São Sebastião do Uatumã', 3)," +
                "('São Sebastião do Umbuzeiro', 15)," +
                "('São Sebastião', 2)," +
                "('São Sebastião', 26)," +
                "('São Sepé', 23)," +
                "('São Simão', 26)," +
                "('São Simão', 9)," +
                "('São Thomé das Letras', 11)," +
                "('São Tiago', 11)," +
                "('São Tomás de Aquino', 11)," +
                "('São Tomé', 18)," +
                "('São Tomé', 20)," +
                "('São Valentim do Sul', 23)," +
                "('São Valentim', 23)," +
                "('São Valério da Natividade', 27)," +
                "('São Valério do Sul', 23)," +
                "('São Vendelino', 23)," +
                "('São Vicente de Minas', 11)," +
                "('São Vicente do Sul', 23)," +
                "('São Vicente Ferrer', 10)," +
                "('São Vicente Ferrer', 16)," +
                "('São Vicente', 20)," +
                "('São Vicente', 26)," +
                "('Sapé', 15)," +
                "('Sapeaçu', 5)," +
                "('Sapezal', 13)," +
                "('Sapiranga', 23)," +
                "('Sapopema', 18)," +
                "('Sapucaia do Sul', 23)," +
                "('Sapucaia', 14)," +
                "('Sapucaia', 19)," +
                "('Sapucaí-Mirim', 11)," +
                "('Saquarema', 19)," +
                "('Sarandi', 18)," +
                "('Sarandi', 23)," +
                "('Sarapuí', 26)," +
                "('Sardoá', 11)," +
                "('Sarutaiá', 26)," +
                "('Sarzedo', 11)," +
                "('Sátiro Dias', 5)," +
                "('Satuba', 2)," +
                "('Satubinha', 10)," +
                "('Saubara', 5)," +
                "('Saudade do Iguaçu', 18)," +
                "('Saudades', 24)," +
                "('Saúde', 5)," +
                "('Schroeder', 24)," +
                "('Seabra', 5)," +
                "('Seara', 24)," +
                "('Sebastianópolis do Sul', 26)," +
                "('Sebastião Barros', 17)," +
                "('Sebastião Laranjeiras', 5)," +
                "('Sebastião Leal', 17)," +
                "('Seberi', 23)," +
                "('Sede Nova', 23)," +
                "('Segredo', 23)," +
                "('Selbach', 23)," +
                "('Selvíria', 12)," +
                "('Sem-Peixe', 11)," +
                "('Sena Madureira', 1)," +
                "('Senador Alexandre Costa', 10)," +
                "('Senador Amaral', 11)," +
                "('Senador Canedo', 9)," +
                "('Senador Cortes', 11)," +
                "('Senador Elói de Souza', 20)," +
                "('Senador Firmino', 11)," +
                "('Senador Georgino Avelino', 20)," +
                "('Senador Guiomard', 1)," +
                "('Senador José Bento', 11)," +
                "('Senador José Porfírio', 14)," +
                "('Senador La Rocque', 10)," +
                "('Senador Modestino Gonçalves', 11)," +
                "('Senador Pompeu', 6)," +
                "('Senador Rui Palmeira', 2)," +
                "('Senador Sá', 6)," +
                "('Senador Salgado Filho', 23)," +
                "('Sengés', 18)," +
                "('Senhor do Bonfim', 5)," +
                "('Senhora de Oliveira', 11)," +
                "('Senhora do Porto', 11)," +
                "('Senhora dos Remédios', 11)," +
                "('Sentinela do Sul', 23)," +
                "('Sento Sé', 5)," +
                "('Serafina Corrêa', 23)," +
                "('Sericita', 11)," +
                "('Seridó', 15)," +
                "('Seringueiras', 21)," +
                "('Sério', 23)," +
                "('Seritinga', 11)," +
                "('Seropédica', 19)," +
                "('Serra Alta', 24)," +
                "('Serra Azul de Minas', 11)," +
                "('Serra Azul', 26)," +
                "('Serra Branca', 15)," +
                "('Serra da Raiz', 15)," +
                "('Serra da Saudade', 11)," +
                "('Serra de São Bento', 20)," +
                "('Serra do Mel', 20)," +
                "('Serra do Navio', 4)," +
                "('Serra do Ramalho', 5)," +
                "('Serra do Salitre', 11)," +
                "('Serra dos Aimorés', 11)," +
                "('Serra Dourada', 5)," +
                "('Serra Grande', 15)," +
                "('Serra Negra do Norte', 20)," +
                "('Serra Negra', 26)," +
                "('Serra Nova Dourada', 13)," +
                "('Serra Preta', 5)," +
                "('Serra Redonda', 15)," +
                "('Serra Talhada', 16)," +
                "('Serra', 8)," +
                "('Serrana', 26)," +
                "('Serrania', 11)," +
                "('Serrano do Maranhão', 10)," +
                "('Serranópolis de Minas', 11)," +
                "('Serranópolis do Iguaçu', 18)," +
                "('Serranópolis', 9)," +
                "('Serranos', 11)," +
                "('Serraria', 15)," +
                "('Serrinha dos Pintos', 20)," +
                "('Serrinha', 20)," +
                "('Serrinha', 5)," +
                "('Serrita', 16)," +
                "('Serro', 11)," +
                "('Serrolândia', 5)," +
                "('Sertaneja', 18)," +
                "('Sertânia', 16)," +
                "('Sertanópolis', 18)," +
                "('Sertão Santana', 23)," +
                "('Sertão', 23)," +
                "('Sertãozinho', 15)," +
                "('Sertãozinho', 26)," +
                "('Sete Barras', 26)," +
                "('Sete de Setembro', 23)," +
                "('Sete Lagoas', 11)," +
                "('Sete Quedas', 12)," +
                "('Setubinha', 11)," +
                "('Severiano de Almeida', 23)," +
                "('Severiano Melo', 20)," +
                "('Severínia', 26)," +
                "('Siderópolis', 24)," +
                "('Sidrolândia', 12)," +
                "('Sigefredo Pacheco', 17)," +
                "('Silva Jardim', 19)," +
                "('Silvânia', 9)," +
                "('Silvanópolis', 27)," +
                "('Silveira Martins', 23)," +
                "('Silveirânia', 11)," +
                "('Silveiras', 26)," +
                "('Silves', 3)," +
                "('Silvianópolis', 11)," +
                "('Simão Dias', 25)," +
                "('Simão Pereira', 11)," +
                "('Simões Filho', 5)," +
                "('Simões', 17)," +
                "('Simolândia', 9)," +
                "('Simonésia', 11)," +
                "('Simplício Mendes', 17)," +
                "('Sinimbu', 23)," +
                "('Sinop', 13)," +
                "('Siqueira Campos', 18)," +
                "('Sirinhaém', 16)," +
                "('Siriri', 25)," +
                "('Sítio d`Abadia', 9)," +
                "('Sítio do Mato', 5)," +
                "('Sítio do Quinto', 5)," +
                "('Sítio Novo do Tocantins', 27)," +
                "('Sítio Novo', 10)," +
                "('Sítio Novo', 20)," +
                "('Sobradinho', 23)," +
                "('Sobradinho', 5)," +
                "('Sobrado', 15)," +
                "('Sobral', 6)," +
                "('Sobrália', 11)," +
                "('Socorro do Piauí', 17)," +
                "('Socorro', 26)," +
                "('Solânea', 15)," +
                "('Soledade de Minas', 11)," +
                "('Soledade', 15)," +
                "('Soledade', 23)," +
                "('Solidão', 16)," +
                "('Solonópole', 6)," +
                "('Sombrio', 24)," +
                "('Sonora', 12)," +
                "('Sooretama', 8)," +
                "('Sorocaba', 26)," +
                "('Sorriso', 13)," +
                "('Sossêgo', 15)," +
                "('Soure', 14)," +
                "('Sousa', 15)," +
                "('Souto Soares', 5)," +
                "('Sucupira do Norte', 10)," +
                "('Sucupira do Riachão', 10)," +
                "('Sucupira', 27)," +
                "('Sud Mennucci', 26)," +
                "('Sul Brasil', 24)," +
                "('Sulina', 18)," +
                "('Sumaré', 26)," +
                "('Sumé', 15)," +
                "('Sumidouro', 19)," +
                "('Surubim', 16)," +
                "('Sussuapara', 17)," +
                "('Suzanápolis', 26)," +
                "('Suzano', 26)," +
                "('Tabaí', 23)," +
                "('Tabaporã', 13)," +
                "('Tabapuã', 26)," +
                "('Tabatinga', 26)," +
                "('Tabatinga', 3)," +
                "('Tabira', 16)," +
                "('Taboão da Serra', 26)," +
                "('Tabocas do Brejo Velho', 5)," +
                "('Taboleiro Grande', 20)," +
                "('Tabuleiro do Norte', 6)," +
                "('Tabuleiro', 11)," +
                "('Tacaimbó', 16)," +
                "('Tacaratu', 16)," +
                "('Taciba', 26)," +
                "('Tacuru', 12)," +
                "('Taguaí', 26)," +
                "('Taguatinga', 27)," +
                "('Taiaçu', 26)," +
                "('Tailândia', 14)," +
                "('Taió', 24)," +
                "('Taiobeiras', 11)," +
                "('Taipas do Tocantins', 27)," +
                "('Taipu', 20)," +
                "('Taiúva', 26)," +
                "('Talismã', 27)," +
                "('Tamandaré', 16)," +
                "('Tamarana', 18)," +
                "('Tambaú', 26)," +
                "('Tamboara', 18)," +
                "('Tamboril do Piauí', 17)," +
                "('Tamboril', 6)," +
                "('Tanabi', 26)," +
                "('Tangará da Serra', 13)," +
                "('Tangará', 20)," +
                "('Tangará', 24)," +
                "('Tanguá', 19)," +
                "('Tanhaçu', 5)," +
                "('Tanque d`Arca', 2)," +
                "('Tanque do Piauí', 17)," +
                "('Tanque Novo', 5)," +
                "('Tanquinho', 5)," +
                "('Taparuba', 11)," +
                "('Tapauá', 3)," +
                "('Tapejara', 18)," +
                "('Tapejara', 23)," +
                "('Tapera', 23)," +
                "('Taperoá', 15)," +
                "('Taperoá', 5)," +
                "('Tapes', 23)," +
                "('Tapira', 11)," +
                "('Tapira', 18)," +
                "('Tapiraí', 11)," +
                "('Tapiraí', 26)," +
                "('Tapiramutá', 5)," +
                "('Tapiratiba', 26)," +
                "('Tapurah', 13)," +
                "('Taquara', 23)," +
                "('Taquaraçu de Minas', 11)," +
                "('Taquaral de Goiás', 9)," +
                "('Taquaral', 26)," +
                "('Taquarana', 2)," +
                "('Taquari', 23)," +
                "('Taquaritinga do Norte', 16)," +
                "('Taquaritinga', 26)," +
                "('Taquarituba', 26)," +
                "('Taquarivaí', 26)," +
                "('Taquaruçu do Sul', 23)," +
                "('Taquarussu', 12)," +
                "('Tarabai', 26)," +
                "('Tarauacá', 1)," +
                "('Tarrafas', 6)," +
                "('Tartarugalzinho', 4)," +
                "('Tarumã', 26)," +
                "('Tarumirim', 11)," +
                "('Tasso Fragoso', 10)," +
                "('Tatuí', 26)," +
                "('Tauá', 6)," +
                "('Taubaté', 26)," +
                "('Tavares', 15)," +
                "('Tavares', 23)," +
                "('Tefé', 3)," +
                "('Teixeira de Freitas', 5)," +
                "('Teixeira Soares', 18)," +
                "('Teixeira', 15)," +
                "('Teixeiras', 11)," +
                "('Teixeirópolis', 21)," +
                "('Tejuçuoca', 6)," +
                "('Tejupá', 26)," +
                "('Telêmaco Borba', 18)," +
                "('Telha', 25)," +
                "('Tenente Ananias', 20)," +
                "('Tenente Laurentino Cruz', 20)," +
                "('Tenente Portela', 23)," +
                "('Tenório', 15)," +
                "('Teodoro Sampaio', 26)," +
                "('Teodoro Sampaio', 5)," +
                "('Teofilândia', 5)," +
                "('Teófilo Otoni', 11)," +
                "('Teolândia', 5)," +
                "('Teotônio Vilela', 2)," +
                "('Terenos', 12)," +
                "('Teresina de Goiás', 9)," +
                "('Teresina', 17)," +
                "('Teresópolis', 19)," +
                "('Terezinha', 16)," +
                "('Terezópolis de Goiás', 9)," +
                "('Terra Alta', 14)," +
                "('Terra Boa', 18)," +
                "('Terra de Areia', 23)," +
                "('Terra Nova do Norte', 13)," +
                "('Terra Nova', 16)," +
                "('Terra Nova', 5)," +
                "('Terra Rica', 18)," +
                "('Terra Roxa', 18)," +
                "('Terra Roxa', 26)," +
                "('Terra Santa', 14)," +
                "('Tesouro', 13)," +
                "('Teutônia', 23)," +
                "('Theobroma', 21)," +
                "('Tianguá', 6)," +
                "('Tibagi', 18)," +
                "('Tibau do Sul', 20)," +
                "('Tibau', 20)," +
                "('Tietê', 26)," +
                "('Tigrinhos', 24)," +
                "('Tijucas do Sul', 18)," +
                "('Tijucas', 24)," +
                "('Timbaúba dos Batistas', 20)," +
                "('Timbaúba', 16)," +
                "('Timbé do Sul', 24)," +
                "('Timbiras', 10)," +
                "('Timbó Grande', 24)," +
                "('Timbó', 24)," +
                "('Timburi', 26)," +
                "('Timon', 10)," +
                "('Timóteo', 11)," +
                "('Tio Hugo', 23)," +
                "('Tiradentes do Sul', 23)," +
                "('Tiradentes', 11)," +
                "('Tiros', 11)," +
                "('Tobias Barreto', 25)," +
                "('Tocantínia', 27)," +
                "('Tocantinópolis', 27)," +
                "('Tocantins', 11)," +
                "('Tocos do Moji', 11)," +
                "('Toledo', 11)," +
                "('Toledo', 18)," +
                "('Tomar do Geru', 25)," +
                "('Tomazina', 18)," +
                "('Tombos', 11)," +
                "('Tomé-Açu', 14)," +
                "('Tonantins', 3)," +
                "('Toritama', 16)," +
                "('Torixoréu', 13)," +
                "('Toropi', 23)," +
                "('Torre de Pedra', 26)," +
                "('Torres', 23)," +
                "('Torrinha', 26)," +
                "('Touros', 20)," +
                "('Trabiju', 26)," +
                "('Tracuateua', 14)," +
                "('Tracunhaém', 16)," +
                "('Traipu', 2)," +
                "('Trairão', 14)," +
                "('Trairi', 6)," +
                "('Trajano de Morais', 19)," +
                "('Tramandaí', 23)," +
                "('Travesseiro', 23)," +
                "('Tremedal', 5)," +
                "('Tremembé', 26)," +
                "('Três Arroios', 23)," +
                "('Três Barras do Paraná', 18)," +
                "('Três Barras', 24)," +
                "('Três Cachoeiras', 23)," +
                "('Três Corações', 11)," +
                "('Três Coroas', 23)," +
                "('Três de Maio', 23)," +
                "('Três Forquilhas', 23)," +
                "('Três Fronteiras', 26)," +
                "('Três Lagoas', 12)," +
                "('Três Marias', 11)," +
                "('Três Palmeiras', 23)," +
                "('Três Passos', 23)," +
                "('Três Pontas', 11)," +
                "('Três Ranchos', 9)," +
                "('Três Rios', 19)," +
                "('Treviso', 24)," +
                "('Treze de Maio', 24)," +
                "('Treze Tílias', 24)," +
                "('Trindade do Sul', 23)," +
                "('Trindade', 16)," +
                "('Trindade', 9)," +
                "('Triunfo Potiguar', 20)," +
                "('Triunfo', 15)," +
                "('Triunfo', 16)," +
                "('Triunfo', 23)," +
                "('Trizidela do Vale', 10)," +
                "('Trombas', 9)," +
                "('Trombudo Central', 24)," +
                "('Tubarão', 24)," +
                "('Tucano', 5)," +
                "('Tucumã', 14)," +
                "('Tucunduva', 23)," +
                "('Tucuruí', 14)," +
                "('Tufilândia', 10)," +
                "('Tuiuti', 26)," +
                "('Tumiritinga', 11)," +
                "('Tunápolis', 24)," +
                "('Tunas do Paraná', 18)," +
                "('Tunas', 23)," +
                "('Tuneiras do Oeste', 18)," +
                "('Tuntum', 10)," +
                "('Tupã', 26)," +
                "('Tupaciguara', 11)," +
                "('Tupanatinga', 16)," +
                "('Tupanci do Sul', 23)," +
                "('Tupanciretã', 23)," +
                "('Tupandi', 23)," +
                "('Tuparendi', 23)," +
                "('Tuparetama', 16)," +
                "('Tupãssi', 18)," +
                "('Tupi Paulista', 26)," +
                "('Tupirama', 27)," +
                "('Tupiratins', 27)," +
                "('Turiaçu', 10)," +
                "('Turilândia', 10)," +
                "('Turiúba', 26)," +
                "('Turmalina', 11)," +
                "('Turmalina', 26)," +
                "('Turuçu', 23)," +
                "('Tururu', 6)," +
                "('Turvânia', 9)," +
                "('Turvelândia', 9)," +
                "('Turvo', 18)," +
                "('Turvo', 24)," +
                "('Turvolândia', 11)," +
                "('Tutóia', 10)," +
                "('Uarini', 3)," +
                "('Uauá', 5)," +
                "('Ubá', 11)," +
                "('Ubaí', 11)," +
                "('Ubaíra', 5)," +
                "('Ubaitaba', 5)," +
                "('Ubajara', 6)," +
                "('Ubaporanga', 11)," +
                "('Ubarana', 26)," +
                "('Ubatã', 5)," +
                "('Ubatuba', 26)," +
                "('Uberaba', 11)," +
                "('Uberlândia', 11)," +
                "('Ubirajara', 26)," +
                "('Ubiratã', 18)," +
                "('Ubiretama', 23)," +
                "('Uchoa', 26)," +
                "('Uibaí', 5)," +
                "('Uiramutã', 22)," +
                "('Uirapuru', 9)," +
                "('Uiraúna', 15)," +
                "('Ulianópolis', 14)," +
                "('Umari', 6)," +
                "('Umarizal', 20)," +
                "('Umbaúba', 25)," +
                "('Umburanas', 5)," +
                "('Umburatiba', 11)," +
                "('Umbuzeiro', 15)," +
                "('Umirim', 6)," +
                "('Umuarama', 18)," +
                "('Una', 5)," +
                "('Unaí', 11)," +
                "('União da Serra', 23)," +
                "('União da Vitória', 18)," +
                "('União de Minas', 11)," +
                "('União do Oeste', 24)," +
                "('União do Sul', 13)," +
                "('União dos Palmares', 2)," +
                "('União Paulista', 26)," +
                "('União', 17)," +
                "('Uniflor', 18)," +
                "('Unistalda', 23)," +
                "('Upanema', 20)," +
                "('Uraí', 18)," +
                "('Urandi', 5)," +
                "('Urânia', 26)," +
                "('Urbano Santos', 10)," +
                "('Uru', 26)," +
                "('Uruaçu', 9)," +
                "('Uruana de Minas', 11)," +
                "('Uruana', 9)," +
                "('Uruará', 14)," +
                "('Urubici', 24)," +
                "('Uruburetama', 6)," +
                "('Urucânia', 11)," +
                "('Urucará', 3)," +
                "('Uruçuca', 5)," +
                "('Uruçuí', 17)," +
                "('Urucuia', 11)," +
                "('Urucurituba', 3)," +
                "('Uruguaiana', 23)," +
                "('Uruoca', 6)," +
                "('Urupá', 21)," +
                "('Urupema', 24)," +
                "('Urupês', 26)," +
                "('Urussanga', 24)," +
                "('Urutaí', 9)," +
                "('Utinga', 5)," +
                "('Vacaria', 23)," +
                "('Vale de São Domingos', 13)," +
                "('Vale do Anari', 21)," +
                "('Vale do Paraíso', 21)," +
                "('Vale do Sol', 23)," +
                "('Vale Real', 23)," +
                "('Vale Verde', 23)," +
                "('Valença do Piauí', 17)," +
                "('Valença', 19)," +
                "('Valença', 5)," +
                "('Valente', 5)," +
                "('Valentim Gentil', 26)," +
                "('Valinhos', 26)," +
                "('Valparaíso de Goiás', 9)," +
                "('Valparaíso', 26)," +
                "('Vanini', 23)," +
                "('Vargeão', 24)," +
                "('Vargem Alegre', 11)," +
                "('Vargem Alta', 8)," +
                "('Vargem Bonita', 11)," +
                "('Vargem Bonita', 24)," +
                "('Vargem Grande do Rio Pardo', 11)," +
                "('Vargem Grande do Sul', 26)," +
                "('Vargem Grande Paulista', 26)," +
                "('Vargem Grande', 10)," +
                "('Vargem', 24)," +
                "('Vargem', 26)," +
                "('Varginha', 11)," +
                "('Varjão de Minas', 11)," +
                "('Varjão', 9)," +
                "('Varjota', 6)," +
                "('Varre-Sai', 19)," +
                "('Várzea Alegre', 6)," +
                "('Várzea Branca', 17)," +
                "('Várzea da Palma', 11)," +
                "('Várzea da Roça', 5)," +
                "('Várzea do Poço', 5)," +
                "('Várzea Grande', 13)," +
                "('Várzea Grande', 17)," +
                "('Várzea Nova', 5)," +
                "('Várzea Paulista', 26)," +
                "('Várzea', 15)," +
                "('Várzea', 20)," +
                "('Varzedo', 5)," +
                "('Varzelândia', 11)," +
                "('Vassouras', 19)," +
                "('Vazante', 11)," +
                "('Venâncio Aires', 23)," +
                "('Venda Nova do Imigrante', 8)," +
                "('Venha-Ver', 20)," +
                "('Ventania', 18)," +
                "('Venturosa', 16)," +
                "('Vera Cruz do Oeste', 18)," +
                "('Vera Cruz', 20)," +
                "('Vera Cruz', 23)," +
                "('Vera Cruz', 26)," +
                "('Vera Cruz', 5)," +
                "('Vera Mendes', 17)," +
                "('Vera', 13)," +
                "('Veranópolis', 23)," +
                "('Verdejante', 16)," +
                "('Verdelândia', 11)," +
                "('Verê', 18)," +
                "('Vereda', 5)," +
                "('Veredinha', 11)," +
                "('Veríssimo', 11)," +
                "('Vermelho Novo', 11)," +
                "('Vertente do Lério', 16)," +
                "('Vertentes', 16)," +
                "('Vespasiano Correa', 23)," +
                "('Vespasiano', 11)," +
                "('Viadutos', 23)," +
                "('Viamão', 23)," +
                "('Viana', 10)," +
                "('Viana', 8)," +
                "('Vianópolis', 9)," +
                "('Vicência', 16)," +
                "('Vicente Dutra', 23)," +
                "('Vicentina', 12)," +
                "('Vicentinópolis', 9)," +
                "('Viçosa do Ceará', 6)," +
                "('Viçosa', 11)," +
                "('Viçosa', 2)," +
                "('Viçosa', 20)," +
                "('Victor Graeff', 23)," +
                "('Vidal Ramos', 24)," +
                "('Videira', 24)," +
                "('Vieiras', 11)," +
                "('Vieirópolis', 15)," +
                "('Vigia', 14)," +
                "('Vila Bela da Santíssima Trindade', 13)," +
                "('Vila Boa', 9)," +
                "('Vila Flor', 20)," +
                "('Vila Flores', 23)," +
                "('Vila Lângaro', 23)," +
                "('Vila Maria', 23)," +
                "('Vila Nova do Piauí', 17)," +
                "('Vila Nova do Sul', 23)," +
                "('Vila Nova dos Martírios', 10)," +
                "('Vila Pavão', 8)," +
                "('Vila Propício', 9)," +
                "('Vila Rica', 13)," +
                "('Vila Valério', 8)," +
                "('Vila Velha', 8)," +
                "('Vilhena', 21)," +
                "('Vinhedo', 26)," +
                "('Viradouro', 26)," +
                "('Virgem da Lapa', 11)," +
                "('Virgínia', 11)," +
                "('Virginópolis', 11)," +
                "('Virgolândia', 11)," +
                "('Virmond', 18)," +
                "('Visconde do Rio Branco', 11)," +
                "('Viseu', 14)," +
                "('Vista Alegre do Alto', 26)," +
                "('Vista Alegre do Prata', 23)," +
                "('Vista Alegre', 23)," +
                "('Vista Gaúcha', 23)," +
                "('Vista Serrana', 15)," +
                "('Vitor Meireles', 24)," +
                "('Vitória Brasil', 26)," +
                "('Vitória da Conquista', 5)," +
                "('Vitória das Missões', 23)," +
                "('Vitória de Santo Antão', 16)," +
                "('Vitória do Jari', 4)," +
                "('Vitória do Mearim', 10)," +
                "('Vitória do Xingu', 14)," +
                "('Vitória', 8)," +
                "('Vitorino Freire', 10)," +
                "('Vitorino', 18)," +
                "('Volta Grande', 11)," +
                "('Volta Redonda', 19)," +
                "('Votorantim', 26)," +
                "('Votuporanga', 26)," +
                "('Wagner', 5)," +
                "('Wall Ferraz', 17)," +
                "('Wanderlândia', 27)," +
                "('Wanderley', 5)," +
                "('Wenceslau Braz', 11)," +
                "('Wenceslau Braz', 18)," +
                "('Wenceslau Guimarães', 5)," +
                "('Westfália', 23)," +
                "('Witmarsum', 24)," +
                "('Xambioá', 27)," +
                "('Xambrê', 18)," +
                "('Xangri-lá', 23)," +
                "('Xanxerê', 24)," +
                "('Xapuri', 1)," +
                "('Xavantina', 24)," +
                "('Xaxim', 24)," +
                "('Xexéu', 16)," +
                "('Xinguara', 14)," +
                "('Xique-Xique', 5)," +
                "('Zabelê', 15)," +
                "('Zacarias', 26)," +
                "('Zé Doca', 10)," +
                "('Zortéa', 24);";

        db.execSQL(create_table_pais);
        db.execSQL(create_table_estados);
        db.execSQL(create_table_cidades);
        db.execSQL(create_table_viagens);
        db.execSQL(insert_table_pais);
        db.execSQL(insert_table_estados);
        db.execSQL(insert_table_cidades_1);
        db.execSQL(insert_table_cidades_2);
    }


    @Override
    //Em caso Upgrade do Banco de Dados
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}


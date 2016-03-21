package ch.ranil.android.flageo.model;

import android.content.Context;

import ch.ranil.android.flageo.R;

public enum Flag implements Quizable {

    AFGHANISTAN(R.drawable.af, R.string.afghanistan, 652090),
    ALBANIA(R.drawable.al, R.string.albania, 28748),
    ALGERIA(R.drawable.dz, R.string.algeria, 2381740),
    ANDORRA(R.drawable.ad, R.string.andorra, 468),
    ANGOLA(R.drawable.ao, R.string.angola, 1246700),
    ANTIGUA_BARBUDA(R.drawable.ag, R.string.antigua_barbuda, 442),
    ARGENTINA(R.drawable.ar, R.string.argentina, 2780400),
    ARMENIA(R.drawable.am, R.string.armenia, 29800),
    AUSTRALIA(R.drawable.au, R.string.australia, 7741220),
    AUSTRIA(R.drawable.at, R.string.austria, 83858),
    AZERBAIJAN(R.drawable.az, R.string.azerbaijan, 86600),
    BAHAMAS(R.drawable.bs, R.string.bahamas, 13878),
    BAHRAIN(R.drawable.bh, R.string.bahrain, 694),
    BANGLADESH(R.drawable.bd, R.string.bangladesh, 143998),
    BARBADOS(R.drawable.bb, R.string.barbados, 430),
    BELARUS(R.drawable.by, R.string.belarus, 207600),
    BELGIUM(R.drawable.be, R.string.belgium, 32545),
    BELIZE(R.drawable.bz, R.string.belize, 22966),
    BENIN(R.drawable.bj, R.string.benin, 112622),
    BHUTAN(R.drawable.bt, R.string.bhutan, 47000),
    BOLIVIA(R.drawable.bo, R.string.bolivia, 1098580),
    BOSNIA_HERZEGOVINA(R.drawable.ba, R.string.bosnia_herzegovina, 51197),
    BOTSWANA(R.drawable.bw, R.string.botswana, 581730),
    BRAZIL(R.drawable.br, R.string.brazil, 8514880),
    BRUNEI(R.drawable.bn, R.string.brunei, 5765),
    BULGARIA(R.drawable.bg, R.string.bulgaria, 110912),
    BURKINA_FASO(R.drawable.bf, R.string.burkina_faso, 274000),
    BURUNDI(R.drawable.bi, R.string.burundi, 27834),
    CAMBODIA(R.drawable.kh, R.string.cambodia, 181035),
    CAMEROON(R.drawable.cm, R.string.cameroon, 475442),
    CANADA(R.drawable.ca, R.string.canada, 9970610),
    CAPE_VERDE(R.drawable.cv, R.string.cape_verde, 4033),
    CENTRAL_AFRICAN_REPUBLIC(R.drawable.cf, R.string.central_african_republic, 622984),
    CHAD(R.drawable.td, R.string.chad, 1284000),
    CHILE(R.drawable.cl, R.string.chile, 756096),
    COLOMBIA(R.drawable.co, R.string.colombia, 1138910),
    COMOROS(R.drawable.km, R.string.comoros, 2235),
    //COOK_ISLANDS(R.drawable.ck, R.string.cook_islands, 240),
    COSTA_RICA(R.drawable.cr, R.string.costa_rica, 51100),
    COTE_DIVOIRE(R.drawable.ci, R.string.cote_divoire, 322463),
    CROATIA(R.drawable.hr, R.string.croatia, 56538),
    CUBA(R.drawable.cu, R.string.cuba, 110861),
    CYPRUS(R.drawable.cy, R.string.cyprus, 9251),
    CZECH_REPUBLIC(R.drawable.cz, R.string.czech_republic, 78866),
    DEMOCRATIC_REPUBLIC_OF_THE_CONGO(R.drawable.cd, R.string.democratic_republic_of_the_congo, 2344860),
    DENMARK(R.drawable.dk, R.string.denmark, 43094),
    DJIBOUTI(R.drawable.dj, R.string.djibouti, 23200),
    DOMINICA(R.drawable.dm, R.string.dominica, 751),
    DOMINICAN_REPUBLIC(R.drawable.dom, R.string.dominican_republic, 48671),
    //EAST_TIMOR(R.drawable.tp, R.string.east_timor, 14874),
    ECUADOR(R.drawable.ec, R.string.ecuador, 283561),
    EGYPT(R.drawable.eg, R.string.egypt, 980869),
    EL_SALVADOR(R.drawable.sv, R.string.el_salvador, 21041),
    EQUATORIAL_GUINEA(R.drawable.gq, R.string.equatorial_guinea, 28051),
    ERITREA(R.drawable.er, R.string.eritrea, 117600),
    ESTONIA(R.drawable.ee, R.string.estonia, 45100),
    ETHIOPIA(R.drawable.et, R.string.ethiopia, 1104300),
    FIJI(R.drawable.fj, R.string.fiji, 18274),
    FINLAND(R.drawable.fi, R.string.finland, 338145),
    FRANCE(R.drawable.fr, R.string.france, 551500),
    GABON(R.drawable.ga, R.string.gabon, 267668),
    GAMBIA(R.drawable.gm, R.string.gambia, 11295),
    GEORGIA(R.drawable.ge, R.string.georgia, 69700),
    GERMANY(R.drawable.de, R.string.germany, 357022),
    GHANA(R.drawable.gh, R.string.ghana, 238533),
    GREECE(R.drawable.gr, R.string.greece, 131957),
    GRENADA(R.drawable.gd, R.string.grenada, 344),
    GUATEMALA(R.drawable.gt, R.string.guatemala, 108889),
    GUINEA(R.drawable.gn, R.string.guinea, 245857),
    GUINEABISSAU(R.drawable.gw, R.string.guineabissau, 36125),
    GUYANA(R.drawable.gy, R.string.guyana, 214969),
    HAITI(R.drawable.ht, R.string.haiti, 27750),
    HONDURAS(R.drawable.hn, R.string.honduras, 112088),
    HUNGARY(R.drawable.hu, R.string.hungary, 93032),
    ICELAND(R.drawable.is, R.string.iceland, 103000),
    INDIA(R.drawable.in, R.string.india, 3287260),
    INDONESIA(R.drawable.id, R.string.indonesia, 1904570),
    IRAN(R.drawable.ir, R.string.iran, 1648200),
    IRAQ(R.drawable.iq, R.string.iraq, 438317),
    IRELAND(R.drawable.ie, R.string.ireland, 70273),
    ISRAEL(R.drawable.il, R.string.israel, 22145),
    ITALY(R.drawable.it, R.string.italy, 301318),
    JAMAICA(R.drawable.jm, R.string.jamaica, 10991),
    JAPAN(R.drawable.jp, R.string.japan, 377873),
    JORDAN(R.drawable.jo, R.string.jordan, 89342),
    KAZAKHSTAN(R.drawable.kz, R.string.kazakhstan, 2724900),
    KENYA(R.drawable.ke, R.string.kenya, 580367),
    KIRIBATI(R.drawable.ki, R.string.kiribati, 726),
    KOSOVO(R.drawable.ks, R.string.kosovo, 10887),
    KUWAIT(R.drawable.kw, R.string.kuwait, 17818),
    KYRGYZSTAN(R.drawable.kg, R.string.kyrgyzstan, 199900),
    LAOS(R.drawable.la, R.string.laos, 236800),
    LATVIA(R.drawable.lv, R.string.latvia, 64600),
    LEBANON(R.drawable.lb, R.string.lebanon, 10400),
    LESOTHO(R.drawable.ls, R.string.lesotho, 30355),
    LIBERIA(R.drawable.lr, R.string.liberia, 111369),
    LIBYA(R.drawable.ly, R.string.libya, 1759540),
    LIECHTENSTEIN(R.drawable.li, R.string.liechtenstein, 160),
    LITHUANIA(R.drawable.lt, R.string.lithuania, 65300),
    LUXEMBOURG(R.drawable.lu, R.string.luxembourg, 2586),
    MACEDONIA(R.drawable.mk, R.string.macedonia, 25713),
    MADAGASCAR(R.drawable.mg, R.string.madagascar, 587041),
    MALAWI(R.drawable.mw, R.string.malawi, 118484),
    MALAYSIA(R.drawable.my, R.string.malaysia, 329847),
    MALDIVES(R.drawable.mv, R.string.maldives, 298),
    MALI(R.drawable.ml, R.string.mali, 1240190),
    MALTA(R.drawable.mt, R.string.malta, 316),
    MARSHALL_ISLANDS(R.drawable.mh, R.string.marshall_islands, 181),
    MAURITANIA(R.drawable.mr, R.string.mauritania, 1025520),
    MAURITIUS(R.drawable.mu, R.string.mauritius, 2040),
    MEXICO(R.drawable.mx, R.string.mexico, 1958200),
    MICRONESIA(R.drawable.fm, R.string.micronesia, 702),
    MOLDOVA(R.drawable.md, R.string.moldova, 33851),
    MONACO(R.drawable.mc, R.string.monaco, 2),
    MONGOLIA(R.drawable.mn, R.string.mongolia, 1564120),
    MONTENEGRO(R.drawable.me, R.string.montenegro, 13812),
    MOROCCO(R.drawable.ma, R.string.morocco, 446550),
    MOZAMBIQUE(R.drawable.mz, R.string.mozambique, 801590),
    MYANMAR(R.drawable.mm, R.string.myanmar, 676578),
    NAMIBIA(R.drawable.na, R.string.namibia, 824292),
    NAURU(R.drawable.nr, R.string.nauru, 21),
    NEPAL(R.drawable.np, R.string.nepal, 147181),
    NETHERLANDS(R.drawable.nl, R.string.netherlands, 41528),
    NEW_ZEALAND(R.drawable.nz, R.string.new_zealand, 270534),
    NICARAGUA(R.drawable.ni, R.string.nicaragua, 130000),
    NIGER(R.drawable.ne, R.string.niger, 1267000),
    NIGERIA(R.drawable.ng, R.string.nigeria, 923768),
    //NIUE(R.drawable.nu, R.string.niue, 260),
    NORTH_KOREA(R.drawable.kp, R.string.north_korea, 120538),
    NORWAY(R.drawable.no, R.string.norway, 385155),
    OMAN(R.drawable.om, R.string.oman, 309500),
    PAKISTAN(R.drawable.pk, R.string.pakistan, 796095),
    PALAU(R.drawable.pw, R.string.palau, 459),
    PANAMA(R.drawable.pa, R.string.panama, 75517),
    PAPUA_NEW_GUINEA(R.drawable.pg, R.string.papua_new_guinea, 462840),
    PARAGUAY(R.drawable.py, R.string.paraguay, 406752),
    PEOPLES_REPUBLIC_OF_CHINA(R.drawable.cn, R.string.peoples_republic_of_china, 9640820, "China"),
    PERU(R.drawable.pe, R.string.peru, 1285220),
    PHILIPPINES(R.drawable.ph, R.string.philippines, 300000),
    POLAND(R.drawable.pl, R.string.poland, 312685),
    PORTUGAL(R.drawable.pt, R.string.portugal, 91982),
    QATAR(R.drawable.qa, R.string.qatar, 11000),
    REPUBLIC_OF_CHINA(R.drawable.tw, R.string.republic_of_china, 36188),
    REPUBLIC_OF_THE_CONGO(R.drawable.cg, R.string.republic_of_the_congo, 342000),
    ROMANIA(R.drawable.ro, R.string.romania, 238391),
    RUSSIA(R.drawable.ru, R.string.russia, 17098200),
    RWANDA(R.drawable.rw, R.string.rwanda, 26338),
    SAINT_KITTS_NEVIS(R.drawable.kn, R.string.saint_kitts_nevis, 261),
    SAINT_LUCIA(R.drawable.lc, R.string.saint_lucia, 539),
    SAINT_VINCENT_THE_GRENADINES(R.drawable.vc, R.string.saint_vincent_the_grenadines, 388),
    SAMOA(R.drawable.ws, R.string.samoa, 2831),
    SAN_MARINO(R.drawable.sm, R.string.san_marino, 61),
    SAO_TOME_PRINCIPE(R.drawable.st, R.string.sao_tome_principe, 964),
    SAUDI_ARABIA(R.drawable.sa, R.string.saudi_arabia, 2149690),
    SENEGAL(R.drawable.sn, R.string.senegal, 196722),
    SERBIA(R.drawable.rs, R.string.serbia, 77474),
    SEYCHELLES(R.drawable.sc, R.string.seychelles, 455),
    SIERRA_LEONE(R.drawable.sl, R.string.sierra_leone, 71740),
    SINGAPORE(R.drawable.sg, R.string.singapore, 683),
    SLOVAKIA(R.drawable.sk, R.string.slovakia, 49033),
    SLOVENIA(R.drawable.si, R.string.slovenia, 20256),
    SOLOMON_ISLANDS(R.drawable.sb, R.string.solomon_islands, 28896),
    SOMALIA(R.drawable.so, R.string.somalia, 637657),
    SOUTH_AFRICA(R.drawable.za, R.string.south_africa, 1221040),
    SOUTH_KOREA(R.drawable.kr, R.string.south_korea, 99538),
    //SOUTH_SUDAN(R.drawable.ss, R.string.south_sudan, 644329),
    SPAIN(R.drawable.es, R.string.spain, 505992),
    SRI_LANKA(R.drawable.lk, R.string.sri_lanka, 65610),
    SUDAN(R.drawable.sd, R.string.sudan, 2505810),
    SURINAME(R.drawable.sr, R.string.suriname, 163820),
    SWAZILAND(R.drawable.sz, R.string.swaziland, 17364),
    SWEDEN(R.drawable.se, R.string.sweden, 449964),
    SWITZERLAND(R.drawable.ch, R.string.switzerland, 41284),
    SYRIA(R.drawable.sy, R.string.syria, 185180),
    TAJIKISTAN(R.drawable.tj, R.string.tajikistan, 143100),
    TANZANIA(R.drawable.tz, R.string.tanzania, 945087),
    THAILAND(R.drawable.th, R.string.thailand, 513115),
    TOGO(R.drawable.tg, R.string.togo, 56785),
    TONGA(R.drawable.to, R.string.tonga, 747),
    TRINIDAD_TOBAGO(R.drawable.tt, R.string.trinidad_tobago, 5130),
    TUNISIA(R.drawable.tn, R.string.tunisia, 163610),
    TURKEY(R.drawable.tr, R.string.turkey, 783562),
    TURKMENISTAN(R.drawable.tm, R.string.turkmenistan, 488100),
    TUVALU(R.drawable.tv, R.string.tuvalu, 26),
    UGANDA(R.drawable.ug, R.string.uganda, 241038),
    UKRAINE(R.drawable.ua, R.string.ukraine, 603700),
    UNITED_ARAB_EMIRATES(R.drawable.ae, R.string.united_arab_emirates, 83600),
    UNITED_KINGDOM(R.drawable.gb, R.string.united_kingdom, 242900),
    UNITED_STATES(R.drawable.us, R.string.united_states, 9629090),
    URUGUAY(R.drawable.uy, R.string.uruguay, 175016),
    UZBEKISTAN(R.drawable.uz, R.string.uzbekistan, 447400),
    VANUATU(R.drawable.vu, R.string.vanuatu, 12189),
    VATICAN_CITY(R.drawable.va, R.string.vatican_city, 44),
    VENEZUELA(R.drawable.ve, R.string.venezuela, 912050),
    VIETNAM(R.drawable.vn, R.string.vietnam, 331689),
    WESTERN_SAHARA(R.drawable.eh, R.string.western_sahara, 266000),
    YEMEN(R.drawable.ye, R.string.yemen, 527968),
    ZAMBIA(R.drawable.zm, R.string.zambia, 752618),
    ZIMBABWE(R.drawable.zw, R.string.zimbabwe, 390757),
    ;

    private final static long REFERENCE_AREA = 17098242;
    private final static long MAX_BOOST = 10000;

    private int drawable;
    private int translation;
    private long area = REFERENCE_AREA;
    private String mapName;

    Flag(int drawable, int translation, long area) {
        this.drawable = drawable;
        this.translation = translation;
        this.area = area;
    }

    Flag(int drawable, int translation, long area, String mapName) {
        this.drawable = drawable;
        this.translation = translation;
        this.area = area;
        this.mapName = mapName;
    }

    /**
     * The flag drawable.
     *
     * @return drawable resource number
     */
    public int getDrawable() {
        return drawable;
    }

    /**
     * Translated flag name.
     *
     * @return string resource id
     */
    public int getTranslation() {
        return translation;
    }

    /**
     * The country name returned by geocoding.
     */
    public String getMapName(Context context) {
        if (mapName == null) {
            return context.getString(translation);
        }
        return mapName;
    }

    /**
     * Small countries get a time boost when in map quiz mode.
     * Calculation based on area in square kilometers: TODO find a better metric
     *
     * @return boost time in ms
     */
    public long getTimeBoost() {
        return Math.min(REFERENCE_AREA / area * 10, MAX_BOOST);
    }
}

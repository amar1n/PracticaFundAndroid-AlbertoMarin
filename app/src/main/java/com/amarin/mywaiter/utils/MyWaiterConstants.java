package com.amarin.mywaiter.utils;

public interface MyWaiterConstants {
    public static final String ARG_TABLE_POSITION = "ARG_TABLE_POSITION";
    public static final String ARG_ORDER_ITEM = "ARG_ORDER_ITEM";

    public static final int LOADING_VIEW_INDEX = 0;
    public static final int MENU_VIEW_INDEX = 1;

    // Nombres de los iconos de los platos del menu
    public static final String CALDO_GALLEGO = "caldo_gallego";
    public static final String CALLOS = "callos";
    public static final String COCIDO_GALLEGO = "cocido_gallego";
    public static final String EMPANADA = "empanada";
    public static final String LACON_CON_GRELOS = "lacon_con_grelos";
    public static final String PAN_GALLEGO = "pan_gallego";
    public static final String PULPO_A_LA_GALLEGA = "pulpo_a_la_gallega";

    // Nombres de los campos del JSON del menu
    public static final String JSON_ATT_DATA = "data";
    public static final String JSON_ATT_NAME = "name";
    public static final String JSON_ATT_DESCRIPTION = "description";
    public static final String JSON_ATT_ICON = "icon";
    public static final String JSON_ATT_PRICE = "price";
    public static final String JSON_ATT_ALLERGENS = "allergens";
    public static final String JSON_ATT_ALLERGEN_NAME = "name";
    public static final String JSON_ATT_ALLERGEN_ICON = "icon";

    // Nombres de los iconos de los alergenos de los platos del menu
    public static final String ALLERGEN_ALTRAMUZ = "allergen_altramuz";
    public static final String ALLERGEN_APIO = "allergen_apio";
    public static final String ALLERGEN_CACAHUETES = "allergen_cacahuetes";
    public static final String ALLERGEN_CEREAL_CON_GLUTEN = "allergen_cereal_con_gluten";
    public static final String ALLERGEN_CRUSTACEOS = "allergen_crustaceos";
    public static final String ALLERGEN_FRUTOS_SECOS = "allergen_frutos_secos";
    public static final String ALLERGEN_HUEVOS = "allergen_huevos";
    public static final String ALLERGEN_LACTEOS = "allergen_lacteos";
    public static final String ALLERGEN_MOLUSCOS = "allergen_moluscos";
    public static final String ALLERGEN_MOSTAZA = "allergen_mostaza";
    public static final String ALLERGEN_PESCADO = "allergen_pescado";
    public static final String ALLERGEN_SESAMO = "allergen_sesamo";
    public static final String ALLERGEN_SOJA = "allergen_soja";
    public static final String ALLERGEN_SULFITOS = "allergen_sulfitos";

    public static final String URL_API_MENU = "http://nodepop.zampateste.com/api/v1/menu";

    public static final int RESULT_OK = 1;
    public static final int RESULT_CANCEL = 0;

    public static final int REQUEST_ORDER_ITEM = 1;
    public static final int REQUEST_MENU = 1;
}

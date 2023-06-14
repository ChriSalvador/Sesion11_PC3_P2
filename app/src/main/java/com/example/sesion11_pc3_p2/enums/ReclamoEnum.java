package com.example.sesion11_pc3_p2.enums;

public enum ReclamoEnum {

    TABLE_NAME ("mi_reclamos"),
    COL_ID ("id"),
    COL_CODIGO ("codigo"),
    COL_ASUNTO ("asunto"),
    COL_DESCRIPCION ("descripcion"),
    COL_ESTADO ("estado"),
    COL_FECHACREACION ("fechacreacion"),
    KEY_NAME("reclamo");

    private String value;

    ReclamoEnum(String value) {
        setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

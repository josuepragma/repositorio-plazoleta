package com.pragma.smallsquare.insfrastructure.util;

public enum StatusEnum {
    PENDING("PENDIENTE"),
    IN_PREPARATION("EN_PREPARACION"),
    READY("LISTO"),
    DELIVERED("ENTREGADO"),
    CANCELED("CANCELADO");

    private final String name;

    StatusEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static boolean statusExists(String statusParam) {
        StatusEnum[] statuses = StatusEnum.values();
        for (StatusEnum st : statuses)
            if (st.name.equals(statusParam)) {
                return true;
            }
        return false;
    }

}

package com.kangyonggan.app.dfjz.model.constants;

import lombok.Getter;

/**
 * @author kangyonggan
 * @since 7/6/17
 */
public enum Dialect {

    MySQL("MySQL"),
    Oracle("Oracle"),
    SQLServer("SQLServer");

    @Getter
    private final String dialect;

    private Dialect(String dialect) {
        this.dialect = dialect;
    }

}

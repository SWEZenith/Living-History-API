package com.zenith.livinghistory.api.zenithlivinghistoryapi.common.SparQL.DTO;


import org.apache.commons.lang3.StringUtils;

public class QueryProperty {

    //region Private Members

    private String name;

    private boolean isDirect;

    private String valueProperty;

    //endregion

    //region Getter and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDirect() {
        return isDirect;
    }

    public void setDirect(boolean direct) {
        isDirect = direct;
    }

    public String getValueProperty() {

        return StringUtils.isNotBlank(valueProperty) ? valueProperty : "rdfs:label";
    }

    public void setValueProperty(String valueProperty) {
        this.valueProperty = valueProperty;
    }
    //endregion

    //region Constructor

    public QueryProperty(String name, boolean isDirect, String valueProperty) {
        this.name = name;
        this.isDirect = isDirect;
        this.valueProperty = valueProperty;
    }

    //endregion
}

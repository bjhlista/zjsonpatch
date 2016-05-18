package com.flipkart.zjsonpatch;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public  class Bean extends Base {
    @JsonProperty("intValue")
    int intValue;

    @JsonProperty("stringValue")
    String stringValue;

    @JsonProperty("longs")
    List<Long> longs;


    public Bean() {}

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public List<Long> getLongs() {
        return longs;
    }

    public void setLongs(List<Long> longs) {
        this.longs = longs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Bean bean = (Bean) o;

        if (intValue != bean.intValue) return false;
        if (stringValue != null ? !stringValue.equals(bean.stringValue) : bean.stringValue != null) return false;
        return longs != null ? longs.equals(bean.longs) : bean.longs == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + intValue;
        result = 31 * result + (stringValue != null ? stringValue.hashCode() : 0);
        result = 31 * result + (longs != null ? longs.hashCode() : 0);
        return result;
    }
}
package com.flipkart.zjsonpatch;

import com.fasterxml.jackson.annotation.JsonProperty;

public   class Base {
    @JsonProperty("bValue")
    String bValue;

    @JsonProperty("a")
    A a;


    public Base() {}

    public String getbValue() {
        return bValue;
    }

    public void setbValue(String bValue) {
        this.bValue = bValue;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Base base = (Base) o;

        if (bValue != null ? !bValue.equals(base.bValue) : base.bValue != null) return false;
        return a != null ? a.equals(base.a) : base.a == null;

    }

    @Override
    public int hashCode() {
        int result = bValue != null ? bValue.hashCode() : 0;
        result = 31 * result + (a != null ? a.hashCode() : 0);
        return result;
    }
}
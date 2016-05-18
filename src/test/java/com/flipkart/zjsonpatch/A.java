package com.flipkart.zjsonpatch;

import com.fasterxml.jackson.annotation.JsonProperty;

public  class A {
    @JsonProperty("value")
    String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public A() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        A a = (A) o;

        return value != null ? value.equals(a.value) : a.value == null;

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
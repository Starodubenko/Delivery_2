package com.epam.star.entity;

public class Element {
    private String element;

    public Element(String element) {
        this.element = element;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    @Override
    public String toString() {
        return element;
    }
}

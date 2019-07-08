package cn.saltedfish.saltedcdd.util;

public class ValueContainer<T> {
    private T mValue;

    public ValueContainer() {
    }

    public ValueContainer(T pValue) {
        this.mValue = pValue;
    }

    public T getValue() {
        return mValue;
    }

    public void setValue(T pValue) {
        this.mValue = pValue;
    }
}
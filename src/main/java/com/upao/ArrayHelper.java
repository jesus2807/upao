package com.upao;


import java.lang.reflect.Array;

public class ArrayHelper {

    protected  <T> T[] addElement(T[] array, T element) {
        array = this.copyArray(element.getClass(), array, array.length);
        array[array.length-1] = element;
        return array;
    }

    private <T> T[] copyArray(Class<?> clazz, T[] source, int length) {
        T[] result = (T[]) Array.newInstance(clazz, length+1);

        for (int i = 0 ; i < length ; i++) {
            result[i] = source[i];
        }

        return result;
    }


}

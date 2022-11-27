// IMathsInterface.aidl
package com.example.aidlserver;

// Declare any non-default types here with import statements

interface IMathsInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int addition(int val1, int val2);
    int substraction(int val1, int val2);
    int multiplication(int val1, int val2);
    int division(int val1, int val2);
}
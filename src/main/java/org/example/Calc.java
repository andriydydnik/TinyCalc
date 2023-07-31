package org.example;

public class Calc {

    private final int LeftRange = -100;
    private final int RightRange = 100;

    public int sum(final int value1, final int value2, final int value3) {
        if (value3 == 0)
            throw new IllegalArgumentException();
        else if (value1 < LeftRange || value1 > RightRange)
            throw new IllegalArgumentException();
        else if (value2 < LeftRange || value2 > RightRange)
            throw new IllegalArgumentException();
        else if (value3 < LeftRange || value3 > RightRange)
            throw new IllegalArgumentException();

        return (value1 + value2 % 2) / (value3 % 3);
    }

    public int sqrt(final CalcExtra ce, final int value) {
        return ce.sqrt(value);
    }
}

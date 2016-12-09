package abc.music.core.domain;

import abc.music.core.exception.AbcException;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
public class Meter {

    public static final Integer DEFAULT_NUMERATOR = 3;
    public static final Integer DEFAULT_DENOMINATOR = 4;
    private Integer numerator = DEFAULT_NUMERATOR;
    private Integer denominator = DEFAULT_DENOMINATOR;
    private Boolean useSymbol;

    public Integer getNumerator() {
        return numerator;
    }

    public void setNumerator(Integer numerator) {
        if (numerator < 1) {
            throw new AbcException("The numerator must be a positive integer");
        }
        this.numerator = numerator;
    }

    public Integer getDenominator() {
        return denominator;
    }

    public void setDenominator(Integer denominator) {
        if (denominator < 1 || (denominator != 1 && denominator % 2 != 0)) {
            throw new AbcException("The denominator must be 1 or a positive power of 2");
        }
        this.denominator = denominator;
    }

    public Boolean useSymbol() {
        return useSymbol == null ? false : useSymbol;
    }

    public void setUseSymbol(Boolean useSymbol) {
        this.useSymbol = useSymbol;
    }

    public boolean isEmpty() {
        return numerator == null || denominator == null;
    }

    public String get() {
        if (useSymbol() && getNumerator() != null && getDenominator() != null) {
            if (getNumerator() == 2 && getDenominator() == 2) {
                return "M: C|";
            } else if (getNumerator() == 4 && getDenominator() == 4) {
                return "M: C";
            }
        }
        return "M: " + getNumerator() + "/" + getDenominator();
    }
}

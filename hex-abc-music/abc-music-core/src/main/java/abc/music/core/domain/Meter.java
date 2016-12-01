package abc.music.core.domain;

import abc.music.core.exception.AbcException;

/**
 * Created 2016-nov-27
 *
 * @author hl
 */
public class Meter extends Field {

    private Integer numerator;
    private Integer denominator;
    private Boolean useSymbol;

    public Meter() {
        super('M');
    }

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
        return useSymbol && denominator == 4 && (numerator == 2 || numerator == 4);
    }

    public void setUseSymbol(Boolean useSymbol) {
        this.useSymbol = useSymbol;
    }

    @Override
    public String getContent() {
        if (useSymbol()) {
            return getNumerator() == 2 ? "C|" : "C";
        }
        return getNumerator() + "/" + getDenominator();
    }

    @Override
    public void setFieldContent(String content) {
        if (content.matches("\\d+/\\d+")) {
            try {
                String[] parts = content.split("/");
                setNumerator(Integer.valueOf(parts[0]));
                setDenominator(Integer.valueOf(parts[1]));
                setUseSymbol(false);
            } catch (NumberFormatException | AbcException e) {
                throw new AbcException("The string " + content + " could not be used as meter");
            }
        } else if (content.equalsIgnoreCase("C")) {
            setNumerator(4);
            setDenominator(4);
            setUseSymbol(true);
        } else if (content.equalsIgnoreCase("C|") || content.equals("₵")) {
            setNumerator(2);
            setDenominator(4);
            setUseSymbol(false);
        }
    }

    public static void main(String[] args) {
    }
}

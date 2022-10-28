package exercise;

// BEGIN
public class ReversedSequence implements CharSequence{
    private final String string;

    public String getString() {
        return string;
    }

    public ReversedSequence(String string) {
        this.string = string;
    }

    @Override
    public int length() {
        char[] chars = getString().toCharArray();
        return chars.length;
    }

    @Override
    public char charAt(int index) {
        String stringNew = new StringBuilder(getString()).reverse().toString();
        char[] chars = stringNew.toCharArray();
        return chars[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        char[] chars = toString().toCharArray();
        StringBuilder result = new StringBuilder();
        for (var i = start; i < end; i++) {
            result.append(chars[i]);
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return new StringBuilder(getString()).reverse().toString();
    }
}
// END

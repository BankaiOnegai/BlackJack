public class Card {

    private final Value value;
    private final Color color;

    public Card(Value value, Color color) {
        this.value = value;
        this.color = color;
    }

    public String toString() {
        return String.format("%s%s", value.getSymbole(), color.getSymbole());
    }

    public String getColorSymbole() {
        return color.getSymbole();
    }

    public String getColorName() {
        return color.name();
    }

    public String getValueSymbole() {
        return value.getSymbole();
    }

    public int getPoints() {
        return value.getPoints();
    }
}
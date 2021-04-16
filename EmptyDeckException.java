public class EmptyDeckException extends Exception {
    public EmptyDeckException(String message) {
        super("message d'erreur\n" + message);
    }
}

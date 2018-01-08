package portfolio;


public class EvaluationException extends Exception{
    String text;

    public EvaluationException (String text) {
        this.text = text;
    }
}

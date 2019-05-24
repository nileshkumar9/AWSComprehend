package hello;

/**
 * TODO: <Add a description for the class here>
 *
 * @author nileshkumar6
 * @since 1.5.0
 */
public class SentimentScore {
    private final Float positive;
    private final Float negative;
    private final Float neutral;
    private final Float mixed;

    public SentimentScore(Float positive, Float negative, Float neutral, Float mixed) {
        this.positive = positive;
        this.negative = negative;
        this.neutral = neutral;
        this.mixed = mixed;
    }

    public Float getPositive() {
        return positive;
    }

    public Float getNegative() {
        return negative;
    }

    public Float getNeutral() {
        return neutral;
    }

    public Float getMixed() {
        return mixed;
    }
}

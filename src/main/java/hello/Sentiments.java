package hello;

/**
 * TODO: <Add a description for the class here>
 *
 * @author nileshkumar6
 * @since 1.5.0
 */

public class Sentiments {
    private final String sentiment;
    SentimentScore sentimentScore;

    public Sentiments(String sentiment, SentimentScore sentimentScore) {
        this.sentiment = sentiment;
        this.sentimentScore = sentimentScore;
    }

    public String getSentiment() {
        return sentiment;
    }

    public SentimentScore getSentimentScore() {
        return sentimentScore;
    }

    public void setSentimentScore(SentimentScore sentimentScore) {
        this.sentimentScore = sentimentScore;
    }
}

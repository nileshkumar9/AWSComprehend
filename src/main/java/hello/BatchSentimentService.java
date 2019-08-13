package hello;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import com.amazonaws.services.comprehend.model.BatchDetectEntitiesItemResult;
import com.amazonaws.services.comprehend.model.BatchDetectEntitiesRequest;
import com.amazonaws.services.comprehend.model.BatchDetectEntitiesResult;
import com.amazonaws.services.comprehend.model.BatchDetectSentimentItemResult;
import com.amazonaws.services.comprehend.model.BatchDetectSentimentRequest;
import com.amazonaws.services.comprehend.model.BatchDetectSentimentResult;
import com.amazonaws.services.comprehend.model.BatchItemError;
import com.amazonaws.services.comprehend.model.DetectSentimentRequest;
import com.amazonaws.services.comprehend.model.DetectSentimentResult;

import java.io.BufferedReader; import java.io.IOException; import java.nio.charset.StandardCharsets; import java.nio.file.Files; import java.nio.file.Path; import java.nio.file.Paths; import java.util.ArrayList; import java.util.List;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: <Add a description for the class here>
 *
 * @author nileshkumar6
 * @since 1.5.0
 */
public class BatchSentimentService {
    public static Sentiments getBatchSentiments(){

                // Create credentials using a provider chain. For more information, see
                // https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html
                AWSCredentialsProvider awsCreds = DefaultAWSCredentialsProviderChain.getInstance();

//                AmazonComprehend comprehendClient =
//                    AmazonComprehendClientBuilder.standard()
//                        .withCredentials(awsCreds)
//                        .withRegion("region")
//                        .build();

        AmazonComprehend comprehendClient =
            AmazonComprehendClientBuilder.standard()
                // .withCredentials(awsCreds)
                //.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(String accessKey, String secretKey)) )
                // Using my credentials of alexa poc box
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(String accessKey, String secretKey)) )
                .withRegion("us-east-1")
                .build();

               // String[] textList = {"I love Seattle", "Today is Sunday", "Tomorrow is Monday", "I love Seattle"};

                // Adding list values from csv
        List<String> textList = readTextFromCSV("C:\\Users\\nileshkumar6\\Desktop\\WorkData\\Projects Info\\ideaThon2019\\Sentiments\\gs-rest-service-master\\complete\\src\\resource\\faceBookData.csv");
        // Adding list value from csv

                // Call detectEntities API
                System.out.println("Calling BatchDetectEntities");
                BatchDetectEntitiesRequest batchDetectEntitiesRequest = new BatchDetectEntitiesRequest().withTextList(textList)
                    .withLanguageCode("en");
                BatchDetectEntitiesResult batchDetectEntitiesResult  = comprehendClient.batchDetectEntities(batchDetectEntitiesRequest);

        BatchDetectSentimentRequest batchDetectSentimentRequest = new BatchDetectSentimentRequest().withTextList(textList)
            .withLanguageCode("en");

        BatchDetectSentimentResult batchDetectSentimentResult = comprehendClient.batchDetectSentiment(batchDetectSentimentRequest);

        Float positive = new Float(0);
        Float negative = new Float(0);
        Float neutral = new Float(0);
        Float mixed  = new Float(0);
       int responseSize = batchDetectSentimentResult.getResultList().size();
        for(BatchDetectSentimentItemResult item : batchDetectSentimentResult.getResultList()) {
            System.out.println( "> Sentiments "+ item);

            positive = positive + item.getSentimentScore().getPositive();
            negative = negative + item.getSentimentScore().getNegative();
            neutral = neutral + item.getSentimentScore().getNeutral();
            mixed = mixed + item.getSentimentScore().getMixed();

        }

        positive = positive / responseSize;
        negative = negative / responseSize;
        neutral = neutral / responseSize;
        mixed = mixed / responseSize;

       String calculatedSentiment =  max(positive,negative,neutral,mixed);


//                for(BatchDetectEntitiesItemResult item : batchDetectEntitiesResult.getResultList()) {
//                    System.out.println(item);
//                }
//
//                // check if we need to retry failed requests
//                if (batchDetectEntitiesResult.getErrorList().size() != 0)
//                {
//                    System.out.println("Retrying Failed Requests");
//                    ArrayList<String> textToRetry = new ArrayList<String>();
//                    for(BatchItemError errorItem : batchDetectEntitiesResult.getErrorList())
//                    {
//                        textToRetry.add(textList[errorItem.getIndex()]);
//                    }
//
//                    batchDetectEntitiesRequest = new BatchDetectEntitiesRequest().withTextList(textToRetry).withLanguageCode("en");
//                    batchDetectEntitiesResult  = comprehendClient.batchDetectEntities(batchDetectEntitiesRequest);
//
//                    for(BatchDetectEntitiesItemResult item : batchDetectEntitiesResult.getResultList()) {
//                        System.out.println(item);
//                    }
//
//                }
                System.out.println("End of DetectEntities");


        // Building return object
        Sentiments sentiments = new Sentiments(calculatedSentiment.toUpperCase() ,
                                               new SentimentScore(positive,
                                                                  negative,
                                                                  neutral,
                                                                  mixed));
        // return detectSentimentResult.getSentiment();
        return sentiments;

    }

    private static List<String> readTextFromCSV(String fileName) {
        List<String> stringLists = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        // create an instance of BufferedReader
        // using try with resource, Java 7 feature to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {
                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(",");

                //Book book = createBook(attributes);
                // adding book into ArrayList
                stringLists.add(attributes[0]);
                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return stringLists;


    }

    public static String max(Float positive, Float negative, Float neutral, Float mixed) {

        float max = positive;
        StringBuffer sentiments = new StringBuffer("POSITIVE");


        if (negative > max){
            max = negative;
            sentiments =  new StringBuffer("Negative");
        }

        if (neutral > max){
            max = neutral;
            sentiments =  new StringBuffer("Neutral");
        }
        if (mixed > max){
            max = mixed;
            sentiments = new StringBuffer("Mixed");

        }

        return sentiments.toString();
    }

}

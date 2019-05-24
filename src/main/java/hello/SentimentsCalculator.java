package hello;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import com.amazonaws.services.comprehend.model.DetectSentimentRequest;
import com.amazonaws.services.comprehend.model.DetectSentimentResult;


/**
 * TODO: <Add a description for the class here>
 *
 * @author nileshkumar6
 * @since 1.5.0
 */
public class SentimentsCalculator {

        public static void main( String[] args )
        {

//            String text = "I love you baby";
//
//            // Create credentials using a provider chain. For more information, see
//            // https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html
//            //AWSCredentialsProvider awsCreds = DefaultAWSCredentialsProviderChain.getInstance();
//
//
////            BasicAWSCredentials awsCreds = new BasicAWSCredentials("access_key_id", "secret_key_id");
////            AmazonS3 s3Client = AmazonS3ClientBuilder.standard() .
////                withCredentials(new AWSStaticCredentialsProvider(awsCreds)) .build();
//
//            AmazonComprehend comprehendClient =
//                AmazonComprehendClientBuilder.standard()
//                   // .withCredentials(awsCreds)
//                    .withCredentials(new AWSStaticCredentialsProvider( new BasicAWSCredentials( "AKIAIXJBKHOT3MX3X7WA", "r5PPCMQlupuieg/+f1xrTJL1PY/0qWSTfJB6YNk5")) )
//                    .withRegion("us-east-1")
//                    .build();
//
//            // Call detectSentiment API
//            System.out.println("Calling DetectSentiment");
//            DetectSentimentRequest detectSentimentRequest = new DetectSentimentRequest().withText(text)
//                .withLanguageCode("en");
//            DetectSentimentResult detectSentimentResult = comprehendClient.detectSentiment(detectSentimentRequest);
//            System.out.println(detectSentimentResult);
//            System.out.println("End of DetectSentiment\n");
//            System.out.println( "Done" );
        }



}

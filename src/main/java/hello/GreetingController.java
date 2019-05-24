package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @RequestMapping("/sentiments")
    //public Sentiments sentiments(@RequestParam(value="text", defaultValue="World") String text) {
        public Sentiments sentiments(@RequestParam(value="text", defaultValue="World") String text) {
        //return new Sentiments(1, 2,3);
        return SentimentsService.getSetiments(text);
    }

    @RequestMapping("/batchSentiments")
    //public Sentiments sentiments(@RequestParam(value="text", defaultValue="World") String text) {
    public Sentiments batchSentiments(@RequestParam(value="text", defaultValue="World") String text) {
        //return new Sentiments(1, 2,3);
        return BatchSentimentService.getBatchSentiments();
    }

}

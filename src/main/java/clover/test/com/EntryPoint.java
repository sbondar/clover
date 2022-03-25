package clover.test.com;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class EntryPoint {

    public static void main (String[] args) {
//new RevenueSummaryController().get... (enum MONTH??)  <- service.getData()
        //group by the same period??

//input.json -> RevenueSummaryDto
        try {
            String jsonString = Files.readString(Paths.get("input.json"), StandardCharsets.US_ASCII);
        } catch (IOException e) {
            log.warn("Input data file is not found");
        }

        ObjectMapper mapper = new ObjectMapper();
        RevenueSummary revenueSummaryDto = mapper.readValue(jsonString, RevenueSummary.class);
    }
}

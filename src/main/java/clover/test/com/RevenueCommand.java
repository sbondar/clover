package clover.test.com;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import picocli.CommandLine;

import java.io.File;
import java.util.Map;
import java.util.concurrent.Callable;

import static clover.test.com.ResponseBuilder.makeJsonResponse;
import static org.slf4j.LoggerFactory.getLogger;

@CommandLine.Command(name = "revenue aggregator", mixinStandardHelpOptions = true, version = "1.0",
    description = "Aggregates revenue by time range.")
public class RevenueCommand  implements Callable<Integer> {
    protected static final Logger log = getLogger(RevenueCommand.class);

    @CommandLine.Option(names = {"-f", "--file"}, required = true, description = "Revenue JSON file")
    private String fileOption = "";

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Integer call() throws Exception {
        // Read file from location specified by CLI option -f (fileOption)
        final File file = new File(this.fileOption);
        if (!file.exists()) {
            System.err.println("File '" + this.fileOption + "' not found");
            return 1;
        }

        // Parse input file as json
        final JsonNode rootNode = mapper.readTree(file);

        // Validate input format
        if (!rootNode.hasNonNull("summaries") ||
            !rootNode.path("summaries").isArray()) {
            System.err.println("Invalid input format");
            return 2;
        }

        // Run aggregation that walk over the json and calculate revenue by month and app
        final Aggregator aggregator = new Aggregator(rootNode);
        aggregator.aggregate();
        final Map<String, Bucket> aggregationBucket = aggregator.getAggregationBucket();

        // Convert aggregation results into resulting json
        final ObjectNode responseNode = makeJsonResponse(aggregator, aggregationBucket);

        // Print resulting json to STDOUT
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseNode));

        return 0;
    }
}

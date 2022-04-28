package clover.test.com;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Aggregator {

    private int totalRevenue;
    private Map<String, Bucket> aggregationBucket;
    private JsonNode rootNode;

    public Aggregator(final JsonNode rootNode) {
        this.rootNode = rootNode;
        this.totalRevenue = 0;
        this.aggregationBucket = new HashMap<>();
    }

    public void aggregate() {
        final JsonNode summaries = this.rootNode.path("summaries");

        final Iterator<JsonNode> elements = summaries.elements();
        while (elements.hasNext()) {
            final JsonNode summary = elements.next();
            int revenue;
            if (summary.hasNonNull("revenue_amount")) {
                revenue = summary.get("revenue_amount").asInt();
                this.totalRevenue += revenue;

                if (summary.hasNonNull("month")) {
                    final String bucketName = summary.path("month").asText();
                    Bucket bucket = this.aggregationBucket.computeIfAbsent(bucketName, key -> new Bucket());
                    bucket.value += revenue;

                    if (summary.hasNonNull("app_id")) {
                        final String bucketNameChild = summary.path("app_id").asText();
                        Bucket bucketChild = bucket.child.computeIfAbsent(bucketNameChild, key -> new Bucket());
                        bucketChild.value += revenue;
                    }
                }
            }
        }
    }

    public int getTotalRevenue() {
        return totalRevenue;
    }

    public Map<String, Bucket> getAggregationBucket() {
        return aggregationBucket;
    }
}

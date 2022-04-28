package clover.test.com;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Map;

public class ResponseBuilder {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static ObjectNode makeJsonResponse(Aggregator aggregator, Map<String, Bucket> aggregationBucket) {
        final ObjectNode responseNode = mapper.createObjectNode();
        final ArrayNode periodsNode = mapper.createArrayNode();
        responseNode.put("totalRevenue", aggregator.getTotalRevenue());
        responseNode.set("periods", periodsNode);

        aggregationBucket.forEach(
            (periodKey, periodBucket) -> {
                final ObjectNode monthBucketNode = mapper.createObjectNode();
                periodsNode.add(monthBucketNode);
                monthBucketNode.put("period", periodKey);
                monthBucketNode.put("revenueForPeriod", periodBucket.value);

                final ArrayNode appsNode = mapper.createArrayNode();
                monthBucketNode.set("apps", appsNode);
                periodBucket.child.forEach(
                    (appKey, appBucket) -> {
                        final ObjectNode appBucketNode = mapper.createObjectNode();
                        appsNode.add(appBucketNode);
                        appBucketNode.put("id", appKey);
                        appBucketNode.put("revenueForApp", appBucket.value);
                    }
                );
            }
        );
        return responseNode;
    }
}

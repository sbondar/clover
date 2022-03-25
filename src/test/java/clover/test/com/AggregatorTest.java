package clover.test.com;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class AggregatorTest {

    protected ObjectMapper mapper = new ObjectMapper();

    @Test
    void aggregate() throws IOException {
        //given
        final String inputJsonStr = Resources.toString(
            Resources.getResource("input.json"), StandardCharsets.UTF_8);

        //when
        Aggregator aggregator = new Aggregator(mapper.readTree(inputJsonStr));
        aggregator.aggregate();

        //then
        assertEquals(2948422, aggregator.getTotalRevenue());

        Bucket bucket1 = aggregator.getAggregationBucket().get("2020-01-01");
        Bucket bucket1_1 = bucket1.child.get("891");
        Bucket bucket1_2 = bucket1.child.get("4317");
        Bucket bucket2 = aggregator.getAggregationBucket().get("2020-02-01");
        Bucket bucket2_1 = bucket2.child.get("891");
        Bucket bucket2_2 = bucket2.child.get("4317");

        assertEquals(1492161, bucket1.value);
        assertEquals(33865, bucket1_1.value);
        assertEquals(1458296, bucket1_2.value);

        assertEquals(1456261, bucket2.value);
        assertEquals(79780, bucket2_1.value);
        assertEquals(1376481, bucket2_2.value);
    }
}
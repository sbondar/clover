package clover.test.com;

import java.util.HashMap;
import java.util.Map;

public class Bucket {
    Integer value = 0;
    Map<String, Bucket> child = new HashMap<>();
}

package clover.test.com;

import java.io.Serializable;
import java.util.List;

public class RevenueForPeriod implements Serializable {

    private String period;
    private Integer revenueForPeriod;
    private List<RevenueForApp> apps;
}

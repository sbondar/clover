package clover.test.com;

import java.io.Serializable;

public class Revenue implements Serializable {

    @JsonProperty("month")
    private String formattedDate;
    @JsonProperty("developer_id")
    private Integer developerId;
    @JsonProperty("app_id")
    private Integer appId;
    @JsonProperty("subscription_id")
    private Integer subscriptionId;
    @JsonProperty("revenue_amount")
    private Integer revenueAmount;
}
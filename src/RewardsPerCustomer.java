import java.util.Map;

public class RewardsPerCustomer {

    private Map<String, Long> rewardsPerMonthMap;
    private long totalRewardsEarned;

    public void setTotalRewardsEarned(long totalRewardsEarned) {
        this.totalRewardsEarned = totalRewardsEarned;
    }

    public void setRewardsPerMonthMap(Map<String, Long> rewardsPerMonthMap) {
        this.rewardsPerMonthMap = rewardsPerMonthMap;
    }

    public long getTotalRewardsEarned() {
        return totalRewardsEarned;
    }

    public Map<String, Long> getRewardsPerMonthMap() {
        return rewardsPerMonthMap;
    }

    @Override
    public String toString() {
        return "rewardsPerMonth:" + this.rewardsPerMonthMap + "totalRewardsEarned:" + this.totalRewardsEarned;
    }
}

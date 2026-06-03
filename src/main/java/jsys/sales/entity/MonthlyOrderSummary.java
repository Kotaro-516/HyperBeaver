package jsys.sales.entity;

/**
 * 月別受注集計結果を保持するクラス。
 */
public class MonthlyOrderSummary {
    private String custCode;
    private String custName;
    private long totalPrice;

    public MonthlyOrderSummary() {
    }

    public MonthlyOrderSummary(String custCode, String custName, long totalPrice) {
        this.custCode = custCode;
        this.custName = custName;
        this.totalPrice = totalPrice;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }
}

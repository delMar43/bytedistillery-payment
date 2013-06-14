package at.pagu.payment.paypal.starter;

import java.math.BigDecimal;

public class StarterXclickFormData extends FormData {

  private String cmd = "_xclick";

  private BigDecimal amount;
  private String item_name;
  private String item_number;

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getItem_name() {
    return item_name;
  }

  public void setItem_name(String item_name) {
    this.item_name = item_name;
  }

  public String getItem_number() {
    return item_number;
  }

  public void setItem_number(String item_number) {
    this.item_number = item_number;
  }

}

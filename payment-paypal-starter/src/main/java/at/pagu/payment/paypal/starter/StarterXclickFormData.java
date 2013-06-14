package at.pagu.payment.paypal.starter;

import java.math.BigDecimal;

public class StarterXclickFormData extends FormData {

  private String cmd = "_xclick";
  private BigDecimal amount;
  private String item_name;
  private String item_number;
}

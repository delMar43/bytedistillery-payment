package at.pagu.payment.paypal.starter;

import java.util.List;

public class StarterCartFormData extends FormData {

  private String cmd = "_cart";
  private String upload = "1";

  private List<CartItem> items;
}

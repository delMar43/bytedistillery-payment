package at.pagu.payment.paypal.starter;

import org.junit.Before;
import org.junit.Test;

public class PayPalStarterTemplateTest {

  private PayPalStarterTemplate template;

  @Before
  public void setup() {
    template = new PayPalStarterTemplate();
  }

  @Test
  public void generateCartHtml() {
    StarterCartFormData formData = generateCartFormData();
    template.setFormTarget("_blank");
    template.setFormData(formData);

    String html = template.generateHtmlForm();
    System.out.println(html);
  }

  private StarterCartFormData generateCartFormData() {
    StarterCartFormData formData = new StarterCartFormData();

    formData.setBusiness("payment@byte-distillers.com");
    formData.setCurrency_code("EUR");
    formData.setCancel_url("http://www.orf.at");
    formData.setSuccess_url("http://www.google.at");

    return formData;
  }

  @Test
  public void generateXclickHtml() {
    template.generateHtmlForm();
  }
}

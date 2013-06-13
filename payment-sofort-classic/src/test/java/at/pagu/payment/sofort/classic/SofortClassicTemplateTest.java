package at.pagu.payment.sofort.classic;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

/**
 * project_id and user_id taken from brunner
 * 
 */
public class SofortClassicTemplateTest {

  private SofortClassicTemplate template;

  @Before
  public void setup() {
    template = new SofortClassicTemplate();

    ClassicFormData formData = new ClassicFormData();
    formData.setProject_id("157999");
    formData.setUser_id("72686");
    formData.setCurrency_id("EUR");
    formData.setReason_1("payment-sofort-classic");
    formData.setReason_2("by pagu.at");

    template.setFormData(formData);
  }

  @Test
  public void validDataSucceeds() {
    ClassicFormData formData = template.getFormData();
    formData.setAmount(BigDecimal.valueOf(123.456));

    formData.getUserVariables().put("mykey", "myvalue");

    String html = template.generateHtmlForm();
    System.out.println(html);
  }
}

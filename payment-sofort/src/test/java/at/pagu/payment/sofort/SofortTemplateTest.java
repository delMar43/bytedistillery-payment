package at.pagu.payment.sofort;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.pagu.payment.sofort.dto.SofortTransactionParameters;
import at.pagu.payment.sofort.dto.TransactionResponse;

public class SofortTemplateTest {

  private SofortTemplate template;

  @Before
  public void setup() throws Exception {
    SofortTransactionParameters parameters = new SofortTransactionParameters();
    parameters.setApiKey("be53a5847f9aa327dbfd7fa431bc5cbe");
    parameters.setCustomerId("68170");
    parameters.setProjectId(149779);

    template = new SofortTemplate();
    template.setTransactionParameters(parameters);

    template.afterPropertiesSet();
  }

  @Test
  public void sucessfullyTransmitTransactionData() throws Exception {
    SofortTransactionParameters transactionData = new SofortTransactionParameters();
    transactionData.setAmount(BigDecimal.valueOf(200.3000));
    transactionData.setCurrencyCode("EUR");
    transactionData.setReasons(Collections.singletonList("meins"));

    TransactionResponse data = template.startTransaction(transactionData);

    Assert.assertNotNull(data);
    Assert.assertTrue(data.isSuccessful());
  }

  @Test
  public void missingReasonsFails() throws Exception {
    SofortTransactionParameters transactionData = new SofortTransactionParameters();
    transactionData.setAmount(BigDecimal.valueOf(200.3000));
    transactionData.setCurrencyCode("EUR");

    TransactionResponse data = template.startTransaction(transactionData);

    Assert.assertNotNull(data);
    Assert.assertFalse(data.isSuccessful());
  }

  @Test
  public void currency() {
    BigDecimal d = new BigDecimal(123456.1);
    NumberFormat df = DecimalFormat.getNumberInstance();
    String formatiert = df.format(d);
    System.out.println(formatiert);
  }

  @Test
  public void successfullyQueryTransactionStatus() {
    template.queryTransactionStatus("68170-149779-513D952E-D294");
  }
}

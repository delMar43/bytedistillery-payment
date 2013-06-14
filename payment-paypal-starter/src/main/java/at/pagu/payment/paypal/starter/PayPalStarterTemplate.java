package at.pagu.payment.paypal.starter;

import java.lang.reflect.Field;

import javax.xml.bind.annotation.XmlAttribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PayPalStarterTemplate {

  private static final Logger logger = LoggerFactory.getLogger(PayPalStarterTemplate.class);

  private String formAction = "https://www.paypal.com/cgi-bin/webscr";
  private String formTarget;
  private String formName = "paypalStarterForm";
  private FormData formData;

  public String generateHtmlForm() {
    StringBuilder result = generateFormTag();

    generateFieldValues(formData.getClass(), result);

    result.append("</form>\r\n");

    return result.toString();
  }

  private StringBuilder generateFormTag() {
    StringBuilder result = new StringBuilder("<form action=\"" + formAction + "\" method=\"post\"");

    if (formTarget != null) {
      result.append(" target=\"" + formTarget + "\"");
    }

    if (formName != null) {
      result.append(" name=\"" + formName + "\"");
    }

    result.append(">\r\n");

    return result;
  }

  private void generateFieldValues(Class<?> clazz, StringBuilder result) {
    if (clazz.getSuperclass() != Object.class) {
      generateFieldValues(clazz.getSuperclass(), result);
    }

    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      String value = handleField(field);
      if (value != null) {
        String inputName;
        if (field.isAnnotationPresent(XmlAttribute.class)) {
          XmlAttribute annotation = field.getAnnotation(XmlAttribute.class);
          inputName = annotation.name();
        } else {
          inputName = field.getName();
        }
        result.append(createInput(inputName, value));
      }
    }
  }

  private String handleField(Field field) {
    boolean accessible = field.isAccessible();
    if (!accessible) {
      field.setAccessible(true);
    }
    try {
      Object valueObject = field.get(formData);
      field.setAccessible(accessible);

      if (valueObject == null) {
        return null;
      }
      String value = valueObject.toString();

      return value;
    } catch (Exception e) {
      return null;
    }
  }

  private String createInput(String name, String value) {
    return "<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\" />\r\n";
  }

  public void setFormData(FormData formData) {
    this.formData = formData;
  }

  public FormData getFormData() {
    return formData;
  }

  public String getFormAction() {
    return formAction;
  }

  public void setFormAction(String formAction) {
    this.formAction = formAction;
  }

  public String getFormName() {
    return formName;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }

  public String getFormTarget() {
    return formTarget;
  }

  public void setFormTarget(String formTarget) {
    this.formTarget = formTarget;
  }
}

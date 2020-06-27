package testI18N;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class I18NTest {
	@Test
	public void test() {
		ResourceBundle bundle = ResourceBundle.getBundle("resources.message",Locale.CANADA);
		System.out.println(bundle.getString("login.username"));
		Locale.setDefault(Locale.US);
		bundle = ResourceBundle.getBundle("resources.message");
		System.out.println(bundle.getString("login.username"));
	}
}

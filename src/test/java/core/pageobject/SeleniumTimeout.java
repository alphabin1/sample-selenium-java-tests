package core.pageobject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to specify ExplicitWait on specific elements in
 * Medium Tests(Selenium)
 * 
 * @author prat3ik
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SeleniumTimeout {
	int explicitWait() default 90;

}

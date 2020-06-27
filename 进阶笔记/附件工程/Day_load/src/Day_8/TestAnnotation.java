package Day_8;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface TestAnnotation {
	int happyDegree() default 6;
	String className() default "ac";
	Season happySeason();
	TestAnnotation1 happyAnnotation();
	String[] methodsName();
}

package io.fabianterhorst.fastlayout.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
@Inherited
public @interface Layouts {
    int[] ids() default {};
    String[] layouts() default {};
    boolean all() default false;
}

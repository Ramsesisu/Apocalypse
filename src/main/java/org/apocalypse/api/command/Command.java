package org.apocalypse.api.command;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    String name();

    String usage();

    String group() default "apocalypse";

    String permissions() default "";

    boolean op() default false;

    String[] aliases() default {};

    String description() default "";

    String prefix() default "";

    Prefix.Color prefixColor() default Prefix.Color.WHITE;

    Prefix.Color prefixGradient() default Prefix.Color.WHITE;
}

package org.apocalypse.api.command;

public @interface Command {

    String name();

    String usage();

    String permissions();

    String[] aliases() default {};

    String description() default "";

    String prefix() default "";

    Prefix.Color prefixColor() default Prefix.Color.WHITE;

    Prefix.Color prefixGradient() default Prefix.Color.WHITE;
}

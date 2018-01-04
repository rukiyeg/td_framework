package fr.afcepf.dja.mvc2.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ActionFormAnnotation {
	String url();
}

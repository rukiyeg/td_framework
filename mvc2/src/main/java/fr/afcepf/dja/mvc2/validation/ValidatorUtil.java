package fr.afcepf.dja.mvc2.validation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import fr.afcepf.dja.mvc2.annotations.NotEmpty;
import fr.afcepf.dja.mvc2.annotations.Username;

public class ValidatorUtil {

	private ValidatorUtil() {

	}

	public static boolean validateEntity(Object object, StringBuilder error) {
		error.append("<ul>");
		boolean isValid = true;
		try {
			for (Field field : object.getClass().getDeclaredFields()) {

				final NotEmpty notEmpty = field.getAnnotation(NotEmpty.class);
				if (notEmpty != null) {
					Method m = object.getClass().getMethod("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
					String fieldValue = (String) m.invoke(object);
					if (fieldValue.isEmpty()) {
						error.append("<li>");
						error.append(field.getName());
						error.append(" est vide</li>");
						isValid = false;
					}
				}

				final Username login = field.getAnnotation(Username.class);
				if (login != null) {
					Method m = object.getClass().getMethod("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
					String fieldValue = (String) m.invoke(object);
					if (!fieldValue.matches("^[a-z0-9._-]+@[a-z0-9._-]{2,}\\.[a-z]{2,4}$")) {
						error.append("<li>");
						error.append(field.getName());
						error.append(" n'est pas une adresse mail</li>");
						isValid = false;
					}
				}
			}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		}
		error.append("</ul>");

		return isValid;
	}

}

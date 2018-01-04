
package fr.afcepf.dja.mvc2.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import fr.afcepf.dja.mvc2.interfaces.IActionForm;

public class BeanPopulate {

	private static BeanPopulate myBeanPopulate;

	private BeanPopulate() {

	}

	public static BeanPopulate getInstance() {
		if (myBeanPopulate == null) {
			myBeanPopulate = new BeanPopulate();
		}
		return myBeanPopulate;
	}

	public Object populateBean(Object object, Map<String, String[]> params) {
		try {
			for (String key : params.keySet()) {
				String methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
				Method m = object.getClass().getMethod(methodName, String.class);
				m.invoke(object, params.get(key));
			}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}

		return object;
	}
	
	public void populateRequest(HttpServletRequest request, IActionForm actionForm) {

		for (Field key : actionForm.getClass().getDeclaredFields()) {
			Method m;
			try {
				m = actionForm.getClass().getMethod("get" + key.getName().substring(0, 1).toUpperCase() + key.getName().substring(1));
				String value = (String) m.invoke(actionForm);
				request.setAttribute(key.getName(), value);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}

	}
	
	
}

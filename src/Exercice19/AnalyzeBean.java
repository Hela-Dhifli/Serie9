package Exercice19;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class AnalyzeBean {
	
		//Methode qui retourne le nom d'une classe
	public String getClassName(Object o) {
		
		return (o.getClass().getName()); 
	}
	
		// Methode qui prend en parametre un nom complet de classe et qui retourne une instance de la classe
		// Condition : il faut que la classe possede un constructeur vide
	public Object getInstance(String className) {
	
		try {
			 Class<?> myClass = Class.forName(className);
			 Object newInstance = myClass.getConstructor().newInstance();
		 
			 return newInstance;
		 
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException| NoSuchMethodException |IllegalArgumentException
				|InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
		
	}
		
		// Methode qui retounre la liste des noms des proprites des objets passes en parametre
		// on reccupere toutes les  methodes qui commencent par get ou qui commencent par is 
	public List<String> getProperties(Object o) {
		
		List<String> listOfProperties = new ArrayList<>();
		
		Method[] myClassMethods = o.getClass().getMethods(); //getMethods() car les proprites d'un objet
															// sont celles definies sur sa classe propre, et sur toutes les superClass
		
		for(Method m : myClassMethods) {
			
			if( (m.getName().startsWith("is") || 
				 m.getName().startsWith("get")) && 
				 m.getParameterCount() == 0 &&
				 Modifier.isPublic(m.getModifiers())) {

				String nameOfProperty = " ";
				if (m.getName().startsWith("is")) 
					nameOfProperty = m.getName().substring(2);
				else
					nameOfProperty = m.getName().substring(3);
				
				nameOfProperty = 
						nameOfProperty.substring(0, 1).toLowerCase() + 
						nameOfProperty.substring(1);
				listOfProperties.add(nameOfProperty);
			}
				
				
		}
		return (listOfProperties);
	    
	}

	
		//Methode qui retourne la valeur de la proprietes d'un bean
		//le type retourne par cette  methode doit  etre Field
		// cette méthode accéde seulement aux champs de la classe et pas au champ de la superClass
	public Field get(Object bean, String property) {
		
		Class<?> classBean = bean.getClass();
		try {
			Field field = classBean.getDeclaredField(property);
			field.setAccessible(true);
			return (field);
		} catch (NoSuchFieldException e) {
			System.out.println(e);
		}
		return null;
		
	}
	
		// Cette méthode retourne la valeur d'un champ
		// cette méthode accéde aux champs de la classe et de superClass
	public Object get2(Object bean, String property)   {
		
		try {
			String methodName = 
					"get" + property.substring(0, 1).toUpperCase() + property.substring(1); 
			Method getProprety = bean.getClass().getMethod(methodName);
			return (getProprety.invoke(bean));
			
			
		} catch (IllegalArgumentException | NoSuchMethodException | SecurityException | 
				IllegalAccessException | InvocationTargetException   e) {
			System.out.println(e);
		}
		return null ;
		
	}
	
		// Methode qui prend un bean et un nom de propriete parametre, et qui enregistre la valeur value pour cette propriete  de ce bean
	public void set(Object bean, String property, Object value)   {
	
		try {
			String methodName = 
					"set" + property.substring(0, 1).toUpperCase() + property.substring(1); 
			Method setProprety = bean.getClass().getMethod(methodName, value.getClass());
			setProprety.invoke(bean, value);
			
			
		} catch (IllegalArgumentException | NoSuchMethodException | SecurityException | 
				IllegalAccessException | InvocationTargetException   e) {
			System.out.println(e);
		}
		
	}
	

		
	

}

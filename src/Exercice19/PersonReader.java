package Exercice19;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonReader extends FileReader {

	private FileReader fR ;
	
	public PersonReader(FileReader fr) throws FileNotFoundException {
		super(FileDescriptor.in);
		this.fR = fr;
	}

	List<Object> readPersonReader() {

		AnalyzeBean a = new AnalyzeBean ();
		Set<String> beanKeys = new HashSet<>();
		Map<String, Object> beanRegistry = new HashMap<>();
		try (FileReader freader = this.fR ; 
			BufferedReader person = new BufferedReader(freader)) {
			
			String line = person.readLine();
			while (line != null) {
				if (!line.startsWith("#")) {
					
					String[] element = line.split("=");
					if (element[0].equals("bean.name")) {
						beanKeys.add(element[1]);
					
					} else if (element[0].endsWith(".class")) {
						String beanKey = element[0].substring(0, element[0].indexOf('.'));
						String className = element[1];
						Class<?> beanClass = Class.forName(className);
						Constructor<?> beanEmptyConstructor = beanClass.getConstructor();
						Object bean = beanEmptyConstructor.newInstance();
						beanRegistry.put(beanKey, bean);
					
						
					} else if (element[0].endsWith(".lastName")) {
						String beanKey = element[0].substring(0, element[0].indexOf('.'));
						String lastName = element[1];
						
						Object bean = beanRegistry.get(beanKey);
						a.set(bean, "lastName", lastName);
					
					}else if (element[0].endsWith(".firstName")) {
						String beanKey = element[0].substring(0, element[0].indexOf('.'));
						String firstName = element[1];
						
						Object bean = beanRegistry.get(beanKey);
						a.set(bean, "firstName", firstName);
					
					} else if (element[0].endsWith(".age")) {
						String beanKey = element[0].substring(0, element[0].indexOf('.'));
						int age = Integer.parseInt(element[1]);
						
						Object bean = beanRegistry.get(beanKey);
						a.set(bean, "age", age);
					}
					else if (element[0].endsWith(".salary")) {
						String beanKey = element[0].substring(0, element[0].indexOf('.'));
						int salary = Integer.parseInt(element[1]);
						
						Object bean = beanRegistry.get(beanKey);
						a.set(bean, "salary", salary);
					}
					
				}
				line = person.readLine();
			}
			
		} catch (IOException | 
				ClassNotFoundException | 
				NoSuchMethodException | SecurityException | 
				InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		Collection<Object> coll  = beanRegistry.values();
		List<Object> list = coll.stream().collect(Collectors.toList());
		return (list);
	}

}

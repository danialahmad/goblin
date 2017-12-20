package com.gen.entity;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JVar;
import org.apache.commons.io.FilenameUtils;

public class CreateFileTest {
	private static final String LOCATION = "../../../../../../../../src/test/java";
	private static final String PACKAGE_NAME = "com.goblin.kemas.service.generic";
	private static final String PARENT_CLASS = "com.goblin.kemas.service.generic.GenericService";
	private static final String ENTITY_PACKAGE = "com.goblin.kemas.model.entity.";
	private static final String REPOSITORY_PACKAGE = "com.goblin.kemas.model.repository.";
	private static final String SERVICE_ANNOTATION = "org.springframework.stereotype.Service";
	private static final String AUTOWIRED_ANNOTATION = "org.springframework.beans.factory.annotation.Autowired";

	public static void main(String[] args) throws ClassNotFoundException {
		String directory = args[0];
		String location= args[1];
		Collection<File> files = FileUtils.listFiles(new File(directory), null,
				false);
		for (File file : files) {
			String fileNameWithOutExt = FilenameUtils.removeExtension(file
					.getName());
//			if(repositoryExist(fileNameWithOutExt)){
				generateSource(fileNameWithOutExt,location);
//			}
			
		}
		
	}

	public static boolean repositoryExist(String fileName) {
		boolean exist=true;
		try {
			toClass(REPOSITORY_PACKAGE + fileName + "Repository");
		}
		catch (ClassNotFoundException e) {
			System.out.println("Repository Not Found");
			exist=false;
		}
		return exist;
	}

	@SuppressWarnings("unchecked")
	public static void generateSource(String fileName,String location)
			throws ClassNotFoundException {
		System.out.println("Generate :" + fileName + "GenericService");
		JCodeModel codeModel = new JCodeModel();
		try {
			JDefinedClass classToBeCreated = codeModel._package(PACKAGE_NAME)
					._class(JMod.PUBLIC, fileName + "GenericService");
			JClass jClassExtends = codeModel.ref(toClass(PARENT_CLASS))
					.narrow(codeModel.directClass(ENTITY_PACKAGE + fileName))
					.narrow(getEntityIDType(fileName))
					.narrow(codeModel.directClass(REPOSITORY_PACKAGE + fileName + "Repository"));
			classToBeCreated._extends(jClassExtends);
			classToBeCreated.annotate(toClass(SERVICE_ANNOTATION));
			
			
//			JFieldVar field1 = classToBeCreated.field(JMod.PROTECTED,
//					codeModel.directClass(REPOSITORY_PACKAGE + fileName + "Repository"),
//					genRepoName(fileName) + "Repository");
//			field1.annotate(toClass(AUTOWIRED_ANNOTATION));
//
//			JMethod con1 = classToBeCreated.constructor(JMod.PUBLIC);
//			con1.body().invoke("super");

//			JMethod con2 = classToBeCreated.constructor(JMod.PUBLIC);
//			con2.param(field1.type(), genRepoName(fileName) + "Repository");
//			con2.body().assign(
//					JExpr._this().ref(genRepoName(fileName) + "Repository"),
//					JExpr.ref(genRepoName(fileName) + "Repository"));

			JMethod field1GetIDMethod = classToBeCreated.method(JMod.PUBLIC,
					getEntityIDType(fileName), "getID");
			JVar param2 = field1GetIDMethod.param(toClass(ENTITY_PACKAGE
					+ fileName), "entity");
			field1GetIDMethod.body()._return(
					param2.invoke(getEntityReturnMethod(fileName)));

		} catch (JClassAlreadyExistsException e) {
			e.printStackTrace();
		}

		try {
			codeModel.build(new File(location));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Class toClass(String clazz) throws ClassNotFoundException {
		Class c = Class.forName(clazz);
		return c;
	}

	public static String genRepoName(String fileName) {
		char first = Character.toLowerCase(fileName.charAt(0));
		String name = first + fileName.substring(1);
		return name;
	}

	public static Class getEntityIDType(String fileName)
			throws ClassNotFoundException {
		Class c = Class.forName(ENTITY_PACKAGE + fileName);
		Method[] methods = c.getMethods();
		for (Method m : methods) {
			if (m.isAnnotationPresent(javax.persistence.Id.class)) {
				return m.getReturnType();
			}
		}
		return null;
	}

	public static String getEntityReturnMethod(String fileName)
			throws ClassNotFoundException {
		Class c = Class.forName(ENTITY_PACKAGE + fileName);
		Method[] methods = c.getMethods();
		for (Method m : methods) {
			if (m.isAnnotationPresent(javax.persistence.Id.class)) {
				return m.getName();
			}
		}
		return null;
	}
}

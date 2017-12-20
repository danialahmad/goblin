package com.gen.entity;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMod;


public class CreateRepositoryFiles {
	
	private static final String REPOSITORY_PACKAGE = "com.goblin.kemas.model.repository.";
	private static final String REPOSITORY_PATH = "com/goblin/kemas/model/repository/";
	private static final String PACKAGE_NAME = "com.goblin.kemas.model.repository";
	private static final String PARENT_CLASS = "org.springframework.data.jpa.repository.JpaRepository";
	private static final String ENTITY_PACKAGE = "com.goblin.kemas.model.entity.";
	
    public static void main(String[] args) throws ClassNotFoundException, JClassAlreadyExistsException{
		
    	String directory = args[0];
		String location= args[1];
		Collection<File> files = FileUtils.listFiles(new File(directory), null,
				false);
		int counter=0;
		for (File file : files) {
			String fileNameWithOutExt = FilenameUtils.removeExtension(file
					.getName());
		    
			if(!repositoryExist(fileNameWithOutExt)){
				generateSource(fileNameWithOutExt,location);
				counter=counter+1;
			}
		
		}
		System.out.println(counter+" Repository files have been created");
		//CreateFileTest.main(args);
		
	}
	
	public static void generateSource(String fileName,String location) throws JClassAlreadyExistsException, ClassNotFoundException{
		System.out.println("Generating " + fileName + "Repository");
		JCodeModel codeModel = new JCodeModel();
		JDefinedClass classToBeCreated = codeModel._package(PACKAGE_NAME)._interface(JMod.PUBLIC,fileName + "Repository");
		JClass jClassExtends = codeModel.ref(toClass(PARENT_CLASS))
				.narrow(codeModel.directClass(ENTITY_PACKAGE + fileName))
				.narrow(getEntityIDType(fileName));
		classToBeCreated._extends(jClassExtends);
		try {
			codeModel.build(new File(location));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean repositoryExist(String fileName) {
		boolean exist=true;
		try {
			toClass(REPOSITORY_PACKAGE + fileName + "Repository");
		}
		catch (ClassNotFoundException e) {
			exist=false;
		}
		return exist;
	}
	
	public static Class toClass(String clazz) throws ClassNotFoundException {
		Class c = Class.forName(clazz);
		return c;
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

}

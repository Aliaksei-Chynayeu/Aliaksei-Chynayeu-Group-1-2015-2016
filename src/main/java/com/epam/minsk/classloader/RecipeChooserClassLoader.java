package com.epam.minsk.classloader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;

/**@author Dzina_Andreyeva
 * Custom classloader
 */
public class RecipeChooserClassLoader extends ClassLoader {
	
	/** Name packages in jar */
	private static final String PACKAGE_TO_CACHE = "com.epam.minsk";
	/** Path to jar */
	private String pathToJar;
	/** Map which contains loaded files */
    private Map<String, Class<?>> cache = new HashMap<String, Class<?>>();
    
    /** Log4j */
	private static final Logger LOG = Logger.getLogger(RecipeChooserClassLoader.class);
    
    public RecipeChooserClassLoader(ClassLoader parent, String pathToJar) {    
        super(parent);
    	this.pathToJar = pathToJar;        
    }

    /** Cashes classes */
    private void cacheClass(String name) {
        try {           
        	if (name.startsWith(PACKAGE_TO_CACHE)) {                   
        		byte[] classData = loadClassData(name);                   
                if (classData != null) {
                	Class<?> clazz = defineClass(name, classData, 0, classData.length);
                    cache.put(clazz.getName(), clazz);
                    LOG.info("== class " + clazz.getName() + " loaded in cache");
                }
            }
        }
        catch (IOException e) {
        	LOG.error("IOException" + e);
        }
    }
    
    /** Loads class by name */ 
    public synchronized Class<?> loadClass(String name) throws ClassNotFoundException {
    	Class<?> result = cache.get(name); 
        if (result == null) {
        	cacheClass(name);
        	result = cache.get(name);
        }  
        if (result == null) {
            result = super.findSystemClass(name);  
        }
        LOG.info("== loadClass(" + name + ")");       
        return result;
    }
   
    /** Replaces symbols in path */
    private String normalize(String className) {
    	return className.replace('.', '/');
    }
    
    /** Loads class as bytes */
    private byte[] loadClassData(String name) throws IOException {
    	JarFile jarFile = new JarFile(pathToJar);
    	JarEntry jarEntry = jarFile.getJarEntry(normalize(name) + ".class");
      
    	if (jarEntry == null || jarEntry.getSize() == -1 || jarEntry.getSize() == 0) {
    		return null;
      }
    	byte[] data = new byte[(int)jarEntry.getSize()];
    	InputStream in = jarFile.getInputStream(jarEntry);
    	in.read(data);
    	return data;
    }
}

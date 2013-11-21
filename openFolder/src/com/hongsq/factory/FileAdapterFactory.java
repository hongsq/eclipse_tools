/**
 * 
 */
package com.hongsq.factory;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jdt.internal.core.JarPackageFragmentRoot;

/**
 * @author hongshuiqiao
 *
 */
public class FileAdapterFactory implements IAdapterFactory {

	public Object getAdapter(Object adaptableObject, Class adapterType) {
		try {
			if ((adapterType == File.class) && (adaptableObject instanceof JarPackageFragmentRoot)) {
				JarPackageFragmentRoot jar =  (JarPackageFragmentRoot) adaptableObject;
				String path = jar.getPath().toOSString();
				return new File(path).getCanonicalFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Class[] getAdapterList() {
		return new Class[] {File.class};
	}

}

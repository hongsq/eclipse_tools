/**
 * 
 */
package openfolder.popup.actions;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.internal.runtime.AdapterManager;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;

/**
 * @author hongshuiqiao
 *
 */
public final class WinExplorerUtil {
	private WinExplorerUtil() {}
	
	public static String getPath(Object object) throws IOException {
		if (object instanceof IResource) {
			IResource resource = (IResource) object;
			return new File(resource.getLocationURI()).getCanonicalPath();
		}else if (object instanceof File) {
			File file = (File) object;
			return file.getCanonicalPath();
		}else if (object instanceof IAdaptable) {
			IAdaptable adaptable = (IAdaptable) object;
			IResource resource = (IResource) adaptable.getAdapter(IResource.class);
			if(null != resource){
				return new File(resource.getLocationURI()).getCanonicalPath();
			}
			
			File file = (File) adaptable.getAdapter(File.class);
			if(null != file){
				return file.getCanonicalPath();
			}
		}else{
			IResource resource = (IResource) AdapterManager.getDefault().getAdapter(object, IResource.class);
			if(null != resource){
				return new File(resource.getLocationURI()).getCanonicalPath();
			}
			
			File file = (File) AdapterManager.getDefault().getAdapter(object, File.class);
			if(null != file){
				return file.getCanonicalPath();
			}
		}
		return null;
	}
	
	public static void openFolder(IResource resource) throws IOException{
		openFolder(new File(resource.getLocationURI()).getCanonicalPath());
	}
	
	public static void openFolder(String resourcePath) throws IOException{
		openFolder(new File(resourcePath));
	}
	
	public static void openFolder(File file) throws IOException{
		StringBuilder command = new StringBuilder();
		command.append("explorer ");
		
		if(file.isFile()){
			command.append("/select,");
		}else{
			command.append("/e,");
		}
		
		command.append("\"");
		command.append(file.getCanonicalPath());
		command.append("\"");
		Runtime.getRuntime().exec(command.toString());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			openFolder("D:/Private/goagent-goagent-259441f/server/uploader.zip");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

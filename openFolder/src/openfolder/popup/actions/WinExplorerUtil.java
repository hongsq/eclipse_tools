/**
 * 
 */
package openfolder.popup.actions;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IResource;

/**
 * @author hongshuiqiao
 *
 */
public final class WinExplorerUtil {
	private WinExplorerUtil() {}
	
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

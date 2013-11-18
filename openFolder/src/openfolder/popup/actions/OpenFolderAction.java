package openfolder.popup.actions;

import java.io.IOException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.internal.core.JarPackageFragmentRoot;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
/**
 * 
 * @author hongshuiqiao
 *
 */
public class OpenFolderAction implements IObjectActionDelegate {
	protected IStructuredSelection currentSelection;
//	private Shell shell;
	
	/**
	 * Constructor for Action1.
	 */
	public OpenFolderAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
//		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		if(null == currentSelection)
			return;
		
		try {
			Object object = currentSelection.getFirstElement();
			if (object instanceof IResource) {
				IResource resource = (IResource) object;
				WinExplorerUtil.openFolder(resource);
			}else if (object instanceof JarPackageFragmentRoot) {
				JarPackageFragmentRoot jar = (JarPackageFragmentRoot) object;
				String path = jar.getPath().toOSString();
				
				WinExplorerUtil.openFolder(path);
			}else if (object instanceof IAdaptable) {
				IAdaptable adaptable = (IAdaptable) object;
				IResource resource = (IResource) adaptable.getAdapter(IResource.class);
				if(null != resource){
					WinExplorerUtil.openFolder(resource);
				}else{
					JarPackageFragmentRoot jar = (JarPackageFragmentRoot) adaptable.getAdapter(JarPackageFragmentRoot.class);
					if(null != jar){
						String path = jar.getPath().toOSString();
						
						WinExplorerUtil.openFolder(path);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		currentSelection = ((IStructuredSelection)selection);
	}

}

package openfolder.popup.actions;

import java.io.IOException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
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
	protected IResource selectedResource;
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
		if(null == selectedResource)
			return;
		
		try {
			WinExplorerUtil.openFolder(selectedResource);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		Object object = ((IStructuredSelection)selection).getFirstElement();
		if (object instanceof IResource) {
			selectedResource = (IResource) object;
		} else if (object instanceof IAdaptable) {
			IAdaptable adaptable = (IAdaptable) object;
			selectedResource = (IResource) adaptable.getAdapter(IResource.class);
		}
	}

}

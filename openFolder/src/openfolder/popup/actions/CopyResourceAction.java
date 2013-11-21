package openfolder.popup.actions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.internal.runtime.AdapterManager;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
/**
 * 
 * @author hongshuiqiao
 *
 */
public class CopyResourceAction implements IObjectActionDelegate {
	protected IStructuredSelection currentSelection;
	private Shell shell;
	
	/**
	 * Constructor for Action1.
	 */
	public CopyResourceAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		if(null == currentSelection)
			return;
		
		try {
			Object[] objects = currentSelection.toArray();
			List<String> files = new ArrayList<String>();
			for (Object object : objects) {
				String path = WinExplorerUtil.getPath(object);
				if(null != path)
					files.add(path);
			}
			
			if(files.size()>0){
				Clipboard clipboard = null;
				try {
					clipboard = new Clipboard(shell.getDisplay());
					clipboard.setContents(new Object[]{files.toArray(new String[files.size()])}, new Transfer[]{FileTransfer.getInstance()});
				} finally {
					if (clipboard != null) {
						clipboard.dispose();
					}
				}
			}
		} catch (Exception e) {
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

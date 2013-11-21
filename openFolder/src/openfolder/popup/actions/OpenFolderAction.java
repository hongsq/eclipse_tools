package openfolder.popup.actions;

import java.io.IOException;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
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
	private Shell shell;
	
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
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		if(null == currentSelection)
			return;
		
		try {
			Object object = currentSelection.getFirstElement();
			String path = WinExplorerUtil.getPath(object);
			
			if(null == path){
				MessageDialog.openInformation(shell, "提示", "不支持此类型");
			}else{
				WinExplorerUtil.openFolder(path);
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

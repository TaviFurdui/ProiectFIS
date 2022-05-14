package proiect;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

public class ErrorCommand extends Dialog {

	protected Object result;
	protected Shell shlEroareLogin;

	public ErrorCommand(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	public Object open() {
		createContents();
		shlEroareLogin.open();
		shlEroareLogin.layout();
		Display display = getParent().getDisplay();
		while (!shlEroareLogin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	private void createContents() {
		shlEroareLogin = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.RESIZE);
		shlEroareLogin.setImage(SWTResourceManager.getImage(ErrorLogin.class, "/proiect/images/eroare.png"));
		shlEroareLogin.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		shlEroareLogin.setSize(450, 300);
		shlEroareLogin.setText("Eroare comanda");
		
		Label lblEroareLogin = new Label(shlEroareLogin, SWT.NONE);
		lblEroareLogin.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblEroareLogin.setAlignment(SWT.CENTER);
		lblEroareLogin.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblEroareLogin.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 16, SWT.BOLD));
		lblEroareLogin.setBounds(33, 82, 378, 71);
		lblEroareLogin.setText("Ne pare rau, stocul este epuizat!");

	}
}

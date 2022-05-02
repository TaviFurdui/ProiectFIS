package proiect;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class NewSale {

	protected Shell shell;
	private Text txtPret;
	private float pret;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			NewSale window = new NewSale();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Label lblPret = new Label(shell, SWT.NONE);
		lblPret.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.BOLD));
		lblPret.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblPret.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblPret.setAlignment(SWT.CENTER);
		lblPret.setBounds(114, 37, 195, 28);
		lblPret.setText("Care va fi noul pret?");
		
		txtPret = new Text(shell, SWT.BORDER);
		txtPret.setBounds(131, 101, 159, 28);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				pret = Float.parseFloat(txtPret.getText());
				shell.close();
			}
		});
		btnNewButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnNewButton.setBounds(149, 165, 124, 25);
		btnNewButton.setText("Finalizeaza reducere");

	}
	public float getPret() {
		return pret;
	}
}

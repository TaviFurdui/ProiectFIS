package proiect;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Order {

	protected Shell shlComanda;
	boolean k;

	public static void main(String[] args) {
		try {
			Order window = new Order();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlComanda.open();
		shlComanda.layout();
		while (!shlComanda.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public boolean isCash() {
		return k;
	}

	protected void createContents() {
		shlComanda = new Shell();
		shlComanda.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		shlComanda.setSize(450, 250);
		shlComanda.setText("Comanda");
		shlComanda.setLayout(null);
		Button btnCash = new Button(shlComanda, SWT.NONE);
		btnCash.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				k = true; // cash
				shlComanda.close();
			}
		});
		btnCash.setBounds(109, 111, 75, 25);
		btnCash.setText("Cash");

		Button btnCard = new Button(shlComanda, SWT.NONE);
		btnCard.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				k = false; // card
				shlComanda.close();
			}
		});
		btnCard.setBounds(238, 111, 75, 25);
		btnCard.setText("Card");

		Label lblIntrebare = new Label(shlComanda, SWT.NONE);
		lblIntrebare.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblIntrebare.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblIntrebare.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblIntrebare.setAlignment(SWT.CENTER);
		lblIntrebare.setBounds(99, 51, 228, 49);
		lblIntrebare.setText("Cum doresti sa platesti?");

	}

}

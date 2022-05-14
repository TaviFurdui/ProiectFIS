package proiect;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import proiect.Models.Cart;

public class NewSpecialCommand {

	protected Shell shlComandaSpeciala;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			NewSpecialCommand window = new NewSpecialCommand();
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
		shlComandaSpeciala.open();
		shlComandaSpeciala.layout();
		while (!shlComandaSpeciala.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	int pret = 0;
	public int getPret(int pret, Combo comboProcesor, Combo comboMemorie, Combo comboSsd,
			Combo comboRam) {
		System.out.println(comboProcesor.getText());
		switch (comboProcesor.getText()) {
		case "Intel Core i3":
			pret = pret + 1300;
			break;
		case "Intel Core i5":
			pret = pret + 1800;
			break;
		case "Intel Core i7":
			pret = pret + 2500;
			break;
		case "Intel Core i9":
			pret = pret + 3800;
			break;
		case "AMD Ryzen 7":
			pret = pret + 2500;
			break;
		default:
			pret = 0;
			break;
		}
		System.out.println(comboRam.getText());
		switch (comboRam.getText()) {
		case "8":
			pret = pret + 300;
			break;
		case "16":
			pret = pret + 600;
			break;
		case "32":
			pret = pret + 900;
			break;
		default:
			pret = 0;
			break;
		}
		switch (comboSsd.getText()) {
		case "SSD":
			pret = pret + 400;
			break;
		case "HDD":
			pret = pret + 400;
			break;
		default:
			pret = 0;
			break;
		}
		switch (comboMemorie.getText()) {
		case "128GB":
			pret = pret + 1350;
			break;
		case "256GB":
			pret = pret + 1500;
			break;
		case "512GB":
			pret = pret + 1750;
			break;
		case "1TB":
			pret = pret + 2000;
			break;
		default:
			pret = 0;
			break;
		}
		return pret;
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlComandaSpeciala = new Shell();
		shlComandaSpeciala.setToolTipText("");
		shlComandaSpeciala.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		shlComandaSpeciala.setSize(450, 300);
		shlComandaSpeciala.setText("Comanda Speciala");
		Label lblProcesor = new Label(shlComandaSpeciala, SWT.NONE);
		lblProcesor.setText("Procesor");
		lblProcesor.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblProcesor.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.BOLD));
		lblProcesor.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblProcesor.setAlignment(SWT.CENTER);
		lblProcesor.setBounds(107, 62, 92, 28);

		Label lblRam = new Label(shlComandaSpeciala, SWT.NONE);
		lblRam.setText("RAM");
		lblRam.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblRam.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.BOLD));
		lblRam.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblRam.setAlignment(SWT.CENTER);
		lblRam.setBounds(107, 90, 92, 28);

		Label lblSsd = new Label(shlComandaSpeciala, SWT.NONE);
		lblSsd.setText("SSD/HDD");
		lblSsd.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblSsd.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.BOLD));
		lblSsd.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblSsd.setAlignment(SWT.CENTER);
		lblSsd.setBounds(107, 119, 92, 28);

		Label lblMemorie = new Label(shlComandaSpeciala, SWT.NONE);
		lblMemorie.setText("Memorie");
		lblMemorie.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblMemorie.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.BOLD));
		lblMemorie.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblMemorie.setAlignment(SWT.CENTER);
		lblMemorie.setBounds(107, 147, 92, 28);
		
		Label lblPret = new Label(shlComandaSpeciala, SWT.NONE);
		lblPret.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblPret.setBounds(144, 181, 148, 26);
		
		Combo comboProcesor = new Combo(shlComandaSpeciala, SWT.NONE);
		comboProcesor.setItems(
				new String[] { "Intel Core i3", "Intel Core i5", "Intel Core i7", "Intel Core i9", "AMD Ryzen 7" });
		comboProcesor.setBounds(228, 62, 91, 23);

		Combo comboRam = new Combo(shlComandaSpeciala, SWT.NONE);
		comboRam.setItems(new String[] { "8", "16", "32" });
		comboRam.setBounds(228, 90, 91, 23);

		Combo comboSsd = new Combo(shlComandaSpeciala, SWT.NONE);
		comboSsd.setItems(new String[] { "SSD", "HDD" });
		comboSsd.setBounds(228, 119, 91, 23);

		Combo comboMemorie = new Combo(shlComandaSpeciala, SWT.NONE);
		comboMemorie.setItems(new String[] { "128GB", "256GB", "512GB", "1TB" });
		comboMemorie.setBounds(228, 147, 91, 23);

		Label lblComandaSpecialaLaptop = new Label(shlComandaSpeciala, SWT.NONE);
		lblComandaSpecialaLaptop.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 14, SWT.BOLD));
		lblComandaSpecialaLaptop.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblComandaSpecialaLaptop.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblComandaSpecialaLaptop.setAlignment(SWT.CENTER);
		lblComandaSpecialaLaptop.setBounds(72, 10, 292, 46);
		lblComandaSpecialaLaptop.setText("Comanda speciala laptop");


		//getPret(0, lblPret, comboProcesor, comboMemorie, comboSsd, comboRam);

		Button btnAdauga = new Button(shlComandaSpeciala, SWT.NONE);
		btnAdauga.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnAdauga.setFont(SWTResourceManager.getFont("MS Sans Serif", 12, SWT.BOLD));
		btnAdauga.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				proiect.Models.SpecialCommand listSpecial = new proiect.Models.SpecialCommand(comboRam.getText(),
						comboSsd.getText(), comboProcesor.getText(), comboMemorie.getText(), "cash / in asteptare", "nu",
						getPret(0, comboProcesor, comboMemorie, comboSsd, comboRam));
				Cart.setCartListSpecial(listSpecial);
				shlComandaSpeciala.close();
				// listSpecial(comboProcesor.getData().toString(),comboRam.getData().toString(),comboSsd.getData().toString(),comboMemorie.getData().toString());
			}
		});
		btnAdauga.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnAdauga.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnAdauga.setBounds(216, 211, 148, 28);
		btnAdauga.setText("Adauga in cos");
		
		Button btnVeziPret = new Button(shlComandaSpeciala, SWT.NONE);
		btnVeziPret.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnVeziPret.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int pret = getPret(0, comboProcesor, comboMemorie, comboSsd, comboRam);
				lblPret.setText(pret + " ron");
			}
		});
		btnVeziPret.setText("Vezi pret");
		btnVeziPret.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnVeziPret.setFont(SWTResourceManager.getFont("MS Sans Serif", 12, SWT.BOLD));
		btnVeziPret.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnVeziPret.setBounds(62, 211, 148, 28);

	}
}

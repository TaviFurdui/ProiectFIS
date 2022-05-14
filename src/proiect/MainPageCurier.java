package proiect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import proiect.Models.SistemsAfterCommand;
import proiect.Models.SpecialCommand;

public class MainPageCurier {

	protected Shell shlMain;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainPageCurier window = new MainPageCurier();
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
		shlMain.open();
		shlMain.layout();
		while (!shlMain.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlMain = new Shell();
		shlMain.setImage(SWTResourceManager.getImage(MainPageCurier.class, "/proiect/images/laptop.png"));
		shlMain.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		shlMain.setSize(850, 600);
		shlMain.setText("Management productie");

		Gson obj = new Gson();
		BufferedReader fisierSistemeComandate = null;
		String filepath = "Database\\sistemeComandate.json";
		try {
			fisierSistemeComandate = new BufferedReader(new FileReader(filepath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		SistemsAfterCommand[] sistem = obj.fromJson(fisierSistemeComandate, SistemsAfterCommand[].class);
		List<SistemsAfterCommand> listaSisteme = new ArrayList<SistemsAfterCommand>();

		BufferedReader fisierComenziPersonalizate = null;
		filepath = "Database\\comenziPersonalizate.json";
		try {
			fisierComenziPersonalizate = new BufferedReader(new FileReader(filepath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		SpecialCommand[] special = obj.fromJson(fisierComenziPersonalizate, SpecialCommand[].class);
		List<SpecialCommand> listaSpecial = new ArrayList<SpecialCommand>();

		if (sistem != null)
			for (int i = 0; i < sistem.length; i++) {
				listaSisteme.add(sistem[i]);
			}
		if (special != null)
			for (int i = 0; i < special.length; i++) {
				listaSpecial.add(special[i]);
			}

		Table table = new Table(shlMain, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		String[] titles = { " ", "Nume produs", "Familie produs", "Adresa", "Situatie plata", "Livrata / nelivrata" };
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
		}
		int countSisteme = sistem.length;
		int countSpecial = special.length;

		for (int i = 0; i < countSisteme; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, (i + 1) + "");
			item.setText(1, sistem[i].getNume());
			item.setText(2, sistem[i].getFamilie());
			item.setText(3, "Timisoara, Aleea Studentilor nr5");
			item.setText(4, sistem[i].getSituatie());
			item.setText(5, sistem[i].getLivrat());
		}
		for (int i = 0; i < countSpecial; i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, (i + 1 + countSisteme) + "");
			item.setText(1, "Comanda speciala");
			item.setText(2, special[i].getProcesor() + " " + special[i].getMemorie() + " " + special[i].getSsd() + " "
					+ special[i].getRam() + "GB RAM ");
			item.setText(3, "Timisoara, Aleea Studentilor nr5");
			item.setText(4, special[i].getSituatie());
			item.setText(5, special[i].getLivrat());
		}
		for (int i = 0; i < titles.length; i++) {
			table.getColumn(i).pack();
		}
		table.setSize(table.computeSize(SWT.DEFAULT, 400));

		Button btnLivrare = new Button(shlMain, SWT.BORDER);
		btnLivrare.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnLivrare.setText("Seteaza ca livrat");
		btnLivrare.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnLivrare.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
		btnLivrare.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		btnLivrare.setBounds(100, 450, 150, 50);
		btnLivrare.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println(table.getSelectionIndex());
				if (table.getSelectionIndex() < countSisteme) {
					sistem[table.getSelectionIndex()].setLivrat("da");
					if (sistem[table.getSelectionIndex()].getSituatie().compareTo("in asteptare / cash") == 0) {
						sistem[table.getSelectionIndex()].setSituatie("platita / cash");
					}
					File jsonFile = new File("Database\\sistemeComandate.json");
					OutputStream outputStream;
					try {
						outputStream = new FileOutputStream(jsonFile);
						Gson gson1 = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
						try {
							outputStream.write(gson1.toJson(listaSisteme).getBytes());
						} catch (IOException e2) {
							e2.printStackTrace();
						}
					} catch (FileNotFoundException e3) {
						e3.printStackTrace();
					}
				} else {
					special[table.getSelectionIndex() - countSisteme].setLivrat("da");
					if (special[table.getSelectionIndex() - countSisteme].getSituatie().compareTo("in asteptare / cash") == 0) {
						special[table.getSelectionIndex() - countSisteme].setSituatie("platita / cash");
					}
					File jsonFile = new File("Database\\comenziPersonalizate.json");
					OutputStream outputStream;
					try {
						outputStream = new FileOutputStream(jsonFile);
						Gson gson1 = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
						try {
							outputStream.write(gson1.toJson(listaSpecial).getBytes());
						} catch (IOException e2) {
							e2.printStackTrace();
						}
					} catch (FileNotFoundException e3) {
						e3.printStackTrace();
					}
				}

			}
		});

	}

}

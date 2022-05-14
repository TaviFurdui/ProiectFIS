package proiect;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import proiect.Models.Problems;

public class ReportProblem {

	protected Shell shlRaportareProblema;
	private Text txtSistem;
	private Text txtComponenta;
	private Text txtDescriere;
	private Label lblSistemul;
	private Label lblComponenta;
	private Label lblDescriereaProblemei;
	private Button btnNewButton;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ReportProblem window = new ReportProblem();
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
		shlRaportareProblema.open();
		shlRaportareProblema.layout();
		while (!shlRaportareProblema.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlRaportareProblema = new Shell();
		shlRaportareProblema.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		shlRaportareProblema.setSize(529, 368);
		shlRaportareProblema.setText("Raportare problema");

		Label lblTitlu = new Label(shlRaportareProblema, SWT.NONE);
		lblTitlu.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 14, SWT.BOLD));
		lblTitlu.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTitlu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblTitlu.setBounds(88, 24, 251, 37);
		lblTitlu.setText("Raporteaza o problema ");

		txtSistem = new Text(shlRaportareProblema, SWT.BORDER);
		txtSistem.setBounds(213, 77, 205, 21);

		txtComponenta = new Text(shlRaportareProblema, SWT.BORDER);
		txtComponenta.setBounds(213, 111, 205, 21);

		txtDescriere = new Text(shlRaportareProblema, SWT.BORDER);
		txtDescriere.setBounds(88, 180, 330, 83);

		lblSistemul = new Label(shlRaportareProblema, SWT.NONE);
		lblSistemul.setText("Sistemul:");
		lblSistemul.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblSistemul.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
		lblSistemul.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblSistemul.setBounds(88, 76, 92, 28);

		lblComponenta = new Label(shlRaportareProblema, SWT.NONE);
		lblComponenta.setText("Componenta:");
		lblComponenta.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblComponenta.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
		lblComponenta.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblComponenta.setBounds(88, 110, 96, 28);

		lblDescriereaProblemei = new Label(shlRaportareProblema, SWT.NONE);
		lblDescriereaProblemei.setText("Descrierea problemei:");
		lblDescriereaProblemei.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblDescriereaProblemei.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
		lblDescriereaProblemei.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblDescriereaProblemei.setBounds(88, 146, 166, 28);

		
		btnNewButton = new Button(shlRaportareProblema, SWT.FLAT);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Gson obj = new Gson();
				BufferedReader fisierProbleme = null;
				String filepath = "Database\\probleme.json";
				List <Problems> listProbleme = new ArrayList <Problems>();
				
				try {
					fisierProbleme = new BufferedReader(new FileReader(filepath));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				Problems[] probleme = obj.fromJson(fisierProbleme, Problems[].class);
				if (probleme != null) 
					for (int i = 0; i < probleme.length; i++) {
						listProbleme.add(probleme[i]);
				}
				Problems problema = new Problems(txtSistem.getText(), txtComponenta.getText(), txtDescriere.getText());
				listProbleme.add(problema);
				try (Writer writer = new FileWriter(filepath, false)) {
					Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
					gson.toJson(listProbleme, writer);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				shlRaportareProblema.close();
			}
		});
		btnNewButton.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnNewButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnNewButton.setBounds(88, 280, 137, 28);
		btnNewButton.setText("Raportare problema");

	}
}

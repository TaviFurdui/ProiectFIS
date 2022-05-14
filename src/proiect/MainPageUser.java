package proiect;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.google.gson.Gson;

import proiect.Models.Cart;
import proiect.Models.Sales;
import proiect.Models.Sistems;
import proiect.Models.SistemsAfterCommand;

public class MainPageUser {

	protected Shell shlAplicatia;

	public static void main(String[] args) {
		try {
			MainPageUser window = new MainPageUser();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlAplicatia.open();
		shlAplicatia.layout();
		while (!shlAplicatia.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		Gson obj = new Gson();
		String filepath = "";

		BufferedReader fisierSisteme = null;
		BufferedReader fisierSistemeComandate = null;
		BufferedReader fisierReduceri = null;

		filepath = "Database\\sisteme.json";
		try {
			fisierSisteme = new BufferedReader(new FileReader(filepath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Sistems[] sistem = obj.fromJson(fisierSisteme, Sistems[].class);

		filepath = "Database\\sistemeComandate.json";
		try {
			fisierSistemeComandate = new BufferedReader(new FileReader(filepath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		SistemsAfterCommand[] sistemDupaComanda = obj.fromJson(fisierSistemeComandate, SistemsAfterCommand[].class);

		filepath = "Database\\reduceri.json";
		try {
			fisierReduceri = new BufferedReader(new FileReader(filepath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Sales[] reduceri = obj.fromJson(fisierReduceri, Sales[].class);

		shlAplicatia = new Shell();
		shlAplicatia.setImage(SWTResourceManager.getImage(MainPage.class, "/proiect/images/laptop.png"));
		shlAplicatia.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		shlAplicatia.setSize(883, 426);
		shlAplicatia.setText("Management productie");

		// Declarare composite meniu
		Composite meniu = new Composite(shlAplicatia, SWT.NONE);
		meniu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
		meniu.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
		meniu.setBounds(0, 0, 192, 386);

		// Declarare Scrolled composite & Composites
		ScrolledComposite scrolledCompositeComponente = new ScrolledComposite(shlAplicatia,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledCompositeComponente.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		scrolledCompositeComponente.setBounds(200, 0, 650, 375);
		scrolledCompositeComponente.setExpandHorizontal(true);
		scrolledCompositeComponente.setExpandVertical(true);

		Composite compositeComponente = new Composite(scrolledCompositeComponente, SWT.NONE);
		compositeComponente.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));

		Composite compositeComandaPersonalizata = new Composite(scrolledCompositeComponente, SWT.NONE);
		compositeComandaPersonalizata.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));

		Composite compositeSisteme = new Composite(scrolledCompositeComponente, SWT.NONE);
		compositeSisteme.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));

		Composite compositeReduceri = new Composite(scrolledCompositeComponente, SWT.NONE);
		compositeReduceri.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));

		// Declarare liste componente / sisteme
		List<SistemsAfterCommand> sisList = new ArrayList<SistemsAfterCommand>();
		List<Sistems> sisCart = new ArrayList<Sistems>();
		List<Sales> saleList = new ArrayList<Sales>();
		List<Sales> saleCart = new ArrayList<Sales>();

		Button btnDeconectare = new Button(meniu, SWT.BORDER);
		btnDeconectare.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlAplicatia.close();
				new Login().open();
			}
		});
		btnDeconectare.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnDeconectare.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
		btnDeconectare.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnDeconectare.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		btnDeconectare.setBounds(0, 337, 192, 49);
		btnDeconectare.setText("Deconectare");

		if (sistemDupaComanda != null)
			for (int i = 0; i < sistemDupaComanda.length; i++) {
				sisList.add(sistemDupaComanda[i]);
			}
		Button btnVizualizareSisteme = new Button(meniu, SWT.BORDER);
		btnVizualizareSisteme.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				compositeComponente.setVisible(false);
				compositeComandaPersonalizata.setVisible(false);
				compositeSisteme.setVisible(true);
				compositeReduceri.setVisible(false);
				scrolledCompositeComponente.setContent(compositeSisteme);
				scrolledCompositeComponente.setMinSize(compositeSisteme.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				int i;
				for (i = 0; i < sistem.length; i++) {
					Label lblNume = new Label(compositeSisteme, SWT.NONE);
					Label lblFamilie = new Label(compositeSisteme, SWT.NONE);
					Label lblImagine = new Label(compositeSisteme, SWT.NONE);
					Label lblPret = new Label(compositeSisteme, SWT.NONE);
					Button btnComandaSisteme = new Button(compositeSisteme, SWT.FLAT);
					btnComandaSisteme.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
					btnComandaSisteme.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
					int curent = i;
					btnComandaSisteme.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {

							if (sistem[curent].getCantitate_inventar() > 0) {
								sisCart.add(sistem[curent]);
								Cart.setCartList(sisCart);
							} else {
								ErrorCommand shlEroare = new ErrorCommand(shlAplicatia, 0);
								shlEroare.open();
							}
						}
					});
					btnComandaSisteme.setBounds(25 + 200 * (i % 3), 220 + 250 * (i / 3), 75, 25);
					btnComandaSisteme.setText("Comanda");
					lblNume.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
					lblNume.setBounds(25 + 200 * (i % 3), 150 + 250 * (i / 3), 150, 25);
					lblFamilie.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
					lblFamilie.setBounds(25 + 200 * (i % 3), 130 + 250 * (i / 3), 150, 25);
					lblPret.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
					lblPret.setBounds(25 + 200 * (i % 3), 180 + 250 * (i / 3), 150, 25);
					lblImagine.setAlignment(SWT.CENTER);
					lblImagine.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
					lblImagine.setImage(
							SWTResourceManager.getImage(MainPage.class, "/proiect/images/Webp.net-resizeimage.png"));
					lblImagine.setBounds(0 + 200 * (i % 3), 10 + 250 * (i / 3), 129, 112);

					lblPret.setText(sistem[i].getPret() + " ron");
					lblNume.setText(sistem[i].getNume());
					lblFamilie.setText(sistem[i].getFamilie());
					scrolledCompositeComponente.setMinSize(compositeSisteme.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				}
			}
		});
		btnVizualizareSisteme.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnVizualizareSisteme.setText("Comanda Sisteme");
		btnVizualizareSisteme.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnVizualizareSisteme.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
		btnVizualizareSisteme.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		btnVizualizareSisteme.setBounds(0, 0, 192, 49);

		Button btnRaportareProbleme = new Button(meniu, SWT.BORDER);
		btnRaportareProbleme.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new ReportProblem().open();
			}
		});
		btnRaportareProbleme.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnRaportareProbleme.setBounds(0, 95, 192, 49);
		btnRaportareProbleme.setText("Raportare probleme");
		btnRaportareProbleme.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnRaportareProbleme.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
		btnRaportareProbleme.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));

		if (reduceri != null)
			for (int i = 0; i < reduceri.length; i++) {
				saleList.add(reduceri[i]);
			}
		Button btnVizualizareReduceri = new Button(meniu, SWT.BORDER);
		btnVizualizareReduceri.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				compositeComponente.setVisible(false);
				compositeComandaPersonalizata.setVisible(false);
				compositeSisteme.setVisible(false);
				compositeReduceri.setVisible(true);
				scrolledCompositeComponente.setContent(compositeReduceri);
				scrolledCompositeComponente.setMinSize(compositeReduceri.computeSize(SWT.DEFAULT, SWT.DEFAULT));

				if (reduceri != null) {
					int i;
					for (i = 0; i < saleList.size(); i++) {
						Label lblNume = new Label(compositeReduceri, SWT.NONE);
						Label lblImagine = new Label(compositeReduceri, SWT.NONE);
						Label lblPret = new Label(compositeReduceri, SWT.NONE);
						Button btnComandaSisteme = new Button(compositeReduceri, SWT.FLAT);
						btnComandaSisteme.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
						btnComandaSisteme.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
						int curent = i;
						btnComandaSisteme.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent e) {
								boolean ok = true;
								for (int k = 0; k < reduceri[curent].getListaReduceri().size(); k++) {
									if (reduceri[curent].getListaReduceri().get(k).getCantitate_inventar() <= 0)
										ok = false;
								}
								if (ok == true) {
									saleCart.add(reduceri[curent]);
									Cart.setCartListSales(saleCart);
								} else {
									ErrorCommand shlEroare = new ErrorCommand(shlAplicatia, 0);
									shlEroare.open();
								}
							}
						});
						btnComandaSisteme.setBounds(55 + 200 * (i % 3), 220 + 250 * (i / 3), 75, 25);
						btnComandaSisteme.setText("Comanda");
						lblNume.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
						lblNume.setBounds(25 + 200 * (i % 3), 150 + 250 * (i / 3), 200, 25);
						lblPret.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
						lblPret.setBounds(75 + 200 * (i % 3), 180 + 250 * (i / 3), 150, 25);
						lblImagine.setAlignment(SWT.CENTER);
						lblImagine.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
						lblImagine.setImage(SWTResourceManager.getImage(MainPage.class,
								"/proiect/images/Webp.net-resizeimage.png"));
						lblImagine.setBounds(30 + 200 * (i % 3), 10 + 250 * (i / 3), 129, 112);

						String numeReducere = "";
						for (int k = 0; k < reduceri[i].getListaReduceri().size(); k++) {
							if (k == reduceri[i].getListaReduceri().size() - 1) {
								numeReducere = numeReducere + reduceri[i].getListaReduceri().get(k).getFamilie();
							} else {
								numeReducere = numeReducere + reduceri[i].getListaReduceri().get(k).getFamilie()
										+ " + ";
							}
						}
						lblPret.setText(saleList.get(i).getPretNou() + " ron");
						lblNume.setText(numeReducere);
						scrolledCompositeComponente.setMinSize(compositeReduceri.computeSize(SWT.DEFAULT, SWT.DEFAULT));
					}
				} else {
					Label lblError = new Label(compositeReduceri, SWT.NONE);
					lblError.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
					lblError.setBounds(150, 150, 450, 50);
					lblError.setText("Ne pare rau! Nu exista reduceri!");
					lblError.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
					lblError.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 20, SWT.NORMAL));
				}
			}
		});
		btnVizualizareReduceri.setText("Vizualizare Reduceri");
		btnVizualizareReduceri.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnVizualizareReduceri.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
		btnVizualizareReduceri.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnVizualizareReduceri.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		btnVizualizareReduceri.setBounds(0, 49, 192, 49);

		Button btnCosDeCumparaturi = new Button(meniu, SWT.BORDER);
		btnCosDeCumparaturi.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				new ShoppingCart().open();
			}
		});
		btnCosDeCumparaturi.setText("Cos de cumparaturi");
		btnCosDeCumparaturi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnCosDeCumparaturi.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
		btnCosDeCumparaturi.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnCosDeCumparaturi.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		btnCosDeCumparaturi.setBounds(0, 143, 192, 49);

	}
}

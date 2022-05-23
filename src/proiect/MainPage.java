package proiect;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import proiect.Models.Components;
import proiect.Models.ComponentsAfterCommand;
import proiect.Models.Problems;
import proiect.Models.Sales;
import proiect.Models.Sistems;
import proiect.Models.SistemsAfterCommand;

public class MainPage {

	protected Shell shlAplicatia;

	public static void main(String[] args) {
		try {
			MainPage window = new MainPage();
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
		String filepath = "Database\\comandaComponente.json";
		BufferedReader fisierComponente = null;
		BufferedReader fisierComponenteDupaComanda = null;
		BufferedReader fisierSisteme = null;
		BufferedReader fisierSistemeComandate = null;
		BufferedReader fisierReduceri = null;
		BufferedReader fisierProbleme = null;

		try {
			fisierComponente = new BufferedReader(new FileReader(filepath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Components[] componenta = obj.fromJson(fisierComponente, Components[].class);

		filepath = "Database\\componente.json";
		try {
			fisierComponenteDupaComanda = new BufferedReader(new FileReader(filepath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		ComponentsAfterCommand[] componentaDupaComanda = obj.fromJson(fisierComponenteDupaComanda,
				ComponentsAfterCommand[].class);

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
		
		filepath = "Database\\probleme.json";		
		try {
			fisierProbleme = new BufferedReader(new FileReader(filepath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Problems[] probleme = obj.fromJson(fisierProbleme, Problems[].class);
		
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

		Composite compositeVizualizareComponente = new Composite(scrolledCompositeComponente, SWT.NONE);
		compositeVizualizareComponente.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));

		Composite compositeSisteme = new Composite(scrolledCompositeComponente, SWT.NONE);
		compositeSisteme.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));

		Composite compositeReduceri = new Composite(scrolledCompositeComponente, SWT.NONE);
		compositeReduceri.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));

		Composite compositeProbleme = new Composite(scrolledCompositeComponente, SWT.NONE);
		compositeProbleme.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		
		Composite compositeGrafice = new Composite(scrolledCompositeComponente, SWT.NONE);
		compositeGrafice.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		
		// Declarare liste componente / sisteme
		List<SistemsAfterCommand> sisList = new ArrayList<SistemsAfterCommand>();
		List<ComponentsAfterCommand> compList = new ArrayList<ComponentsAfterCommand>();
		List<Sistems> listaReducere = new ArrayList<Sistems>();
		List<Sistems> listaSisteme = new ArrayList<Sistems>();
		List<Sales> saleList = new ArrayList<Sales>();

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

		Button btnVizualizareGrafice = new Button(meniu, SWT.BORDER);
		btnVizualizareGrafice.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				compositeComponente.setVisible(false);
				compositeVizualizareComponente.setVisible(false);
				compositeSisteme.setVisible(false);
				compositeReduceri.setVisible(false);
				compositeProbleme.setVisible(false);
				compositeGrafice.setVisible(true);
				scrolledCompositeComponente.setContent(compositeGrafice);
				scrolledCompositeComponente.setMinSize(compositeGrafice.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				
				Button btnInventar = new Button(compositeGrafice, SWT.BORDER);
				btnInventar.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						shlAplicatia.close();
						new GraficInventar().main(null);
					}
				});
				btnInventar.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
				btnInventar.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
				btnInventar.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				btnInventar.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
				btnInventar.setBounds(20, 50, 200, 50);
				btnInventar.setText("Grafic inventar");

				Button btnVanzari = new Button(compositeGrafice, SWT.BORDER);
				btnVanzari.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						shlAplicatia.close();
						new GraficVanzari().main(null);
					}
				});
				btnVanzari.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
				btnVanzari.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
				btnVanzari.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				btnVanzari.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
				btnVanzari.setBounds(20, 150, 200, 50);
				btnVanzari.setText("Grafic inventar");
				
				Button btnProbleme = new Button(compositeGrafice, SWT.BORDER);
				btnProbleme.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						shlAplicatia.close();
						new GraficProbleme().main(null);
					}
				});
				btnProbleme.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
				btnProbleme.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
				btnProbleme.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				btnProbleme.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
				btnProbleme.setBounds(20, 250, 200, 50);
				btnProbleme.setText("Grafic inventar");
			}
		});
		btnVizualizareGrafice.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnVizualizareGrafice.setText("Vizualizare grafice");
		btnVizualizareGrafice.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnVizualizareGrafice.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
		btnVizualizareGrafice.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		btnVizualizareGrafice.setBounds(0, 242, 192, 49);

		Button btnAdaugareReduceri = new Button(meniu, SWT.BORDER);
		if (reduceri != null)
			for (int i = 0; i < reduceri.length; i++) {
				saleList.add(reduceri[i]);
			}
		if (sistem != null)
			for (int i = 0; i < sistem.length; i++) {
				listaSisteme.add(sistem[i]);
			}
		btnAdaugareReduceri.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				compositeComponente.setVisible(false);
				compositeVizualizareComponente.setVisible(false);
				compositeSisteme.setVisible(false);
				compositeReduceri.setVisible(true);
				compositeProbleme.setVisible(false);
				compositeGrafice.setVisible(false);
				scrolledCompositeComponente.setContent(compositeReduceri);
				scrolledCompositeComponente.setMinSize(compositeReduceri.computeSize(SWT.DEFAULT, SWT.DEFAULT));

				for (int i = 0; i < sistem.length; i++) {
					Label lblNume = new Label(compositeReduceri, SWT.NONE);
					Label lblFamilie = new Label(compositeReduceri, SWT.NONE);
					Label lblImagine = new Label(compositeReduceri, SWT.NONE);
					Label lblPret = new Label(compositeReduceri, SWT.NONE);
					Button btnAdauga = new Button(compositeReduceri, SWT.FLAT);

					btnAdauga.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
					btnAdauga.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
					int curent = i;
					btnAdauga.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							listaReducere.add(sistem[curent]);
							btnAdauga.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_ARROW));
							btnAdauga.getGrayed();
							btnAdauga.setEnabled(false);
							btnAdauga.setText("Selectat");
						}
					});

					btnAdauga.setBounds(25 + 200 * (i % 3), 255 + 250 * (i / 3), 75, 25);
					btnAdauga.setText("Adauga");
					lblNume.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
					lblNume.setBounds(25 + 200 * (i % 3), 215 + 250 * (i / 3), 150, 25);
					lblFamilie.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
					lblFamilie.setBounds(25 + 200 * (i % 3), 190 + 250 * (i / 3), 150, 25);
					lblImagine.setAlignment(SWT.CENTER);
					lblImagine.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
					lblImagine.setImage(
							SWTResourceManager.getImage(MainPage.class, "/proiect/images/Webp.net-resizeimage.png"));
					lblImagine.setBounds(0 + 200 * (i % 3), 70 + 250 * (i / 3), 129, 112);

					lblPret.setText(sistem[i].getPret() + " ron");
					lblNume.setText(sistem[i].getNume());
					lblFamilie.setText(sistem[i].getFamilie());
					scrolledCompositeComponente.setMinSize(compositeReduceri.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				}
			}
		});
		Button btnFinalizeazaReducere = new Button(compositeReduceri, SWT.FLAT);
		btnFinalizeazaReducere.setBounds(325, 10, 150, 30);
		btnFinalizeazaReducere.setText("Finalizeaza reducere");
		btnFinalizeazaReducere.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnFinalizeazaReducere.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnFinalizeazaReducere.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewSale sale = new NewSale();
				sale.open();
				float pretNou = sale.getPret();
				Sales reduceri = new Sales(listaReducere, pretNou);
				for (int i = 0; i < listaReducere.size(); i++) {
					if (listaReducere.contains(sistem[i])) {
						listaSisteme.remove(sistem[i]);
						String filePath = "Database\\sisteme.json";
						try (Writer writer = new FileWriter(filePath, false)) {
							Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
							gson.toJson(listaSisteme, writer);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
				saleList.add(reduceri);
				String filePath = "Database\\reduceri.json";
				try (Writer writer = new FileWriter(filePath, false)) {
					Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
					gson.toJson(saleList, writer);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		Button btnStergeLista = new Button(compositeReduceri, SWT.FLAT);
		btnStergeLista.setBounds(165, 10, 150, 30);
		btnStergeLista.setText("Sterge");
		btnStergeLista.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnStergeLista.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnStergeLista.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				listaReducere.clear();
			}
		});

		btnAdaugareReduceri.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnAdaugareReduceri.setText("Adaugare reduceri");
		btnAdaugareReduceri.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnAdaugareReduceri.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
		btnAdaugareReduceri.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		btnAdaugareReduceri.setBounds(0, 145, 192, 49);

		Button btnComandaComponente = new Button(meniu, SWT.BORDER);

		if (componentaDupaComanda != null)
			for (int i = 0; i < componentaDupaComanda.length; i++) {
				compList.add(componentaDupaComanda[i]);
			}
		btnComandaComponente.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				compositeComponente.setVisible(true);
				compositeVizualizareComponente.setVisible(false);
				compositeSisteme.setVisible(false);
				compositeReduceri.setVisible(false);
				compositeProbleme.setVisible(false);
				compositeGrafice.setVisible(false);
				scrolledCompositeComponente.setContent(compositeComponente);
				scrolledCompositeComponente.setMinSize(compositeComponente.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				int i;
				for (i = 0; i < componenta.length; i++) {
					Label lblComponentaNume = new Label(compositeComponente, SWT.NONE);
					Label lblComponentaFurnizor = new Label(compositeComponente, SWT.NONE);
					Label lblComponentaImagine = new Label(compositeComponente, SWT.NONE);
					Label lblPretComponenta = new Label(compositeComponente, SWT.NONE);
					Button btnComanda = new Button(compositeComponente, SWT.FLAT);

					btnComanda.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
					btnComanda.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
					btnComanda.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							Order order = new Order();
							order.open();
							String situatie;
							if (order.isCash() == true) {
								situatie = "in asteptare / cash";
							} else {
								situatie = "platita / card";
							}
							String nume = lblComponentaNume.getText();
							String furnizor = lblComponentaFurnizor.getText();
							String status = "in asteptare";
							String observatii = "";
							String inventar = "";
							Date data = new Date();

							ComponentsAfterCommand comp = new ComponentsAfterCommand(nume, inventar, furnizor, status,
									situatie, observatii, data);
							compList.add(comp);
							String filePath = "Database\\componente.json";
							try (Writer writer = new FileWriter(filePath, false)) {
								Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
								gson.toJson(compList, writer);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					});
					btnComanda.setBounds(25 + 200 * (i % 3), 220 + 250 * (i / 3), 75, 25);
					btnComanda.setText("Comanda");
					lblPretComponenta.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
					lblPretComponenta.setBounds(25 + 200 * (i % 3), 180 + 250 * (i / 3), 150, 20);
					lblComponentaNume.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
					lblComponentaNume.setBounds(25 + 200 * (i % 3), 130 + 250 * (i / 3), 150, 20);
					lblComponentaFurnizor.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
					lblComponentaFurnizor.setBounds(25 + 200 * (i % 3), 150 + 250 * (i / 3), 150, 20);
					lblComponentaImagine.setAlignment(SWT.CENTER);
					lblComponentaImagine.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
					lblComponentaImagine.setImage(
							SWTResourceManager.getImage(MainPage.class, "/proiect/images/Webp.net-resizeimage.png"));
					lblComponentaImagine.setBounds(0 + 200 * (i % 3), 10 + 250 * (i / 3), 129, 112);

					lblPretComponenta.setText(componenta[i].getPret() + " ron");
					lblComponentaNume.setText(componenta[i].getNume());
					lblComponentaFurnizor.setText(componenta[i].getFurnizor());
					scrolledCompositeComponente.setMinSize(compositeComponente.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				}
			}
		});
		btnComandaComponente.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnComandaComponente.setText("Comanda componente");
		btnComandaComponente.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnComandaComponente.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
		btnComandaComponente.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		btnComandaComponente.setBounds(0, 0, 192, 49);

		Button btnVizualizareComponente = new Button(meniu, SWT.BORDER);

		if (sistemDupaComanda != null)
			for (int i = 0; i < sistemDupaComanda.length; i++) {
				sisList.add(sistemDupaComanda[i]);
			}
		btnVizualizareComponente.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				compositeComponente.setVisible(false);
				compositeVizualizareComponente.setVisible(true);
				compositeSisteme.setVisible(false);
				compositeReduceri.setVisible(false);
				compositeProbleme.setVisible(false);
				compositeGrafice.setVisible(false);
				scrolledCompositeComponente.setContent(compositeVizualizareComponente);
				scrolledCompositeComponente
						.setMinSize(compositeVizualizareComponente.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				int i;
				for (i = 0; i < componentaDupaComanda.length; i++) {
					Label lblComponentaNume = new Label(compositeVizualizareComponente, SWT.NONE);
					Label lblComponentaFurnizor = new Label(compositeVizualizareComponente, SWT.NONE);
					Label lblComponentaImagine = new Label(compositeVizualizareComponente, SWT.NONE);
					lblComponentaNume.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
					lblComponentaNume.setBounds(25 + 200 * (i % 3), 215 + 250 * (i / 3), 150, 25);
					lblComponentaFurnizor.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
					lblComponentaFurnizor.setBounds(25 + 200 * (i % 3), 190 + 250 * (i / 3), 150, 25);
					lblComponentaImagine.setAlignment(SWT.CENTER);
					lblComponentaImagine.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
					lblComponentaImagine.setImage(
							SWTResourceManager.getImage(MainPage.class, "/proiect/images/Webp.net-resizeimage.png"));
					lblComponentaImagine.setBounds(0 + 200 * (i % 3), 70 + 250 * (i / 3), 129, 112);

					lblComponentaNume.setText(componentaDupaComanda[i].getNume());
					lblComponentaFurnizor.setText(componentaDupaComanda[i].getFurnizor());
					scrolledCompositeComponente
							.setMinSize(compositeVizualizareComponente.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				}
			}
		});
		btnVizualizareComponente.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnVizualizareComponente.setText("Vizualizare componente\r\n");
		btnVizualizareComponente.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnVizualizareComponente.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
		btnVizualizareComponente.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		btnVizualizareComponente.setBounds(0, 48, 192, 49);

		Label lblTitlu = new Label(compositeVizualizareComponente, SWT.FLAT);
		lblTitlu.setBounds(200, 10, 300, 30);
		lblTitlu.setText("Vizualizare componente comandate / primite");
		lblTitlu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblTitlu.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		Button btnVizualizareSisteme = new Button(meniu, SWT.BORDER);
		btnVizualizareSisteme.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				compositeComponente.setVisible(false);
				compositeVizualizareComponente.setVisible(false);
				compositeSisteme.setVisible(true);
				compositeReduceri.setVisible(false);
				compositeProbleme.setVisible(false);
				compositeGrafice.setVisible(false);
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
					btnComandaSisteme.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							Order order = new Order();
							order.open();
							String situatie;
							if (order.isCash() == true) {
								situatie = "in asteptare / cash";
							} else {
								situatie = "platita / card";
							}
							String nume = lblNume.getText();
							String familie = lblFamilie.getText();

							SistemsAfterCommand sis = new SistemsAfterCommand(familie, nume, situatie, "nu");
							sisList.add(sis);
							String filePath = "Database\\sistemeComandate.json";
							try (Writer writer = new FileWriter(filePath, false)) {
								Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
								gson.toJson(sisList, writer);
							} catch (IOException e1) {
								e1.printStackTrace();
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
		btnVizualizareSisteme.setText("Vizualizare sisteme");
		btnVizualizareSisteme.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnVizualizareSisteme.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
		btnVizualizareSisteme.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		btnVizualizareSisteme.setBounds(0, 97, 192, 49);

		Button btnVizualizareProbleme = new Button(meniu, SWT.BORDER);
		btnVizualizareProbleme.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				compositeComponente.setVisible(false);
				compositeVizualizareComponente.setVisible(false);
				compositeSisteme.setVisible(false);
				compositeReduceri.setVisible(false);
				compositeProbleme.setVisible(true);
				compositeGrafice.setVisible(false);
				scrolledCompositeComponente.setContent(compositeProbleme);
				scrolledCompositeComponente.setMinSize(compositeProbleme.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				Table table = new Table(compositeProbleme, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
				table.setLinesVisible(true);
				table.setHeaderVisible(true);
				String[] titles = { " ", "Sistem", "Componenta care a cauzat problema", "Descrierea problemei" };
				for (int i = 0; i < titles.length; i++) {
					TableColumn column = new TableColumn(table, SWT.NONE);
					column.setText(titles[i]);
				}
				int count = probleme.length;
				for (int i = 0; i < count; i++) {
					TableItem item = new TableItem(table, SWT.NONE);
					item.setText(0, (i+1)+"");
					item.setText(1, probleme[i].getSistem());
					item.setText(2, probleme[i].getComponenta());
					item.setText(3, probleme[i].getDescriere());
				}
				for (int i = 0; i < titles.length; i++) {
					table.getColumn(i).pack();
				}
				table.setSize(table.computeSize(SWT.DEFAULT, 200));
				scrolledCompositeComponente.setMinSize(compositeProbleme.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			}
		});
		btnVizualizareProbleme.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnVizualizareProbleme.setBounds(0, 194, 192, 49);
		btnVizualizareProbleme.setText("Vizualizare probleme");
		btnVizualizareProbleme.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnVizualizareProbleme.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
		btnVizualizareProbleme.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));

	}
}

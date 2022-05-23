package proiect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
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
import com.google.gson.GsonBuilder;

import proiect.Models.Sales;
import proiect.Models.Sistems;
import proiect.Models.SistemsAfterCommand;
import proiect.Models.SpecialCommand;

public class ShoppingCart {

	protected Shell shlCosDeCumparaturi;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ShoppingCart window = new ShoppingCart();
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
		shlCosDeCumparaturi.open();
		shlCosDeCumparaturi.layout();
		while (!shlCosDeCumparaturi.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {

		shlCosDeCumparaturi = new Shell();
		shlCosDeCumparaturi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		shlCosDeCumparaturi.setSize(675, 425);
		shlCosDeCumparaturi.setText("Cos de cumparaturi");
		ScrolledComposite scrolledCompositeComponente = new ScrolledComposite(shlCosDeCumparaturi,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledCompositeComponente.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		scrolledCompositeComponente.setBounds(0, 0, 650, 375);
		scrolledCompositeComponente.setExpandHorizontal(true);
		scrolledCompositeComponente.setExpandVertical(true);

		Composite composite = new Composite(scrolledCompositeComponente, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		composite.setVisible(true);

		scrolledCompositeComponente.setContent(composite);
		scrolledCompositeComponente.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		List<SistemsAfterCommand> sisList = new ArrayList<SistemsAfterCommand>();

		List<Sistems> cartList = proiect.Models.Cart.getCartList();
		List<Sales> cartListSales = proiect.Models.Cart.getCartListSales();

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

		if (sistemDupaComanda != null)
			for (int i = 0; i < sistemDupaComanda.length; i++) {
				sisList.add(sistemDupaComanda[i]);
			}
		System.out.println(cartList);
		float total = 0;
		Label lblInfo = new Label(composite, SWT.NONE);
		lblInfo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblInfo.setBounds(100, 50, 500, 25);
		lblInfo.setText("La o comanda de peste 1000 lei, poti personaliza comanda!");
		lblInfo.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		lblInfo.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
		if (cartList.isEmpty() && cartListSales.isEmpty()) {
			Label lblError = new Label(composite, SWT.NONE);
			lblError.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
			lblError.setBounds(180, 150, 450, 50);
			lblError.setText("Cosul este gol!");
			lblError.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			lblError.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 20, SWT.NORMAL));
		} else if (!cartList.isEmpty() && cartListSales.isEmpty()) {
			int i;
			for (i = 0; i < cartList.size(); i++) {

				Label lblNume = new Label(composite, SWT.NONE);
				Label lblFamilie = new Label(composite, SWT.NONE);
				Label lblPret = new Label(composite, SWT.NONE);
				Label lblImagine = new Label(composite, SWT.NONE);
				lblNume.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
				lblNume.setBounds(25 + 200 * (i % 3), 210 + 250 * (i / 3), 150, 25);
				lblFamilie.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
				lblFamilie.setBounds(25 + 200 * (i % 3), 240 + 250 * (i / 3), 150, 25);
				lblPret.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
				lblPret.setBounds(25 + 200 * (i % 3), 270 + 250 * (i / 3), 150, 25);
				lblImagine.setAlignment(SWT.CENTER);
				lblImagine.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
				lblImagine.setImage(
						SWTResourceManager.getImage(MainPage.class, "/proiect/images/Webp.net-resizeimage.png"));
				lblImagine.setBounds(0 + 200 * (i % 3), 70 + 250 * (i / 3), 130, 110);

				lblNume.setText(cartList.get(i).getNume() + "");
				lblFamilie.setText(cartList.get(i).getFamilie() + "");
				lblPret.setText(cartList.get(i).getPret() + " ron");
				total += cartList.get(i).getPret();
				scrolledCompositeComponente.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			}
		} else if (cartList.isEmpty() && !cartListSales.isEmpty()) {
			int i;
			for (i = 0; i < cartListSales.size(); i++) {
				Label lblNume = new Label(composite, SWT.NONE);
				Label lblFamilie = new Label(composite, SWT.NONE);
				Label lblPret = new Label(composite, SWT.NONE);
				Label lblImagine = new Label(composite, SWT.NONE);
				lblNume.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
				lblNume.setBounds(25 + 200 * (i % 3), 210 + 250 * (i / 3), 150, 25);
				lblFamilie.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
				lblFamilie.setBounds(25 + 200 * (i % 3), 240 + 250 * (i / 3), 150, 25);
				lblPret.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
				lblPret.setBounds(25 + 200 * (i % 3), 270 + 250 * (i / 3), 150, 25);
				lblImagine.setAlignment(SWT.CENTER);
				lblImagine.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
				lblImagine.setImage(
						SWTResourceManager.getImage(MainPage.class, "/proiect/images/Webp.net-resizeimage.png"));
				lblImagine.setBounds(0 + 200 * (i % 3), 70 + 250 * (i / 3), 130, 110);

				String numeReducere = "";
				for (int k = 0; k < cartListSales.get(i).getListaReduceri().size(); k++) {
					if (k == cartListSales.get(i).getListaReduceri().size() - 1) {
						numeReducere = numeReducere + cartListSales.get(i).getListaReduceri().get(k).getFamilie();
					} else {
						numeReducere = numeReducere + cartListSales.get(i).getListaReduceri().get(k).getFamilie()
								+ " + ";
					}
				}
				lblNume.setText(numeReducere);
				lblPret.setText(cartListSales.get(i).getPretNou() + " ron");
				total += cartListSales.get(i).getPretNou();
				scrolledCompositeComponente.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			}
		} else {
			int i;
			for (i = 0; i < cartList.size(); i++) {
				Label lblNume = new Label(composite, SWT.NONE);
				Label lblFamilie = new Label(composite, SWT.NONE);
				Label lblPret = new Label(composite, SWT.NONE);
				Label lblImagine = new Label(composite, SWT.NONE);
				lblNume.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
				lblNume.setBounds(25 + 200 * (i % 3), 210 + 250 * (i / 3), 150, 25);
				lblFamilie.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
				lblFamilie.setBounds(25 + 200 * (i % 3), 240 + 250 * (i / 3), 150, 25);
				lblPret.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
				lblPret.setBounds(25 + 200 * (i % 3), 270 + 250 * (i / 3), 150, 25);
				lblImagine.setAlignment(SWT.CENTER);
				lblImagine.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
				lblImagine.setImage(
						SWTResourceManager.getImage(MainPage.class, "/proiect/images/Webp.net-resizeimage.png"));
				lblImagine.setBounds(0 + 200 * (i % 3), 70 + 250 * (i / 3), 130, 110);

				lblNume.setText(cartList.get(i).getNume() + "");
				lblFamilie.setText(cartList.get(i).getFamilie() + "");
				lblPret.setText(cartList.get(i).getPret() + " ron");
				total += cartList.get(i).getPret();
			}
			for (i = cartList.size(); i < cartListSales.size() + cartList.size(); i++) {
				Label lblNume = new Label(composite, SWT.NONE);
				Label lblFamilie = new Label(composite, SWT.NONE);
				Label lblPret = new Label(composite, SWT.NONE);
				Label lblImagine = new Label(composite, SWT.NONE);
				lblNume.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
				lblNume.setBounds(25 + 200 * (i % 3), 210 + 250 * (i / 3), 150, 25);
				lblFamilie.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
				lblFamilie.setBounds(25 + 200 * (i % 3), 240 + 250 * (i / 3), 150, 25);
				lblPret.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
				lblPret.setBounds(25 + 200 * (i % 3), 270 + 250 * (i / 3), 150, 25);
				lblImagine.setAlignment(SWT.CENTER);
				lblImagine.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
				lblImagine.setImage(
						SWTResourceManager.getImage(MainPage.class, "/proiect/images/Webp.net-resizeimage.png"));
				lblImagine.setBounds(0 + 200 * (i % 3), 70 + 250 * (i / 3), 130, 110);

				String numeReducere = "";
				for (int k = 0; k < cartListSales.get(i - cartList.size()).getListaReduceri().size(); k++) {
					if (k == cartListSales.get(i - cartList.size()).getListaReduceri().size() - 1) {
						numeReducere = numeReducere
								+ cartListSales.get(i - cartList.size()).getListaReduceri().get(k).getFamilie();
					} else {
						numeReducere = numeReducere
								+ cartListSales.get(i - cartList.size()).getListaReduceri().get(k).getFamilie() + " + ";
					}
				}
				lblNume.setText(numeReducere);
				lblPret.setText(cartListSales.get(i - cartList.size()).getPretNou() + " ron");
				total += cartListSales.get(i - cartList.size()).getPretNou();
			}
			scrolledCompositeComponente.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		}
		if (proiect.Models.Cart.cartListSpecial != null) {
			Label lblProcesor = new Label(composite, SWT.NONE);
			Label lblMemorie = new Label(composite, SWT.NONE);
			Label lblRam = new Label(composite, SWT.NONE);
			Label lblPret = new Label(composite, SWT.NONE);
			Label lblImagine = new Label(composite, SWT.NONE);
			lblProcesor.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
			lblProcesor.setBounds(25 + 200 * (cartListSales.size() + cartList.size() % 3),
					200 + 250 * (cartListSales.size() + cartList.size() / 3), 150, 25);
			lblMemorie.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
			lblMemorie.setBounds(25 + 200 * (cartListSales.size() + cartList.size() % 3),
					225 + 250 * (cartListSales.size() + cartList.size() / 3), 150, 25);
			lblRam.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
			lblRam.setBounds(25 + 200 * (cartListSales.size() + cartList.size() % 3),
					250 + 250 * (cartListSales.size() + cartList.size() / 3), 150, 25);
			lblPret.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
			lblPret.setBounds(25 + 200 * (cartListSales.size() + cartList.size() % 3),
					275 + 250 * (cartListSales.size() + cartList.size() / 3), 150, 25);
			lblImagine.setAlignment(SWT.CENTER);
			lblImagine.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
			lblImagine
					.setImage(SWTResourceManager.getImage(MainPage.class, "/proiect/images/Webp.net-resizeimage.png"));
			lblImagine.setBounds(0 + 200 * (cartListSales.size() + cartList.size() % 3),
					70 + 250 * (cartListSales.size() + cartList.size() / 3), 130, 110);

			lblProcesor.setText(proiect.Models.Cart.cartListSpecial.getProcesor());
			lblRam.setText(proiect.Models.Cart.cartListSpecial.getRam() + "GB RAM");
			lblMemorie.setText(proiect.Models.Cart.cartListSpecial.getMemorie() + " "
					+ proiect.Models.Cart.cartListSpecial.getSsd());
			lblPret.setText(proiect.Models.Cart.cartListSpecial.getPret() + " ron");

			total += proiect.Models.Cart.cartListSpecial.getPret();
			scrolledCompositeComponente.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		}
		Label lblTotal = new Label(composite, SWT.NONE);
		lblTotal.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblTotal.setBounds(100, 20, 80, 25);
		lblTotal.setText("Total: " + total);
		if (total > 1000) {
			Button btnPersonalizata = new Button(composite, SWT.FLAT);
			btnPersonalizata.setBounds(200, 10, 150, 30);
			btnPersonalizata.setText("Comanda personalizata");
			btnPersonalizata.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
			btnPersonalizata.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
			btnPersonalizata.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					new NewSpecialCommand().open();
					shlCosDeCumparaturi.close();
				}
			});
		}

		Button btnFinalizeaza = new Button(composite, SWT.FLAT);
		btnFinalizeaza.setBounds(370, 10, 150, 30);
		btnFinalizeaza.setText("Finalizeaza comanda");
		btnFinalizeaza.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnFinalizeaza.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnFinalizeaza.addSelectionListener(new SelectionAdapter() {
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
				int i;
				for (i = 0; i < cartList.size(); i++) {

					for (int k = 0; k < sistem.length; k++) {
						if (sistem[k].toString().contentEquals((cartList.get(i).toString()))) {
							sistem[k].setCantitate_inventar(sistem[k].getCantitate_inventar() - 1);
							sistem[k].setCantitate_comandata(sistem[k].getCantitate_comandata() + 1);
							File jsonFile = new File("Database\\sisteme.json");
							OutputStream outputStream;
							try {
								outputStream = new FileOutputStream(jsonFile);
								Gson gson1 = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
								try {
									outputStream.write(gson1.toJson(sistem).getBytes());
								} catch (IOException e2) {
									e2.printStackTrace();
								}
							} catch (FileNotFoundException e3) {
								e3.printStackTrace();
							}
						}
					}

					SistemsAfterCommand sis = new SistemsAfterCommand(cartList.get(i).getFamilie(),
							cartList.get(i).getNume(), situatie, "nu");
					sisList.add(sis);
				}
				for (i = 0; i < cartListSales.size(); i++) {
					for (int k = 0; k < cartListSales.get(i).getListaReduceri().size(); k++) {
						// update cantitate inventar si cantitate comandata
						for (int j = 0; j < reduceri.length; j++) {
							if (reduceri[j].getListaReduceri().get(k).toString()
									.contentEquals((cartListSales.get(i).getListaReduceri().get(k).toString()))) {
								reduceri[j].getListaReduceri().get(k).setCantitate_inventar(
										reduceri[j].getListaReduceri().get(k).getCantitate_inventar() - 1);
								reduceri[j].getListaReduceri().get(k).setCantitate_comandata(
										reduceri[j].getListaReduceri().get(k).getCantitate_comandata() + 1);
								File jsonFile = new File("Database\\reduceri.json");
								OutputStream outputStream;
								try {
									outputStream = new FileOutputStream(jsonFile);
									Gson gson1 = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
									try {
										outputStream.write(gson1.toJson(reduceri).getBytes());
									} catch (IOException e2) {
										e2.printStackTrace();
									}
								} catch (FileNotFoundException e3) {
									e3.printStackTrace();
								}

							}
						}

						SistemsAfterCommand sis = new SistemsAfterCommand(
								cartListSales.get(i).getListaReduceri().get(k).getFamilie(),
								cartListSales.get(i).getListaReduceri().get(k).getNume(), situatie, "nu");
						sisList.add(sis);
					}
					
				}
				String filePath = "Database\\sistemeComandate.json";
				try (Writer writer = new FileWriter(filePath, false)) {
					Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
					gson.toJson(sisList, writer);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if (proiect.Models.Cart.cartListSpecial != null) {
					List <SpecialCommand> listaComenziPersonalizate = new ArrayList <SpecialCommand>();
					BufferedReader fisierPersonalizata = null;
					String filepath = "Database\\comenziPersonalizate.json";
					try {
						fisierPersonalizata = new BufferedReader(new FileReader(filepath));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					SpecialCommand[] comandaPersonalizata = obj.fromJson(fisierPersonalizata, SpecialCommand[].class);
					if (comandaPersonalizata != null)
						for (int k = 0; k < comandaPersonalizata.length; k++) {
							listaComenziPersonalizate.add(comandaPersonalizata[k]);
						}
					proiect.Models.Cart.cartListSpecial.setSituatie(situatie);
					listaComenziPersonalizate.add(proiect.Models.Cart.cartListSpecial);
					File jsonFile = new File(filepath);
					OutputStream outputStream;
					try {
						outputStream = new FileOutputStream(jsonFile);
						Gson gson1 = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
						try {
							outputStream.write(gson1.toJson(listaComenziPersonalizate).getBytes());
						} catch (IOException e2) {
							e2.printStackTrace();
						}
					} catch (FileNotFoundException e3) {
						e3.printStackTrace();
					}
				}
				proiect.Models.Cart.cartList.clear();
				proiect.Models.Cart.cartListSales.clear();
				proiect.Models.Cart.cartListSpecial = null;
				
				shlCosDeCumparaturi.close();
			}
		});
	}
}

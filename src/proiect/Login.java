package proiect;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import proiect.Models.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.google.gson.Gson;

public class Login {
	
	protected Shell shlLogin;
	private Text txtEmail;
	private Text txtParola;

	public Text getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(Text txtEmail) {
		this.txtEmail = txtEmail;
	}

	public Text getTxtParola() {
		return txtParola;
	}

	public void setTxtParola(Text txtParola) {
		this.txtParola = txtParola;
	}

	Composite[] layouts;
	int crtActive;
	StackLayout stack;

	public static void main(String[] args) {
		try {
			Login window = new Login();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlLogin.open();
		shlLogin.layout();
		while (!shlLogin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	void changeLayout(int winNumber) {
		layouts[crtActive].setVisible(!layouts[crtActive].isVisible());
		stack.topControl = layouts[winNumber];
		crtActive = winNumber;
		shlLogin.layout();
	}

	protected void createContents() {
		
		Gson obj = new Gson();
		String filepath = "Database\\users.json";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filepath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Users[] user = obj.fromJson(br, Users[].class);
		
		shlLogin = new Shell();
		shlLogin.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		shlLogin.setImage(SWTResourceManager.getImage(Login.class, "/proiect/images/laptop.png"));
		shlLogin.setSize(705, 433);
		shlLogin.setText("Login");
		shlLogin.setLayout(new FormLayout());
		Composite composite = new Composite(shlLogin, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		FormData fd_composite = new FormData();
		fd_composite.left = new FormAttachment(0, 168);
		composite.setLayoutData(fd_composite);

		Composite compositeLogin = new Composite(shlLogin, SWT.BORDER);
		fd_composite.right = new FormAttachment(compositeLogin, 0, SWT.RIGHT);
		fd_composite.top = new FormAttachment(compositeLogin, -61, SWT.TOP);
		fd_composite.bottom = new FormAttachment(compositeLogin, -6);
		compositeLogin.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		FormData fd_compositeLogin = new FormData();
		fd_compositeLogin.bottom = new FormAttachment(100, -46);
		fd_compositeLogin.top = new FormAttachment(0, 93);
		fd_compositeLogin.left = new FormAttachment(0, 168);
		fd_compositeLogin.right = new FormAttachment(100, -163);

		Label lblSalut = new Label(composite, SWT.NONE);
		lblSalut.setBounds(51, 0, 293, 24);
		lblSalut.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblSalut.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 14, SWT.BOLD));
		lblSalut.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblSalut.setText("Salut,");

		Label lblLogin = new Label(composite, SWT.NONE);
		lblLogin.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblLogin.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblLogin.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
		lblLogin.setBounds(51, 27, 220, 24);
		lblLogin.setText("Logheaza-te cu email si parola");
		compositeLogin.setLayoutData(fd_compositeLogin);

		txtEmail = new Text(compositeLogin, SWT.NONE);
		txtEmail.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_IBEAM));
		txtEmail.setBounds(51, 51, 265, 21);

		txtParola = new Text(compositeLogin, SWT.PASSWORD);
		txtParola.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_IBEAM));
		txtParola.setBounds(51, 120, 265, 21);

		Label lblEmail = new Label(compositeLogin, SWT.NONE);
		lblEmail.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblEmail.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
		lblEmail.setBounds(51, 23, 52, 22);
		lblEmail.setText("Email:");

		Label lblParola = new Label(compositeLogin, SWT.NONE);
		lblParola.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblParola.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblParola.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
		lblParola.setBounds(51, 92, 61, 22);
		lblParola.setText("Parola:");

		Button btnLogin = new Button(compositeLogin, SWT.FLAT | SWT.CENTER);
		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i;
				boolean gasit = false;
				for (i = 0; i < user.length; i++) {
					if (user[i].getEmail().compareTo(txtEmail.getText()) == 0
							&& user[i].getParola().compareTo(txtParola.getText()) == 0) {
						//System.out.println(user[i] + "\n" + txtEmail.getText());
						shlLogin.close();
						if (user[i].getRol().compareTo("admin") == 0)
						new MainPage().open();
						else if (user[i].getRol().compareTo("curier") == 0)
						new MainPageCurier().open();
						else
						new MainPageUser().open();
						gasit = true;
					}
				}
				if (!gasit) {
					new ErrorLogin(shlLogin, 0).open();
				}
			}
		});
		btnLogin.setCursor(SWTResourceManager.getCursor(SWT.CURSOR_HAND));
		btnLogin.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnLogin.setFont(SWTResourceManager.getFont("Microsoft Sans Serif", 12, SWT.NORMAL));
		btnLogin.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnLogin.setBounds(51, 179, 265, 30);
		btnLogin.setText("Log in");

	}
}
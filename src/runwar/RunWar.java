package runwar;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.apache.catalina.startup.Tomcat;

import net.arnx.jsonic.JSON;

/**
 * TomcatとDerbyを利用した簡易Webアプリケーションサーバ。
 * <pre>
 * 組み込みTomcatとDebyを組み合わせた簡易Webアプリケーションサーバーです。
 * 設定したWebアプリケーションを起動し、それをアクセスするブラウザを起動します。
 * dataforms.jarで作成したWebアプリケーションを、あたかもスタンドアロン
 * アプリケーションの様に使用することができます。
 * </pre>
 *
 */
public class RunWar extends JFrame {

	private static final String SYSTEM_NAME = "Runwar";
	private static final String VERSION = "1.10";
	private Map<String, Object> config = null;
	private int port = 8080;
	private JPanel contentPane;
	private Tomcat tomcat = null;
	private TrayIcon icon = null;
	private JList<String> appList = null;
	
	private static ResourceBundle resource = ResourceBundle.getBundle("runwar.RunWar"); 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RunWar frame = new RunWar();
					frame.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * タスクトレイを設定します。
	 * @throws Exception 例外。
	 */
	private void setTaskTrayIcon() throws Exception {
		// アイコンイメージの読み込み
		Image image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("runwar.png"));
		this.setIconImage(image);
		// トレイアイコン生成
		this.icon = new TrayIcon(image, SYSTEM_NAME);
		// イベント登録
		icon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RunWar.this.setVisible(true);
			}
		});
		// ポップアップメニュー
		PopupMenu menu = new PopupMenu();
		// メニューの例
		MenuItem appList = new MenuItem(RunWar.resource.getString("menuitem.applist"));
		appList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RunWar.this.setVisible(true);
			}
		});

		MenuItem about = new MenuItem(RunWar.resource.getString("menuitem.about"));
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RunWar.this.about();
			}
		});

		// 終了メニュー
		MenuItem exitItem = new MenuItem(RunWar.resource.getString("menuitem.exit"));
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RunWar.this.stop();
				System.exit(0);
			}
		});
		// メニューにメニューアイテムを追加
		menu.add(appList);
		menu.add(about);
		menu.add(exitItem);
		icon.setPopupMenu(menu);

		// タスクトレイに格納
		SystemTray.getSystemTray().add(icon);
	}
	
	
	private void setFrameMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu(RunWar.resource.getString("menu.file"));
		JMenuItem exitItem = new JMenuItem(RunWar.resource.getString("menuitem.exit"));
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RunWar.this.stop();
				System.exit(0);
			}
		});
		fileMenu.add(exitItem);
		JMenu helpMenu = new JMenu(RunWar.resource.getString("menu.help"));
		JMenuItem aboutItem = new JMenuItem(RunWar.resource.getString("menuitem.about"));
		aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RunWar.this.about();
			}
		});
		helpMenu.add(aboutItem);
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		this.setJMenuBar(menuBar);
	}
	
	/**
	 * コンストラクタ。
	 */
	public RunWar() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				RunWar.this.setVisible(false);
			}
		});
		setTitle(SYSTEM_NAME);
		this.setFrameMenu();
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 346, 237);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JButton closeButton = new JButton(RunWar.resource.getString("button.close"));
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RunWar.this.setVisible(false);
			}
		});
		panel.add(closeButton);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);

		JLabel label = new JLabel(RunWar.resource.getString("menuitem.applist"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label);

		appList = new JList<String>();
		appList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					try {
						int idx = appList.getSelectedIndex();
						@SuppressWarnings("unchecked")
						List<Map<String, Object>> list = (List<Map<String, Object>>) config.get("webapps");
						Map<String, Object> m = list.get(idx);
						RunWar.this.runBrowser(m);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		contentPane.add(appList, BorderLayout.CENTER);
	}


	/**
	 * 設定ファイルの読み込み。
	 * @return 設定情報。
	 * @throws Exception 例外。
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> getConfig() throws Exception {
		Map<String, Object> ret = null;
		File config = new File("runwar.json");
		FileInputStream is = new FileInputStream(config);
		try {
			ret = JSON.decode(is, HashMap.class);
		} finally {
			is.close();
		}
		return ret;
	}
	
	/**
	 * アプリケーションの起動。
	 */
	private void start() {
		try {
			System.out.println("start");
			config = this.getConfig();
			this.tomcat = new Tomcat();
			if (config.get("port") != null) {
				port = Integer.parseInt(config.get("port").toString());
			}
			boolean started = this.isStarted();
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> webapps = (List<Map<String, Object>>) config.get("webapps");
			if (!started) {
				tomcat.setPort(port);
				tomcat.enableNaming();
				DefaultListModel<String> model = new DefaultListModel<String>();
				for (Map<String, Object> m: webapps) {
					model.addElement(m.get("title").toString());
					String context = (String) m.get("context");
					String docBase = (String) m.get("docBase");
					tomcat.addWebapp(context, new File(docBase).getAbsolutePath());
				}
				appList.setModel(model);
				System.out.println("port=" + tomcat.getConnector().getPort() + ",protocol=" + tomcat.getConnector().getProtocol());
				tomcat.start();
				this.setTaskTrayIcon();
			}
			for (Map<String, Object> m: webapps) {
				this.runBrowser(m);
			}
			
			if (started) {
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * TCPのポートを確認しTOMCATが起動しているかどうかを確認する。
	 * @return 起動している場合true。
	 * @throws IOException 例外。
	 */
	private boolean isStarted() throws IOException {
		boolean started = false;
		try {
			ServerSocket socket = new ServerSocket(port);
			try {
				;//
			} finally {
				socket.close();
			}
		} catch (BindException ex) {
			started = true;
			ex.printStackTrace();
		}
		return started;
	}


	/**
	 * ブラウザを起動して、指定したアプリケーションを表示します。
	 * @param m アプリケーションの情報マップ。
	 * @throws IOException IO例外。
	 * @throws URISyntaxException 文法例外。
	 */
	private void runBrowser(Map<String, Object> m) throws IOException, URISyntaxException {
		@SuppressWarnings("unchecked")
		List<String> browser = (List<String>) config.get("browser");
		String context = (String) m.get("context");
		String docBase = (String) m.get("docBase");
		String startPage = (String) m.get("startPage");
		Boolean br = (Boolean) m.get("browser");
		if (br) {
			System.out.println("context=" + context + ",docBase=" + docBase + startPage);
			if (browser == null) {
				Desktop.getDesktop().browse(new URI("http://localhost:" + port + context + startPage));
			} else {
				String[] cmd = new String[browser.size() + 1];
				int idx = 0;
				for (String c: browser) {
					cmd[idx++] = c;
				}
				cmd[idx] = "http://localhost:" + port + context + startPage;
				Runtime r = Runtime.getRuntime();
				r.exec(cmd);
			}
		}
	}

	/**
	 * アプリケーションの停止。
	 */
	private void stop() {
		System.out.println("stop");
		try {
			this.tomcat.stop();
			this.tomcat.destroy();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * バージョン情報。
	 */
	private void about() {
		this.icon.displayMessage(RunWar.resource.getString("menuitem.about"), SYSTEM_NAME + " ver." + VERSION + " (C) 2017 Masahiko Takayanagi.\nPowerd by Apache tomcat & Apache derby.", MessageType.INFO);
	}
}

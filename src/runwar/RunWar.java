package runwar;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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

	private Map<String, Object> config = null;
	private int port = 8080;
	private JPanel contentPane;
	private Tomcat tomcat = null;

	private JList<String> appList = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RunWar frame = new RunWar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * コンストラクタ。
	 */
	public RunWar() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				RunWar.this.start();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				RunWar.this.stop();
			}
		});
		setTitle("Runwar(Tomcat&derby)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 346, 237);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JButton closeButton = new JButton("閉じる");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stop();
				dispose();
			}
		});
		panel.add(closeButton);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);

		JLabel label = new JLabel("アプリケーション一覧");
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
						String context = (String) m.get("context");
						Desktop.getDesktop().browse(new URI("http://localhost:" + port + context));
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
			tomcat.setPort(port);
			tomcat.enableNaming();
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> webapps = (List<Map<String, Object>>) config.get("webapps");
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
			@SuppressWarnings("unchecked")
			List<String> browser = (List<String>) config.get("browser");
			for (Map<String, Object> m: webapps) {
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
			

		} catch (Exception e) {
			e.printStackTrace();
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
}

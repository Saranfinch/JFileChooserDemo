package jFileChooserDemo.resources;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JFileChooser;

/**
 * Realiza el siguiente tutorial "Adding a File Chooser to a Java Application"
 * 
 * @author Antonio Luque Bravo
 * @version 1.0
 *
 */
public class JFileChooserDemo extends JFrame {

	private JPanel contentPane;
	private JFileChooser fileChooser;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFileChooserDemo frame = new JFileChooserDemo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFileChooserDemo() {
		setTitle("JFileChooser Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 331, 266);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileChooser.showOpenDialog(contentPane);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						// What to do with the file, e.g. display it in a
						// TextArea
						textArea.read(new FileReader(file.getAbsolutePath()), null);
					} catch (IOException ex) {
						System.out.println("problem accessing file" + file.getAbsolutePath());
					}
				} else {
					System.out.println("File access cancelled by user.");
				}
			}
		});
		mnFile.add(mntmOpen);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 0, 315, 206);
		contentPane.add(textArea);

		fileChooser = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("Archivos de Texto (*.txt)", "txt");
		fileChooser.setFileFilter(filter);
	}
}

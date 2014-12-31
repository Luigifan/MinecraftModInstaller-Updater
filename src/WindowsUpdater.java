import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JProgressBar;
import javax.swing.JLabel;

import org.apache.commons.io.FileUtils;


public class WindowsUpdater extends JFrame implements Runnable
{

	private JPanel contentPane;
	private String dir;
	private File executableLocation;
	private HashMap componentMap;
	/**
	 * Create the frame.
	 */
	public WindowsUpdater(String _dir)
	{
		dir = _dir;
		
		setTitle("Updating Minecraft Mod Installer");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 529, 117);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setName("progBar");
		progressBar.setIndeterminate(true);
		progressBar.setBounds(10, 11, 503, 37);
		contentPane.add(progressBar);
		
		JLabel lblStatus = new JLabel("status");
		lblStatus.setName("progLabel");
		lblStatus.setBounds(12, 63, 503, 14);
		contentPane.add(lblStatus);
	}
	
	private void mainVoid()
	{
		JFileChooser save = new JFileChooser();
		save.setDialogTitle("Select the 'Minecraft Mod Installer' File");
		if(Program.ISWIN32EXE)
			save.setFileFilter(new FileNameExtensionFilter("Windows Executables", "exe"));
		else
			save.setFileFilter(new FileNameExtensionFilter("Executable Jar File", "jar"));
		save.setDialogType(JFileChooser.SAVE_DIALOG);
		save.setMultiSelectionEnabled(false);
		if(save.showDialog(this, "Save") == 0)
		{
			executableLocation = save.getSelectedFile();
		}
		else
			System.exit(0);
		Thread thrd = new Thread(this);
		thrd.start();
	}
	
	public void run()
	{
		int success = 1;
		
		JLabel progLabel = (JLabel)getComponentByName("progLabel");
		JProgressBar progBar = (JProgressBar)getComponentByName("progBar");
		progLabel.setText("Download update..");
		executableLocation.delete();
		if(executableLocation.getAbsolutePath().endsWith("jar"))
		{
			try
			{
				FileUtils.copyURLToFile(new URL(Program.LATESTJAR), executableLocation);
				success = 0;
			} 
			catch (MalformedURLException e)
			{
				JOptionPane.showMessageDialog(this, 
						"An error occurrred while trying to download the update from '" + Program.LATESTJAR + "'\n\nMessage: " + e.getMessage(), 
						"Minecraft Mod Installer Updater", 
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} 
			catch (IOException e)
			{
				JOptionPane.showMessageDialog(this, 
						"An IO error occurrred while trying to download the update from '" + Program.LATESTJAR + "'\n\nMessage: " + e.getMessage(), 
						"Minecraft Mod Installer Updater", 
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		else if(executableLocation.getAbsolutePath().endsWith("exe"))
		{
			try
			{
				FileUtils.copyURLToFile(new URL(Program.LATESTWINDOWSBUNDLE), executableLocation);
				success = 0;
			} 
			catch (MalformedURLException e)
			{
				JOptionPane.showMessageDialog(this, 
						"An error occurrred while trying to download the update from '" + Program.LATESTWINDOWSBUNDLE + "'\n\nMessage: " + e.getMessage(), 
						"Minecraft Mod Installer Updater", 
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} 
			catch (IOException e)
			{
				JOptionPane.showMessageDialog(this, 
						"An IO error occurrred while trying to download the update from '" + Program.LATESTWINDOWSBUNDLE + "'\n\nMessage: " + e.getMessage(), 
						"Minecraft Mod Installer Updater", 
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		
		progBar.setIndeterminate(false);
		progLabel.setText("Done");
		if(success == 0)
		{
			int result = JOptionPane.showConfirmDialog(this, 
					"Update completed successfully! Would you like to launch now?", 
					"Minecraft Mod Installer Updater", 
					JOptionPane.YES_NO_OPTION);
			if(result == 0)
			{
				ProcessBuilder pb = new ProcessBuilder(executableLocation.getAbsolutePath());
				try
				{
					pb.start();
					System.exit(0);
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else
				System.exit(0);
		}
		else
		{
			System.exit(-3);
		}
	}
	//
	private void createComponentMap() 
	{
        componentMap = new HashMap<String,Component>();
        Component[] components = this.getContentPane().getComponents();
        for (int i = 0; i < components.length; i++) {
                componentMap.put(components[i].getName(), components[i]);
        }
	}
	private Component getComponentByName(String name) 
	{ 
		return getComponentByName(this.getRootPane(), name); 
	} 
	private Component getComponentByName(Container root, String name) 
	{ 
		for (Component c : root.getComponents()) 
		{ 
			if (name.equals(c.getName())) 
			{ 
				return c; 
			} 
			if (c instanceof Container) 
			{ 
				Component result = getComponentByName((Container) c, name); 
				if (result != null) 
				{ 
					return result; 
				} 
			} 
		} 
		return null; 
	}
	//
}

import java.io.File;


public class Program
{
	public static String VERSIONURL = "http://mrmiketheripper.x10.mx/mcmodinstaller/version.txt";
	public static String LATESTMACBUNDLE = "http://mrmiketheripper.x10.mx/mcmodinstaller/mcmodinstaller.zip";
	public static String LATESTJAR = "http://mrmiketheripper.x10.mx/mcmodinstaller/mcmodinstaller.jar";
	public static String LATESTWINDOWSBUNDLE = "http://mrmiketheripper.x10.mx/mcmodinstaller/Minecraft%20Mod%20Installer.exe";

	public static Boolean ISWIN32EXE = false;
	public static Boolean ISOSXAPP = false;
	
	public static void main(String[] args)
	{
		//order of args
		// updater.jar "/home/mike/.mcmodinstaller" -isWin32 -isOSXApp blah blah blah
		if(args.length > 0)
		{
			for(int i = 0; i < args.length; i++)
			{
				if(i == 0)
				{}
				else
				{
					if(args[i].equals("-isWin32"))
						ISWIN32EXE = true;
					else if(args[i].equals("-isOSXApp"))
						ISOSXAPP = true;
				}
			}
			
			File mcmodinstallerdir = new File(args[0]);
			File mcmodinstallerjar = new File(mcmodinstallerdir.getAbsolutePath() + File.separator + "mcmodinstaller.jar");
			if(!mcmodinstallerdir.exists())
			{
				System.out.println("directory doesn't exist: " + mcmodinstallerdir.getAbsolutePath());
				System.exit(-2);
			}
			switch(OsCheck.getOperatingSystemType())
			{
			case Linux:
				break;
			case MacOS:
				break;
			case Windows:
				WindowsUpdater win32 = new WindowsUpdater(mcmodinstallerdir.getAbsolutePath());
				win32.setVisible(true);
				break;
			case Other:
				System.out.println("really man stop trying to run this on " + OsCheck.getOperatingSystemType().name());
				break;
			}
		}
		else
		{
			System.out.println("please pass the minecraft mod installer directory as an argument");
		}
	}

}

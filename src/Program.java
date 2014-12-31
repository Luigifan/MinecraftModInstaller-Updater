import java.io.File;


public class Program
{

	public static void main(String[] args)
	{
		if(args.length > 0)
		{
			File mcmodinstallerdir = new File(args[0]);
			File mcmodinstallerjar = new File(mcmodinstallerdir.getAbsolutePath() + File.separator + "mcmodinstaller.jar");
			if(!mcmodinstallerdir.exists())
			{
				System.out.println("directory doesn't exist: " + mcmodinstallerdir.getAbsolutePath());
				System.exit(-2);
			}
			if(!mcmodinstallerjar.exists())
			{
				System.out.println("file doesn't exist: " + mcmodinstallerjar.getAbsolutePath());
				System.exit(-3);
			}
			
			switch(OsCheck.getOperatingSystemType())
			{
			case Linux:
				break;
			case MacOS:
				break;
			case Windows:
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

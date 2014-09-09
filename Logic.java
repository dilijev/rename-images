import java.io.File;

public class Logic {
	public static void fixButton(String file) {
		File f = new File(file);
		if(f.isDirectory()) fixAllMedialinkImagesInDirectory(f);
		else fixMedialinkImage(f);
	}
	
	public static void tagButton(String file, String tag) {
		File f = new File(file);
		if(tag.equals("")) {
			tag = f.getName();
		}
		if(f.isDirectory()) {
			untagAllFilesInDirectory(f, tag);
			tagAllFilesInDirectory(f, tag);
		} else {
			untagFile(f, tag);
			tagFile(f,tag);
		}
	}

	public static void untagButton(String file, String tag) {
		File f = new File(file);
		if(tag.equals("")) {
			tag = f.getName();
		}
		if(f.isDirectory()) untagAllFilesInDirectory(f, tag);
		else untagFile(f,tag);
	}

	public static void cleanButton(String text) {
		File f = new File(text);
		if(f.isDirectory()) cleanAllFilesInDirectory(f);
		else cleanFile(f);
	}

	public static final String[] ext = {".jpg",".png",".gif"};

	private static boolean hasExt(File f) {
		String file = f.toString();
		for (int i = 0; i < ext.length; i++)
			if(file.contains(ext[i])) return true;
		return false;
	}

	private static void fixMedialinkImage(File f) {
		String str = f.toString().replaceAll("\\d{4}-", "");
		f.renameTo(new File(str));
	}

	private static void fixAllMedialinkImagesInDirectory(File dir) {
		File[] files = dir.listFiles();
		for (File f : files) {
			if(hasExt(f)) fixMedialinkImage(f);
		}
	}

	private static void tagFile(File f, String tag) {
		String newName = f.toString().replaceAll("(\\.\\w+)",String.format(" - %s$1",tag));
		f.renameTo(new File(newName));
	}
	
	private static void tagAllFilesInDirectory(File dir, String tag) {
		File[] files = dir.listFiles();
		for(File f : files) {
			if(hasExt(f)) tagFile(f,tag);
		}
	}
	
	private static void untagFile(File f, String tag) {
		String newName = f.toString().replaceAll(String.format(" - %s",tag),"");
		f.renameTo(new File(newName));
	}

	private static void untagAllFilesInDirectory(File dir, String tag) {
		File[] files = dir.listFiles();
		for(File f : files) {
			if(hasExt(f)) untagFile(f,tag);
		}
	}

	private static void cleanFile(File f) {
		String rename = f.toString().replaceAll("(.*\\\\).*?(\\d+).*(\\.\\w+)","$1$2$3");
		f.renameTo(new File(rename));
	}
	
	private static void cleanAllFilesInDirectory(File dir) {
		File[] files = dir.listFiles();
		for(File f : files) {
			if(hasExt(f)) cleanFile(f);
		}
	}
}
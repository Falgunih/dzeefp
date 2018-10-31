package testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import commonActions.Log;

public class Sample {

	static String sourceDirPath = "C:\\Users\\gs-0985\\workspace\\Demo products\\test report";
	static String zipFilePath = "C:\\Users\\gs-0985\\Demo_reports\\test-report";
	
	static String newDate;
	
	public static String getDateTime()
	{
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh-mm");
	    //df.setTimeZone(TimeZone.getTimeZone("IST"));
	    newDate = df.format(new Date());
	    System.out.println("NewDate: " +newDate);
	    return newDate;
	}
	
	public static void main(String[] args) throws IOException {
		getDateTime();
		File directoryToZip = new File(sourceDirPath);
        File zipFile = new File(zipFilePath);
		List<File> fileList = new ArrayList<File>();
		System.out.println("---Getting references to all files in: " + directoryToZip.getCanonicalPath());
		Log.info("---Getting references to all files in: " + directoryToZip.getCanonicalPath());
		getAllFiles(directoryToZip, fileList);
		System.out.println("---Creating zip file");
		Log.info("---Creating zip file");
		writeZipFile(zipFile, fileList);
		System.out.println("---Zip file Done");
		Log.info("---Zip file Done");
	}

	public static void getAllFiles(File dir, List<File> fileList) {
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				fileList.add(file);
				if (file.isDirectory()) {
					System.out.println("directory:" + file.getCanonicalPath());
					getAllFiles(file, fileList);
				} else {
					System.out.println("     file:" + file.getCanonicalPath());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static  void writeZipFile(File zipFile, List<File> fileList) {

		try {
			System.out.println("zipFile "+zipFile);
			FileOutputStream fos = new FileOutputStream(zipFile+newDate+".zip");
			System.out.println("zip file path : "+zipFile+".zip");
			ZipOutputStream zos = new ZipOutputStream(fos);

			for (File file : fileList) {
				if (!file.isDirectory()) { // we only zip files, not directories
					addToZip(zipFile, file, zos);
				}
			}
			zos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addToZip(File directoryToZip, File file, ZipOutputStream zos) throws FileNotFoundException,
			IOException {

		FileInputStream fis = new FileInputStream(file);

		// we want the zipEntry's path to be a relative path that is relative
		// to the directory being zipped, so chop off the rest of the path
		String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1,
				file.getCanonicalPath().length());
		System.out.println("Writing '" + zipFilePath + "' to zip file");
		ZipEntry zipEntry = new ZipEntry(zipFilePath);
		zos.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}
		zos.closeEntry();
		fis.close();
	}

}

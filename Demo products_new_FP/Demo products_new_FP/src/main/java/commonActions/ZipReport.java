package commonActions;

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

public class ZipReport {

	String sourceDirPath = System.getProperty("user.dir")+"\\test report";
	String zipFilePath = System.getProperty("user.dir")+"Demo_reports\\test-report";
	protected String newDate, newDate1;


	public ZipReport() {
		getDateTime();
		getDateTimeForMail();
	}

	public String getDateTime() {
		DateFormat df = new SimpleDateFormat("yyyy-MMM-dd__hh-mm");
		return newDate = df.format(new Date());
	}
	
	public String getDateTimeForMail() {
		DateFormat df = new SimpleDateFormat("dd MMM yyyy");
		return newDate1 = df.format(new Date());
	}

	public String zipTestReport() {
		File directoryToZip = new File(sourceDirPath);
		File zipFile = new File(zipFilePath);
		List<File> fileList = new ArrayList<File>();
		try {
			Log.info("--- Getting references to all files in: " + directoryToZip.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getAllFiles(directoryToZip, fileList);
		Log.info("---Creating zip file");
		String emailReportFile = writeZipFile(zipFile, fileList);
		Log.info("---Zip file Done");
		return emailReportFile;
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

	public String writeZipFile(File zipFile, List<File> fileList) {
		String zippedReport = zipFile+"-" + newDate + ".zip";
		try {
			System.out.println("zipFile " + zipFile);
	//		String zippedReport = zipFile + newDate + ".zip";
			System.out.println(zippedReport);
			FileOutputStream fos = new FileOutputStream(zippedReport);
			System.out.println("zip file path : " + zipFile + ".zip");
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
		return zippedReport;
	}

	public void addToZip(File directoryToZip, File file, ZipOutputStream zos)
			throws FileNotFoundException, IOException {

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

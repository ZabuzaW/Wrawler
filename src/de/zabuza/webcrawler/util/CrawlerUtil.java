package de.zabuza.webcrawler.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import de.zabuza.webcrawler.struct.EventList;

/**
 * Utility class for crawlers.
 * 
 * @author Zabuza {@literal <zabuza.dev@gmail.com>}
 *
 */
public final class CrawlerUtil {

	static {
		final TrustManager[] trustAllCertificates = new TrustManager[] { new X509TrustManager() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * javax.net.ssl.X509TrustManager#checkClientTrusted(java.security.
			 * cert.X509Certificate[], java.lang.String)
			 */
			@Override
			public void checkClientTrusted(final X509Certificate[] certs, final String authType) {
				// Do nothing. Just allow them all.
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.
			 * cert.X509Certificate[], java.lang.String)
			 */
			@Override
			public void checkServerTrusted(final X509Certificate[] certs, final String authType) {
				// Do nothing. Just allow them all.
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
			 */
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				// Not relevant.
				return null;
			}
		} };

		try {
			final SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCertificates, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (final GeneralSecurityException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * Converts date as Calendar in the string format 'dd.mm.yyyy'.
	 * 
	 * @param date
	 *            String in format 'dd.mm.yyyy' where months starts at '01'
	 * @return Date object of the given date
	 */
	public static String convertDateToString(final Calendar date) {
		String dateText = "";
		final int day = date.get(Calendar.DAY_OF_MONTH);
		if (day < 10) {
			dateText += "0";
		}
		dateText += day + ".";
		final int month = date.get(Calendar.MONTH) + 1;
		if (month < 10) {
			dateText += "0";
		}
		dateText += month + ".";
		final int year = date.get(Calendar.YEAR);
		dateText += year;

		return dateText;
	}

	/**
	 * Converts date in the string format 'dd.mm.yyyy' to an date object.
	 * 
	 * @param date
	 *            String in format 'dd.mm.yyyy' where months starts at '01'
	 * @return Date object of the given date
	 */
	public static Calendar convertStringToDate(final String date) {
		final int day = Integer.parseInt(date.substring(0, 2));
		// Month is 0-based
		final int month = Integer.parseInt(date.substring(3, 5)) - 1;
		final int year = Integer.parseInt(date.substring(6));

		return new GregorianCalendar(year, month, day);
	}

	/**
	 * Converts time in the string format 'hh:mm:ss' to an date object.
	 * 
	 * @param time
	 *            String in format 'hh:mm:ss'
	 * @return Date object of the given time
	 */
	public static Calendar convertStringToTime(final String time) {
		if (time == null) {
			return null;
		}
		final int hour = Integer.parseInt(time.substring(0, 2));
		final int minute = Integer.parseInt(time.substring(3, 5));
		final int second = Integer.parseInt(time.substring(6, 7));

		final Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		return calendar;
	}

	/**
	 * Converts time as Calendar in the string format 'hh:mm:ss'.
	 * 
	 * @param time
	 *            Calendar of the time to convert
	 * @return Date object of the given time
	 */
	public static String convertTimeToString(final Calendar time) {
		String timeText = "";

		final int hour = time.get(Calendar.HOUR_OF_DAY);
		if (hour < 10) {
			timeText += "0";
		}
		timeText += hour + ":";
		final int minute = time.get(Calendar.MINUTE);
		if (minute < 10) {
			timeText += "0";
		}
		timeText += minute + ":";
		final int second = time.get(Calendar.SECOND);
		if (second < 10) {
			timeText += "0";
		}
		timeText += second;

		return timeText;
	}

	/**
	 * Deserializes an event list from given path.
	 * 
	 * @param path
	 *            Path where event list is serialized
	 * @return Deserialized event list object
	 */
	public static EventList deserialize(final String path) {
		EventList list = null;
		try (final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
			list = (EventList) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error while deserializing event list.");
			System.err.println(e);
		}

		return list;
	}

	/**
	 * Gets the content of a file and returns it as list of lines.
	 * 
	 * @param path
	 *            Path to the file
	 * @return List of lines from the content
	 * @throws IOException
	 *             If an I/O-Exception occurs
	 */
	public static List<String> getFileContent(final String path) throws IOException {
		try (final BufferedReader site = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
			final List<String> content = new ArrayList<>();

			String line = site.readLine();
			while (line != null) {
				content.add(line);
				line = site.readLine();
			}

			return content;
		}
	}

	/**
	 * Gets the content of a web page and returns it as list of lines.
	 * 
	 * @param path
	 *            Path to the web page
	 * @return List of lines from the content
	 * @throws IOException
	 *             If an I/O-Exception occurs
	 */
	public static List<String> getWebContent(final String path) throws IOException {
		final URL url = new URL(path);
		try (final BufferedReader site = new BufferedReader(new InputStreamReader(url.openStream()))) {
			final List<String> content = new ArrayList<>();

			String line = site.readLine();
			while (line != null) {
				content.add(line);
				line = site.readLine();
			}

			return content;
		}
	}

	/**
	 * Parses a line from the database format and returns it as list of values.
	 * 
	 * @param databaseFormatLine
	 *            Line in the database format
	 * @return List of values that where contained in the line
	 */
	public static String[] parseDatabaseFormatLine(final String databaseFormatLine) {
		String databaseFormatLineToUse = databaseFormatLine;
		databaseFormatLineToUse = databaseFormatLineToUse.replaceAll(",$", ",\"\"").replaceAll("^,", "\"\",");
		final String[] values = databaseFormatLineToUse.split("\",\"");
		values[0] = values[0].substring(1);
		values[values.length - 1] = values[values.length - 1].substring(0, values[values.length - 1].length() - 1);
		return values;
	}

	/**
	 * Serializes a given event list to the given path.
	 * 
	 * @param list
	 *            list to serialize
	 * @param path
	 *            path where object should be saved
	 */
	public static void serialize(final EventList list, final String path) {
		try (final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
			oos.writeObject(list);
		} catch (final IOException e) {
			System.err.println("Error while serializing event list.");
			System.err.println(e);
		}
	}

	/**
	 * Utility class. No implementation.
	 */
	private CrawlerUtil() {

	}
}

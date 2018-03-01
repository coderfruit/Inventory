package com.stee.inventory.utils;

import com.itextpdf.text.pdf.BaseFont;
import com.lowagie.text.DocumentException;
import com.stee.inventory.dto.Result;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Base64.Decoder;

public final class Utils {

	private static final String TEMPLATE = "template.html";

	private static final String FONT = "simhei.ttf";

	private static final String Class = null;

	/**
	 * the count of day of one month
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int countDaysOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		int dayNum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return dayNum;
	}

	/**
	 * the date add one day（加或减）
	 * 
	 * @param date
	 * @return
	 */
	public static Date addOneDay(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * convert string to date
	 * 
	 * @param year
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String dateString) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date associatedTime = null;
		associatedTime = formatter.parse(dateString);
		return associatedTime;
	}

	public static Date stringToDateYearMonth(String dateString) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		Date associatedTime = null;
		associatedTime = formatter.parse(dateString);
		return associatedTime;
	}
	/**
	 * convert Date to String
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String DatesTotring(Date date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String associatedTime = null;
		associatedTime = formatter.format(date);
		return associatedTime;
	}

	public static String DatesTotringTime(Date date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String associatedTime = null;
		associatedTime = formatter.format(date);
		return associatedTime;
	}

	public static String DatesTotringYearMonth(Date date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		String associatedTime = null;
		associatedTime = formatter.format(date);
		return associatedTime;
	}
	/**
	 * 
	 * @param dataList
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static boolean createCsv(List<String> dataList,String csvFileName)
			throws UnsupportedEncodingException, IOException {
		boolean isSucess = true;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			FileOutputStream fout = new FileOutputStream(new File(csvFileName));
			fout.write(new byte[]{(byte)0xEF, (byte)0xBB, (byte)0xBF});
			osw = new OutputStreamWriter(new BufferedOutputStream(fout),
					"utf-8");
			bw = new BufferedWriter(osw);
			if (dataList != null && !dataList.isEmpty()) {
				for (String data : dataList) {
					bw.append(data).append("\r");
				}
			}
			return isSucess;
		} finally {
			if (bw != null) {
				try {
					bw.close();
					bw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (osw != null) {
				try {
					osw.close();
					osw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * get freemarker configuration
	 * 
	 * @return freemarker configuration
	 * @throws IOException
	 */
	public static Configuration getConfiguration() throws IOException {
		Configuration freemarkerCfg = new Configuration(Configuration.VERSION_2_3_22);
//		String path = pathUtil("template");
//		freemarkerCfg.setDirectoryForTemplateLoading(new File(path));
		/**
		 * jar包运行找不到resource文件路径问题
		 */
		freemarkerCfg.setClassForTemplateLoading(Utils.class, "/template");
		freemarkerCfg.setDefaultEncoding("UTF-8");
		return freemarkerCfg;
	}

	/**
	 * get the template directory path for freemarker to load
	 * 
	 * @param directoryName
	 * @return the directory path
	 */
	public static String pathUtil(String directoryName) {
		String path = Utils.class.getClassLoader().getResource(directoryName).getFile();
		return path;
	}

	/**
	 * create pdf file
	 * 
	 * @param content
	 * @return result of this operation,true or false
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void createPdf(String content) throws DocumentException, IOException {
		ITextRenderer render = new ITextRenderer();
		ITextFontResolver fontResolver = render.getFontResolver();
		fontResolver.addFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		render.setDocumentFromString(content);
		render.getSharedContext().setBaseURL("file:/" + System.getProperty("user.dir").replace('\\', '/') + "/");
		render.layout();
		render.createPDF(new BufferedOutputStream(new FileOutputStream(new File("report.pdf"))));
		// render.createPDF(new
		// BufferedOutputStream(response.getOutputStream()));

	}

	/**
	 * freemarker render html page
	 * 
	 * @param data
	 * @param templateName
	 * @return
	 */
	public static String freeMarkerRender(Map<String, Object> data, String templateName) {
		Writer out = new StringWriter();
		String result = null;
		try {
			Configuration configuration = getConfiguration();
			Template template = configuration.getTemplate(templateName);
			template.process(data, out);
			out.flush();
			result = out.toString();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		} finally {
			try {
				if (null != out) {
					out.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * create .png picture use base64 code for creating pdf
	 * 
	 * @param base64Info
	 * @throws IOException
	 */
	public static void createPicture(String base64Info) throws IOException {
		String pngName = "report.png";
		base64Info = base64Info.replaceAll(" ", "+");
		Decoder decoder = Base64.getDecoder();
		String[] arr = base64Info.split("base64,");
		byte[] buffer;
		OutputStream output = null;
		try {
			buffer = decoder.decode(arr[1]);
			output = new BufferedOutputStream(new FileOutputStream(new File(pngName)));
			output.write(buffer);
			output.flush();
		} finally {
			if (null != output) {
				output.close();
			}
		}
	}

	public static Result<String> exportPdf(HttpServletResponse response) {
		response.reset();
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=" + "report.pdf");
		Result<String> result = new Result<>();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(
					new FileInputStream(new File(System.getProperty("user.dir") + "/report.pdf")));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buffer = new byte[1024];
			while (-1 != bis.read(buffer)) {
				bos.write(buffer, 0, buffer.length);
			}
			bos.flush();
			result.setStatusCode("000000");
			result.setMessage("success");
			return result;
		} catch (IOException e) {
			result.setStatusCode("999999");
			result.setMessage("fail");
			return result;
		} finally {
			try {
				if (null != bis) {
					bis.close();
				}
				if (null != bos) {
					bos.close();
				}
			} catch (IOException e) {
				result.setStatusCode("999999");
				result.setMessage("fail");
				return result;
			}
		}

	}

	public static Result<String> exportCsv(HttpServletResponse response, String csvFileName) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos1 = null;
		response.reset();
		response.setContentType("application/csv");
		response.setHeader("Content-Disposition", "attachment; filename=" + csvFileName);
		Result<String> result = new Result<>();
		try {
			bis = new BufferedInputStream(
					new FileInputStream(new File(System.getProperty("user.dir") + "/"+csvFileName)));
			bos1 = new BufferedOutputStream(response.getOutputStream());
			byte[] buffer = new byte[1024];
			int byteread = 0; 
			while (-1 != (byteread=bis.read(buffer))) {
				bos1.write(buffer, 0, byteread);
			}
			bos1.flush();
			result.setStatusCode("000000");
			result.setMessage("success");
			return result;
		} catch (IOException e) {
			result.setStatusCode("999999");
			result.setMessage("fail");
			return result;
		} finally {
			try {
				if (null != bis) {
					bis.close();
				}
				if (null != bos1) {
					bos1.close();
				}
			} catch (IOException e) {
				result.setStatusCode("999999");
				result.setMessage("fail");
				return result;
			}
		}
	}




}

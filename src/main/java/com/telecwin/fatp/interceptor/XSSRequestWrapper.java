package com.telecwin.fatp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

public class XSSRequestWrapper extends HttpServletRequestWrapper {
	
	public XSSRequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
	}

	@Override
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = stripXSS(values[i]);
		}
		return encodedValues;
	}

	@Override
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		return stripXSS(value);
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		return stripXSS(value);
	}

	private String stripXSS(String value) {
		if (value != null) {
			return Jsoup.clean(value, "", Whitelist.relaxed().addAttributes("a", new String[]{"target"})
					.addAttributes("h3", new String[]{"style"})
					.addAttributes("div", new String[]{"style"})
					.addAttributes("table", new String [] {"style","border","cellpadding","cellspacing"})
					.addAttributes("tr", new String [] {"style"})
					.addAttributes("td", new String [] {"style"})
					.addAttributes("p", new String [] {"style"})
					, new Document.OutputSettings().prettyPrint(false));
		}
		return value;
	}
	public static void main(String[] args) {
		System.out.println(Whitelist.basic().toString());
	}
}

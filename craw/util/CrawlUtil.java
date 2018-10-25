package crawl.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CrawlUtil {

	public Document getDocument(String url) {
		try {
			// 5000是设置连接超时时间，单位ms
			return Jsoup.connect(url).timeout(10000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<String> getData(String companyName) {
		System.out.println(companyName);
		List<String> comList = new ArrayList<String>();
		CrawlUtil t = new CrawlUtil();
		Document doc = t.getDocument("https://www.tianyancha.com/search?key=" + companyName);

		// 搜索结果列表
		Elements e = doc.select("[class=search-result-single  ]");

		Elements content = e.select("[class=content]");
		Elements tbody = content.select("[class=name select-none]");
		String[] str1 = tbody.get(0).toString().split("href=\"");
		String[] str2 = str1[1].split("\" target=");
		String url = str2[0];
		Document docurl = t.getDocument(url);

		Elements eurl = docurl.select("[class=table -striped-col -border-top-none]");

		Elements ttbody = eurl.get(0).select("tbody");

		Elements trurl = ttbody.get(0).select("tr");

		Elements tb = new Elements();
		for (int i = 0; i < trurl.size(); i++) {
			tb = trurl.get(i).select("tr");
			Elements tdurl = tb.get(0).select("td");
			String tdStr = "";
			for (int j = 0; j < tdurl.size(); j++) {
				tdStr = tdurl.get(j).text();
				if (tdStr.contains("名词解释由天眼查合作伙伴北大法宝提供")) {
					String[] tdS = tdStr.split(" ");
					tdStr = tdS[0];
				}
				comList.add(tdStr);
			}
		}
		return comList;
	}

}

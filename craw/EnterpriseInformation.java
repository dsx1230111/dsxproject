package crawl;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class EnterpriseInformation {
	public  Document getDocument (String url){
        try {
       	 //5000是设置连接超时时间，单位ms
            return Jsoup.connect(url).timeout(5000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public static void main(String[] args) {
		EnterpriseInformation t = new EnterpriseInformation();
		String companyName = "武汉银服";
		Document doc = t.getDocument("https://www.qichacha.com/search?key="+ companyName);
		
		//搜索结果列表
		Elements e = doc.select("[class=m_srchList]");
		
		//tbody
		Elements tbody = e.select("tbody");
		
		//tr
		Elements tr = tbody.select("tr");
		
		//td
		Elements td = tr.get(0).select("td");
		Elements onerror = td.get(0).children();
		
		//url
		String[] str1 = onerror.toString().split("href=\"");
		String[] str2 = str1[1].split("\" target=");		
		
		String url ="https://www.qichacha.com" +str2[0];
		System.out.println("url:"+url);
		
		Document docurl = t.getDocument(url);
		
		Elements eurl = docurl.select("[class=panel b-a base_info]");
		
		//ntable
		Elements tntable = eurl.select("[class=ntable]");
		
		//tbody
		Elements ttbody = tntable.get(1).select("tbody");
		
		//tr
		Elements trurl = ttbody.get(0).select("tr");
		
		//tb
		Elements tb = new Elements();
		for (int i = 0; i < trurl.size(); i++) {
			tb = trurl.get(i).select("tr");
			Elements tdurl = tb.get(0).select("td");
			String tdStr = "";
			for (int j = 0; j < tdurl.size(); j++) {
				tdStr = tdurl.get(j).text();
				System.out.println(tdStr);
			}
		}
	}

}

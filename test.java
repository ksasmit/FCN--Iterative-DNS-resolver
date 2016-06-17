import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class test {

	public static void main(String[] args) throws IOException {
		
		List<String> list=new ArrayList<String>();
		list.add("www.google.com");
		list.add("www.facebook.com");
		list.add("www.youtube.com");
		list.add("www.baidu.com");
		list.add("www.yahoo.com");
		list.add("www.amazon.com");
		list.add("www.wikipedia.org");
		list.add("www.Qq.com");
		list.add("www.google.co.in");
		list.add("www.twitter.com");
		list.add("www.live.com");
		list.add("www.taobao.com");
		list.add("www.sina.com.cn");
		list.add("www.msn.com");
		list.add("www.yahoo.co.jp");
		list.add("www.linkedin.com");
		list.add("www.weibo.com");
		list.add("www.google.co.jp");
		list.add("www.vk.com");
		list.add("www.bing.com");
		list.add("www.yandex.ru");
		list.add("www.hao123.com");
		list.add("www.ebay.com");
		list.add("www.instagram.com");
		list.add("www.google.de");
		//list.add("www.Qq.com");
		
		File file = new File("myDNS.txt");
	    file.createNewFile();
	    FileWriter writer = new FileWriter(file); 
	    for(int i=0;i<list.size();i++)
		{
	    	System.out.println(list.get(i));
			writer.write(list.get(i)+":");
			long startTime = System.currentTimeMillis();
			for(int j=0;j<10;j++)
			{
				mydnsresolver.main(new String[]{list.get(i)});
			}
			long endTime = System.currentTimeMillis();
			writer.write("Average RTT: " + (endTime - startTime) / 10+" msec"+"\n");
		}
		writer.flush();
	    writer.close();
	}

}

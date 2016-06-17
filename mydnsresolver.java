import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.StringTokenizer;

import org.xbill.DNS.*;
public class mydnsresolver {

	  static Name name = null;
	  static int type = Type.A, dclass = DClass.IN;
	  public static void main(String[] args) throws UnknownHostException, TextParseException,IOException
	  {
			Message query, response=null;
			Record rec;
			SimpleResolver res = null;
			int i=0;
			int r=1;int k;
			for(i=0;i<args.length;i++)
			{
				//System.out.println(args[i]);
			}
			int flag=0;
			String server=null;
			ArrayList<String> root_list = new ArrayList<String>();
			ArrayList<String> list = new ArrayList<String>();
			root_list.add("198.41.0.4");
			root_list.add("192.228.79.201");
			root_list.add("192.33.4.12");
			root_list.add("199.7.91.13");
			root_list.add("192.203.230.10");
			root_list.add("192.5.5.241");
			root_list.add("192.112.36.4");
			root_list.add("198.97.190.53");
			root_list.add("192.36.148.17");
			root_list.add("192.58.128.30");
			root_list.add("193.0.14.129");
			root_list.add("199.7.83.42");
			root_list.add("202.12.27.33");
				i=0;k=0;
				name = Name.fromString(args[0], Name.root);
				while(true)//i<list.size())
				{
					flag =0;
					if(r==1)
					{
						if(k<13)
						{
							server = root_list.get((k++)%13); // %13 does round robin
							r=0;
						}
						else
						{
							System.out.println("Could not resolve");
							return;
						}
					}
					else if(r==0 && i<list.size())
						server = list.get(i++);
					else
					{
						r=1;
						continue;
					}
					//System.out.println("server name selected: "+server);
					if (server != null)
						res = new SimpleResolver(server);
					else
						res = new SimpleResolver();
					
					rec = Record.newRecord(name, type, dclass);
					query = Message.newQuery(rec);
						//System.out.println(query);
					try {
						response = res.send(query);
					} catch (IOException e) {
						e.printStackTrace();
					}
						//System.out.println(response);
						if(response ==null)
							continue;
						String head=response.getHeader().toString();
						if(head.contains("status: NOERROR"))
						{
							//check status: NOERROR
							Record [] ans = response.getSectionArray(Section.ANSWER);
							if(ans.length ==0)
							{
								//System.out.println("answer is null");
								
								Record [] records = response.getSectionArray(Section.AUTHORITY);
								if(records.length !=0)
								{
									list.clear();
									i=0;
									for (int j = 0; j < records.length; j++)
									{
										if(records[j].getType()==Type.SOA)
										{
										}
										else
											list.add(records[j].rdataToString());
										//System.out.println(records[j].rdataToString());
									}
								}
								else
								{
									r=1;
									continue;
								}
							}
							else
							{// check if answer has ip address
								//list.clear();
								for (int j = 0; j < ans.length; j++)
								{
									if(ans[j].getType()==Type.A || ans[j].getType()==Type.AAAA )
									{
										//System.out.println("valid ip");
										list.clear();
										list.add(ans[j].rdataToString());
										System.out.println(ans[j].rdataToString());
										flag =1;
										break;
									}
									//else if(ans[j].getType()==Type.CNAME)
									else
									{
										//list.add(ans[j].rdataToString());
										name = Name.fromString(ans[j].rdataToString(), Name.root);
										r=1;
										continue;
									}
									//System.out.println(ans[j].rdataToString());
								}
								if(flag==1)
									break;
							}
						}
						else
						{
							continue;
						}
			}//end of while
			
	  }//end of main

}
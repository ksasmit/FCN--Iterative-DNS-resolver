import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.StringTokenizer;

import org.xbill.DNS.*;
public class mydig {

	  static Name name = null;
	  static int type = Type.ANY, dclass = DClass.IN;
	  public static void main(String[] args) throws UnknownHostException, TextParseException,IOException
	  {
		  	long start_time=0,end_time=0;
		  	InetAddress address=null;
			Message query, response=null;
			Record rec;Record[] q=null;
			SimpleResolver res = null;
			int i=0,f=0,a=0;
			int r=1;int k;
			java.util.Date date = new java.util.Date();
			int an=0,qst=1,ath=0,adtnl=0;
			if(args.length<2)
			{
				System.out.println("Insufficient no of arguments");
				return;
			}
			for(i=0;i<args.length;i++)
			{
				//System.out.println(args[i]);
			}
			if(args[1].equalsIgnoreCase("A"))
				type = Type.A;
			else if(args[1].equalsIgnoreCase("NS"))
				type = Type.NS;
			else if(args[1].equalsIgnoreCase("MX"))
				type = Type.MX;
			else{
				System.out.println("Unknown type entered");
				return;
			}
			int flag=0;
			String server=null;
			Record[] answer=null;
			Record authority;
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
							server = root_list.get((k++)%13);
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
					start_time = System.currentTimeMillis();
					rec = Record.newRecord(name, type, dclass);
					query = Message.newQuery(rec);
					//System.out.println("query\n\n\n");
						//System.out.println(query);
					try {
						response = res.send(query);
					} catch (IOException e) {
						e.printStackTrace();
					}
						//System.out.println(response);
						if(response == null)
							continue;
						//System.out.println(response);
						String head=response.getHeader().toString();
						
						if(head.contains("status: NOERROR"))
						{
							if(f==0)
							{
								q =response.getSectionArray(Section.QUESTION);
							}
							//check status: NOERROR
							Record [] ans = response.getSectionArray(Section.ANSWER);
							Record [] records = response.getSectionArray(Section.AUTHORITY);
							Record []addit=response.getSectionArray(Section.ADDITIONAL);
							ath=records.length;
							adtnl=addit.length;
							if(ans.length ==0)
							{
								//System.out.println("answer is null");
								


								if(records.length !=0)
								{
									list.clear();
									i=0;
									for (int j = 0; j < records.length; j++)
									{
										if(records[j].getType()==Type.SOA)
										{
											    System.out.println("QUERY: "+qst+", ANSWER: "+an+", AUTHORITY: "+ath+", ADDITIONAL: "+adtnl);
												System.out.println(";;QUESTION");
												System.out.println(q[0].toString());
												System.out.println(";;ANSWER");
											if(f==1)
											{
												for(int z=0;z<an;z++)
													System.out.println(answer[z].toString());
											}
											authority=records[j];
											System.out.println(";;AUTHORITY");
											System.out.println(authority.toString());
											System.out.println(";;ADDITIONAL");
											for(int z=0;z<adtnl;z++)
												System.out.println(addit[z].toString());
											end_time = System.currentTimeMillis();
											System.out.print(";; Query time: ");
											System.out.print(end_time-start_time);
											System.out.println(" msec");
											System.out.print(";; SERVER: ");
											//address = InetAddress.getByName(root_list.get(k-1)); 
											address = InetAddress.getLocalHost(); 
											System.out.println(address.getHostAddress());
											//System.out.println(root_list.get(k-1));
											System.out.print(";; WHEN: ");
										    System.out.println(date);
											return;
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
							else//answer section is not blank
							{// check if answer has ip address

								//list.clear();
								//System.out.println("answer is not null");
								if(an==0)
									an = ans.length;
								for (int j = 0; j < ans.length; j++)
								{
									//if(validIP(ans[j].rdataToString()))
									answer= ans;
									if(ans[j].getType()==Type.A || ans[j].getType()==Type.AAAA ||ans[j].getType()==type)
									{
										//System.out.println("valid ip");
										
									//System.out.println(head);	
								    System.out.println("QUERY: "+qst+", ANSWER: "+an+", AUTHORITY: "+ath+", ADDITIONAL: "+adtnl);
									System.out.println(";;QUESTION");
									System.out.println(q[0].toString());
									System.out.println(";;ANSWER");
									for(int z=0;z<an;z++)
										System.out.println(answer[z].toString());
									System.out.println(";;AUTHORITY");
									for(int z=0;z<ath;z++)
										System.out.println(records[z].toString());
									System.out.println(";;ADDITIONAL");
									for(int z=0;z<adtnl;z++)
										System.out.println(addit[z].toString());
									end_time = System.currentTimeMillis();
									System.out.print(";; Query time: ");
									System.out.print(end_time-start_time);
									System.out.println(" msec");
									System.out.print(";; SERVER: ");
									//address = InetAddress.getByName(root_list.get(k-1)); 
									//address = InetAddress.getByName(server); 
									//System.out.println(address.getHostAddress());
									address = InetAddress.getLocalHost(); 
									System.out.println(address.getHostAddress());
									
									//System.out.println(root_list.get(k-1));
									System.out.print(";; WHEN: ");
								    System.out.println(date);
									return;
									}
									//else if(ans[j].getType()==Type.CNAME)
									else
									{//cname is taken and resolved from root
										//list.add(ans[j].rdataToString());
										if(f==0)
										{
											f++;;
										}
										//answer= ans;
										//System.out.println(answer.toString());
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
import java.net.UnknownHostException;

import org.xbill.DNS.ARecord;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.Resolver;
import org.xbill.DNS.SimpleResolver;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

public class googleresolver {

	public static void main(String[] args) throws TextParseException, UnknownHostException {
		Resolver resolver = new SimpleResolver("8.8.8.8");
	    Lookup lookup = new Lookup(args[0], Type.A);
	    lookup.setResolver(resolver);
	    Record[] records = lookup.run();
	    String address = ((ARecord) records[0]).getAddress().toString();
	    System.out.println(address);

	}

}


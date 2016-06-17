import java.net.InetAddress;
import java.net.UnknownHostException;

public class localresolver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InetAddress address=null;
		try {
			address = InetAddress.getByName(args[0]);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println(address.getHostAddress());
	}

}

import java.io.* ;
import java.net.* ;
import java.util.* ;

public final class Server 
{
	public static void main(String[] args) throws Exception
	{
		ServerSocket web = new ServerSocket(6789);                //Create socket for listening
		while (true) {
			System.out.println("Waiting on socket 6789....");
			Socket mysocket = web.accept();                         //Start listening on socket
			System.out.println("Accepted: " + mysocket);
			
			MyRequest httpreq = new MyRequest(mysocket);        //This class will handle the request on our socket
			Thread thread = new Thread(httpreq);                  //This thread processess request from above

			thread.start();
		}
	}
}
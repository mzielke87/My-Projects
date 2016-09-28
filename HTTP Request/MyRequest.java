import java.io.* ;
import java.net.* ;
import java.util.* ;

public final class MyRequest implements Runnable
{
	
	Socket mysocket;
	
	//-------Constructor-------//
	public MyRequest(mysocket mysocket) throws Exception
	{
		this.mysocket = mysocket;
	}
	
	//-------Run MyRequest Class-------//
	public void run()
	{
		try 
		{
			processRequest();
		} catch (Exception e)
		{
			System.out.println(e);
		}

	}

	private void processRequest() throws Exception
	{
		InputStream instream = mysocket.getInputStream();

		DataOutputStream outstream = new DataOutputStream(mysocket.getOutputStream());

		BufferedReader buffread = new BufferedReader(new InputStreamReader(instream));

		String requestLine = buffread.readLine();

		// Display the request line.
		System.out.println();
		System.out.println(requestLine);

		String headerLine = null;
		while ((headerLine = buffread.readLine()).length()!=0) 
		{
			System.out.println(headerLine);
		}
		// Extract the filename from the request line.
		StringTokenizer tokens = new StringTokenizer(requestLine);
		tokens.nextToken();
		tokens.nextToken(); // skip over the method, which should be “GET”
		String fileName = tokens.nextToken();
		URL url = new URL(u);
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		int code = http.getResponseCode();
		// Prepend a “.” so that file request is within the current directory.
		fileName = “.” + fileName;

		System.out.println(“Filename to Get” + fileName);

		// Open the requested file.
		FileInputStream fileis = null;
		boolean fileExists = true;
		try 
		{
			fileis = new FileInputStream(fileName);
		} catch (FileNotFoundException e)
		{
			fileExists = false;
		}

		// Construct the response message.
		String statusLine = null;
		String contentTypeLine = null;
		String entityBody = null;
		if (fileExists) 
		{
			statusLine = “200 OK:”;
			contentTypeLine = “Content-Type: ” +
			contentType( fileName, code ) + CRLF;
		} else 
		{
			statusLine = “HTTP/1.1 404 Not Found:”;
			contentTypeLine = “Content-Type: text/html”+ CRLF;
			entityBody = “” +
			“Not Found” +
			“Not Found”;
		}
		// Send the status line.
		outstream.writeBytes(statusLine);

		// Send the content type line.
		outstream.writeBytes(contentTypeLine);

		// Send a blank line to indicate the end of the header lines.
		outstream.writeBytes(CRLF);
		// Send the entity body.
		if (fileExists) 
		{
			sendBytes(fileis, outstream);
			fileis.close();
		} else 
		{
			outstream.writeBytes(entityBody);
		}
		outstream.writeBytes(entityBody);

		DataOutputStream outToClient = new
		DataOutputStream(mysocket.getOutputStream());

		outToClient.writeBytes(“HTTP/1.1 200 OK”);
		outToClient.writeBytes(“Content-Length: 100″);
		outToClient.writeBytes(“Content-Type: text/html\n\n”);
		outToClient.writeBytes(“

		My First Heading

		My first paragraph.

		\n”);
		outToClient.close();
		// Close streams and mysocket.
		outstream.close();
		buffread.close();
		mysocket.close();
	}
	
	private static String contentType(String fileName, int responseCode)
	{
		
		String s = "HTTP/1.0 ";
		
		switch (responseCode) {
		  case 200:
			s = s + "200 OK";
			break;
		  case 400:
			s = s + "400 Bad Request";
			break;
		  case 403:
			s = s + "403 Forbidden";
			break;
		  case 404:
			s = s + "404 Not Found";
			break;
		  case 500:
			s = s + "500 Internal Server Error";
			break;
		  case 501:
			s = s + "501 Not Implemented";
			break;
		}
		
		if(fileName.endsWith(“.html”) || fileName.endsWith(“.html”)) 
		{
			s = s + "Content-Type: text/html\r\n";
		}
		if (fileName.endsWith(".zip") || fileName.endsWith(".exe")
          || fileName.endsWith(".tar")) 
		{
			s = s + "Content-Type: application/x-zip-compressed\r\n";
		}
		if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) 
		{
			s = s + "Content-Type: image/jpeg\r\n";
		}
		if (fileName.endsWith(".gif")) 
		{
			s = s + "Content-Type: image/gif\r\n";
		}
		
		output.writeBytes(s);
		
	}

	private static void sendBytes(FileInputStream fileis, OutputStream outstream)
	throws Exception
	{
		// Construct a 1K buffer to hold bytes on their way to the mysocket.
		byte[] buffer = new byte[1024];
		int bytes = 0;

		// Copy requested file into the mysocket’s output stream.
		while((bytes = fileis.read(buffer)) != -1 ) 
		{
			outstream.write(buffer, 0, bytes);
		}
	}
}
import java.io.*;
import java.net.*;

public class Talk {
	
	public static void main(String [] args)
	{
		System.out.println("Starting Talk Protocol");
		System.out.println("------------------------\n\n");
		
		Talk obj = new Talk();
		String mode = args[0]; //parse the line for what mode we will be starting
		int portNumber = 12987; //default unless explicitly changed
		String serverName = null; //placeholder for if client mode is used
		
		
			//if statements for modes will go here
			//IDEA: create method for both -h and -s and then call those if -a is used
			//-h needs hostname/IPaddress and -p port number
		if(mode.equals("-h") || mode.equals("-s") || mode.equals("-a") || mode.equals("-help"))
		{
			if(mode.equals("-h"))
				{	
					if(args.length == 1 || args.length == 2)
					{
						if(args.length == 2 && !args[1].equals("-p"))
							serverName = args[1];
						else if(args.length > 1 && args[1].equals("-p"))
						{
							System.out.println("I'm sorry... Please check your input and try again");
							System.exit(1);
						}
						else
							try {
								serverName = InetAddress.getLocalHost().getHostAddress();
							} catch (UnknownHostException e) {
								e.printStackTrace();
							}
						portNumber = 12987;
					}
					else if(args.length == 3)
					{
						if(args[1].equals("-p"))
						{	try {
								portNumber = Integer.parseInt(args[2]);
							}
							catch(Exception e)
							{
								System.out.println("I'm sorry... Please check your input and try again");
								System.exit(1);
							}
						}
						else 
						{
							System.out.println("I'm sorry... Please check your input and try again");
							System.exit(1);
						}
					}
					else if(args.length == 4)
					{
						serverName = args[1];
						try {
							portNumber = Integer.parseInt(args[3]);
						}
						catch(Exception e)
						{
							System.out.println("I'm sorry... Please check your input and try again");
							System.exit(1);
						}
					}
					else if(args.length > 4)
					{
						System.out.println("I'm sorry... Please check your input and try again");
						System.exit(1);
					}
					
					System.out.println("Attempting to Connect to Server......\n");
					obj.ClientMode(serverName, portNumber);
				}
				
				//-s needs only -p port number
				else if(mode.equals("-s"))
				{
					System.out.println("\nAttempting to Setup Server......\n");
					
					if(args.length >= 2)
					{
						if(args[1].equals("-p") && args.length == 3)
							try {
								portNumber = Integer.parseInt(args[2]);
							}
							catch(Exception e)
							{
								System.out.println("I'm sorry... Please check your input and try again");
								System.exit(1);
							}
						else
						{
							System.out.println("I'm sorry... Please check your input and try again");
							System.exit(1);
						}
					}
					else if(args.length > 3)
					{
						System.out.println("I'm sorry... Please check your input and try again");
						System.exit(1);
					}
					obj.ServerMode(portNumber);
					
				}
				
				//-a needs hostname/ipaddress and -p port number try catch finally?
				else if(mode.equals("-a"))
				{
					System.out.println("Starting AutoMode......");
					try 
					{
						obj.autoMode = true;
						if(args.length == 1 || args.length == 2)
						{
							if(args.length == 2 && !args[1].equals("-p"))
								serverName = args[1];
							else if(args.length > 1 && args[1].equals("-p"))
							{
								System.out.println("I'm sorry... Please check your input and try again");
								System.exit(1);
							}
							else
								try {
									serverName = InetAddress.getLocalHost().getHostAddress();
								} catch (UnknownHostException e) {
									e.printStackTrace();
								}
							portNumber = 12987;      
						}
						else if(args.length == 3)
						{
							if(args[1].equals("-p"))
							{
								try {
									portNumber = Integer.parseInt(args[2]);
								}
								catch(Exception e)
								{
									System.out.println("I'm sorry... Please check your input and try again");
									System.exit(1);
								}
							}
							else 
							{
								System.out.println("I'm sorry... Please check your input and try again");
								System.exit(1);
							}
						}
						else if(args.length == 4)
						{
							serverName = args[1];
							try {
								portNumber = Integer.parseInt(args[3]);
							}
							catch(Exception e)
							{
								System.out.println("I'm sorry... Please check your input and try again");
								System.exit(1);
							}
						}
						else if(args.length > 4)
						{
							System.out.println("I'm sorry... Please check your input and try again");
							System.exit(1);
						}
						
						System.out.println("Attempting to Connect to Server......\n");
						obj.ClientMode(serverName, portNumber);
						
						
						if(obj.autoMode)
						{
							System.out.println("\nAttempting to Setup Server......\n");
							
							if(args.length == 3)
							{
								if(args[1].equals("-p"))
								{	try {
										portNumber = Integer.parseInt(args[2]);
									}
									catch(Exception e)
									{
										System.out.println("I'm sorry... Please check your input and try again");
										System.exit(1);
									}
								}
							}
							else if(args.length > 4)
							{
								System.out.println("I'm sorry... Please check your input and try again");
								System.exit(1);
							}
							obj.ServerMode(portNumber);
						}
					}
					catch(Exception e) 
					{
						System.out.println(e);
					}
				}
				
				//-help prints name, gives instructions
				else if(mode.equals("-help"))
				{
					System.out.println("Hello! My name is Chloe Flores and I made this program! Here are some neat tricks for all you cool cats and kittens:\n");
					System.out.println("TO CONNECT AS CLIENT: \n Type in \"-h\" and nothing else in order to use deafult values for the hostname/IP and port number");
					System.out.println(" To specify the hostname/IP, type in \"-h SERVERNAME\" with SERVERNAME being the hostname you are connecting to.\n If you would like to only specify "+
							"the port number and want to use the default hostname/IP you can type \"-h -p PORTNUMBER\" \n If you would like to specify both please type \"-h SERVERNAME -p PORTNUMBER\"");
					System.out.println("\nTO SET UP SERVER: \n For default values, you can type in \"-s\" and if you would like to specify the portnumber, you can type \"-s -p PORTNUMBER\"");
					System.out.println("\nTO ENTER AUTOMODE: \n For default values, you can type in \"-a\".\n If you would like to specify the hostname type \"-a SERVERNAME\" and you can specify the port number "+
							"by typing \"-a -p PORTNUMBER\".\n If you would like to specify both hostname/IP and portnumber, please type \"-a SERVERNAME -p PORTNUMBER\"");
					System.out.println("\nKEYWORD TIDBITS:");
					System.out.println("\n If at any time during your connection you would like to see information reagarding your connection, please enter \"STATUS\"");
					System.out.println(" If you would like to end the connection at any time, please enter \"EXIT\" or \"exit\"\n"+ "\nUntil next time, this is Chloe Flores signing off!\"");
				}
		}
		else
		{	
			System.out.println("I'm sorry... Please check your input and try again");
			System.exit(1);
		}
		
	}
	
	//my private variables, no touchy
	private BufferedReader in = null;
	private Socket client = null;
	private Socket clientConnection = null;
	private ServerSocket server = null;
	private boolean autoMode = false;
	
	public void ServerMode(int port)
	{
		try 
		{
			server = new ServerSocket(port);
			System.out.println("ServerSocket \nLocal IP:"+server.getInetAddress().getHostAddress()+"  Local Port:"+server.getLocalPort());
			
		}
		catch(IOException e)
		{
			System.out.println("Error: " + e);
			System.exit(-1);
		}
		
		try
		{
			clientConnection = server.accept();
			//Print client IP and port number
			System.out.println("Server accepted connection");
			System.out.println("Local IP:"+clientConnection.getLocalAddress().getHostAddress()+" Local port:"+clientConnection.getLocalPort());
			System.out.println("Remote IP: "+clientConnection.getInetAddress().getHostAddress()+"  Remote port:"+clientConnection.getPort());
			
		}
		catch(IOException e)
		{
			System.out.println("Error: " + e);
			System.exit(-1);
		}
		
		try{
			in = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
		}
		catch (IOException e)
		{
			System.out.println("Couldn't get an inputStream from the client");
			System.exit(-1);
		}
		
		try{
			PrintStream sendDataToClient = new PrintStream(clientConnection.getOutputStream());
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
			
			Thread sendMessage = new Thread(new Runnable() {
				public void run() {
					while(true)
					{
						String message;
						try 
						{
							message = keyboard.readLine();
							if(message.equals("STATUS"))
							{
								System.out.println("\nLocal IP:"+clientConnection.getLocalAddress().getHostAddress()+" Local port:"+clientConnection.getLocalPort());
								System.out.println("Remote IP: "+clientConnection.getInetAddress().getHostAddress()+"  Remote port:"+clientConnection.getPort());
							}
							else if(message.equals("EXIT") || message.equals("exit"))
							{
								System.out.println("Ending connection... Goodbye!");
								sendDataToClient.println("exit");
								System.exit(1);
							}
							else
								sendDataToClient.println(message);
							
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			});
			
			Thread readMessage = new Thread(new Runnable() {
				public void run() {
					while(true)
					{
						String clientMessage;
						try 
						{	if(in.ready())
							{
								clientMessage = in.readLine();
								if(clientMessage.equals("exit"))
								{
									System.out.println("The client has disconnected... ending connection... Goodbye!");
									sendDataToClient.println("approved000");
									System.exit(1);
								}
								else
									System.out.println("[REMOTE] " + clientMessage);
							}
							
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			});
			
			sendMessage.start();
			readMessage.start();
			
		}
		catch (IOException e) {
			System.out.println("Read failed");
			System.exit(-1);
		}
	}
	
	public void ClientMode(String server, int port)
	{
		try{
			client = new Socket(server, port);
			//Print local IP address and port number
			System.out.println("Local IP:"+client.getLocalAddress().getHostAddress()+"  Local Port:"+client.getLocalPort());
			//Print remote IP and port number
			System.out.println("Remote IP:"+client.getInetAddress().getHostAddress()+"  Remote port:"+client.getPort());
	       	
			DataOutputStream sendDataToServer = new DataOutputStream(client.getOutputStream());
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
	  
	       	
	       	Thread sendMessage = new Thread(new Runnable() {
				public void run() {
					while(true)
					{
						String message;
						try 
						{
							message = keyboard.readLine();
							if(message.equals("STATUS"))
							{
								System.out.println("\nLocal IP:"+client.getLocalAddress().getHostAddress()+" Local port:"+client.getLocalPort());
								System.out.println("Remote IP: "+client.getInetAddress().getHostAddress()+"  Remote port:"+client.getPort());
							}
							else if(message.equals("EXIT") || message.equals("exit"))
							{
								System.out.println("Ending connection... Goodbye!");
								sendDataToServer.writeBytes("exit"+"\n");
								//System.exit(1);
							}
							else
								sendDataToServer.writeBytes(message + "\n");
							
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			});
			
			Thread readMessage = new Thread(new Runnable() {
				public void run() {
					while(true)
					{
						String serverMessage;
						try 
						{
							if(in.ready())
							{
								serverMessage = in.readLine();
								if(serverMessage.equals("exit"))
								{
									System.out.println("The server has shut down... ending connection... Goodbye!");
									System.exit(1);
								}
								else if(serverMessage.equals("approved000"))
									System.exit(1);
								else
									System.out.println("REMOTE] " + serverMessage);
							}
							
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			});
			
			sendMessage.start();
			readMessage.start();
			autoMode = false;
			
	     } catch (UnknownHostException e) {
	       System.out.println("Unknown host:"+server);
	       System.exit(1);
	     } catch  (IOException e) 
		{
	       if(!autoMode)
	       {	
	    	   System.out.println("Error: No I/O, please check your IP or port Number and try again");
	    	   System.exit(1);
	       }
	       else
	    	   System.out.println("Connection has timed out, Starting Server Mode");
	    	   
	     }
	}
	
}

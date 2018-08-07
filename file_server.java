package file_share;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.util.Scanner;

public class file_server
{
	private static Scanner sc;
	private static FileOutputStream fos;
	private static ServerSocket ss;

	public static void main(String[] args) throws UnknownHostException 
	{
		sc = new Scanner(System.in);
		System.out.println("\nEnter name and extension of file: ");
		String name = sc.nextLine();
		File newfile = new File("D:\\" + name);
		String iplocal = InetAddress.getLocalHost().getHostAddress(); 
		System.out.println("Inform client about your IP: "+iplocal);
		try 
		{
			fos = new FileOutputStream(newfile);
			ss = new ServerSocket(3333);
			Socket s = ss.accept();
			DataInputStream dis = new DataInputStream(s.getInputStream());
			int count = 0;
			int len = dis.readInt();
			while(count < len - 1)
			{
				fos.write(dis.readByte());
				count++;
				System.out.println("Receiving "+(count/1024)+" KBs of "+(len/1024)+" KBs");
			}
			System.out.println("\nFile received in Local Disk D: ");
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
}
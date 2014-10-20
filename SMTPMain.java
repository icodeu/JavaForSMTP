import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import Decoder.BASE64Encoder;

/*
 * ���������ԭ����
 * Github:  http://github.com/icodeu
 */

public class SMTPMain {

	public static void main(String[] args) {

		// ����һЩ������õ���������������� ���������û�˻���˺�����д����ô���Ե�
		String sender = "cnsmtp01@163.com";
		String receiver = "cnsmtp02@163.com";
		String password = "computer";
		String user = new BASE64Encoder().encode(sender.substring(0,
				sender.indexOf("@")).getBytes());
		String pass = new BASE64Encoder().encode(password.getBytes());

		try {
			Socket socket = new Socket("smtp.163.com", 25);
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			PrintWriter writter = new PrintWriter(outputStream, true); // ������ȥ
																		// ���true̫�ؼ���!

			System.out.println(reader.readLine());

			// HELO
			writter.println("HELO huan");
			System.out.println(reader.readLine());

			// AUTH LOGIN
			writter.println("auth login");
			System.out.println(reader.readLine());
			writter.println(user);
			System.out.println(reader.readLine());
			writter.println(pass);
			System.out.println(reader.readLine());
			// Above Authentication successful

			// Set "mail from" and "rcpt to"
			writter.println("mail from:<" + sender + ">");
			System.out.println(reader.readLine());
			writter.println("rcpt to:<" + receiver + ">");
			System.out.println(reader.readLine());

			// Set "data"
			writter.println("data");
			System.out.println(reader.readLine());

			writter.println("subject:Ů������");
			writter.println("from:" + sender);
			writter.println("to:" + receiver);
			writter.println("Content-Type: text/plain;charset=\"gb2312\"");
			writter.println();
			writter.println("Ů�����Ͽ��Թ��������");
			writter.println(".");
			writter.println("");
			System.out.println(reader.readLine());

			// ��������ˣ���Ҫ�ͷ�����˵�ݰ���
			writter.println("rset");
			System.out.println(reader.readLine());

			writter.println("quit");
			System.out.println(reader.readLine());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

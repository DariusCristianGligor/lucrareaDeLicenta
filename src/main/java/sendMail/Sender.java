package sendMail;

import java.io.IOException;

public class Sender {

	public static void main(String[] args) throws IOException {
		SendMail sender = new SendMail();
		sender.Send("darius.gligor00@e-uvt.ro","This email sent using JavaMailer API from Java Code!!!");
		sender.Send("darius.gligor00@e-uvt.ro","This email sent using JavaMailer API from Java Code!!!","C://Users//dariu//Downloads//crs.xls");
	}

}

import mail.SendMail;

import javax.mail.MessagingException;

public class MailDemo {
    public static void main(String[] args) {

        //String to = "viethoang6b@gmail.com"; // Thay bằng địa chỉ email của người nhận
        String[] listEmail = {"viethoang6b@gmail.com", "luan.phan@codegym.vn", "haduynam.94@gmail.com", "trungbaizen@gmail.com"};
        String subject = "Test Email";
        String body = "Đây là một email test từ Java.";

        for (String email: listEmail) {
            SendMail sender = new SendMail(email, body, subject);
            Thread emailThread = new Thread(sender);
            emailThread.start();
        }

    }
}

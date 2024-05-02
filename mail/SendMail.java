package mail;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
public class SendMail implements Runnable {
    private final Properties props;
    private final String to;
    private final String subject;
    private final String body;

    public SendMail(String to, String subject, String body) {
        // Thiết lập các thuộc tính cho giao thức gửi email
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", ConfigMail.HOST_NAME);
        props.put("mail.smtp.socketFactory.port", ConfigMail.SSL_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", ConfigMail.SSL_PORT);
        this.to = to;
        this.body = body;
        this.subject = subject;
    }

    public void sendEmail() throws MessagingException {
        // Tạo một phiên gửi email
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ConfigMail.APP_EMAIL, ConfigMail.APP_PASSWORD);
            }
        });

        // Tạo một tin nhắn email
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(ConfigMail.APP_EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(body);

        // Gửi email
        Transport.send(message);
        System.out.println("Email đã được gửi thành công đến " + to);
    }

    @Override
    public void run() {
        try {
            sendEmail();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

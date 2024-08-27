package com.coworking.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.PrintWriter;
import java.io.StringWriter;

@Service
public class EnviarCorreo {
    @Autowired
    JavaMailSender mailSender;

    public boolean enviarCorreo(final String msj, String correoLLegada) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {

                String footer = "NOTA CONFIDENCIAL:<br>"
                        + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris laoreet felis vitae nulla dapibus suscipit. Integer sit amet tempus enim. Suspendisse potenti. Donec id tortor vitae justo vehicula laoreet ac non sapien. Morbi mattis iaculis nibh, a pretium orci interdum a. Sed ultrices sagittis est, sit amet volutpat dolor faucibus a. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Cras commodo felis et blandit viverra. Ut pulvinar neque et ante placerat, ut ultricies velit ultricies. Sed fermentum mauris vitae felis tincidunt finibus. Ut et augue facilisis libero faucibus rhoncus. Nulla facilisi. Donec vitae laoreet purus. Proin pellentesque enim felis, sed molestie libero ullamcorper iaculis.";

                String mensaje = "";
                mensaje = mensaje
                        + "<div style='margin: 0 auto; padding: 30px; width: 80%; background-color: #f1f1f1;'>";
                mensaje = mensaje
                        + "<div style='padding: 20px; background-color: #FFFFFF' class='w3-code notranslate htmlHigh'>";
                mensaje = mensaje + "<p><span style='color: black'>Se&ntilde;or(a):</span>";

                mensaje = mensaje + "</p>";
                mensaje = mensaje + "<p>" + msj + "</p>";
                mensaje = mensaje + "</div><br><br>";

                mensaje = mensaje + "</div>";
                mensaje = mensaje + footer;

                mimeMessage.setHeader("Content-Type", "text/plain; charset=UTF-8");
                mimeMessage.setHeader("charset", "UTF-8");

                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

                helper.setTo(correoLLegada);
                helper.setSubject("Código de Ingreso");
                helper.setText(mensaje, true);

            }
        };

        try {

            mailSender.send(preparator);

            return true;
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
            StringWriter stack = new StringWriter();
            ex.printStackTrace(new PrintWriter(stack));
            return false;
        }
    }

}
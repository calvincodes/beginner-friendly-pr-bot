package com.github.calvincodes.email;

public class MailjetPostfixSender {

    public void sendEmail(String subject) {
        try {
            String emailCommand =
                    "echo 'Hi! They found out about me.' | " +
                            "mail -s '"+ subject +"' " +
                            "-aFrom:" + System.getenv("FOSC_MAILJET_SENDER") +
                            " " + System.getenv("FOSC_MAILJET_RECIPIENT");
            String[] commandArray = {"/bin/sh", "-c", emailCommand};
            Process p = Runtime.getRuntime().exec(commandArray);
            p.waitFor();
            System.out.println ("Email process exit: " + p.exitValue());
            p.destroy();
        } catch (Exception ex) {
            System.err.println("[com.github.calvincodes.email.MailjetPostfixSender] Exception while sending email.");
            ex.printStackTrace();
        }
    }
}

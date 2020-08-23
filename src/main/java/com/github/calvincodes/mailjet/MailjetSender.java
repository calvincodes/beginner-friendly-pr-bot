package com.github.calvincodes.mailjet;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;

public class MailjetSender {

    private static volatile Boolean IS_MAILJET_CLIENT_INITIALIZED = false;
    private static MailjetClient client = null;
    private final String recipient = System.getenv("FOSC_MAILJET_RECIPIENT");
    private final String sender = System.getenv("FOSC_MAILJET_SENDER");

    public MailjetSender() {
        synchronized (IS_MAILJET_CLIENT_INITIALIZED) {
            if (!IS_MAILJET_CLIENT_INITIALIZED) {
                // TODO: Create separate config file
                final String mailjetApiKey = System.getenv("FOSC_MAILJET_API_KEY");
                final String mailjetSecretKey = System.getenv("FOSC_MAILJET_SECRET_KEY");
                client = new MailjetClient(mailjetApiKey, mailjetSecretKey, new ClientOptions("v3.1"));
                IS_MAILJET_CLIENT_INITIALIZED = true;
                System.out.println("MailJet Client initialized");
                // TODO: Remove me.
                System.out.println("SENDER: " + sender);
                System.out.println("recipient: " + recipient);
                System.out.println("mailjetApiKey: " + mailjetApiKey);
                System.out.println("mailjetSecretKey: " + mailjetSecretKey);
            }
        }
    }

    public void sendEmail(String subject) {
        MailjetRequest email = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", sender)
                                        .put("Name", "Arpit Jain"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", recipient)))
                                .put(Emailv31.Message.SUBJECT, subject)
                                .put(Emailv31.Message.TEXTPART,
                                        "Hi! They found out about me.")
                                .put(Emailv31.Message.HTMLPART,
                                        "<h3>Hi!</h3>"
                                                + "<br />They found out about me.")));


        try {
            // trigger the API call
            MailjetResponse response = client.post(email);
            // Read the response data and status
            System.out.println(response.getStatus());
            System.out.println(response.getData());
        } catch (MailjetException | MailjetSocketTimeoutException e) {
            System.out.println("Mailjet Exception: " + e);
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}

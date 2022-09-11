package marumasa.discord_send_command;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class request {
    public static void post(final String URL, final String JSON) {
        try {
            URL url = new URL(URL);
            HttpURLConnection uc;
            uc = (HttpURLConnection) url.openConnection();
            uc.setRequestMethod("POST");
            uc.setUseCaches(false);
            uc.setDoOutput(true);
            uc.setRequestProperty("user-agent", "Mozilla/5.0 ");
            uc.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            OutputStreamWriter out = new OutputStreamWriter(new BufferedOutputStream(uc.getOutputStream()), StandardCharsets.UTF_8);
            out.write(JSON);
            out.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String line = in.readLine();
            StringBuilder body;
            body = new StringBuilder();
            while (line != null) {
                body.append(line);
                line = in.readLine();
            }
            uc.disconnect();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
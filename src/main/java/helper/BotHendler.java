package helper;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BotHendler {
    private static String urlApi = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s&parse_mode=markdown";
    private static final String BOT_TOKEN = "1248195750:AAGoO8n9BL60v6ufS0oLKDyJDtIjuxnnI4w";
//    private static final String chatId = "602478502";

    public static void sendMessage(String chatId, String message){
        String urlString = String.format(urlApi, BOT_TOKEN, chatId, urlEncode(message));
        URL url = null;
        try {
            url = new URL(urlString);
            URLConnection conn = url.openConnection();
            StringBuilder sb = new StringBuilder();
            InputStream is = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            String response = sb.toString();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMultipleChat(List<String> chatsId, String message){
        new Thread(() -> {
            for (String chatId : chatsId){
                sendMessage(chatId,message);
            }
        }).start();
    }

    private static String urlEncode(String plainText){
        try {
            return URLEncoder.encode(plainText, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

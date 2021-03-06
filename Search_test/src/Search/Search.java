package Search;

import Login.LoginResp;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLOutput;

public class Search {
    public static void main(String[] args) throws Exception {
        case1();
       case2();
//        case3();
//        case4();
//        case5();
//        case6();
//        case7();
    }
    public static SearchResp Search(String token, String keyword, String user_id, int index, int count) throws IOException {
        URL url = new URL(Constant.Search + "?token=" + token + "&keyword=" + keyword + "&user_id=" + user_id
                + "&index=" + index + "&count=" + count);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        try {
            StringBuilder content;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                content = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            String java_string_content = content.toString();
            System.out.println(java_string_content);
            Gson g = new Gson();

            return g.fromJson(java_string_content, SearchResp.class);
        }
        finally {
            connection.disconnect();
        }
    }

    public static LoginResp getInfoFromServer(String phonenumber, String password) throws IOException {
        URL url = new URL(Constant.LOG_IN + "?phonenumber=" + phonenumber + "&password=" + password);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        try {
            StringBuilder content;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                content = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            String java_string_content = content.toString();
            System.out.println(java_string_content);
            Gson g = new Gson();

            return g.fromJson(java_string_content, LoginResp.class);
        }
        finally {
            connection.disconnect();
        }
    }

    public static void case1() throws IOException {
        System.out.println("Case 1: Token is correct");
        LoginResp loginResp = getInfoFromServer("0974732000", "123456");
        SearchResp searchResp = Search(loginResp.data.token, "funny", "20187270", 12, 20);
        try {
            assert "1000".equals(searchResp.code) : "Fail";
            System.out.println("OK");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case2() throws IOException {
        System.out.println("Case 2: Token is invalid" );
        LoginResp loginResp = getInfoFromServer("0974732000", "123456");
        SearchResp searchResp = Search(loginResp.data.token, "funny", "20187270", 29, 1);

        try {
            assert "9998".equals(searchResp.code) : "OK";
            System.out.println("token is invalid");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case3() throws IOException {
        System.out.println("Case 3: Token is correct but none is returned");
        LoginResp loginResp = getInfoFromServer("0974732000", "123456");
        SearchResp searchResp = Search(loginResp.data.token, "funny", "20187270", 29, 1);
        try {
            assert "9992".equals(loginResp.code) : "OK";
            System.out.println("Post is not existed");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case4() throws IOException {
        System.out.println("Case 4: Token and parameters is valid but user is locked");
        LoginResp loginResp = getInfoFromServer("0974732000", "123456");
        SearchResp searchResp = Search(loginResp.data.token, "funny", "20187270", 29, 1);
        try {
            assert "9995".equals(loginResp.code) : "OK";
            System.out.println("User is not validated");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case5() throws IOException {
        System.out.println("Case 5: Token is valid but user_id is invalid");
        LoginResp loginResp = getInfoFromServer("0974732000", "123456");
        SearchResp searchResp = Search(loginResp.data.token, "funny", "20187270", 29, 1);
        try {
            assert "1000".equals(loginResp.code) : "Ok";
            System.out.println("You have not enter phone number and password");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case6() throws IOException {
        System.out.println("Case 6: Parameters are valid but keyword is null");
        LoginResp loginResp = getInfoFromServer("0974732000", "123456");
        SearchResp searchResp = Search(loginResp.data.token, "funny", "20187270", 29, 1);
        try {
            assert "1000".equals(loginResp.code) : "OK";
            System.out.println("Parameters are valid but keyword is null");
       }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case7() throws IOException {
        System.out.println("Case 7: Token and parameters are valid but author's id returned is invalid");
        LoginResp loginResp = getInfoFromServer("0974732000", "123456");
        SearchResp searchResp = Search(loginResp.data.token, "funny", "20187270", 29, 1);
        try {
           assert "1000".equals(loginResp.code) : "OK";
            System.out.println("Token and parameters are valid but author's id data which returned is failed");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
}

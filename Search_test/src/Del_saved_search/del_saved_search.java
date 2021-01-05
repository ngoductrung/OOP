package Del_saved_search;

import Login.LoginResp;
import Del_saved_search.Constant;
import Del_saved_search.Resp;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class del_saved_search {
    public static Resp Del_saved_search(String token, String search_id, String all) throws IOException {
        URL url = new URL(Constant.DEL_SAVED_SEARCH + "?token=" + token + "&search_id=" + search_id + "&all=" + all);
        System.out.println("URL: " +url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        String java_string_content= "";
        Gson g = new Gson();
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
            java_string_content = content.toString();
            System.out.println(java_string_content);


//            return g.fromJson(java_string_content, Resp.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            connection.disconnect();
            return g.fromJson(java_string_content, Resp.class);
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
//    public static void case1() throws IOException {
//        System.out.println("Case 1: Entering ");
//        LoginResp loginResp = getInfoFromServer("0974732000", "1234567");
//        Resp Resp = Del_saved_search(loginResp.data.token, "12","1");
//        try {
//            assert "1000".equals(loginResp.data.token) : "Fail" ;
//            System.out.println("OK");
//        }
//        catch (AssertionError e) {
//            e.printStackTrace();
//        }
//    }
//    public static void case2() throws IOException {
//        System.out.println("Case 2: Entering ");
//        LoginResp loginResp = getInfoFromServer("0974732000", "1234567");
//        Resp resp = Del_saved_search(loginResp.data.token, "12","1");
//        try {
//            assert "9998".equals(resp.code) : "Fail";
//            System.out.println("Wrong token");
//        }
//        catch (AssertionError e) {
//            e.printStackTrace();
//        }
//    }
public static void case1() throws IOException {
    System.out.println("Case 1: Check all ");
    LoginResp loginResp = getInfoFromServer("0974732000", "123456");
    Resp resp = Del_saved_search(loginResp.data.token, "12","1");
    try {
        assert ("1000".equals(resp.code));
        System.out.println("OK");
    }
    catch (AssertionError e) {
        e.printStackTrace();
    }
}
    public static void case2() throws IOException {
        System.out.println("Case 2: Sai token ");
        LoginResp loginResp = getInfoFromServer("0974732000", "123456");
        Resp resp = Del_saved_search(loginResp.data.token, "12","1");
        try {
            assert ("9998".equals(resp.code));
            System.out.println("OK");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case3() throws IOException {
        System.out.println("Case 3: Nhap dung nhung khong co gia tri tra ve");
        LoginResp loginResp = getInfoFromServer("0974732000", "123456");
        Resp resp = Del_saved_search(loginResp.data.token, "12","1");
        try {
            assert ("9994".equals(resp.code)) ;
            System.out.println("OK");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case4() throws IOException {
        System.out.println("Case 4: Kiem tra tham so day du ");
        LoginResp loginResp = getInfoFromServer("0974732000", "123456");
        Resp resp = Del_saved_search(loginResp.data.token, "123","1");
        try {
            assert ("1002".equals(resp.code));
            System.out.println("OK");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case5() throws IOException {
        System.out.println("Case 5: Kiem tra nhap sai thong tin ");
        LoginResp loginResp = getInfoFromServer("0974732000", "123456");

        try {
            Resp resp = Del_saved_search(loginResp.data.token, "-5","1");
            assert "9997".equals(resp.code) : "Fail";
            System.out.println("OK");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
        catch (Exception ei){
            ei.printStackTrace();
        }
    }
    public static void case6() throws IOException {
        System.out.println("Case 6: Kiem tra kieu tham so ");
        LoginResp loginResp = getInfoFromServer("0974732000", "123456");
        Resp resp = Del_saved_search(loginResp.data.token, "1","1");
        try {
            assert "1003".equals(resp.code) : "Fail";
            System.out.println("OK");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case7() throws IOException {
        System.out.println("Case 7: kiem tra bai viet co ton tai khong");
        LoginResp loginResp = getInfoFromServer("0974732000", "123456");
        Resp resp = Del_saved_search(loginResp.data.token, "1","1");
        try {
            assert "9992".equals(resp.code) : "Fail";
            System.out.println("OK");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }
    public static void case8() throws IOException {
        System.out.println("Case 8: Kiem tra gia tri tham so ");
        LoginResp loginResp = getInfoFromServer("0974732000", "123456");
        Resp resp = Del_saved_search(loginResp.data.token, "123","1");
        try {
            assert ("1004".equals(resp.code));
            System.out.println("OK");
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
//        case1();
//        case2();
//        case3();
//        case4();
        case5();
//        case6();
//        case7();
//        case8();
    }
}

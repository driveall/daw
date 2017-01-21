package html;

public class BuildLog {

    public String buildLogContent() {
        return "<h1>Exist player:</h1><hr>"
                + "<form action=\"/daw/login\" method=\"POST\">"
                + "Enter Name:<input type=\"text\" name=\"name\"/><br>"
                + "Enter Pass:<input type=\"password\" name=\"pass\"/><br>"
                + "<input type=\"submit\" value=\"Login\" class=\"button\"/>"
                + "</form><hr>"
                + "<a href=\"/daw/main\"><input type=\"submit\" value=\"Main\" class=\"button\"></a>";
    }

    public String buildLogOkContent(String name) {
        return "<h1>You were logined as " + name + "!</h1>"
                + "<a href=\"/daw/user\">"
                + "<input type=\"submit\" value=\"To user page\" class=\"button\"/>"
                + "</a>";
    }

    public String buildLogFailContent(String name) {
        return "<h1>Login fail. Name " + name + " or password incorrect! Try to log in again!</h1>"
                + "<a href=\"/daw/main\"><input type=\"submit\" value=\"Main\" class=\"button\"/></a>"
                + "<a href=\"/daw/login\"><input type=\"submit\" value=\"Login\" class=\"button\"/></a>";
    }
}

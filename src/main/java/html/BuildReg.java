package html;

public class BuildReg {
    public String buildRegContent() {
        return "<h1>New player:</h1><hr>"
                + "<form action=\"/daw/register\" method=\"POST\">"
                + "Enter Name:<input type=\"text\" id=\"name\" name=\"name\" />"
                + "<div><span id=\"isUserValue\" /></div>"
                + "Enter Pass:<input type=\"password\" id=\"password\" name=\"pass\" onkeyup=\"doPassCheck()\"/>"
                + "<div><span id=\"strengthPassValue\"></div>"
                + "<input type=\"radio\" name=\"sex\" value=\"male\"> Male<br>"
                + "<input type=\"radio\" name=\"sex\" value=\"female\"> Female<br>"
                + "<input type=\"submit\" value=\"Register\" class=\"button\"/>"
                + "</form><hr>"
                + "<a href=\"/daw/main\">"
                + "<input type=\"submit\" value=\"Main\" class=\"button\">"
                + "</a>";
    }

    public String buildRegOkContent(String name) {
        return "<a href=\"/daw/main\">"
                + "<input type=\"submit\" value=\"Main\" class=\"button\">"
                + "</a>";
    }

    public String buildRegFailContent(String name) {
        return "<a href=\"/daw/register\">"
                + "<input type=\"submit\" value=\"Register\" class=\"button\"/>"
                + "</a><hr>"
                + "<a href=\"/daw/main\">"
                + "<input type=\"submit\" value=\"Main\" class=\"button\">"
                + "</a>";
    }
    
    public String buildRegOkMessage(String name){
        return "<h1>You were registered as " + name + "!</h1><hr>";
    }
    
    public String buildRegFailMessage(String name){
        return "<h1>Register fail. Name " + name + " is already use! Try again!</h1><hr>";
    }
    
    public String buildRegNotFullMessage(){
        return "<h1>Register fail. Please enter data to all fields</h1><hr>";
    }
}

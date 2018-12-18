package shoutingMTserver;

class XMLParser {
    public String read(String input) {
        int start = input.indexOf(">");
        int end = input.indexOf("<");

        return input.substring(start, end);
    }
}
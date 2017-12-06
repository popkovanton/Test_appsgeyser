package popkovanton.test_appsgeyser.data;


//в дальнейшем будет принимать данные со стороннего сервиса
public class ModelHistoryElement {

    private String text;
    private String language;

    public ModelHistoryElement() {
    }

    public ModelHistoryElement(String text, String language) {
        this.text = text;
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

package imperial.domain;

import java.util.List;

public class ImperialBoxCategory {

    private String category;
    private List<ImperialBox> imperialBoxes;

    public ImperialBoxCategory() {
    }

    public ImperialBoxCategory(String category, List<ImperialBox> imperialBoxes) {
        this.category = category;
        this.imperialBoxes = imperialBoxes;
    }

    public List<ImperialBox> getImperialBoxes() {
        return imperialBoxes;
    }

    public String getCategory() {
        return category;
    }

    public void setImperialBoxes(List<ImperialBox> imperialBoxes) {
        this.imperialBoxes = imperialBoxes;
    }
}

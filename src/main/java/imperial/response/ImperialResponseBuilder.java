package imperial.response;

import com.google.gson.Gson;
import core.domain.Item;
import core.repositories.MarketRepository;
import imperial.domain.ImperialBox;
import imperial.domain.ImperialBoxCategory;
import imperial.domain.ImperialParams;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ImperialResponseBuilder {

    public static ImperialResponseBuilder _instance = null;

    public static ImperialResponseBuilder getInstance() {
        if (_instance == null) {
            _instance = new ImperialResponseBuilder();
            return _instance;
        }
        return _instance;
    }

    public List<ImperialBoxCategory> buildImperialResponse(HashMap<?, String> parameters) {

        if (parameters.get(ImperialParams.LEVEL).equals("all")) {
            //TODO process every level of lifeSkill
        }

        Gson gson = new Gson();
        ImperialBoxCategory category = null;
        try {
            category = gson.fromJson(getFileFromResourceAsString("Imperial/" + parameters.get(ImperialParams.CATEGORY) +
                    "/" + parameters.get(ImperialParams.LEVEL) + ".json"), ImperialBoxCategory.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        MarketRepository marketRepository = new MarketRepository();
        String currentItemJson = null;

        for (ImperialBox ib : category.getImperialBoxes()) {
            try {
                currentItemJson = marketRepository.getItemInfo(ib.getItemKey());
            } catch (IOException e) {
                e.printStackTrace();
            }
            ib.setItem(new Gson().fromJson(String.valueOf(currentItemJson), Item.class));
            ib.setCurrentMarketCostPerBox((int) (ib.getItem().getPricePerOne() * ib.getAmountPerBox()));
        }

        List<ImperialBoxCategory> ibcs = new ArrayList<>();
        ibcs.add(category);
        return ibcs;
    }

    private String getFileFromResourceAsString(String fileName) throws IOException {

        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStreamToString(inputStream);
        }

    }

    public static String inputStreamToString(InputStream inputStream) throws IOException {
        return IOUtils.toString(inputStream, "UTF-8");
    }

    public List<ImperialBoxCategory> filterResultsFromParams(List<ImperialBoxCategory> ibcs, HashMap<?, String> parameters) {

        List<ImperialBoxCategory> filteredCategories = new ArrayList<>();
        ImperialBoxCategory filteredCategory;
        List<ImperialBox> filteredIbs;

        if (parameters.get(ImperialParams.CP) != null) {
            try {
                for (ImperialBoxCategory ibc : ibcs) {
                    filteredIbs = new ArrayList<>();
                    for (ImperialBox ib : ibc.getImperialBoxes()) {
                        int neededAmountOnMarket = (Integer.parseInt(parameters.get(ImperialParams.CP)) / 2) * ib.getAmountPerBox();
                        if (neededAmountOnMarket < ib.getItem().getCount()) {
                            filteredIbs.add(ib);
                        }
                    }
                    filteredCategory = new ImperialBoxCategory(ibc.getCategory(), filteredIbs);
                    filteredCategories.add(filteredCategory);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            filteredCategories = ibcs;
        }

        if (parameters.get(ImperialParams.LIMIT) != null) {
            int limit = Integer.parseInt(parameters.get(ImperialParams.LIMIT));
            for (ImperialBoxCategory ibc : filteredCategories) {
                int listSize = ibc.getImperialBoxes().size();
                Collections.<ImperialBox>sort(ibc.getImperialBoxes());
                if (limit < listSize) {
                    ibc.setImperialBoxes(ibc.getImperialBoxes().subList(0, limit));
                }
            }
        }
        return filteredCategories;
    }
}

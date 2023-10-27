package Data.OrderData;

import java.util.ArrayList;
import java.util.List;

public class HashIngredients {
    public final String HASH_KRATORNAYBULKA = "61c0c5a71d1f82001bdaaa6c";
    public final String HASH_GALACTICSAUCE = "61c0c5a71d1f82001bdaaa74";
    public final String HASH_MINERALRINGS = "61c0c5a71d1f82001bdaaa76";
    public final String HASH_INVALID_INGREDIENT = "dfgvdfvbdf";
    //ПОМЕНЯТЬ!!

    public List<String> fillOrder(){
        List<String> ingredients = new ArrayList<>();
        ingredients.add(HASH_KRATORNAYBULKA);
        ingredients.add(HASH_MINERALRINGS);
        ingredients.add(HASH_GALACTICSAUCE);
        return ingredients;
    }

    public List<String> fillOrderWrong(){
        List<String> ingredients = new ArrayList<>();
        ingredients.add(HASH_INVALID_INGREDIENT);
        return ingredients;
    }
}

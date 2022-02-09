import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;

public class BurgerTest {

    @Test
    public void setBunsTest() {
        //Arrange
        Burger burger = new Burger();
        Bun bun = new Bun("name", 100);
        //Act
        burger.setBuns(bun);
        //Assert
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest () {
        //Arrange
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        List<Ingredient> expectedIngredients = new ArrayList<>();
        expectedIngredients.add(ingredient);
        //Act
        burger.addIngredient(ingredient);
        //Assert
        assertEquals(expectedIngredients, burger.ingredients);
        //Если замокать Burger, то List<Ingredient> = null, ->
        //-> без мока List<Ingredient> = [] - в созданный список можно добавлять значения, а в null нет
    }

    @Test
    public void removeIngredientTest() {
        //Arrange
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        burger.ingredients.add(ingredient);
        List<Ingredient> expectedIngredients = new ArrayList<>();
        //Проверка, что список не пустой
        MatcherAssert.assertThat("New ingredient no added", burger.ingredients, notNullValue());
        //Act
        burger.removeIngredient(0);
        //Assert
        //Проверка, что добавленное значение удалилось
        Assert.assertEquals("New ingredient not removed", burger.ingredients, expectedIngredients);
    }

    @Test
    public void moveIngredientTest() {
        //Arrange
        Burger burger = new Burger();
        Ingredient firstIngredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        Ingredient secondIngredient = new Ingredient(IngredientType.SAUCE, "sour cream", 200);
        List<Ingredient> actualIngredients = burger.ingredients;
        actualIngredients.add(firstIngredient);
        actualIngredients.add(secondIngredient);
        List<Ingredient> expectedIngredients = new ArrayList<>();
        expectedIngredients.add(firstIngredient);
        expectedIngredients.add(secondIngredient);
        //Act
        burger.moveIngredient(1,0);
        //Assert
        Assert.assertNotEquals(actualIngredients, expectedIngredients);
        assertEquals(actualIngredients.get(0), expectedIngredients.get(1));
    }

    @Test
    public void getPriseReturnBurgerPrice() {
        //Arrange
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        burger.ingredients.add(ingredient);
        burger.bun = new Bun("name", 10);
        float expectedPrice = 120;
        //Act
        float actualPrice = burger.getPrice();
        //Assert
        assertEquals("Wrong price", expectedPrice, actualPrice, 0);
    }

    @Test
    public void getReceiptReturnBurgerReceipt() {
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        burger.ingredients.add(ingredient);
        burger.bun = new Bun("name", 10);
        String expectedReceipt = "(==== name ====)\n" +
                "= sauce hot sauce =\n" +
                "(==== name ====)\n" +
                "\n" +
                "Price: 120,000000" +
                "\n";
        String actualReceipt = burger.getReceipt();
        assertEquals("Wrong receipt", expectedReceipt, actualReceipt);
        System.out.println(actualReceipt);
    }
}


import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)

public class BurgerTest {

    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;

    Burger burger = new Burger();

    @Test
    public void setBunsTest() {
        //Arrange
        Burger burger = new Burger();
        //Act
        burger.setBuns(bun);
        //Assert
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest () {

        burger.addIngredient(ingredient);

        int actualResult = burger.ingredients.size();
        int expectedResult = 1;

        assertEquals("The burger size is not correct",expectedResult, actualResult);
    }

    @Test
    public void removeIngredientTest() {

        burger.addIngredient(ingredient);
        burger.removeIngredient(0);

        int actualResult = burger.ingredients.size();
        int expectedResult = 0;

        assertEquals("The burger size is not correct",expectedResult, actualResult);
    }

    @Test
    public void moveIngredientTest() {

        burger.addIngredient(new Ingredient(IngredientType.FILLING, "dinosaur", 200));
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "chili sauce", 300));
        burger.moveIngredient(0, 1);

        String actualResult = burger.ingredients.get(1).name;
        String expectedResult = "dinosaur";

        assertEquals("The burger ingredient is not correct",expectedResult, actualResult);
    }

    @Test
    public void getPriseTest() {
        //Arrange
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        Mockito.when(bun.getPrice()).thenReturn(2f);
        Mockito.when(ingredient.getPrice()).thenReturn(2f);
        float expectedPrice = 6;
        //Act
        float actualPrice = burger.getPrice();
        // System.out.println(actualPrice);
        //Assert
        assertEquals("Wrong price", expectedPrice, actualPrice, 0);
    }

    @Test
    public void getReceiptReturnBurgerReceipt() {

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(bun.getPrice()).thenReturn(300f);
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredient.getName()).thenReturn("dinosaur");
        Mockito.when(ingredient.getPrice()).thenReturn(300f);

        String actualResult = burger.getReceipt();
        String expectedResult = "(==== black bun ====)\n= filling dinosaur =\n(==== black bun ====)\n\nPrice: 900,000000\n";

        assertEquals("The burger receipt is not correct",expectedResult, actualResult);
    }

}


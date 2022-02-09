import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)

public class TestWithMockStub {

    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;

    @Test
    public void getPriseReturnBurgerPrice() {
        //Arrange
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        Mockito.when(bun.getPrice()).thenReturn(2f);
        Mockito.when(ingredient.getPrice()).thenReturn(2f);
        float expectedPrice = 6;
        //Act
        float actualPrice = burger.getPrice();
        System.out.println(actualPrice);
        //Assert
        assertEquals("Wrong price", expectedPrice, actualPrice, 0);
    }
}
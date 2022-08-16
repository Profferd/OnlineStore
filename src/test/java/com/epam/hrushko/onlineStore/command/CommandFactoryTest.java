package com.epam.hrushko.onlineStore.command;

import com.epam.hrushko.onlinestore.command.Command;
import com.epam.hrushko.onlinestore.command.CommandFactory;
import com.epam.hrushko.onlinestore.command.CommandName;
import com.epam.hrushko.onlinestore.command.action.*;
import com.epam.hrushko.onlinestore.command.action.auto.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandFactoryTest {
    @Test
    public void testCreateCommand_ShouldReturnAddToBasketCommand_WhenCommandIsAddToBasket() {
        Command actual = CommandFactory.getInstance().getCommand(CommandName.ADD_TO_BUCKET);
        assertEquals(actual.getClass(), AddToBucketCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnCompleteOrderCommand_WhenCommandIsCompleteUserOrder() {
        Command actual = CommandFactory.getInstance().getCommand(CommandName.COMPLETE_USER_ORDER_COMMAND);
        assertEquals(actual.getClass(), CompleteUserOrderCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnConfirmAddingProductCommand_WhenCommandIsConfirmAddingProduct() {
        Command actual = CommandFactory.getInstance().getCommand(CommandName.CONFIRM_ADD_PRODUCT);
        assertEquals(actual.getClass(), ConfirmAddingProductCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnConfirmOrderCommand_WhenCommandIsConfirmOrder() {
        Command actual = CommandFactory.getInstance().getCommand(CommandName.CONFIRM_ORDER_COMMAND);
        assertEquals(actual.getClass(), ConfirmOrderCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnConfirmProductChangeCommand_WhenCommandIsConfirmProductChange() {
        Command actual = CommandFactory.getInstance().getCommand(CommandName.CONFIRM_EDIT_PRODUCT);
        assertEquals(actual.getClass(), ConfirmEditProductCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnDeleteOrderCommand_WhenCommandIsDeleteOrder() {
        Command actual = CommandFactory.getInstance().getCommand(CommandName.DELETE_ORDER);
        assertEquals(actual.getClass(), DeleteOrderCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnDeleteUserOrderCommand_WhenCommandIsDeleteUserOrder() {
        Command actual = CommandFactory.getInstance().getCommand(CommandName.DELETE_USER_ORDER_COMMAND);
        assertEquals(actual.getClass(), DeleteUserOrderCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnLogInCommand_WhenCommandIsCheckLogin() {
        Command actual = CommandFactory.getInstance().getCommand(CommandName.CHECK_LOGIN_COMMAND);
        assertEquals(actual.getClass(), LogInCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnLogOutCommand_WhenCommandIsLogOut() {
        Command actual = CommandFactory.getInstance().getCommand(CommandName.LOG_OUT_COMMAND);
        assertEquals(actual.getClass(), LogoutCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnLogUpCommand_WhenCommandIsRegistration() {
        Command actual = CommandFactory.getInstance().getCommand(CommandName.REGISTRATION_COMMAND);
        assertEquals(actual.getClass(), SignUpCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToAddOrderCommand_WhenCommandIsAddOrder() {
        Command actual = CommandFactory.getInstance().getCommand(CommandName.ADD_ORDER);
        assertEquals(actual.getClass(), AddOrderPageCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToAddProductCommand_WhenCommandIsAddProduct() {
        Command actual = CommandFactory.getInstance().getCommand(CommandName.ADD_PRODUCT);
        assertEquals(actual.getClass(), AddProductPageCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToBasketCommand_WhenCommandIsBasket() {
        Command actual = CommandFactory.getInstance().getCommand(CommandName.BASKET_COMMAND);
        assertEquals(actual.getClass(), BucketPageCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToCatalogCommand_WhenCommandIsCatalog() {
        Command actual = CommandFactory.getInstance().getCommand(CommandName.CATALOG_COMMAND);
        assertEquals(actual.getClass(), CatalogPageCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToContactsCommand_WhenCommandIsContacts() {
        Command actual = CommandFactory.getInstance().getCommand(CommandName.PROFILE_COMMAND);
        assertEquals(actual.getClass(), ProfilePageCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToEditProductCommand_WhenCommandIsEditProduct() {
        Command actual = CommandFactory.getInstance().getCommand(CommandName.EDIT_PRODUCT_COMMAND);
        assertEquals(actual.getClass(), EditProductPageCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToLogInCommand_WhenCommandIsLogIn() {
        Command actual = CommandFactory.getInstance().getCommand(CommandName.LOG_IN_COMMAND);
        assertEquals(actual.getClass(), LoginPageCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToLogUpCommand_WhenCommandIsLogUp() {
        Command actual = CommandFactory.getInstance().getCommand(CommandName.SIGN_UP_COMMAND);
        assertEquals(actual.getClass(), SignUpPageCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToMainCommand_WhenCommandIsMain() {
        Command actual = CommandFactory.getInstance().getCommand(CommandName.HOME_COMMAND);
        assertEquals(actual.getClass(), HomePageCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToMyOrdersCommand_WhenCommandIsMyOrders() {
        Command actual = CommandFactory.getInstance().getCommand(CommandName.ORDERS_COMMAND);
        assertEquals(actual.getClass(), OrdersPageCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToPromotionsCommand_WhenCommandIsPromotions() {
        Command actual = CommandFactory.getInstance().getCommand(CommandName.PROMOTIONS_COMMAND);
        assertEquals(actual.getClass(), PromotionPageCommand.class);
    }

    @Test
    public void testCreateCommand_ShouldReturnGoToViewOrdersCommand_WhenCommandIsViewOrders() {
        Command actual = CommandFactory.getInstance().getCommand(CommandName.VIEW_ORDERS_COMMAND);
        assertEquals(actual.getClass(), ViewOrdersPageCommand.class);
    }
}

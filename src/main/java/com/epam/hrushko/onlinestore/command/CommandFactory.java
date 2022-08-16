package com.epam.hrushko.onlinestore.command;

import com.epam.hrushko.onlinestore.command.action.*;
import com.epam.hrushko.onlinestore.command.action.auto.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandFactory {
    private static final Map<String, Command> commands = new HashMap<>();

    private CommandFactory() {
        commands.put(CommandName.REGISTRATION_COMMAND, new SignUpCommand());
        commands.put(CommandName.LOG_IN_COMMAND, new LoginPageCommand());
        commands.put(CommandName.SIGN_UP_COMMAND, new SignUpPageCommand());
        commands.put(CommandName.HOME_COMMAND, new HomePageCommand());
        commands.put(CommandName.CHECK_LOGIN_COMMAND, new LogInCommand());
        commands.put(CommandName.LOG_OUT_COMMAND, new LogoutCommand());
        commands.put(CommandName.PROFILE_COMMAND, new ProfilePageCommand());
        commands.put(CommandName.CATALOG_COMMAND, new CatalogPageCommand());
        commands.put(CommandName.ADD_TO_BUCKET, new AddToBucketCommand());
        commands.put(CommandName.BASKET_COMMAND, new BucketPageCommand());
        commands.put(CommandName.DELETE_ORDER, new DeleteOrderCommand());
        commands.put(CommandName.ADD_ORDER, new AddOrderPageCommand());
        commands.put(CommandName.ORDERS_COMMAND, new OrdersPageCommand());
        commands.put(CommandName.CONFIRM_ORDER_COMMAND, new ConfirmOrderCommand());
        commands.put(CommandName.PROMOTIONS_COMMAND, new PromotionPageCommand());
        commands.put(CommandName.CONFIRM_ADD_PRODUCT, new ConfirmAddingProductCommand());
        commands.put(CommandName.ADD_PRODUCT, new AddProductPageCommand());
        commands.put(CommandName.EDIT_PRODUCT_COMMAND, new EditProductPageCommand());
        commands.put(CommandName.CONFIRM_EDIT_PRODUCT, new ConfirmEditProductCommand());
        commands.put(CommandName.VIEW_ORDERS_COMMAND, new ViewOrdersPageCommand());
        commands.put(CommandName.DELETE_USER_ORDER_COMMAND, new DeleteUserOrderCommand());
        commands.put(CommandName.COMPLETE_USER_ORDER_COMMAND, new CompleteUserOrderCommand());
    }

    public static CommandFactory getInstance() {
        return Holder.INSTANCE;
    }

    public Command getCommand(String name) {
        return Optional.ofNullable(commands.get(name)).orElse(commands.get(CommandName.DEFAULT_COMMAND));
    }

    private static class Holder {
        static final CommandFactory INSTANCE = new CommandFactory();
    }
}

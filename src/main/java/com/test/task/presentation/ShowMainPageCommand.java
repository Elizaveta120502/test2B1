package com.test.task.presentation;

import com.test.task.command.Command;
import com.test.task.command.CommandRequest;
import com.test.task.command.CommandResponse;
import com.test.task.controller.PropertyContext;
import com.test.task.controller.RequestFactory;

public enum ShowMainPageCommand implements Command {
    INSTANCE(RequestFactory.getInstance(), PropertyContext.instance());


    private static final CommandResponse FORWARD_TO_MAIN_PAGE_RESPONSE = new CommandResponse() {
        @Override
        public boolean isRedirect() {
            return false;
        }

        @Override
        public String getPath() {
            return "/WEB-INF/jsp/common/main.jsp";
        }
    };

    ShowMainPageCommand(RequestFactory instance, PropertyContext instance1) {
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return FORWARD_TO_MAIN_PAGE_RESPONSE;
    }
}

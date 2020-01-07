package com.santander.ibmi.params;

import org.jetbrains.annotations.Contract;

import java.util.HashMap;
import java.util.Map;

public class EnumParams {
    private static final String CPY_TO_IFS = "cpyToIfs";
    private static final String CPY_FROM_IFS = "cpyFromIfs";

    public enum Action {
        CPYTOIFS (CPY_TO_IFS),
        CPYFROMFS (CPY_FROM_IFS);

        private String actionType;

        @Contract(pure = true)
        Action(String action) {
            this.actionType = action;
        }

        @Contract(pure = true)
        public String getActionType() {
            return actionType;
        }

        private static final Map<String, Action> lookup = new HashMap<>();
        static
        {
            for(Action action : Action.values())
            {
                lookup.put(action.getActionType(), action);
            }
        }

        public static Action get(String action)
        {
            return lookup.get(action);
        }
    }
}

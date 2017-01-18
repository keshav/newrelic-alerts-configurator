package com.ocado.panda.newrelic.sync.configuration.condition.terms;

import com.ocado.panda.newrelic.api.model.conditions.UserDefined;
import com.ocado.panda.newrelic.sync.configuration.condition.UserDefinedConfiguration;

public class UserDefinedUtils {

    private UserDefinedUtils() {

    }

    public static UserDefined createUserDefined(UserDefinedConfiguration userDefinedConfiguration) {
        if(userDefinedConfiguration != null) {
            return UserDefined.builder()
                    .metric(userDefinedConfiguration.getMetric())
                    .valueFunction(userDefinedConfiguration.getValueFunction().getAsString())
                    .build();
        }
        return null;
    }
}

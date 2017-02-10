package com.ocado.panda.newrelic.sync.configuration.channel.internal;

import com.ocado.newrelic.api.NewRelicApi;
import com.ocado.newrelic.api.model.channels.AlertsChannelConfiguration;
import com.ocado.newrelic.api.model.users.User;
import com.ocado.panda.newrelic.sync.configuration.channel.Channel;
import com.ocado.panda.newrelic.sync.configuration.channel.ChannelTypeSupport;
import com.ocado.panda.newrelic.sync.configuration.channel.UserChannel;
import com.ocado.panda.newrelic.sync.exception.NewRelicSyncException;
import lombok.AllArgsConstructor;

import static java.lang.String.format;

@AllArgsConstructor
public class UserChannelTypeSupport implements ChannelTypeSupport {
    private Channel channel;

    @Override
    public AlertsChannelConfiguration generateAlertsChannelConfiguration(NewRelicApi api) {
        UserChannel userChannel = (UserChannel) channel;
        int userId = getUserId(api, userChannel.getUserEmail());
        AlertsChannelConfiguration.AlertsChannelConfigurationBuilder builder = AlertsChannelConfiguration.builder();
        builder.userId(userId);
        return builder.build();
    }

    private static int getUserId(NewRelicApi api, String userEmail) {
        return api.getUsersApi().getByEmail(userEmail)
                .map(User::getId)
                .orElseThrow(() -> new NewRelicSyncException(format("NewRelic user with email %s does not exist", userEmail)));
    }
}

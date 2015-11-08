/**
 * @author: YenNguyen
 * @date: Nov 7, 2015
 */
package com.footballun.admin;

import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.ScreenContextConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;

import com.footballun.model.MatchupLive;

/**
 * @author YenNguyen
 *
 */
public class MatchupLiveAdministration extends AdministrationConfiguration<MatchupLive> {

	public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
		return configurationBuilder.nameField( "Matchup" ).build();
	}

	public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
		return screenContextBuilder
				.screenName( "Game Live Administration" )
				.build();
	}

	public FieldSetConfigurationUnit listView(final FieldSetConfigurationUnitBuilder fragmentBuilder) {
		return fragmentBuilder
				.field( "id" ).caption( "ID" )
				.field( "matchup" ).caption( "Matchup" )
				.field( "matchupRegister" ).caption( "Matchup Register" )
				.field( "event" ).caption( "Event" )
				.field( "updateMinute" ).caption( "Update Minute" )
				.field( "timestamp" ).caption( "Timestamp" )
				.build();
	}
}

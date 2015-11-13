/**
 * @author: yen.nt
 * @created on Nov 13, 2015
 */
package com.footballun.admin;

import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.ScreenContextConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;

import com.footballun.model.StandingLive;

/**
 * @author yen.nt
 *
 */
public class StandingLiveAdministration extends AdministrationConfiguration<StandingLive> {

	public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
		return configurationBuilder.nameField( "squad" ).build();
	}

	public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
		return screenContextBuilder
				.screenName( "Standing Live Administration" )
				.build();
	}

	public FieldSetConfigurationUnit listView(final FieldSetConfigurationUnitBuilder fragmentBuilder) {
		return fragmentBuilder
				.field( "standing" ).caption( "Standing" )
				.field( "currentPosition" ).caption( "#" )
				.field( "previousPosition" ).caption( "#prev" )
				.field( "squad" ).caption( "Squad" )
				.field( "point" ).caption( "Point" )
				.field( "played" ).caption( "Played" )
				.field( "won" ).caption( "W" )
				.field( "lost" ).caption( "L" )
				.field( "drawn" ).caption( "D" )
				.field( "goalsScored" ).caption( "GF" )
				.field( "goalsAgainst" ).caption( "GA" )
				.build();
	}
}

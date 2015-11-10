/**
 * @author: yen.nt
 * @created on Nov 10, 2015
 */
package com.footballun.admin;

import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.ScreenContextConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;

import com.footballun.model.Standing;

/**
 * @author yen.nt
 *
 */
public class StandingAdministration extends AdministrationConfiguration<Standing> {

	public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
		return configurationBuilder.nameField( "squad" ).build();
	}

	public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
		return screenContextBuilder
				.screenName( "Standing Administration" )
				.build();
	}

	public FieldSetConfigurationUnit listView(final FieldSetConfigurationUnitBuilder fragmentBuilder) {
		return fragmentBuilder
				.field( "currentPosition" ).caption( "#" )
				.field( "previousPosition" ).caption( "#prev" )
				.field( "squad" ).caption( "Squad" )
				.field( "point" ).caption( "Point" )
				.field( "played" ).caption( "Played" )
				.field( "won" ).caption( "W" )
				.field( "lost" ).caption( "L" )
				.field( "drawn" ).caption( "D" )
				.build();
	}
}

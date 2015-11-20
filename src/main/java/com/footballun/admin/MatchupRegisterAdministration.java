/**
 * @author: YenNguyen
 * @date: Nov 7, 2015
 */
package com.footballun.admin;

import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FiltersConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.ScreenContextConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.FiltersConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;

import com.footballun.model.MatchupRegister;

/**
 * @author YenNguyen
 *
 */
public class MatchupRegisterAdministration extends AdministrationConfiguration<MatchupRegister> {

	public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
		return configurationBuilder.nameField( "squadMember" ).build();
	}

	public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
		return screenContextBuilder
				.screenName( "Matchup Register Administration" )
				.build();
	}

	public FieldSetConfigurationUnit listView(final FieldSetConfigurationUnitBuilder fragmentBuilder) {
		return fragmentBuilder
				.field( "id" ).caption( "ID" )
				.field( "squadMember" ).caption( "Squad Member" )
				.field( "matchupDetail" ).caption( "Matchup Detail" )
				.field( "position" ).caption( "Position" )
				.field( "isLineup" ).caption( "Lineup" )
				.build();
	}
	
	public FiltersConfigurationUnit filters( final FiltersConfigurationUnitBuilder filterBuilder ) {
		return filterBuilder
				.filter( "ID", "id" )
				.filter( "Squad Member", "squadMember" )
				.filter( "Matchup Detail", "matchupDetail" )
				.filter( "Position", "position" )
				.filter( "Lineup", "isLineup" )
				.build();
	}
}

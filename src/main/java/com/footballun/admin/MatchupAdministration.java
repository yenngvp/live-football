/**
 * 
 *
 * @author: yen.nt
 * @created on Oct 30, 2015
 */
package com.footballun.admin;

import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.ScreenContextConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;

import com.footballun.model.Matchup;

/**
 * @author yen.nt
 *
 */
public class MatchupAdministration extends AdministrationConfiguration<Matchup> {

	public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
		return configurationBuilder.nameField( "name" ).build();
	}

	public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
		return screenContextBuilder
				.screenName( "Matchups Administration" )
				.build();
	}

	public FieldSetConfigurationUnit listView(final FieldSetConfigurationUnitBuilder fragmentBuilder) {
		return fragmentBuilder
				.field( "id" ).caption( "ID" )
				.field( "details" ).caption( "Details" )
				.field( "status" ).caption( "Status" )
				.field( "result" ).caption( "Result" )
				.build();
	}
	
//	public FieldSetConfigurationUnit quickView( final FieldSetConfigurationUnitBuilder fragmentBuilder ) {
//        return fragmentBuilder
//				.field( "startAt" ).caption( "Start" )
//				.field( "endAt" ).caption( "Finish" )
//				.build();
//    }
}

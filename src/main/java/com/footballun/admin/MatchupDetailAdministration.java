/**
 * @author: yen.nt
 * @created on Nov 11, 2015
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

import com.footballun.model.MatchupDetail;
import com.footballun.repository.eventlistener.MatchupDetailRepositoryEventListener;

/**
 * @author yen.nt
 *
 */
public class MatchupDetailAdministration extends AdministrationConfiguration<MatchupDetail> {

	public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
		return configurationBuilder
				.nameField( "name" )
				.repositoryEventListener(MatchupDetailRepositoryEventListener.class)
				.build();
	}

	public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
		return screenContextBuilder
				.screenName( "Matchup Details Administration" )
				.build();
	}

	public FieldSetConfigurationUnit listView(final FieldSetConfigurationUnitBuilder fragmentBuilder) {
		return fragmentBuilder
				.field( "matchup" ).caption( "Matchup" )
				.field( "squad" ).caption( "Squad" )
				.field( "id" ).caption( "ID" )
				.field( "isHomeSquad" ).caption( "Home" )
				.field( "isFirstSquad" ).caption( "First Squad" )
				.field( "goal" ).caption( "Goal" )
				.build();
	}
	
	public FiltersConfigurationUnit filters( final FiltersConfigurationUnitBuilder filterBuilder ) {
		return filterBuilder
				.filter( "ID", "id" )
				.filter( "Matchup", "matchup" )
				.filter( "Squad", "squad" )
				.filter( "Goal", "goal" )
				.build();
	}
}

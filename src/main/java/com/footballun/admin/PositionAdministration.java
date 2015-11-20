/**
 * @author: yen.nt
 * @created on Nov 5, 2015
 */
package com.footballun.admin;

import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.ScreenContextConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;

import com.footballun.model.Position;

/**
 * @author yen.nt
 *
 */
public class PositionAdministration  extends AdministrationConfiguration<Position> {

	public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
		return configurationBuilder.nameField( "Name" ).build();
	}

	public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
		return screenContextBuilder
				.screenName( "Positions Administration" )
				.build();
	}

	public FieldSetConfigurationUnit listView(final FieldSetConfigurationUnitBuilder fragmentBuilder) {
		return fragmentBuilder
				.field( "id" ).caption( "ID" )
				.field( "name" ).caption( "Name" )
				.field( "particularName" ).caption( "Particular Name" )
				.field( "areaTopdown" ).caption( "Topdown (1-5)" )
				.field( "areaLeftright" ).caption( "Leftright(1-5)" )
				.build();
	}
}

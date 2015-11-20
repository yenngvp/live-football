/**
 * @author: yen.nt
 * @created on Nov 20, 2015
 */
package com.footballun.admin;

import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.ScreenContextConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;

import com.footballun.model.Formation;

/**
 * @author yen.nt
 *
 */
public class FormationAdministration extends AdministrationConfiguration<Formation> {
	
	public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
		return configurationBuilder.nameField( "Name" ).build();
	}

	public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
		return screenContextBuilder
				.screenName( "Formation Administration" )
				.build();
	}

	public FieldSetConfigurationUnit listView(final FieldSetConfigurationUnitBuilder fragmentBuilder) {
		return fragmentBuilder
				.field( "id" ).caption( "ID" )
				.field( "name" ).caption( "Name" )
				.field( "position1" ).caption( "Position1" )
				.field( "position2" ).caption( "Position2" )
				.field( "position3" ).caption( "Position3" )
				.field( "position4" ).caption( "Position4" )
				.field( "position5" ).caption( "Position5" )
				.field( "positionArrayForWeb" ).caption( "Position Displayed (An example of 4-2-3-1 is [ 1,2,3,4|5,6|7,8,9|10 ] )" )
				.build();
	}
}

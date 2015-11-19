/**
 * @author: yen.nt
 * @created on Nov 18, 2015
 */
package com.footballun.admin;

import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.ScreenContextConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;

import com.footballun.model.Setting;

/**
 * @author yen.nt
 *
 */
public class SettingRepositoryAdministration extends
		AdministrationConfiguration<Setting> {

	public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
		return configurationBuilder.nameField( "name" ).build();
	}

	public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
		return screenContextBuilder
				.screenName( "Settings Administration" )
				.build();
	}

	public FieldSetConfigurationUnit listView(final FieldSetConfigurationUnitBuilder fragmentBuilder) {
		return fragmentBuilder
				.field( "id" ).caption( "ID" )
				.field( "name" ).caption( "Team" )
				.field( "competition" ).caption( "Selected Competition" )
				.field( "timeZoneId" ).caption( "TimeZone" )
				.field( "overrideServerTime" ).caption( "Override Server Time" )
				.field( "startCountdown" ).caption( "Start Countdown (hours)" )
				.build();
	}
}

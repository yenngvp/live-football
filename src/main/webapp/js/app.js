
var app = angular.module('footballun', ['ui.router',
                                        'ui.router.stateHelper',
                                        'ngAnimate',
                                        'ngCookies',
                                        'ngResource',
                                        'ngMockE2E',
                                        'ngStorage',
                                        'angular-progress-arc',
                                        'timer',
                                    	'ngSanitize',
                                    	'angular-timeline',
                                    	'angular-scroll-animate',
                                    	'ui.bootstrap',
                                    	'LocalStorageModule',
                                    	'ngLocalize',
                                    	'ngLocalize.InstalledLanguages',
                                    	'ngLocalize.Events',
                                    	'ngLocale'
                                    	]);


/** Start of Configurable constants **/
app.constant('useMockData', false);
app.constant('context', '/footballun');
app.constant('enableCache', false);


/** End of Configurable constants **/

app.config(['stateHelperProvider','$urlRouterProvider','$urlMatcherFactoryProvider',function(stateHelperProvider,$urlRouterProvider,$urlMatcherFactoryProvider) {

	$urlRouterProvider.otherwise("/");

	$urlMatcherFactoryProvider.strictMode(false)

	stateHelperProvider.state({
		name: "landing",
		url: "/landing",
		templateUrl: "components/landing/landing.html",
		controller: "MainController",
		data: { requireLogin : false }
	}).state({
		name: "dashboard",
		url: "/",
		templateUrl: "components/dashboard/dashboard.html",
		controller: "DashboardController",
		data: { requireLogin : false }
	}).state({
		name: "matchdays",
		url: "/competition/:id/matchdays",
		templateUrl: "components/matchdays/matchdays.html",
		controller: "MatchDayController",
		data: { requireLogin : false }
	}).state({
		name: "teams",
		url: "/teams",
		templateUrl: "components/teams/teams.html",
		controller: "TeamController",
		data: { requireLogin : false }
	}).state({
		name: "standings",
		url: "/standings",
		templateUrl: "components/standings/standings.html",
		controller: "StandingController",
		data: { requireLogin : false }
	}).state({
		name: "stats",
		url: "/stats",
		templateUrl: "components/stats/stats.html",
		controller: "StatsController",
		data: { requireLogin : false }
	}).state({
		name: "teamDetails",
		url: "/teams/:id/members",
		templateUrl: "components/teams/team_details.html",
		controller: "TeamDetailsController",
		data: {requireLogin : false}
	}).state({
		name: "matchDetails",
		url: "/match/:id",
		templateUrl: "components/matchdays/matchup_detail.html",
		controller: "MatchupDetailController",
		data: {requireLogin : false}
	}).state({
		name: "matchRegister",
		url: "/match-register/:id",
		templateUrl: "components/matchdays/matchup_detail.html",
		controller: "MatchupDetailController",
		data: {requireLogin : false}
	})
	;

} ]);

// Local storage configuration
app.config(function (localStorageServiceProvider) {
	  localStorageServiceProvider
	    .setPrefix('footballun')
	    .setStorageType('localStorage')
	    .setNotify(true, true)
});

/** Controllers **/
app.controller('MainController', MainController);
app.controller('MatchDayController', MatchDayController);
app.controller('TeamController', TeamController);
app.controller('StandingController', StandingController);;
app.controller('StatsController', StatsController);

app.controller('SearchController', SearchController);
app.controller('TeamDetailsController', TeamDetailsController);
app.controller('MatchupDetailController', MatchupDetailController);

app.controller('DashboardController', ['$scope', 'MatchDay', 'enableCache', 'localStorageService','locale',
                                       function($scope, MatchDay, enableCache, localStorageService,locale) {

	if (enableCache) {
		// Query matchdays
		// Gets localStorage cached

		var key = 'matchdaysCache'; 
		$scope.matchdays = localStorageService.get(key);
		if (angular.isUndefined($scope.matchdays) || $scope.matchdays == null || $scope.matchdays == 0) {
			MatchDay.matchdays.query().$promise.then(
					//success
					function( value ) {
						localStorageService.set(key, value);
						$scope.matchdays = value;
					},
					//error
					function( error ) {
						// TODO: Handle request returns error
						console.log("Failed with: " + error);
					}
					);
		}
	} else {
		$scope.matchdays = MatchDay.matchdays.query();
	}
		
	if (enableCache) {
		// Query featured players
		// Gets localStorage cached
		key = 'featuredMatchupsCache'; 
		$scope.featuredMatchups = localStorageService.get(key);
		if (angular.isUndefined($scope.featuredMatchups) || $scope.featuredMatchups == null || $scope.featuredMatchups == 0) {
			MatchDay.featuredMatchups.query().$promise.then(
					//success
					function( value ) {
						localStorageService.set(key, value);
						$scope.featuredMatchups = value;
					},
					//error
					function( error ) {
						// TODO: Handle request returns error
						console.log("Failed with: " + error);
					}
					);
		}
	} else {
		$scope.featuredMatchups = MatchDay.featuredMatchups.query();
	}
	
	// Carousel directive
	$scope.myInterval = 5000;
	$scope.noWrapSlides = false;
	
	// Clicked matchup
	$scope.saveSelectedMatchup = function($obj) {
		MatchDay.setSelectedMatchup($obj);
	};
}]);


/** Services **/
app.factory('MatchDay', MatchDay);
app.factory('Team', Team);
app.factory('Standing', Standing);
app.factory('Stats', Stats);
app.factory('MockService', MockService);

/** Directives **/

app.directive('scrollToTarget', function() {
  return function(scope, element) {
    element.bind('click', function() {
    	angular.element('html, body').stop().animate({
			scrollTop: angular.element(angular.element(element).attr('href')).offset().top - 20
		}, 1500);
		return false;
    });
  };
});

app.directive('datePicker', DatePickerDirective);
app.directive('myMatchup', MatchupDirective);

app.value('localeSupported', [
                           'en-US',
                           'fr-FR',
                           'vi-VN'
                       ]);
app.value('localeFallbacks', {
                           'en': 'en-US',
                           'fr': 'fr-FR',
                           'vi': 'vi-VN'
                       });

/** Custom filters **/
/** Custom date filter  **/
app.filter('smarterDate', function($filter) {
	return function(input) {
		if (input == null) { 
			return "";
		} 

		console.log(input);
		var smarterDate = $filter('date') (new Date(input));

		return smarterDate;
	};
});
app.filter('smarterTime', function($filter) {
	return function(input) {
		if (input == null) { 
			return "";
		} 

		console.log(input);
		var smarterDate = $filter('date') (new Date(input), "h:ss a");

		return smarterDate;
	};
});


app.run(function(useMockData, MockService) {
	MockService.mock(useMockData);
});

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
app.constant('enableCache', true);


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
		url: "/matchdays/:day/competition/:id",
		templateUrl: "components/matchdays/matchdays.html",
		controller: "MatchDayController",
		data: { requireLogin : false }
	}).state({
		name: "results",
		url: "/results",
		templateUrl: "components/results/results.html",
		controller: "ResultController",
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
app.controller('ResultController', ResultController);
app.controller('MatchdayPagedController', MatchdayPagedController);

app.controller('DashboardController', ['$scope', 'MatchDay', 'enableCache', 'localStorageService','locale',
                                       function($scope, MatchDay, enableCache, localStorageService,locale) {

        // Query matchdays
        // Gets localStorage cached
        var key = 'matchdaysCache';
        if (enableCache) {
            $scope.matchdays = localStorageService.get(key);
        } else {
            $scope.matchdays = undefined;
        }

		if (angular.isUndefined($scope.matchdays) || $scope.matchdays == 0) {
			MatchDay.featured.query().$promise.then(
					//success
					function( value ) {
                        if (enableCache) {
                            localStorageService.set(key, value);
                        }

						$scope.matchdays = value;
						
						$scope.todayCounter = 0;
						$scope.thisWeekCounter = 0;
						$scope.nextWeekCounter = 0;
						
						for (var i = 0; i < $scope.matchdays.length; i++) {
							
							var matchups = $scope.matchdays[i];

							for (var index in matchups) {
								if (matchups[index].isToday) {
									$scope.todayCounter++;
								}
								else if (matchups[index].isThisWeek) {
									$scope.thisWeekCounter++;
								}
								else if (matchups[index].isNextWeek) {
									$scope.nextWeekCounter++;
								}
							}
						}
						
						// Countdown for the soonest match
						var soonestMatch = $scope.matchdays[0][0];
						if (!angular.isUndefined(soonestMatch)) {
							var startDate = new Date(soonestMatch.startAt);
							$scope.soonestCountdown = startDate.getTime();
						} else {
							$scope.soonestCountdown = 0;
						}
			
					},
					
					//error
					function( error ) {
						// TODO: Handle request returns error
						console.log("Failed with: " + error);
					}
					);
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
						   'vi': 'vi-VN',
                           'en': 'en-US',
                           'fr': 'fr-FR'
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
app.filter('startFrom', function() {
  return function(input, start) {
      start = +start; //parse to int
      return input.slice(start);
  }
});



app.run(function(useMockData, MockService) {
	MockService.mock(useMockData);
});
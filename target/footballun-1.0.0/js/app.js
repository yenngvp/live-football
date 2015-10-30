var app = angular.module('footballun', ['ui.router','ui.router.stateHelper','ngAnimate','ngCookies','ngResource','ngMockE2E','ngStorage']);


/** Start of Configurable constants **/
app.constant('useMockData', false);
app.constant('context', '/footballun');
/** End of Configurable constants **/

app.config(['stateHelperProvider','$urlRouterProvider','$urlMatcherFactoryProvider',function(stateHelperProvider,$urlRouterProvider,$urlMatcherFactoryProvider) {

	$urlRouterProvider.otherwise("/");

	$urlMatcherFactoryProvider.strictMode(false)

	stateHelperProvider.state({
		name: "landing",
		url: "/",
		templateUrl: "components/landing/landing.html",
		controller: "MainController",
		data: { requireLogin : false }
	}).state({
		name: "dashboard",
		url: "/dashboard",
		templateUrl: "components/dashboard/dashboard.html",
		controller: "DashboardController",
		data: { requireLogin : false }
	}).state({
		name: "matchdays",
		url: "/matchdays",
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
		url: "/teams/:id",
		templateUrl: "components/teams/team_details.html",
		controller: "TeamDetailsController",
		data: {requireLogin : false}
	});

} ]);

/** Controllers **/
app.controller('MainController', MainController);
app.controller('DashboardController', DashboardController);
app.controller('MatchDayController', MatchDayController);
app.controller('TeamController', TeamController);
app.controller('StandingController', StandingController);
app.controller('StatsController', StatsController);

app.controller('SearchController', SearchController);

app.controller('TeamDetailsController', TeamDetailsController);

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

app.run(function(useMockData, MockService) {
	MockService.mock(useMockData);
});
var StandingController = ['$scope','$http','Standing', function ($scope, $http, Standing) {
	
	$scope.$on('$viewContentLoaded', function(event){
		$('html, body').animate({
		    scrollTop: $("#veterinarians").offset().top
		}, 1000);
	});
	
	$scope.standings = Standing.query();
	
}];
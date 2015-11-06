var TeamController = ['$scope','$state','Team','localStorageService',function($scope,$state,Team,localStorageService) {

	// Gets localStorage cached
	var key = 'teamsCache'; 
    $scope.teams = localStorageService.get(key);
    if (angular.isUndefined($scope.teams) || $scope.teams == null || $scope.teams.length == 0) {
    	Team.teams.query().$promise.then(
    			//success
    			function( value ) {
    				localStorageService.set(key, value);
    				$scope.teams = value;
    			},
    			//error
    			function( error ) {
    				// TODO: Handle request returns error
    				console.log("Failed with: " + error);
    			}
    	);
    }
    
}];


var TeamDetailsController = ['$scope','$rootScope','$stateParams', '$cacheFactory','Team','localStorageService', function($scope,$rootScope,$stateParams,$cacheFactory,Team,localStorageService) {
	
    // Gets localStorage cached
	var key = 'teamMembersCache_' + $stateParams.id;
    $scope.teamMembers = localStorageService.get(key);
    if (angular.isUndefined($scope.teamMembers) || $scope.teamMembers == null || $scope.teamMembers.length == 0) {
    	Team.teamMembers.query({id: $stateParams.id}).$promise.then(
    			//success
    			function( value ) {
    				localStorageService.set(key, value);
    				$scope.teamMembers = value;
    			},
    			//error
    			function( error ) {
    				// TODO: Handle request returns error
    				console.log("Failed with: " + error);
    			}
    	);
    } 	
}];

/*
var AddTeamController = ['$scope','Team', function($scope,Team) {

	$scope.Team={id:0,pets:[]};

	$scope.addTeam = function(){
		Team.save($scope.Team);
	}
}];
*/
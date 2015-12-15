var TeamController = ['$scope','$state','Team','localStorageService', 'enableCache','$stateParams',
                      function($scope,$state,Team,localStorageService,enableCache,$stateParams) {

	// Gets localStorage cached
	var key = 'teamsCache'; 
	if (enableCache) {
		$scope.teams = localStorageService.get(key);
	} else {
		$scope.teams = null;
	}
	
	if (angular.isUndefined($scope.teams) || $scope.teams == null || $scope.teams.length == 0) {
		Team.teams.query({competition: $stateParams.competition}).$promise.then(
				//success
				function( value ) {
					if (enableCache) {
						localStorageService.set(key, value);
					}
					
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


var TeamDetailsController = ['$scope','$rootScope','$stateParams', '$cacheFactory','Team','localStorageService','enableCache',
                             function($scope,$rootScope,$stateParams,$cacheFactory,Team,localStorageService,enableCache) {
	
    // Gets localStorage cached
	var key = 'teamMembersCache_' + $stateParams.id;
	if (enableCache) {
		$scope.teamMembers = localStorageService.get(key);
	} else {
		$scope.teamMembers = null;
	}
	
    if (angular.isUndefined($scope.teamMembers) || $scope.teamMembers == null || $scope.teamMembers.length == 0) {
    	Team.teamMembers.query({id: $stateParams.id}).$promise.then(
    			//success
    			function( value ) {
    				if (enableCache) {
    					localStorageService.set(key, value);
    				}
    				
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

var MatchDayController = ['$scope', 'MatchDay','enableCache','localStorageService',
                          function($scope, MatchDay, enableCache, localStorageService) {

	/*
	$scope.$on('$viewContentLoaded', function(event){
		$('html, body').animate({
		    scrollTop: $("#matchdays").offset().top
		}, 1000);
	});*/

	if (enableCache) {
		// Gets localStorage cached
		console.log("Cache service enabled");
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
	
	
    /*
     *  Calulates lineup positions
     */
    var lineups = [];
    lineups.push([5,0]);
    lineups.push([2,1]);
    lineups.push([4,1]);
    lineups.push([6,1]);
    lineups.push([8,1]);
    lineups.push([3,2]);
    lineups.push([7,2]);
    lineups.push([2,3]);
    lineups.push([5,3]);
    lineups.push([8,3]);
    lineups.push([5,4]);
    // Maximum pitch image background (in pixels)
    var imgWidth = 750;
    var imgHeight = 450;
    var maxGridX = 10;
    var maxGridY = 5; 		
    var imgWidthUnit = imgWidth / maxGridX;
    var imgHeightUnit = imgHeight / maxGridY;
    var imagePositions = [];
    var pos = [];
    console.log(toString(lineups.length));
    for (var i = 0; i < lineups.length; i++) {
    	pos = [imgWidthUnit * lineups[i][0] - 30, imgHeightUnit * lineups[i][1]];
    	imagePositions.push(pos);
    }
    $scope.imagePositions = imagePositions;
    console.log($scope.imagePositions);
}];


var MatchupDetailController = ['$scope','$rootScope','$stateParams', 'MatchDay','localStorageService','locale', 
                               function($scope,$rootScope,$stateParams,MatchDay,localStorageService,locale) {
	
	$scope.matchup = MatchDay.getSelectedMatchup();
	
	// Get the matchup registration
	MatchDay.registers.query({id: $stateParams.id}).$promise.then(
			//success
			function( value ) {
				var registers = value;
				$scope.lineupAvailable = false;
				
				if (value !== undefined && value != '' && value.length > 0) {
						
					var matchupInvolves = registers[0];
					// Inside matchupRegister object returned by Json we can get matchup object from
					// matchupRegister.matchup or matchRegister.matchupDetail.matchup, but due it serialized/deserialized by
					// @JsonIdentityInfo so on one matchup is actual an object the other is an ID number refered to the matchup.
					if (typeof matchupInvolves[0].matchup == 'object') {
						$scope.matchup = matchupInvolves[0].matchup;
					} else if (matchupInvolves[0].matchupDetail != null) {
						$scope.matchup = matchupInvolves[0].matchupDetail.matchup;
					}
					
					for (var i = 0; i < registers.length; i++) {
						var register = registers[i];
						if (register[0].matchupDetail == null) {
							$scope.referees = register; 
						} else if (register[0].matchupDetail.id = $scope.matchup.firstDetail.id) {
							$scope.firstRegisters = register;
							$scope.lineupAvailable = true;
						}  else if (register[0].matchupDetail.id = $scope.matchup.secondDetail.id) {
							$scope.secondRegisters = register;
							$scope.lineupAvailable = true;
						}
					}	
				}
				
				
				if ($scope.matchup === undefined) {
					$scope.matchup = MatchDay.matchup.query({id: $stateParams.id});
					console.log("Getting the matchup");
				}
				
				console.log("Getting registers");
			},
			//error
			function( error ) {
				// TODO: Handle request returns error
				console.log("Failed with: " + error);
			}
	);


}];



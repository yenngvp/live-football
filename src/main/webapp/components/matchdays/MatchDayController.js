var MatchDayController = ['$scope', '$stateParams', 'MatchDay','enableCache','localStorageService',
                          function($scope, $stateParams, MatchDay, enableCache, localStorageService) {

	/*
	$scope.$on('$viewContentLoaded', function(event){
		$('html, body').animate({
		    scrollTop: $("#matchdays").offset().top
		}, 1000);
	});*/

	console.log($stateParams);
	// Gets localStorage cached
	var key = 'matchdaysCache'; 
	if (enableCache) {
		$scope.matchdays = localStorageService.get(key);
		console.log("Cache service enabled");
	} else {
		$scope.matchdays = undefined;
	}

	if (angular.isUndefined($scope.matchdays) || $scope.matchdays == null || $scope.matchdays == 0) {
		var day;
		if (angular.isUndefined($stateParams.day)) {
			day = 0;
		} else {
			day = $stateParams.day;
		}
		var compId = localStorageService.get("PREFERENCES_COMPETITION").id;
		
		MatchDay.matchdays.query({day: day, giai_dau_id: compId}).$promise.then(
				//success
				function( value ) {
					if (enableCache) {
						localStorageService.set(key, value);
					}
						
					$scope.matchdays = value;
					
					$scope.todayCounter = 0;
					$scope.thisWeekCounter = 0;
					$scope.nextWeekCounter = 0;

                    $scope.soonestMatch = null;
                    var secondsNow = Date.now() / 1000;

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

                            // Won't include past matches
                            if (matchups[index].countdown > secondsNow
                                && ($scope.soonestMatch == null
                                || $scope.soonestMatch.countdown > matchups[index].countdown)) {

                                $scope.soonestMatch = matchups[index];
                            }
                        }
                    }
					
					// Countdown for the soonest match
                    if ($scope.soonestMatch != null) {
                        $scope.soonestCountdown = $scope.soonestMatch.countdown * 1000;
                    } else {
                        $scope.soonestCountdown = 0;
                    }


                    $scope.hideSpinner = true;
				},
				//error
				function( error ) {
					// TODO: Handle request returns error
					console.log("Failed with: " + error);
                    $scope.hideSpinner = true;
                    $scope.notFoundMessage = 'Trang trong';
				}
				);
	}
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
				}
				
				$scope.formation1 = getPitchLaidout($scope.matchup.firstDetail, $scope.firstRegisters);
				$scope.formation2 = getPitchLaidout($scope.matchup.secondDetail, $scope.secondRegisters);
			
				$scope.hideSpinner = true;
			},
			//error
			function( error ) {
				// TODO: Handle request returns error
				console.log("Failed with: " + error.toString());
				
				$scope.hideSpinner = true;
			}
	);


	$scope.renderFormationCanvas = function(x, y, name) {
		return {
			  'position': 'absolute',
			  'left': + x + 'px',
			  'top': + y + 'px',
			  'background-image': 'url(images/teams/' + name.toLowerCase() + '-150.png)',
			  'background-size': 'cover',
		}
	};
	
	var getPitchLaidout = function (matchupDetail, matchupRegisters) {
		
		if (!$scope.lineupAvailable || matchupDetail == null || matchupRegisters == null || matchupRegisters === undefined) return null;
		
		/*
		 *  Calulates lineup positions
		 */
		var form;
		var formPositions;
		
		if (matchupDetail.formation != null) {
			// Available formation for this matchup
			form = matchupDetail.formation
		} else {
			// User favorite formation from the squad
			form = matchupDetail.squad.formation; 
		}
		
		formPositions = form.parsedPositions;
		if (formPositions == null) return null;
		
		// DON'T change maxGridX,Y value. It must be in consistent with parsedPositions predefined in the database
		var maxGridX = 10;
		var maxGridY = 5; 	
		
		// Maximum pitch image background (in pixels)
		var imgWidth = 750;
		var imgHeight = 450;	
		var imgWidthUnit = imgWidth / maxGridX;
		var imgHeightUnit = imgHeight / maxGridY;
		var imagePositions = [];
		var pos = [];
		
		var lineupPositions = [];
		var x, y, row;
		// Decodes prefiened formation positions for web displayed
		for (var i = 0; i < formPositions.length; i++) {
			row = formPositions[i];
			for (var j = 0; j < row.length; j++) {
				x = row[j] % maxGridX - 1;
				y = Math.floor(row[j] / maxGridX);
				pos = [imgWidthUnit * x - 30, imgHeightUnit * y];
				lineupPositions.push(pos);
			}
		}
		

		var lineupPlayers = [];
		// Gets lineup players squad
		for (var index in matchupRegisters) {
			if (matchupRegisters[index].isLineup) {
				lineupPlayers.push(matchupRegisters[index]);
			}
		}
			
		return [lineupPlayers, lineupPositions];
	};
    
}];

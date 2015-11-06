var MatchDayController = ['$scope', 'MatchDay','localStorageService', function($scope, MatchDay, localStorageService) {

	/*
	$scope.$on('$viewContentLoaded', function(event){
		$('html, body').animate({
		    scrollTop: $("#matchdays").offset().top
		}, 1000);
	});*/

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

/*
var PetDetailsController = ['$scope','PetType','OwnerPet',function($scope,PetType,OwnerPet,Pet) {
	$scope.petTypes = PetType.query();
	
	$scope.save = function(){
		currentOwnerId = $scope.currentOwner.id;

		for (i=0; i<$scope.petTypes.length; i++){
			if ($scope.petTypes[i].id == $scope.currentPet.type.id){
				$scope.currentPet.type.name = $scope.petTypes[i].name;
				break;
			}
		}
		
		OwnerPet.save({ownerId:currentOwnerId},$scope.currentPet,function(pet) {
			var newPet = true;
			for (i=0;i<$scope.currentOwner.pets.length;i++) {
				if($scope.currentOwner.pets[i].id == pet.id) {
					$scope.currentOwner.pets[i] == pet;
					newPet = false;
					break;
				}
			}
			if(newPet) {
				$scope.currentOwner.pets.push(pet);
			}
		});
	};
}];
*/
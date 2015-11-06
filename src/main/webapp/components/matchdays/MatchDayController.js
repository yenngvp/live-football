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
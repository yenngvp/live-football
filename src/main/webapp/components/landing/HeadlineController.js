var HeadlineController = ['$scope','$state','Headline',function($scope,$state,Headline) {
	$scope.$on('$viewContentLoaded', function(event){
		$('html, body').animate({
		    scrollTop: $("#headlines").offset().top
		}, 1000);
	});

	$scope.headline = Headline.query();
}];

var HeadlineDetailsController = ['$scope','$rootScope','$stateParams','Headline', function($scope,$rootScope,$stateParams,Headline) {

	var currentId = $stateParams.id;
	var nextId = parseInt($stateParams.id) + 1;
	var prevId = parseInt($stateParams.id) - 1;

	$scope.prevHeadline = Headline.get({id:prevId});
	$scope.nextHeadline = Headline.get({id:nextId});
	$scope.currentHeadline = Headline.get($stateParams);

	$scope.saveHeadline = function(){
		headline = $scope.currentHeadline;
		Headline.save(headline);
	}
	
	$scope.addPet = function() {
		$scope.petFormHeader = "Add a new Pet";
		$scope.currentPet = {type:{}};
	}
	
	$scope.editPet = function(id) {
		$scope.petFormHeader = "Edit Pet";
		for(i = 0;i < $scope.currentHeadline.pets.length; i++) {
			if($scope.currentHeadline.pets[i].id == id) {
				$scope.currentPet = $scope.currentHeadline.pets[i];
				break;
			}
		}
	};

}];

var AddHeadlineController = ['$scope','Headline', function($scope,Headline) {

	$scope.headline={id:0,pets:[]};

	$scope.addHeadline = function(){
		Headline.save($scope.headline);
	}
}];
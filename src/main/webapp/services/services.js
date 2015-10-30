
var MatchDay = ['$resource','context', function($resource, context) {
	return $resource(context + '/api/matchdays');
}];

var Team = ['$resource','context', function($resource, context) {
	return $resource(context + '/api/teams');
}];

var Standing = ['$resource','context', function($resource, context) {
	return $resource(context + '/api/standings');
}];

var Stats = ['$resource','context', function($resource, context) {
	return $resource(context + '/api/stats');
}];

var MockService = ['$httpBackend', '$http', '$q', 'context', function($httpBackend, $http, $q, context) {
	return {
		mock : function(useMockData) {
			
			var passThroughRegex = new RegExp('/static/mock-data/|components/');
			$httpBackend.whenGET(passThroughRegex).passThrough();	
			
			if(useMockData) {
				$q.defer();
				$q.all([
				        $http.get(context + '/static/mock-data/pets.json'),
				        $http.get(context + '/static/mock-data/vets.json'),
				        $http.get(context + '/static/mock-data/owners.json'),
				        $http.get(context + '/static/mock-data/owner_one.json'),
				        $http.get(context + '/static/mock-data/pettypes.json'),
				]).then(function(data) {
					console.log("Mocking /api/pets");
					$httpBackend.whenGET(context + '/api/pets').respond(data[0].data);
					console.log("Mocking /api/vets");
					$httpBackend.whenGET(context + '/api/vets').respond(data[1].data);
					console.log("Mocking /api/owners");
					$httpBackend.whenGET(context + '/api/owners').respond(data[2].data);
					console.log("Mocking /api/owners/1");
					$httpBackend.whenGET(context + '/api/owners/1').respond(data[3].data);
					console.log("Mocking /api/pets/types");
					$httpBackend.whenGET(context + '/api/pets/types').respond(data[4].data);
					
					console.log("Setting up passthrough for other urls");
					var passThroughRegex = new RegExp('/');
					$httpBackend.whenGET(passThroughRegex).passThrough();
				});
			} else {
				console.log("Setting up passthrough for other urls");
				var passThroughRegex = new RegExp('/');
				$httpBackend.whenGET(passThroughRegex).passThrough();			
				$httpBackend.whenPOST(passThroughRegex).passThrough();
				$httpBackend.whenPUT(passThroughRegex).passThrough();
				$httpBackend.whenDELETE(passThroughRegex).passThrough();
			}
		}
	}	
}];

var MatchDay = ['$resource','context', function($resource, context) {
	
	var selectedMatchups;
	
	var setSelectedMatchup = function(obj) {
		selectedMatchups = obj;
	};
	
	var getSelectedMatchup = function() {
		return selectedMatchups;
	};
	
	return {
		  /*
		   * matchups schedules service
		   */
		  featured: $resource(context + '/api/matchdays', {}, {
	    	  query: {method: 'GET', params: {}, isArray: true}
	      }),
	      matchdays: $resource(context + '/api/matchdays/:day/competition/:id', {}, {
	    	  query: { method: 'GET', params: {day: '@day', id: '@id'}, isArray: true}
	      }),
	      
	      /*
	       * Results services
	       */
	      results: $resource(context + '/api/results', {}, {
	    	  query: { method: 'GET', params: {}, isArray: true}
	      }),
	      resultsByDay: $resource(context + '/api/results-day/competition/:id/matchday/:day', {}, {
	    	  query: { method: 'GET', params: {id: '@id', day: '@day'}, isArray: true}
	      }),
	      
	      // Matchup Register service
	      registers: $resource(context + '/api/match-register/:id', {}, {
	    	  query: { method: 'GET', params: {id: '@id'}, isArray: true}
		  }),
		  // Matchup
	      matchup: $resource(context + '/api/match/:id', {}, {
	    	  query: { method: 'GET', params: {id: '@id'}, isArray: false}
		  }),
		  
		  setSelectedMatchup: setSelectedMatchup,
		  getSelectedMatchup: getSelectedMatchup,
	};
}];


var Team = ['$resource','context', function($resource, context) {

	return {
		teams: $resource(context + '/api/teams', {}, {
	        query: {method: 'GET', params: {}, isArray: true}
	     }),
		teamMembers: $resource(context + '/api/teams/:id/members', {}, {
	        query: { method: 'GET', params: {id: '@id'}, isArray: true }
	    })
	};
}];

var Standing = ['$resource','context', function($resource, context) {
	return {
		standingsShortlist: $resource(context + '/api/standings', {}, {
			query: {method: 'GET', params: {}, isArray: true}
		}),
      
		standings: $resource(context + '/api/standings?competition=:competitionId', {}, {}, {
			query: {method: 'GET', params: {competitionId: "@competitionId"}, isArray: true}
		}),
      
	};
		
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
				$q.all([,
				        $http.get(context + '/static/mock-data/pettypes.json'),
				]).then(function(data) {
					console.log("Mocking /api/pets/types");
					$httpBackend.whenGET(context + '/api/pets/types').respond(data[4].data);
					
					console.log("Setting up passthrough for other urls");
					var passThroughRegex = new RegExp('/');
					$httpBackend.whenGET(passThroughRegex).passThrough();
				});
			} else {
				var passThroughRegex = new RegExp('/');
				$httpBackend.whenGET(passThroughRegex).passThrough();			
				$httpBackend.whenPOST(passThroughRegex).passThrough();
				$httpBackend.whenPUT(passThroughRegex).passThrough();
				$httpBackend.whenDELETE(passThroughRegex).passThrough();
			}
		}
	}	
}];

var Preferences = ['$resource','context', function($resource, context) {
	return {
		pref: $resource(context + '/api/_____/prefs', {}, {
			query: {method: 'GET', params: {}, isArray: true}
		}),
	
	};
}];

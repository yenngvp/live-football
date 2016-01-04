
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
		  featured: $resource(context + '/api/dang-xem-nhat', {}, {
	    	  query: {method: 'GET', params: {}, isArray: true}
	      }),
	      matchdays: $resource(context + '/api/lich-thi-dau/:day?giai_dau=:giai_dau_id', {}, {
	    	  query: { method: 'GET', params: {day: '@day', giai_dau_id: '@giai_dau'}, isArray: true}
	      }),
	      
	      /*
	       * Results services
	       */
	      results: $resource(context + '/api/ket-qua-tran-dau?giai_dau=:giai_dau_id', {}, {
	    	  query: { method: 'GET', params: {giai_dau_id: '@giai_dau'}, isArray: true}
	      }),
	      resultsByDay: $resource(context + '/api/ket-qua-tran-dau/vong-dau/:matchday?giai_dau=:giai_dau_id', {}, {
	    	  query: { method: 'GET', params: {matchday: '@matchday', giai_dau_id: '@giai_dau'}, isArray: true}
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
		teams: $resource(context + '/api/thong-tin-doi-bong?giai_dau=:giai_dau_id', {}, {
	        query: {method: 'GET', params: {giai_dau_id: '@giai_dau'}, isArray: true}
	     }),
		teamMembers: $resource(context + '/api/thong-tin-doi-bong/:id/danh-sach-cau-thu-va-ban-huan-luyen', {}, {
	        query: { method: 'GET', params: {id: '@id'}, isArray: true }
	    })
	};
}];

var Standing = ['$resource','context', function($resource, context) {
	return {
		standingsShortlist: $resource(context + '/api/bang-xep-hang', {}, {
			query: {method: 'GET', params: {}, isArray: true}
		}),
      
		standings: $resource(context + '/api/bang-xep-hang?giai_dau=:giai_dau_id', {}, {}, {
			query: {method: 'GET', params: {giai_dau_id: '@giai_dau'}, isArray: true}
		}),

        standingsAllBySquad: $resource(context + '/api/bang-xep-hang/all/team/:squadId', {}, {}, {
            query: {method: 'GET', params: {squadId: '@squadId'}, isArray: true}
        }),

        standingBySquad: $resource(context + '/api/bang-xep-hang/team/:squadId', {}, {}, {
            get: {method: 'GET', params: {squadId: '@squadId'}, isArray: false}
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

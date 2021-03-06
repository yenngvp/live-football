var MainController =  ['$scope','$rootScope','$state','$sessionStorage','$location', 'context',
                       'locale', 'localeSupported', 'localeEvents','localStorageService','Standing','Preferences',
                       function($scope, $rootScope, $state, $sessionStorage,$location, context,
                               locale, localeSupported, localeEvents, localStorageService, Standing, Preferences) {
    
	$scope.$storage = $sessionStorage;
	
	$scope.getSession = function() {
		return $scope.$storage.session;
	};
	
	$scope.login = function() {
		$scope.$storage.session = { 'username' : 'test' };
		$state.go('dashboard');
	};
	
	$scope.logout = function() {
		$scope.$storage.session = null;
		$state.go('dashboard');
	};
	
	$scope.goHome = function() {
		$state.go('dashboard');
		
		/*
		if ($scope.getSession() == null) {
			$state.go('landing');
		} else {
			$state.go('dashboard');
		}*/
	}
	
	
	$scope.menuTabs = [ {
		'name' : 'dashboard',
		'url' : '#',
		'font' : 'fa fa-home'
	}, {
		'name' : 'matchdays',
		'url' : '#matchdays',
		'font' : 'fa fa-eyedropper'
	}, {
		'name' : 'teams',
		'url' : '#teams',
		'font' : 'fa fa-paw'
	}, {
		'name' : 'standings',
		'url' : '#standings',
		'font' : 'fa fa-user'
	}, {
		'name' : 'stats',
		'url' : '#stats',
		'font' : 'fa fa-question'
	}, {
		'name' : 'Brackets',
		'url' : '#',
		'font' : 'fa fa-question'
	}
	];
	
	$scope.context = context;
	
	$scope.footerText = '© ' + new Date().getFullYear() + ' Yen Nguyen. Alrights reserved!';
	
	$rootScope.$state = $state;
	
	$rootScope.$on('$stateChangeStart', function (event, toState, toParams) {
		var requireLogin = toState.data.requireLogin;

		if (requireLogin && $scope.getSession() == null) {
			event.preventDefault();
			$state.go('dashboard');
		}

	});

   var init = function() {

	   Preferences.pref.query().$promise.then(
               //success
               function (value) {
            	   
            	   var key = "_APP_PREFERENCES_";
            	   
            	   var clearFlags = value[0];
            	   if (clearFlags[0]) {
            		   // backup user selected competition
            		   var savedComp = localStorageService.get("PREFERENCES_COMPETITION");
            		   var competitionName = null;
       	    		   if (savedComp != null) {
       	    			   competitionName = savedComp.name;
       	    		   }
            		   
            		   localStorageService.clearAll();
            		   localStorageService.set(key, value);
                       $scope.appPrefs = value;
                       
                       if (competitionName == null) {
                    	   competitionName = value[1][0].name;
                       }
                       // default one competition
                       $rootScope.saveSelectedCompetition(competitionName);
                       
                       console.log("LOADING_APP_PREFS_CLEAR_ALL");
            	   } else {
            		   $scope.appPrefs = localStorageService.get(key);
            		   console.log("LOADING_APP_PREFS_CACHED");
            	   }                   
               },
               //error
               function (error) {
                   // TODO: Handle request returns error
                   console.log("Network error: " + error);
               }
	   );
   };

	/*
	 * Localization supported
	 */
	$scope.supportedLang = localeSupported;
    $scope.localeData = {
        'vi-VN': {
            flagClass: 'flag-vi',
            langDisplayText: 'Tiếng Việt'
        },
        'en-US': {
            flagClass: 'flag-us',
            langDisplayText: 'English'
        },
        'fr-FR': {
            flagClass: 'flag-fr',
            langDisplayText: 'Français'
        }
    };

    $scope.currentYear = new Date().getFullYear();
    $scope.fullName = 'Yen Nguyen';

    $scope.setLocale = function (loc) {
        locale.setLocale(loc);
    };

    locale.ready('common').then(function () {
        $scope.flagClass = $scope.localeData[locale.getLocale()].flagClass;
        $scope.langDisplayText = $scope.localeData[locale.getLocale()].langDisplayText;
        
        $rootScope.dateFormatString = locale.getString('common.dateFormat');
    });

    $scope.$on(localeEvents.localeChanges, function (event, data) {
    	$rootScope.dateFormatString = locale.getString('common.dateFormat');
    	
        $scope.flagClass = $scope.localeData[data].flagClass;
        $scope.langDisplayText = $scope.localeData[data].langDisplayText;
    });
    
    /*
     * Saves user preferences to local storage 
     */
    // From a click event
    $rootScope.saveSelectedCompetition = function(name) {
    	if (!angular.isUndefined($scope.appPrefs)) {
    		var competition = findCompetitionByName(name);
    		if (competition != null) {
	    		localStorageService.set("PREFERENCES_COMPETITION", competition);
	    		$scope.selectedCompetition = competition;
	    		$location.search({giai_dau: competition.id});
    		}
    	}
    };
    
    var findCompetitionByName = function(name) {
    	if (!angular.isUndefined($scope.appPrefs[1])) {
    		var competitions = $scope.appPrefs[1];

    		for (var index in competitions) {
    			if (competitions[index].name == name) {
    				return competitions[index];
    			}
    		}
    	}
    	return null;
    }
    
    $scope.selectedCompetition = localStorageService.get("PREFERENCES_COMPETITION");

    /*
     * Standings service
     */
    $scope.standingsShortlist = Standing.standingsShortlist.query();
    
    $scope.myModel = {
            Url: 'http://jasonwatmore.com/post/2014/08/01/AngularJS-directives-for-social-sharing-buttons-Facebook-Like-GooglePlus-Twitter-and-Pinterest.aspx',
            Name: "AngularJS directives for social sharing buttons - Facebook, Google+, Twitter and Pinterest | Jason Watmore's Blog", 
            ImageUrl: 'http://www.jasonwatmore.com/pics/jason.jpg'
    };
    
    /*
     * ui.bootstrap.dropdown
     */
    $scope.status = {
    		isopen: false,
    		isopenMobileMenu: false,
    };

//    $scope.toggled = function(open) {
//    	$log.log('Dropdown is now: ', open);
//    };

    $scope.toggleDropdown = function($event) {
    	$event.preventDefault();
    	$event.stopPropagation();
    	$scope.status.isopen = !$scope.status.isopen;
    };

    $scope.toggleMobileMenuDropdown = function($event) {
    	$event.preventDefault();
    	$event.stopPropagation();
    	$scope.status.isopenMobileMenu = !$scope.status.isopenMobileMenu;
    };
    
   /*
    * Initialises data for the main controller on page load
    */
   init();
}];


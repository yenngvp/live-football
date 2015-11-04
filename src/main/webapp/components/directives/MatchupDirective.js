/**
 * @author yen.nt
 *
 */
var MatchupDirective = function() {
	return {
		restrict: 'E',
		scope: {
			matchup: '='
		},
		templateUrl: 'components/directives/matchup-directive.html'
	};
};

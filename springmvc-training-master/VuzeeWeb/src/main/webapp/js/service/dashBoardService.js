'use strict';
App.factory('DashBoardService', ['$http', '$q', function($http, $q){

	return {
		
			resumenDashBoard: function() {
					return $http.get('dashboard/getResumen')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error get all resumen');
										return $q.reject(errResponse);
									}
							);
			},
		    
		   
	};

}]);

'use strict';
App.controller('DashBoardController', ['$scope', 'DashBoardService', function($scope, DashBoardService) {
          var self = this;
//          self.user={id:null,username:'',address:'',email:''};
//          self.users=[];
          self.resumen=[];
              
          self.getResumen = function(){
        	  DashBoardService.resumenDashBoard()
                  .then(
      					       function(d) {
      						        self.resumen = d;
      					       },
            					function(errResponse){
            						console.error('Error while fetching Currencies');
            					}
      			       );
          };
          
          self.getResumen();
          
          $scope.PromedioTask = function (tasks, candidato){
        	  var sum = 0;
        	  for (var i = 0; i < tasks.length; i++) {
        		    sum += tasks[i].total;
        		}
        	  
        	  var promedio = (candidato.total*100)/sum;
        	  
        	  return  promedio;
          };
           
          

      }]);
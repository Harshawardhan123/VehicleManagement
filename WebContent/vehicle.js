
var app = angular.module('myApp', []);
		app.controller('myCtrl', function($scope, $http) 
		{			
			
			//used to pass data to service in required format.
				Object.toparams = function ObjecttoParams(obj) 
				{
				    var p = [];
				    for (var key in obj) {
				        p.push(key + '=' + encodeURIComponent(obj[key]));
				    }
				    return p.join('&');
				};
					
				//converting the XML data to JSON object.
				function display(response)
				{
					  xmlDoc = response.data;
					    parser=new DOMParser();
  					var xmlDoc1=parser.parseFromString(xmlDoc,"text/xml");
					    var cars = (xmlDoc1.getElementsByTagName("car"));
					    
					    var carObjs = [];
					    
					    for (var i = 0; i < cars.length; i++) {
					    	var car = {
					    		Id:cars[i].childNodes[0].firstChild.data,Year:cars[i].childNodes[3].firstChild.data,Make:cars[i].childNodes[1].firstChild.data,Model:cars[i].childNodes[2].firstChild.data
					    	};
							carObjs.push(car);
						}	
					 return(carObjs);
				}
				
				//create a new vehicle entry
				$scope.insert = function()
				{
					
					myobject = {'Id':$scope.Id,'Year':$scope.Year,'Model':$scope.Model,'Make':$scope.Make}
					
					$http
					({
					method:"POST",
					url:"http://localhost:8080/VehicleManagement/rest/hi/put",
					dataType: 'json',
    				data: Object.toparams(myobject),
    				headers: 
    				{
        			"Content-Type": "application/json"
        			}
					}).then
					(function(response)
					{
						$scope.cars=display(response);
					}
					, function(response) 
					{
				        $scope.msg = "Something went wrong";
				    }
					);				
				}
				
				//Retrieve the specified vehicle 
				$scope.retrieve = function()
				{
					
					$http
					({
					method:"GET",
					url:"http://localhost:8080/VehicleManagement/rest/hi/get/"+$scope.Id
					}).then
					(function(response)
					{
						$scope.cars=display(response);
					}
					, function(response) 
					{
				        $scope.content = "Something went wrong";
				    }
					);
				}
				
				
				//update the specified vehicle
				$scope.update = function()
				{
					myobject = {'Id':$scope.Id,'Year':$scope.Year,'Model':$scope.Model,'Make':$scope.Make}
					$http
					({
					method:"POST",
					url:"http://localhost:8080/VehicleManagement/rest/hi/update",
					dataType: 'json',
    				data: Object.toparams(myobject),//JSON.stringify({"x":$scope.Id,"y":$scope.Make}),
    				headers: 
    				{
        			"Content-Type": "application/json"
        			}
					}).then
					(function(response)
					{
						$scope.cars=display(response);
					
					}
					, function(response) 
					{
				        $scope.content = "Something went wrong";
				    }
					);
				}
				
				//Retrieve the vehicle object depending upon the condition given by user. (Ex - Make : Toyota)
				$scope.retrieveByProperties = function()
				{
					myobject = {'Id':$scope.Id,'Year':$scope.Year,'Model':$scope.Model,'Make':$scope.Make}
					
					$http
					({
					method:"POST",
					url:"http://localhost:8080/VehicleManagement/rest/hi/getByProperties",
					dataType: 'json',
    				data: Object.toparams(myobject),//JSON.stringify({"x":$scope.Id,"y":$scope.Make}),
    				headers: 
    				{
        			"Content-Type": "application/json"
        			}
					}).then
					(function(response)
					{
						$scope.cars=display(response);
					}
					, function(response) 
					{
				        $scope.content = "Something went wrong";
				    }
					);
				}
				
				//delete the vehicle
				$scope.deletes = function()
				{
					$http
					({
					method:"DELETE",
					url:"http://localhost:8080/VehicleManagement/rest/hi/delete/"+$scope.Id
					}).then
					(function(response)
					{
						$scope.cars=display(response);
					}
					, function(response) 
					{
				        $scope.content = "Something went wrong";
				    }
					);
				}
				
				//Retrieves all vehicles
				$scope.retrieveAll = function()
				{
					$http
					({
					method:"GET",
					url:"http://localhost:8080/VehicleManagement/rest/hi/get/"
					}).then
					(function(response)
					{
						$scope.cars=display(response);
					}
					, function(response) 
					{
				        $scope.content = "Something went wrong";
				    }
					);
				}				
		});
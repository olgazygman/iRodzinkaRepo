apka.controller('FamilyController', function($scope,$http,$filter,$q) {
 
    //parametry
    $scope.selectedUser = "all";
    $scope.stepFuture = 14;
    $scope.stepPast = 7;
    $scope.users = [];
    $scope.zakupy = [];
    $http.defaults.headers.post["Content-Type"] = "application/json";
   
    //wybor rodziny
    
    var irodzina = "grupa1"; //"grupa1"; //"Rodzina1";
    $scope.irodzina = irodzina;
 
    
    //date
    $scope.formatDate = function (date) {
        return $filter("date")(date, 'yyyy-MM-dd');
      }; 
  	var date = new Date();  
    $scope.today = $scope.formatDate($scope.date);
    date.setDate(date.getDate() + $scope.stepFuture);
    $scope.dateEnd = $scope.formatDate(date);
    date = new Date();
    date.setDate(date.getDate() - $scope.stepPast);
    $scope.dateStart = $scope.formatDate(date);
    
    //odczyt ID grupy
    $http.get("http://localhost:8080/grupa/search/findByNazwa?nazwa="+irodzina).success(function (data)
            {
            var userURL = data._embedded.grupa[0]._links.self.href;
            var array = userURL.split('/');
            $scope.id = array[4];  
            }).error(function(error)
            {
            alert("Problem z odczytem ID grupy");
            });

    //odczyt użytkowników 
    $http.get("http://localhost:8080/uzytkownik/search/findByGrupa_Nazwa?grupa="+irodzina).success(function (data)
            {
            $scope.users = data._embedded.uzytkownik;
            angular.forEach($scope.users,function(i)
                    {
                    var userURL = i._links.self.href;
                    var array = userURL.split('/');
                    i.id = array[4];  
                    });
            }).error(function(error)
            {
            alert("Problem z odczytem uzytkownikow");
            });
    
    
    $http.get("http://localhost:8080/lista/search/findByGrupa_NazwaAndKiedyBetween?grupa="
    		+ irodzina + "&od=" + $scope.dateStart + "&do=" + $scope.dateEnd )
    .then(function(data) {
        var zakupy = data.data._embedded.lista;
        var TasksArray = [];
        angular.forEach(zakupy,function(i)
                {
                var userURL = i._links.uzytkownik.href;
                var array = userURL.split('/'); 
                i.id = array[4];
                TasksArray.push($http.get(userURL)); 
                });
        $scope.zakupy = zakupy;
        return $q.all(TasksArray)  //$http.get(userURL)
    	})
    .then(function(arrayOfResults) {
        for(var i=0;i<arrayOfResults.length;i++) {
            $scope.zakupy[i].imie = arrayOfResults[i].data.imie;
        	}
        });
   
    
    $scope.selectUser = function(user) {
    	if (user=='all') $scope.selectedUser = 'all';
    		else $scope.selectedUser = user;
    };

    
    $scope.filterFunction = function(input) {
    	if ((($scope.Check.kup)&&(input.stan == 'kupione'))||
    		((input.imie != $scope.selectedUser)&&($scope.selectedUser != "all"))) return false;
    		else return true;
    	
    	};
  
    $scope.edycjaOkno = function(item)
    	{
    	$scope.panelEdit = !$scope.panelEdit;
    	if (item!=null) {
    		var index = $scope.zakupy.indexOf(item);
    		$scope.zakupy.index = index;
    		$scope.edycja = angular.copy($scope.zakupy[index]);
    	}
    	else {
    		$scope.edycja = null;
    		$scope.zakupy.index = null;
    	}
    	};

    $scope.cofnij = function()
		{
    	$scope.panelEdit = !$scope.panelEdit;
    	$scope.edycja = null;
		};
    
    $scope.zapisz = function()
		{
    	$scope.panelEdit = !$scope.panelEdit;
    	if ($scope.zakupy.index != null) {
    		var index = $scope.zakupy.index;
    		$scope.zakupy[index] = angular.copy($scope.edycja);
    		$scope.zakupy[index].imie = $scope.users[$scope.edycja.imie].imie;
    		$scope.zakupy[index].uzytkownik = "http://localhost:8080/uzytkownik/"+$scope.users[$scope.edycja.imie].id;
    		$http.put("http://localhost:8080/lista/"+$scope.zakupy[index].id,$scope.zakupy[index]).success(function (data)
        		{
        		console.log("Zmieniono zakup id:" + $scope.zakupy[index].id)
        		}).error(function(error)
        	    {
                alert("Problem z zmianą statusu");
                });
    		$scope.edycja = null;
    		}
    	else {
    		$scope.edycja.uzytkownik = "http://localhost:8080/uzytkownik/"+$scope.users[$scope.edycja.imie].id;
    		$scope.edycja.stan = "kup";
    		$scope.edycja.kategoria = "http://localhost:8080/kategoria/1";
    		$scope.edycja.grupa = "http://localhost:8080/grupa/" + $scope.id;
    		$http.post("http://localhost:8080/lista",$scope.edycja).success(function (data)
            		{
    				$scope.edycja.imie = $scope.users[$scope.edycja.imie].imie;
    				$scope.zakupy.push($scope.edycja);
    				alert("Dodano zakup");
            		}).error(function(error)
            	    {
                    alert("Błąd dodania zakupu");
                    });
    		}
		};
    
    
    $scope.stan = function(item)
    {
    	var index = $scope.zakupy.indexOf(item);
    	if ($scope.zakupy[index].stan == "kup") $scope.zakupy[index].stan="kupione"; 
    		else $scope.zakupy[index].stan="kup";;
    	$http.put("http://localhost:8080/lista/"+$scope.zakupy[index].id,$scope.zakupy[index]).success(function (data)
    		{
    		console.log("Zmieniono status w id:" + $scope.zakupy[index].id)
    		}).error(function(error)
    	    {
            alert("Problem z zmianą statusu", data);
            });
    };

    
    $scope.usun = function(item)
    	{
    	//$scope.panelUsun = !$scope.panelUsun;
    	var index = $scope.zakupy.indexOf(item);
    	$http.delete("http://localhost:8080/lista/"+$scope.zakupy[index].id).success(function (data)
    			{
    			console.log("Usunięto pozycję");
    			}).error(function(error)
    			{
    			alert("Problem z usunięciem pozycji");
    			});
    	$scope.zakupy.splice(index, 1);
    	//$scope.panelUsun = !$scope.panelUsun;
    	};


});
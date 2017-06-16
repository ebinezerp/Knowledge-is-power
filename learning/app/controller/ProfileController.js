var ProfileModule=angular.module('ProfileModule',[]);

ProfileModule.controller('ProfileController',function(ProfileServices,$cookieStore,$rootScope,$scope,$route,$location){
    var prfCtrl=this;
    $rootScope.currentUser=$cookieStore.get('currentUser');
    prfCtrl.message='this is profile controller';
    prfCtrl.imageform=false;
    alert("hello");
    $rootScope.imageUrl="http://localhost:8090/project/profile/"+$rootScope.currentUser.userId+".jpg";
    prfCtrl.image={};
    $scope.tab='profile'
    this.displayTab=function(tabvalue)
    {
        alert("hello");
        $scope.tab=tabvalue;
    }


    this.imageUpload=function()
    {
        prfCtrl.image.name=$rootScope.currentUser.userId;
        console.log(prfCtrl.image);
       var resultimage= prfCtrl.post(prfCtrl.image);

        ProfileServices.imageUpload(resultimage).then
        (
            function(response)
            {
                console.log(response);
                console.log(response.status=='200')
                if(response.status=='200')
                {
                   $route.reload();
                }
            },
            function(error)
            {
                console.log(error);
            }
        )

    }

    this.editProfile=function()
    {
        $scope.tab="profile";
        console.log($rootScope.currentUser);
        ProfileServices.editProfile($rootScope.currentUser).then(

            function(response)
            {
                console.log(response);
                $cookieStore.put('currentUser',response.data);
                
            },function(error)
            {
                console.log(error);
            }
        )
    }
    
    this.changeImage=function()
    {
         prfCtrl.imageform=true;
    }


    this.post=function(data)
	{
		
		 
		var fd=new FormData();
		for(var key in data)
		{
			
			fd.append(key, data[key]);
		}
		
		return fd;
	}
})

ProfileModule.directive('errSrc', function() {
  return {
    link: function(scope, element, attrs) {
      element.bind('error', function() {
        if (attrs.src != attrs.errSrc) {
          attrs.$set('src', attrs.errSrc);
        }
      });
    }
  }
});


JobModule.directive('fileModel',function($parse){
	return{
		restrict:'A',
		link:function(scope,element,attrs)
		{
			console.log(attrs);
			console.log(scope);
			console.log(element);
             console.log('before'+attrs.fileModel);

			 //$parse is the service which converts the data into function
			var model=$parse(attrs.fileModel);
			console.log('model::'+model);
			console.log(model.assign);
			var modelSetter=model.assign;
             // this is the function which invokes when the element status changes
			element.bind('change',function(){
				console.log(element);
				scope.$apply(function(){
					// this is the function that sets the file to the element 
                    modelSetter(scope,element[0].files[0]);
					console.log(modelSetter(scope,element[0].files[0]));

				})
			})
		}
	}
})
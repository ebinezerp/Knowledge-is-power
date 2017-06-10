var ProfileModule=angular.module('ProfileModule',[]);

ProfileModule.controller('ProfileController',function(ProfileServices,$cookieStore,$rootScope,$scope){
    var prfCtrl=this;
    $rootScope.currentUser=$cookieStore.get('currentUser');
    prfCtrl.message='this is profile controller';
    $scope.tab='profile'
    this.displayTab=function(tabvalue)
    {
        alert("hello");
        $scope.tab=tabvalue;
    }


    this.profilePic=function()
    {
        var stdId=$rootScope.currentUser.userId;
        ProfileServices.profilePic(stdId).this
        {

        }

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
})

this.changeImage=function()
{
    
}


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
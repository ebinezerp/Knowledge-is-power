var JobModule=angular.module('JobModule',[]);
JobModule.controller('JobController',function(multipartFile,$location,$rootScope,$route){
    this.message="this is job controler";
    this.job={};
   var jobCtrl=this;
   $rootScope.alljobsdiv=true;
   $rootScope.newjob=false;
  this.createjob=function()
    {
        alert('hello new job');
       $rootScope.newjob=true;
	    $rootScope.alljobsdiv=false;
    }

    this.allBlogs=function()
	{
		$rootScope.newjob=false;
	    $rootScope.alljobsdiv=true;

	}
   multipartFile.alljobs().then(
	   function(response)
	   {
            console.log(response);
			console.log('controller');
			$rootScope.jobs=response.data;
	   },
	   function(error)
	   {
            console.log(error);
			console.log('error');
	   }
   );
    this.addJob=function()
    {
		var uploadUrl='uploadlogo';
        console.log(jobCtrl.job);
	var result=	multipartFile.post(jobCtrl.job);
		console.log(result);

		multipartFile.httpPost(uploadUrl,result,jobCtrl).then(
			function(response){
				console.log(response);
				console.log('Testing result');
				$rootScope.newjob=false;
				$route.reload();
                //$location.path("/admin");
				
			},
			function(error)
			{
				console.log(error);

			}
		)

    }
})




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



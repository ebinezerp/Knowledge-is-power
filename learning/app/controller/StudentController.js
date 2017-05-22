var StudentModule=angular.module('StudentModule',[]);
StudentModule.controller('StudentController',function(StudentServices){
    this.message='student controller';
    var stdCtrl=this;
    stdCtrl.tab='jobsdiv';
    this.view=function(tabvalue)
    {
        stdCtrl.tab=tabvalue;
    }

   
    this.alljobs=function()
    {
        StudentServices.alljobs().then(
            
                function(response){
                    console.log(response);

                    console.log('controller');
                    stdCtrl.jobs=response.data;
                },
                function(error)
                {
                    console.log(error);
                }
            
        )
    }
     this.alljobs();
})
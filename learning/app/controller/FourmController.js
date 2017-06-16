var FourmModule=angular.module('FourmModule',[]);

FourmModule.controller('FourmController',function(FourmServices)
{
   var fourmCtrl=this;
   this.allFourmsDiv=true;
   this.newFourmDiv=false;
   this.allFourmDiv=function()
   {
     fourmCtrl.allFourmsDiv=true;
     fourmCtrl.newFourmDiv=false;
   }
   this.newFourm=function()
   {
       fourmCtrl.newFourmDiv=true;
       fourmCtrl.allFourmsDiv=false;
   }

    this.fourm={}

   this.postFourm=function()
   {
        console.log(fourmCtrl.fourm);
        FourmServices.postFourm(fourmCtrl.fourm).then(
          function(response)
          {
            console.log(response);
            fourmCtrl.allFourmsDiv=true;
            fourmCtrl.newFourmDiv=false;
            fourmCtrl.allFourms=response.data;
          },function(error)
          {
            console.log(error);
          }
        )
   }


   this.allFourmsFun=function()
   {
      FourmServices.allFourmsFun().then(
        function(success)
        {
          console.log(success);
          fourmCtrl.allFourms=success.data;
        },
        function(error)
        {
            console.log(error);

        }
      )
   }


   this.allFourmsFun();
})
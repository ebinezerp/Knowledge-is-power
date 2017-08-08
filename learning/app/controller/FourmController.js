var FourmModule=angular.module('FourmModule',[]);

FourmModule.controller('FourmController',function(FourmServices,$cookieStore)
{
   var fourmCtrl=this;
   this.fourmTab='allfourmsdiv'
   this.newFourmDiv=false;
  this.currentUser=$cookieStore.get('currentUser');

   this.allFourmDiv=function()
   {
     this.fourmTab='allfourmsdiv'
     fourmCtrl.newFourmDiv=false;
   }
   this.newFourm=function()
   {
       fourmCtrl.newFourmDiv=true;
       this.fourmTab=''
   }

    this.fourm={}

   this.postFourm=function()
   {
        console.log(fourmCtrl.fourm);
        FourmServices.postFourm(fourmCtrl.fourm).then(
          function(response)
          {
            console.log(response);
            this.fourmTab='allfourmsdiv'
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


   this.getFourm=function(fourmId)
   {
     fourmCtrl.fourmTab='forumdiv'

     FourmServices.getFourm(fourmId).then
     (
       function(success)
       {
         fourmCtrl.pfourm=success.data;
         if(fourmCtrl.currentUser.userName!='admin')
          {
          fourmCtrl.getPermission(fourmId,fourmCtrl.currentUser.userId)
          }

       },
        function(error)
        {
          console.log(error);
        }
     )
   }


   this.getPermission=function(fourmId,userId)
   {
     FourmServices.getPermission(fourmId,userId).then
     (
       function(success)
       {
         console.log(success);
         fourmCtrl.post.permission=success.data;
       },
        function(error)
        {
          console.log(error);
        }
     )
   }
})
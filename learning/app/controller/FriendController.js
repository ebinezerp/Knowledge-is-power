var FriendModule=angular.module('FriendModule',[]);

FriendModule.controller('FriendController',function(FriendServices,$rootScope,$cookies,$cookieStore)
{
    this.message="this is friend controller";
  var  frndCtrl=this;
   this.tab='suggested'
    this.allUsers={}
    frndCtrl.friendShip={};
    frndCtrl.currentUser=$cookieStore.get("currentUser");
    this.suggestedFriends=function(userId)
    {
         FriendServices.suggestedFriends(userId).then
         (
             function(success)
             {
                 console.log("suggested friends::::"+success);
                 $cookieStore.put('allUsers',success.data);
                 frndCtrl.allUsers=success.data;

             },
             function(error)
             {
                 console.log(error);
             }
         )
    }

     this.getFriends=function()
     {
        FriendServices.getFriends(frndCtrl.currentUser).then(

            function(response)
            {
                console.log("friends:::::"+response);
                frndCtrl.friends=response.data;

            },
            function(error)
            {
                console.log(error);
            }
        )
     }

     this.getFriendRequests=function()
     {
          FriendServices.getFriendRequests(frndCtrl.currentUser.userId).then(
              function(response)
              {
                  console.log("friends Requests:::::::::::::::"+response.data);
                  frndCtrl.frndRequests=response.data;
              },
              function(error)
              {
                  console.log(error);
              }
          )
     }

    this.suggestedFriends(frndCtrl.currentUser.userId);
    this.getFriends();
    this.getFriendRequests();

    this.addFriend=function(friendid)
    {
        alert(friendid);
       
       angular.forEach(frndCtrl.allUsers,function(value,key)
       {
           console.log(value.userId==friendid)
           if(value.userId==friendid)
           {
               frndCtrl.friend=value;
           }
       });
       frndCtrl.friendShip.userId=frndCtrl.currentUser.userId;
       frndCtrl.friendShip.friendId=friendid;
       FriendServices.addFriend(frndCtrl.friendShip).then(

           function(response)
           {
               console.log(response);
           },function(error)
           {
               console.log(error);
           }
       )
       
    }

    this.selectTab=function(tabvalue)
    {
        frndCtrl.tab=tabvalue;
    }

    this.updateStat=function(id,status)
    {
        
    }

    this.updateStatus=function(friendId,status)
    {
        console.log(friendId+"   "+status);

        FriendServices.updateStatus(frndCtrl.currentUser.userId,friendId,status).then(
            function(response)
            {
                console.log(response);
                frndCtrl.frndRequests=response.data;
            },
            function(error)
            {
                console.log(error);
            }
        )
    }
})
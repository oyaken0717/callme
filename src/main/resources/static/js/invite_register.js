$(function() {
//■ ajaxでユーザー候補を取得する。
	$('#searchUser').on("keyup", function() {
		
		var hostUrl = 'http://localhost:8080/api-user-search/find-by-like-name-and-user-id-and-group-id';
		var name = $("#searchUser").val();    
		var userId = $("#userId").val();    
		var groupId = $("#groupId").val();    

//■ ajaxーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー			
		$.ajax({
			url : hostUrl,
			type : 'POST',
//			dataType : 'json', ■ userListがnullの場合 > 強制的にエラーになる > ここをコメントアウトする > なんか上手くいく
			data : {
				 name : name,
				 userId : userId,
				 groupId : groupId				 
			},
			async: true		
//■ doneーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー			
		}).done(function(data) {
//			console.log(data);
			console.dir(JSON.stringify(data));
			
			//■ #addSearchUserを検索前の状態に戻す。> 「.empty()」> 子要素のみ消す。
			$("#addSearchUser").empty();
			
	    	//■ 「招待するユーザー」を配列に入れる。
			var inviteUsers = [];
			var inviteUsers = $(".inviteUser");
			
			//■ 「招待するユーザー」と「検索結果のユーザー」が重複しないようにする。
			if (inviteUsers.length >= 1) {
//				https://www.it-swarm.dev/ja/javascript/2%E3%81%A4%E3%81%AEjavascript%E9%85%8D%E5%88%97%E3%82%92%E6%AF%94%E8%BC%83%E3%81%97%E3%81%A6%E9%87%8D%E8%A4%87%E3%82%92%E5%89%8A%E9%99%A4%E3%81%99%E3%82%8B/1071091800/
				inviteUsers = inviteUsers.filter(function(inviteUser) {
					return data.indexOf( Number(inviteUser.value) ) != -1;
				});

				//■ 検索にマッチしたユーザーを表示する。
				$.each(data, function(index,user) {				
					$("#addSearchUser").append("<div class=\"addUser\" ><input class=\"childAddUser\" type=\"hidden\" value="+user.userId+">" + user.name +"</input></div>");				
				});			
				
			//■ ifの終わり
			} else {					
				//■ 検索にマッチしたユーザーを表示する。
				$.each(data, function(index,user) {				
					$("#addSearchUser").append("<div class=\"addUser\" ><input class=\"childAddUser\" type=\"hidden\" value="+user.userId+">" + user.name +"</input></div>");				
				});				
			}
//■ failーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー			
		}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
			alert("エラーが発生しました！");
			console.log("XMLHttpRequest : " + XMLHttpRequest.status);
			console.log("textStatus     : " + textStatus);
			console.log("errorThrown    : " + errorThrown.message);
		});
	});

//■ ユーザー一覧から招待候補を確定する。
//	$().on();では後から追加されたHTMLについてはクリックイベントが発動しない
    $(document).on("click", ".addUser", function() {	
    	var userName = $(this).text();
    	var userId = $(this).find(".childAddUser").val();
    	
    	$(".invite-list").append("<div class=\"candidateUser\"><input type=\"hidden\" class=\"inviteUser\" name=\"userList\" value="+userId+">"+userName+"</div>");
    	$(this).remove();
    });

//■ やっぱり招待しない。
    $(document).on("click",".candidateUser", function() {	
    	$(this).remove();
    });

});


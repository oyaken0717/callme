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
				var temporaryDatas = [];
				var count = data.length;				
				for (let i = 0; i < count; i++) {	
					for (const inviteUser of inviteUsers) {
						if ( Number(data[i].userId) != Number(inviteUser.value) ) {
							temporaryDatas.push(data);
						}
					//■ 内側のforの終わり
					}	
				//■ 外側のforの終わり
				}
				var data = [];
				for (const temporaryData of temporaryDatas) {
					data.push(temporaryData);
				}
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
    $(document).on("click", ".addUser", function() {	
    	var userName = $(this).text();
    	var userId = $(this).find(".childAddUser").val();
    	
    	$(".invite-list").append("<div><input type=\"hidden\" class=\"inviteUser\" name=\"userList\" value="+userId+">"+userName+"</div>");
    	$(this).remove();
    });

});


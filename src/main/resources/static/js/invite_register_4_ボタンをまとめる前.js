$(function() {
//■ ajaxでユーザー候補を取得する。
	$('#searchUser').on("keyup", function() {
		
		var hostUrl = 'http://localhost:8080/api-user-search/find-by-like-name-and-user-id-and-group-id';
		var name = $("#searchUser").val();    

console.log(name);
		
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

			function setTemplate(user) {
				var template = 
					`<tr class=\"row\">
						<td class=\"addUser col-9 \">
							<input class=\"childAddUser\" type=\"hidden\" value=\"${user.userId}\">
								${user.name}
							</input>
						</td>
						
						<td class=\"col-3  \">
							<div class=\"childAddUser btn btn-primary btn-sm \">
								招待
							</div>
						</td>
					</tr>`
				;
				return template;	
			};
				
			//■ 「招待するユーザー」と「検索結果のユーザー」が重複しないようにする。
			if (inviteUsers.length >= 1) {
//				https://www.it-swarm.dev/ja/javascript/2%E3%81%A4%E3%81%AEjavascript%E9%85%8D%E5%88%97%E3%82%92%E6%AF%94%E8%BC%83%E3%81%97%E3%81%A6%E9%87%8D%E8%A4%87%E3%82%92%E5%89%8A%E9%99%A4%E3%81%99%E3%82%8B/1071091800/
				
                var added_user_ids = {};
                inviteUsers.each(function(index, inviteUser) {
                    added_user_ids[inviteUser.value] = 1;
                })

				//■ 検索にマッチしたユーザーを表示する。
				$.each(data, function(index,user) {
					var template = setTemplate(user);
					if (!added_user_ids[user.userId]) {
						$("#addSearchUser").append(template);				
					}
				});			
				
			//■ ifの終わり
			} else {					
				//■ 検索にマッチしたユーザーを表示する。
				$.each(data, function(index,user) {				
					var template = setTemplate(user);
					$("#addSearchUser").append(template);
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

//■ ユーザー候補一覧から招待候補を確定する。
//	$().on();では後から追加されたHTMLについてはクリックイベントが発動しない
    $(document).on("click", ".addUser", function() {	
    	var userName = $(this).text();
    	var userId = $(this).find(".childAddUser").val();
    	
    	$(".invite-list").append("<tr><td class=\"candidateUser mt-4\"><input type=\"hidden\" class=\"inviteUser\" name=\"userList\" value="+userId+">"+userName+"</input></td></tr>");    	
    	$(this).remove();
    });

//■ やっぱり招待しない。
    $(document).on("click",".candidateUser", function() {	
    	$(this).remove();	
    });

});


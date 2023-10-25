<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook-ajax.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>

var flag = false;
var sno = 0;

$(function() {
	fetch(sno);
	
	var dialogDelete = $("#dialog-delete-form").dialog({
		autoOpen: false,
		model: true,
		buttons: {
			"삭제": function() {
				// var no, var password 를 빼오는게 문제임
				
				var no =$('#hidden-no').val();
				var password = $('#password-delete').val();
				//console.log("ajax 삭제하는 걸 여기서 ...");
				
				$.ajax({
					url: '${pageContext.request.contextPath }/api/guestbook/' + no,
					type: 'delete',
					dataType: 'json',
					contentType: 'application/x-www-form-urlencoded',
					data: 'password=' + password,
					success: function(response) {
						if(response.result === 'fail') {
							console.error(response.message);
							return;
						}
						
						if(response.data.password === password) {
							// 삭제
							var er = document.querySelector('#list-guestbook li[data-no="'+no+'"]');
							er.remove();
							$('#validateTips-error').hide();
							dialogDelete.dialog('close');
							console.log($(this));
						}else {
							// input data 삭제하고 비번 틀렸다 하기
							$('#password-delete').val('');
							$('#validateTips-error').show();
						}
					}
				})
				// 후처리
				//1. response.data 가지고 있는 <li data+no='{no}' > 찾아서 삭제
				
				//2. dialogDelete.dialog('close');
				
				//3. 폼의 input reset 해주기
				
			},
			"취소": function() {
				$(this).dialog('close');
				$('#validateTips-error').hide();
			}
		},
		close: function() {
			//console.log("야 다이알로그 꺼졌다 이제 정리 ㄱㄱ");
			$('#password-delete').val('');
		}
	});
	
	// 댓글 삭제 버튼 click 이벤트 처리 dom 이 있는 상태에서 찾아서 해야하는데
	// fetch() 통신이라서 생기기 전에 즉 dom 이 다 생성되기 전에 해버릴 가능성이 큼
	// -> Live Event 해야 함 -> document 한테 click 이벤트 위임해 보리기
	/*
	$("#list-guestbook li a").click(function(event) {
		event.preventDefault();
		console.log("clicked");
	})
	*/
	
	$(document).on('click', '#list-guestbook li a',function() {
		event.preventDefault();
		
		$('#hidden-no').val($(this).data('no'));
		
		console.log($(this).data('no'));
		dialogDelete.dialog('open');
		// ajax 로 조져 !!!!!
		// 근데 어떻게 해 
	});
	
	$('#add-form').submit(function(event) {
		event.preventDefault();
		
		var vo = {};
		vo.name = $("#input-name").val();
		vo.password = $("#input-password").val();
		vo.contents = $("#tx-content").val();
		
		$.ajax({
			url: '${pageContext.request.contextPath }/api/guestbook',
			type: 'post',
			dataType: 'json',
			contentType: 'application/json',
			data: JSON.stringify(vo),
			success: function(response) {
				if(response.result === 'fail') {
					console.error(response.message);
					return;
				}
				render(response.data, true);
			}
		})
		$('#input-name').val('');
		$('#input-password').val('');
		$('#tx-content').val('');
	});
	
	$(window).scroll(function() {
		
		if(flag || sno === -1) {
			return;
		}
		
		var $window = $(this);
		var $document = $(document);
		
		var wh = $window.height();
		var dh = $document.height();
		var st = $window.scrollTop();
		
		if(dh < wh + st + 10) {
			flag = true;
			fetch(sno);
		}
	})
});

var render = function(vo, mode) {
	var html =
		"<li data-no='"+ vo.no + "'>" +
		"<strong>" + vo.name + "</strong>" +
		"<p>" + vo.contents + "</p>" +
		"<strong></strong>" +
		"<a href='#' data-no='"+ vo.no + "'>삭제</a>" +
		"</li>"
	$("#list-guestbook")[mode ? 'prepend' :'append'](html);
}

var fetch = function() {
	//console.log(sno);
	$.ajax({
		url: "${pageContext.request.contextPath }/api/guestbook/"+sno,
		type: "get",
		dataType: "json",
		success: function(response) {
			if(response.result === 'fail') {
				console.error(response.message);
				return;
			}
			
			if(!response.data.length){
				sno = -1;
				return;
			}
			
			response.data.forEach(function(vo) {
				render(vo, false);
			});
			
		}
	})
	sno += 5;
	flag = false;
}
</script>

</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" placeholder="이름">
					<input type="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook"></ul>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display:none">
  				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p id="validateTips-error" style="display:none; color:red">비밀번호가 틀립니다.</p>
  				<form>
 					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" value="">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
  				</form>
			</div>
			<div id="dialog-message" title="" style="display:none">
  				<p></p>
			</div>						
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>
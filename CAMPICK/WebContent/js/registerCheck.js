/**
 * 
 */
  function register(){
        	var joinForm = document.joinForm;
        	var pw = joinForm.user_pw.value;
        	var pw2 = joinForm.user_pw2.value;
        	//var idCheck = joinForm.idDuplication.value;
/*        	if(joinForm.idDuplication.value!="idOk"){
        		alert('아이디 중복 체크를 해주세요.');
        		joinForm.pw2.focus();
        	}
        	else */if(pw!=pw2){
        		alert('비밀번호를 다시 입력해 주세요.');
        		joinForm.pw2.focus();
        	}
        	else{
            joinForm.method="post";
            joinForm.action="user.do?action=register";
        	}
        }

        function idCheck(){
            alert("id 중복입니다. 다시 입력!");
        }
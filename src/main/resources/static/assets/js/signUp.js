// 유효성 검증 관련 함수들 임포트
// 디스트럭처링을 통해 특정 js 파일에서 원하는 함수만 가져와서 쓸라고.
import { validateInput } from './validation.js';
import { debounce } from './util.js';

// 폼과 회원가입 버튼 요소를 가져옴
const form = document.getElementById('signUpForm'); // 회원가입 폼
const signupButton = document.getElementById('signup-btn'); // 회원가입 버튼

// 각 필드에 대한 정보 배열 (id, 유효성 검증 함수, 에러 메시지 표시 요소, 초기 유효 상태)
const fields = [
  {
    id: 'user_id', // 진짜 input가 가지고 있는 id
    validator: validateInput.account, // 유효하지 않으면 valid:false, message: 상황에 맞는 메세지
    errorElement: 'idChk', // 입력값 유효성 검증에 문제 발생 시 메세지를 남길 span의 id
    valid: false, // 최종 검증 상태
  },
  {
    id: 'password',
    validator: validateInput.password,
    errorElement: 'pwChk',
    valid: false,
  },
  {
    id: 'password_check',
    validator: (value) =>
      validateInput.passwordCheck(
        value,
        document.getElementById('password').value
      ),
    errorElement: 'pwChk2',
    valid: false,
  },
  {
    id: 'user_name',
    validator: validateInput.name,
    errorElement: 'nameChk',
    valid: false,
  },
  {
    id: 'user_email',
    validator: validateInput.email,
    errorElement: 'emailChk',
    valid: false,
  },
];

// 버튼 상태를 업데이트하는 함수
const updateButtonState = () => {
  // 모든 valid가 true인지 확인
  // every 배열 고차 함수: 배열 내의 데이터를 순회하면서 특정 값이 모두 true인지를 확인.
  const isFormValid = fields.every((field) => field.valid);

  if (isFormValid) {
    // 모든 입력창이 다 true임.
    signupButton.disabled = false; // 버튼 비활성화를 풀어주자.
    signupButton.style.backgroundColor = 'orangered'; // 활성화된 버튼 배경색
  } else {
    // 5개 중 하나라도 valid가 false라면
    signupButton.disabled = true;
    signupButton.style.backgroundColor = 'lightgray'; // 비활성화된 버튼 배경색
  }
};

// 각 필드에 대해 입력값 검증 이벤트 리스너를 추가 (반복문)
fields.forEach((field) => {
  const $input = document.getElementById(field.id); // 입력 요소 가져오기

  // 일괄 이벤트 등록 처리
  $input.addEventListener(
    'keyup',
    debounce(async (e) => {
      // 키보드 입력 시마다 유효성 검증
      // 0.5초 이내에 새로운 입력 들어오면 기존의 timeout 취소를 debounce로 제어.
      const isValid = await field.validator($input.value); // 유효성 검증 함수 호출
      const $errorSpan = document.getElementById(field.errorElement); // 에러 메시지 표시 요소 가져오기
      console.log(isValid);

      if (isValid.valid) {
        // 유효한 경우
        $input.style.borderColor = 'skyblue'; // 입력 요소의 테두리 색 변경
        $errorSpan.innerHTML = '<b class="success">[사용가능합니다.]</b>'; // 성공 메시지 표시
        field.valid = true; // 필드의 유효 상태를 true로 설정
      } else {
        // 유효하지 않은 경우
        $input.style.borderColor = 'red'; // 입력 요소의 테두리 색 변경
        $errorSpan.innerHTML = `<b class="warning">[${isValid.message}]</b>`; // 에러 메시지 표시
        field.valid = false; // 필드의 유효 상태를 false로 설정
      }
      updateButtonState(); // 각 입력 유효성 검사 후 버튼 상태 업데이트
    }, 500)
  );
});

// 회원가입 버튼 클릭 이벤트 리스너 추가
signupButton.addEventListener('click', (e) => {
  // 모든 필드가 유효한지 확인
  const isFormValid = fields.every((result) => result.valid);
  if (isFormValid) {
    // 모든 필드가 유효한 경우
    form.submit(); // 폼 제출
  } else {
    // 유효하지 않은 필드가 있는 경우
    alert('입력란을 다시 확인하세요!'); // 경고 메시지 표시
  }
});

// 디바운싱 함수 정의
// wait초 만큼 시간을 지연. 중간에 입력이 들어온다면 clearTimeout을 통해 취소.
export function debounce(callback, wait) {
  let timeout;
  return (...args) => {
    clearTimeout(timeout);
    timeout = setTimeout(() => callback(...args), wait);
  };
}

console.log('attendance.js');

calendar()
function calendar(){
         // 달력 생성 함수
          function createCalendar(year, month) {
            // 현재 날짜 정보 가져오기
            var today = new Date();
            var currentYear = today.getFullYear();
            var currentMonth = today.getMonth() + 1;
            var currentDay = today.getDate();

            // 이전 달과 다음 달을 계산
            var prevMonth = new Date(year, month - 1, 0);
            var currentMonth = new Date(year, month, 0);
            var nextMonth = new Date(year, month + 1, 0);

            // 달력 헤더 생성
            var calendarHTML = '<table class="calendar">';
            calendarHTML += '<tr><th colspan="7">' + currentMonth.getFullYear() + '년 ' + (currentMonth.getMonth() + 1) + '월</th></tr>';
            calendarHTML += '<tr><th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th></tr>';

            // 달력 바디 생성
            calendarHTML += '<tr>';
            var day = 1;
            for (var i = 0; i < 42; i++) {
              if (i < currentMonth.getDay() || day > currentMonth.getDate()) {
                calendarHTML += '<td></td>'; // 이전 달이나 다음 달의 빈 칸 생성
              } else {
                if (year === currentYear && month === currentMonth.getMonth() + 1 && day === currentDay) {
                  calendarHTML += '<td class="today">' + day + `<div class="event-text">${r[0].title}</div></td>`; // 오늘 날짜일 경우 강조

                } else {
                  calendarHTML += '<td>' + day + '<div class="event-text">이벤트</div></td>'; // 현재 달의 날짜 생성
                }
                day++;
              }
              if ((i + 1) % 7 === 0) {
                calendarHTML += '</tr>';
                if (day > currentMonth.getDate()) break; // 현재 달의 마지막 날짜에 도달하면 루프 종료
                calendarHTML += '<tr>';
              }
            }
            calendarHTML += '</table>';

            // HTML에 달력 삽입
            document.getElementById('calendar').innerHTML = calendarHTML;
          }

          // 현재 날짜를 기준으로 달력 생성
          var currentDate = new Date();
          createCalendar(currentDate.getFullYear(), currentDate.getMonth() + 1);

}
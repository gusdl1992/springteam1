console.log('fullcalendar.js');
document.addEventListener('DOMContentLoaded', function() {
          var calendarEl = document.getElementById('calendar');

          var calendar = new FullCalendar.Calendar(calendarEl, {
            initialDate: new Date(),
            locale: "ko" ,
            editable: false,
            selectable: true,
            businessHours: true,
            dayMaxEvents: true, // allow "more" link when too many events
            events: [

            ]
          });

          calendar.render();
        });



        /*
            이벤트 방법 예제
        {
                        title: 'All Day Event',
                        start: '2024-03-01'
                      },
                      {
                        title: 'Long Event',
                        start: '2024-03-07',
                        end: '2024-07-10'
                      },
                      {
                        groupId: 999,
                        title: 'Repeating Event',
                        start: '2024-03-09T16:00:00'
                      },
                      {
                        groupId: 999,
                        title: 'Repeating Event',
                        start: '2024-03-16T16:00:00'
                      },
                      {
                        title: 'Conference',
                        start: '2024-03-11',
                        end: '2024-03-13'
                      },
                      {
                        title: '출근',
                        start: '2024-03-12T10:30:00',
                        end: '2024-03-12T12:30:00'
                      },
                      {
                        title: 'Lunch',
                        start: '2024-03-12T12:00:00'
                      },
                      {
                        title: 'Meeting',
                        start: '2024-03-12T14:30:00'
                      },
                      {
                        title: 'Happy Hour',
                        start: '2024-03-12T17:30:00'
                      },
                      {
                        title: 'Dinner',
                        start: '2024-03-12T20:00:00'
                      },
                      {
                        title: 'Birthday Party',
                        start: '2024-03-13T07:00:00'
                      },
                      {
                        title: 'Click for Google',
                        url: 'http://google.com/',
                        start: '2024-03-28'
                      }



        */
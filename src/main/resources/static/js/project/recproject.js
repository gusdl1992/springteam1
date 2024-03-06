

function onWrite(){
    console.log("onWrite()")
    let pjno = document.querySelector("#pjno").value
    let list1Elements = document.querySelectorAll(".list1");
    let list1 = Array.from(list1Elements).map(element => element.value);
    let list2Elements = document.querySelectorAll(".list2");
    let list2 = Array.from(list2Elements).map(element => element.value);
    let list3Elements = document.querySelectorAll(".list3");
    let list3 = Array.from(list3Elements).map(element => element.value);
    console.log(pjno)
    console.log(list1)
    $.ajax({
        url : "/project/view/assign",
        method : "post",
        traditional : true,
        data :{pjno:pjno,list1:list1,list2:list2,list3:list3},
        success: (r) => {
        console.log(r)
        }

    })
}
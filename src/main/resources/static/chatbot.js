var infoUser = [];
var j = 1;
var question = [
    "Nhập tuổi của bé (Từ 1 - 6 tuổi)",
    "Nhập chiều cao của bé (đơn vị : cm)",
    "Nhập cân nặng của bé (đơn vị : kg)",
    "Nhập mức độ hoạt động (Hoạt động thể chât ở cường độ trung bình bao gôm: " +
    "Thể dục, thể thao, chạy nhảy...) : " +
    "<br>1. Hoạt động dưới 1 tiếng 1 ngày" +
    "<br>2. Hoạt động từ 1 - 3 tiếng 1 ngày" +
    "<br>3. Hoạt động trên 3 tiếng 1 ngày",
    "Sai cú pháp !!! Gõ OK để tiếp tục",
    "Bot đang tính toán dữ liệu của bé ...",
    "Nhập OK để tiếp tục tư vấn",
];
function responBot() {
    switch (j) {
        case 1:
            checkStart();
            break;
        case 2:
            checkAge();
            break;
        case 3:
            checkHeight();
            break;
        case 4:
            checkWeight();
            break;
        case 5:
            checkLevel();
            break;
        case 6:
            repeat();
            break;
    }

    j += 1;
    var message = (document.getElementById("message").value = "");
}

function checkStart() {
    var info = document.getElementById("message").value;
    var messageDisplay = document.getElementById("messageDisplay");
    var messageContainer = document.createElement("div");
    messageContainer.setAttribute("id", "messageContainer");
    var userMessage = document.createElement("div");
    userMessage.setAttribute("class", "chat userMessage");
    messageContainer.appendChild(userMessage);
    messageDisplay.appendChild(messageContainer);
    userMessage.innerHTML = info;

    if (info == "OK" || info == "ok") {
        var botMessage = document.createElement("div");
        botMessage.setAttribute("class", "chat botMessage");
        botMessage.innerHTML = question[0];
        messageDisplay.appendChild(botMessage);
    } else {
        var botMessage = document.createElement("div");
        botMessage.setAttribute("class", "chat botMessage");
        botMessage.innerHTML = question[4];
        messageDisplay.appendChild(botMessage);
        j -= 1;
    }
}

function checkAge() {
    var age = document.getElementById("message").value;
    var messageDisplay = document.getElementById("messageDisplay");
    var messageContainer = document.createElement("div");
    messageContainer.setAttribute("id", "messageContainer");
    var userMessage = document.createElement("div");
    userMessage.setAttribute("class", "chat userMessage");
    messageContainer.appendChild(userMessage);
    messageDisplay.appendChild(messageContainer);
    userMessage.innerHTML = age;

    if (age >= 1 && age <= 6) {
        infoUser[0] = age;
        var botMessage = document.createElement("div");
        botMessage.setAttribute("class", "chat botMessage");
        botMessage.innerHTML = question[1];
        messageDisplay.appendChild(botMessage);
    } else {
        var botMessage = document.createElement("div");
        botMessage.setAttribute("class", "chat botMessage");
        botMessage.innerHTML = "Tuổi của trẻ phải ở trong khoảng 1-6 tuổi";
        messageDisplay.appendChild(botMessage);
        j -= 1;
    }
}

function checkHeight() {
    var height = document.getElementById("message").value;
    var messageDisplay = document.getElementById("messageDisplay");
    var messageContainer = document.createElement("div");
    messageContainer.setAttribute("id", "messageContainer");
    var userMessage = document.createElement("div");
    userMessage.setAttribute("class", "chat userMessage");
    messageContainer.appendChild(userMessage);
    messageDisplay.appendChild(messageContainer);
    userMessage.innerHTML = height;

    if (height > 0) {
        infoUser[1] = height;
        var botMessage = document.createElement("div");
        botMessage.setAttribute("class", "chat botMessage");
        botMessage.innerHTML = question[2];
        messageDisplay.appendChild(botMessage);
    } else {
        var botMessage = document.createElement("div");
        botMessage.setAttribute("class", "chat botMessage");
        botMessage.innerHTML = "Chiều cao không được bằng 0 hoặc âm";
        messageDisplay.appendChild(botMessage);
        j -= 1;
    }
}

function checkWeight() {
    var weight = document.getElementById("message").value;
    var messageDisplay = document.getElementById("messageDisplay");
    var messageContainer = document.createElement("div");
    messageContainer.setAttribute("id", "messageContainer");
    var userMessage = document.createElement("div");
    userMessage.setAttribute("class", "chat userMessage");
    messageContainer.appendChild(userMessage);
    messageDisplay.appendChild(messageContainer);
    userMessage.innerHTML = weight;

    if (weight > 0) {
        infoUser[2] = weight;
        var botMessage = document.createElement("div");
        botMessage.setAttribute("class", "chat botMessage");
        botMessage.innerHTML = question[3];
        messageDisplay.appendChild(botMessage);
    } else {
        var botMessage = document.createElement("div");
        botMessage.setAttribute("class", "chat botMessage");
        botMessage.innerHTML = "Cân nặng không được bằng 0 hoặc âm";
        messageDisplay.appendChild(botMessage);
        j -= 1;
    }
}

function checkLevel() {
    var level = document.getElementById("message").value;
    var messageDisplay = document.getElementById("messageDisplay");
    var messageContainer = document.createElement("div");
    messageContainer.setAttribute("id", "messageContainer");
    var userMessage = document.createElement("div");
    userMessage.setAttribute("class", "chat userMessage");
    messageContainer.appendChild(userMessage);
    messageDisplay.appendChild(messageContainer);
    userMessage.innerHTML = level;

    if (level == 1 || level == 2 || level == 3) {
        infoUser[3] = level;
        var botMessage = document.createElement("div");
        botMessage.setAttribute("class", "chat botMessage");
        botMessage.innerHTML = question[5];
        messageDisplay.appendChild(botMessage);
        infoUser[1] = infoUser[1] / 100;
        infoUser[1] = infoUser[2] / (infoUser[1] * infoUser[1]);
        var sendData = {
            age: infoUser[0],
            bmi: infoUser[1].toFixed(1),
            activity_level: infoUser[3],
        };
        var sent = JSON.stringify(sendData);
        console.log(sent);
        $.ajax({
            url: "http://localhost:8080/find-solution",
            type: "POST",
            data:  sent ,
            dataType: "json",
            contentType: "application/json",
            error: function(e) {
                console.log(e);
            },
            success: function (reiObj) {
                var botMessage = document.createElement("div");
                botMessage.setAttribute("class", "chat botMessage");
                botMessage.innerHTML = `Trạng thái của trẻ: ${reiObj.status} (${sendData["bmi"]})`;
                messageDisplay.appendChild(botMessage);
                var botMessage = document.createElement("div");
                botMessage.setAttribute("class", "chat botMessage");
                botMessage.innerHTML = reiObj.energy_requirement;
                messageDisplay.appendChild(botMessage);
                var botMessage = document.createElement("div");
                botMessage.setAttribute("class", "chat botMessage");
                botMessage.innerHTML = "Thực đơn gợi ý: " + reiObj.menu_suggestion;
                messageDisplay.appendChild(botMessage);
                var botMessage = document.createElement("div");
                botMessage.setAttribute("class", "chat botMessage");
                botMessage.innerHTML = question[6];
                messageDisplay.appendChild(botMessage);
            },
        });
    } else {
        var botMessage = document.createElement("div");
        botMessage.setAttribute("class", "chat botMessage");
        botMessage.innerHTML = "Mức độ hoạt động phải trong khoảng từ 1 - 3";
        messageDisplay.appendChild(botMessage);
        j -= 1;
    }
}
function repeat() {
    var info = document.getElementById("message").value;
    var messageDisplay = document.getElementById("messageDisplay");
    var messageContainer = document.createElement("div");
    messageContainer.setAttribute("id", "messageContainer");
    var userMessage = document.createElement("div");
    userMessage.setAttribute("class", "chat userMessage");
    messageContainer.appendChild(userMessage);
    messageDisplay.appendChild(messageContainer);
    userMessage.innerHTML = info;

    if (info == "OK" || info == "ok") {
        var botMessage = document.createElement("div");
        botMessage.setAttribute("class", "chat botMessage");
        botMessage.innerHTML = question[0];
        messageDisplay.appendChild(botMessage);
        j = 1;
    } else {
        var botMessage = document.createElement("div");
        botMessage.setAttribute("class", "chat botMessage");
        botMessage.innerHTML = question[4];
        messageDisplay.appendChild(botMessage);
        j -= 1;
    }
}

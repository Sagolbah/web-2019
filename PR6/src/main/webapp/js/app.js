window.notify = function (message) {
    $.notify(message, {position: "bottom right"}, "success")
};

window.notifyError = function (message) {
    $.notify(message, {position: "bottom right"}, "error")
};

ajax = function (parameters, q_type, success_action, error_action) {
    if (typeof success_action === "undefined")
        success_action = function () {
        };
    $.ajax({
        dataType: "json",
        data: parameters,
        success: function (response) {
            success_action(response);
            if (response["redirect"]) {
                document.location.href = response["redirect"];
            }
        },
        error: error_action,
        type: q_type
    });
};

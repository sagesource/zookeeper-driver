jQuery(document).ready(function () {
    // Fullscreen background
    $.backstretch([
        "static/front/index/img/backgrounds/2.jpg"
        , "static/front/index/img/backgrounds/3.jpg"
        , "static/front/index/img/backgrounds/1.jpg"
    ], {duration: 3000, fade: 750});

    $('#btn_connect').click(function () {
        connectToServer();
    });

    //.......//
    getUseServerList();
});

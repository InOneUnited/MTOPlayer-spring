$("#register").find("input").on("keyup blur focus", function (e) {
    console.log("I FOUND IT!!!");
    let $this = $(this),
        label = $this.prev('label');
    console.log("this" + $this);
    if (e.type === 'keyup') {
        console.log('keyup');
        if ($this.val() === '') {
            label.removeClass('active highlight');
        } else {
            label.addClass('active highlight');
        }
    } else if (e.type === 'blur') {
        if( $this.val() === '' ) {
            label.removeClass('active highlight');
        } else {
            label.removeClass('highlight');
        }
    } else if (e.type === 'focus') {

        if( $this.val() === '' ) {
            label.removeClass('highlight');
        }
        else if( $this.val() !== '' ) {
            label.addClass('highlight');
        }
    }

});

$('#birthday-input').on('keyup blur focus', function(e){
    if(e.type === 'keyup'){
        if($(this).val() === ''){
            $(this).addClass('color-grey');
        } else {
            $(this).removeClass('color-grey');
        }
    } else if (e.type === 'blur'){
        if($(this).val() === ''){
            $(this).removeClass('color-grey');
        } else {
            $(this).removeClass('color-grey');
        }
    } else if (e.type === 'focus'){
        if($(this).val() === ''){
            $(this).addClass('color-grey');
        } else {
            $(this).removeClass('color-grey');
        }
    }
});

let pw   = $("#input-password"),
    cb   = $("#checkbox-unmask"),
    mask = true;

cb.on("click", function(){

    if(mask === true){
        mask = false;
        pw.attr("type", "text");
    } else {
        mask = true;
        pw.attr("type", "password");
    }

});

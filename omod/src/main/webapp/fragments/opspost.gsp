<script>
jQuery( document ).ready(function() {
    jQuery("#but_reset").click(function (e) {
        console.log("Clicked");
        jQuery.post("${ ui.actionLink("resetApiKey")}", {
                returnFormat: 'json',
                type: "data",
                patientId: "${patient.id}"
            },
            function (data) {
                jQuery("#apikey").val(data);
            })
            .error(function () {
                jQuery().toastmessage('showErrorToast', "Error. Try again after page refresh");
            });
    });
});
</script>

<div id="opspost-main" class="info-section opspost">
    <div class="info-header">
        <i class="icon-user-md"></i>
            <h3>API - Key <div id="apikey">${apiKey}</div></h3>
    </div>

        <a class="button" id="but_reset">
            <i class="icon-upload-alt"></i>
        </a>
</div>
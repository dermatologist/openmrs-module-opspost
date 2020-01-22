<script>
    var jq = jQuery;
    jq("#but_reset").click(function (e) {
        jq.post("${ ui.actionLink("resetApiKey")}", {
                returnFormat: 'json',
                type: "data",
                patientId: "${patient.id}"
            },
            function (data) {
                jq("#apikey").val(data);
            })
            .error(function () {
                jq().toastmessage('showErrorToast', "Error. Try again after page refresh");
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
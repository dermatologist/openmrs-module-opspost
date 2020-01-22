<script>
    var jq = jQuery;
    jq("#but_delete").click(function (e) {
        jq.post("${ ui.actionLink("resetApiKey")}", {
                returnFormat: 'json',
                type: "data",
                patientId: "${patient.id}"
            },
            function (data) {
                if(data.indexOf("${MESSAGE_SUCCESS}")>=0){
                    jq().toastmessage('showSuccessToast', "Key Reset");
                    location.reload();
                }else{
                    jq().toastmessage('showErrorToast', "Error. Try again after page refresh");
                }
            })
            .error(function () {
                jq().toastmessage('showErrorToast', "Error. Try again after page refresh");
            });
    });
</script>

<div id="dermimage-main" class="info-section dermimage">
    <div class="info-header">
        <i class="icon-user-md"></i>
            <h3>API - Key ${apiKey}</h3>
    </div>

        <a class="button" id="but_reset">
            <i class="icon-upload-alt"></i>
        </a>
</div>
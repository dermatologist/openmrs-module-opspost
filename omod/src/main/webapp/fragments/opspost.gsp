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
                console.log(data);
                jQuery("#apikey").text(data);
            });
    });
});
</script>

<div id="opspost-main" class="info-section opspost">
    <div class="info-header">
        <i class="icon-user-md"></i>
            <h3>API - Key</h3>
    </div>
        <textarea id="apikey">
        ${apiKey}
        </textarea>
        <a class="button" id="but_reset">
            <i class="icon-upload-alt"></i>
        </a>
</div>
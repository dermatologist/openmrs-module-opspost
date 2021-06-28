# openmrs-module-opspost

Openmrs-module-opspost is a module for allowing patients to post observations to their own records. This module is for use with [openmrs-suite-dermatology](https://github.com/dermatologist/openmrs-suite-dermatology) for uploading images for online dermatology consultation from a mobile app. 

## How this works

* Install this module.
* You can generate a key for any patient in the patient chart. Share the key with the patient for entering in his/her mobile app.
* Create a role that has privileges on this module ONLY.
* The mobile app sends a request as the above user with the patient’s unique key (openmrs/rest/v1/opspost/{apikey})
* If the unique key exists, this module returns a session ID with the necessary previledges (set in settings).
* Post the observation/image with the sessionID.
* A sample mobile app is available [here](https://github.com/dermatologist/skinhelpdesk-app).

## This is an experimental application that can make OpenMRS insecure.
## DO NOT USE this in production

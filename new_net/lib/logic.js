/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

'use strict';
/**
 * Write your transction processor functions here
 */


/**
 * addPlayer
 * @param {org.criotam.prototype.managementTransactions.addPlayer} addPlayer
 * @transaction
 */
async function addPlayer(playerData) {
    var NS1 = 'org.criotam.prototype.participants';
    var NS2 = 'org.criotam.prototype.managementTransactions'
    let factory = getFactory();
    let Player = factory.newResource(NS1, "Player", playerData.player_id);
    Player.player_id = playerData.player_id;
    Player.player_name = playerData.player_name;
    Player.player_age = playerData.player_age;
    Player.player_weight = playerData.player_weight;
    Player.player_height = playerData.player_height;
    Player.player_sex = playerData.player_sex;

    const assetRegistry = await getParticipantRegistry('org.criotam.prototype.participants.Player');
    await assetRegistry.add(Player);

    let event = factory.newEvent(NS2, 'addedPlayer');
    event.player_id = playerData.player_id;
    emit(event);
}


 /**
 * addScientist
 * @param {org.criotam.prototype.managementTransactions.addScientist} addScientist
 * @transaction
 */
 async function addScientist(scientistData) {
    var NS1 = 'org.criotam.prototype.participants';
    var NS2 = 'org.criotam.prototype.managementTransactions'
    let factory = getFactory();
    let Scientist = factory.newResource(NS1,"Scientist",scientistData.scientistId);
    Scientist.scientistId = scientistData.scientistId;
    Scientist.userName = scientistData.userName;
    Scientist.Email = scientistData.Email;
    Scientist.Password = scientistData.Password;
    const participantRegistry = await getParticipantRegistry('org.criotam.prototype.participants.Scientist');
    await participantRegistry.add(Scientist);

    let event = factory.newEvent(NS2, 'addedScientist');
    event.scientistId = scientistData.scientistId;
    emit(event);

 }

// Add transaction for experiment 1 load cell sensors

/**
 * add Experiment 1 lc sensor 
 * @param {org.criotam.prototype.iotTransactions.experiment1lcDataAdd} experiment1lcDataAdd
 * @transaction
 */
async function experiment1lcDataAdd(experiment1lcData) {
    const NS1 = 'org.criotam.prototype.iotAssets';
    const NS2 = 'org.criotam.prototype.iotTransactions'
    let factory = getFactory();
    let Experiment = factory.newResource(NS1, "experiment1lcData", experiment1lcData.experimentId);
    Experiment.experimentId = experiment1lcData.experimentId;
    Experiment.Raw_value = experiment1lcData.Raw_value;
    Experiment.lc1_Raw_value = experiment1lcData.lc1_Raw_value ;
    Experiment.lc2_Raw_value = experiment1lcData.lc2_Raw_value ;
    Experiment.experimenter = experiment1lcData.experimenter  ;
    Experiment.player = experiment1lcData.player;
     
    const assetRegistry = await getAssetRegistry('org.criotam.prototype.iotAssets.experiment1lcData');
    await assetRegistry.add(Experiment);

    let event = factory.newEvent(NS2,'experiment1lcDataAdded');
    event.experimentId = experiment1lcData.experimentId;
    emit(event);
 }



 /**
 * read experiment 1 lc Data
 * @param {org.criotam.prototype.iotTransactions.readexperiment1lcData} readexperiment1lcData
 * @transaction
 */
async function readexperiment1lcData(experiment1lcData) {
    var NS = 'org.criotam.prototype.iotTransactions';
    let Raw_value = experiment1lcData.Raw_value;
  	let lc1_Raw_value = experiment1lcData.lc1_Raw_value;
  	let lc2_Raw_value = experiment1lcData.lc2_Raw_value;
    let expId = getExperimentId(Raw_value);
    let factory = getFactory();
  	lc1_Raw_value = getExplc(Raw_value);
    lc2_Raw_value = getExplc(Raw_value);
    experiment1lcData.experiment1.lc1_Raw_value = lc1_Raw_value[0];
    experiment1lcData.experiment1.lc2_Raw_value = lc2_Raw_value[1];
    experiment1lcData.experiment1.experimentId = expId;
    experiment1lcData.experiment1.Raw_value = Raw_value;
    const assetRegistry = await getAssetRegistry('org.criotam.prototype.iotAssets.experiment1lcData');
    await assetRegistry.update(experiment1lcData.experiment1);

    let event = factory.newEvent(NS,'experiment1lcDataread');
    event.experimentId = experiment1lcData.experiment1.experimentId;
    emit(event);
 }
//experiment 1 lc Read and Add ends


 /**
  * Add transaction for experiment 2 load cell sensors
  * @param {org.criotam.prototype.iotTransactions.experiment2lcDataAdd} experiment2lcDataAdd
  */
async function experiment2lcDataAdd(experiment2lcData) {
    const NS1 = 'org.criotam.prototype.iotAssets';
    const NS2 = 'org.criotam.prototype.iotTransactions'
    let factory = getFactory();
    let Experiment = factory.newResource(NS1, "experiment2lcData", experiment2lcData.experimentId);
    Experiment.experimentId = experiment2lcData.experimentId;
    Experiment.Raw_value = experiment2lcData.Raw_value;
    Experiment.lc1_Raw_value = experiment2lcData.lc1_Raw_value;
    Experiment.lc2_Raw_value = experiment2lcData.lc2_Raw_value;
    Experiment.experimenter = experiment2lcData.experimenter;
    Experiment.player = experiment2lcData.player;

    const assetRegistry = await getAssetRegistry('org.criotam.prototype.iotAssets.experiment2lcData');
    await assetRegistry.add(Experiment);

    let event = factory.newEvent(NS2,'experiment2lcDataAdded');
    event.experimentId = experiment2lcData.experimentId;
    emit(event);
}


/**
 * read experiment 2 lc Data
 * @param {org.criotam.prototype.iotTransactions.readexperiment2lcData} readexperiment2lcData
 */
async function readexperiment2lcData(experiment2lcData) {
    var NS = 'org.criotam.prototype.iotTransactions';
    let Raw_value = experiment2lcData.Raw_value;
    let expId = getExperimentId(Raw_value);
    let factory = getFactory();
    lc_Raw_value = getExplc(Raw_value);
    experiment2lcData.experiment1.lc1_Raw_value = lc_Raw_value[0];
    experiment2lcData.experiment1.lc2_Raw_value = lc_Raw_value[1];
    experiment2lcData.experiment1.experimentId = expId;
    experiment2lcData.experiment1.Raw_value = Raw_value;
    const assetRegistry= await getAssetRegistry('org.criotam.prototype.iotAssets.experiment2lcData');
    await assetRegistry.update(experiment2lcData.experiment1);

    let event = factory.newEvent(NS, 'experiment2lcDataread');
    event.experimentId = experiment2lcData.experiment1.experimentId;
    emit(event);
}


/**
 * add Experiment 2 emg sensor
 * @param {org.criotam.prototype.iotTransactions.experiment2emgDataAdd} experiment2emgDataAdd
 */
async function experiment2emgDataAdd(experiment2emgData) {
    const NS1 = 'org.criotam.prototype.iotAssets'
    const NS2 = 'org.criotam.prototype.iotTransactions'
    let factory = getFactory();
    let Experiment = factory.newResource(NS1, "experiment2emgData", experiment2emgData.experimentId);
    Experiment.experimentId = experiment2emgData.experimetnId;
    Experiment.Raw_value = experiment2emgData.Raw_value;
    Experiment.emg_Raw_value = experiment2emgData.emg_Raw_value;
    Experimetnt.experimenter = experiment2emgData.experimenter;
    Experiment.player = experiment2emgData.player;

    const assetRegistry = await getAssetRegistry('org.criotam.prototype.iotAssets.experiment2emgData');
    await assetRegistry.add(Experiment);

    let event = factory.newEvent(NS2,'experiment2emgDataAdded');
    event.experimentId = experiment2emgData.experimentId;
    emit(event);
}

/**
 * read experiment 2 emg sensor
 * @param {org.criotam.prototype.iotTransactions.readexperiment2emgData} readexperiment2emgData
 */
 async function readexperiment2emgData(experiment2emgData) {
     const NS = 'org.criotam.prototype.iotTransactions'
     let Raw_value = experiment2emgData.Raw_value;
     let emg_Raw_value = experiment2emgData.emg_Raw_value;
     let expId = getExperimentId(Raw_value);
     let factory = getFactory();
     emg_Raw_value = getExpemg(Raw_value);
     experiment2emgData.experiment1.emg_Raw_value = emg_Raw_value;
     experiment2emgData.experiment1.experimentId = expId;
     experiment2emgData.experiment1.Raw_value = Raw_value;
     const assetRegistry = await getAssetRegistry('org.criotam.prototype.iotAssets.experiment2emgData');
     await assetRegistry.update(experiment2emgData.experiment1);

     let event = factory.newEvent(NS, 'experiment2emgData');
     event.experimentId = experiment2emgData.experiment1.experimentId;
     emit(event);
     }


/**
 * add expeiment 3 fp sensor
 * @param {org.criotam.prototype.iotTransactions.experiment3fpDataAdd} experiment3fpDataAdd
 */
 async function experiment3fpDataAdd(experiment3fpData) {
     const NS1 = 'org.criotam.prototype.iotAssets';
     const NS2 = 'org.criotam.prototype.iotTransactions';
     let factory = getFactoy();
     let Experiment = factory.newResource(NS1, "experiment3fpData", experiment3fpData.experimentId);
     Experiment.experimentId = experiment3fpData.experimentId;
     Experiment.Raw_value = experiment3fpData.Raw_value;
     Experiment.lc1_Raw_value = experiment3fpData.lc1_Raw_value;
     Experiment.lc2_Raw_value = experiment3fpData.lc2_Raw_value;
     Experiment.lc3_Raw_value = experiment3fpData.lc3_Raw_value;
     Experiment.experimenter = experiment3fpData.experimenter;
     Experiment.player = experiment3fpData.player;

     const assetRegistry = await getAssetRegistry('org.criotam.prototype.iotAssets.experiment3fpData');
     await assetRegistry.add(Experiment);

     let event = factory.newEvent(NS2,'experiment3fpDataAdded');
     event.experimentId = experiment3fpData.experimentId;
     emit(event);
 }

/**
 * read experiment 3 fp Data
 * @param {org.criotam.prototype.iotTransactions.readexperiment3fpData} readexperiment3fpData
 */
async function readexperiment3fpData(experiment3fpData) {
    var NS = 'org.criotam.prototype.iotTransactions';
    let Raw_value = experiment3fpData.Raw_value;
    let expId = getExperimentId(Raw_value);
    let factory = getFactory();
    lc_Raw_value = getExp3fp(Raw_value);
    experiment3fpData.experiment1.lc1_Raw_value = lc_Raw_value[0];
    experiment3fpData.experiment1.lc2_Raw_value = lc_Raw_value[1];
    experiment3fpData.experiment1.lc3_Raw_value = lc_Raw_value[2];
    experiment3fpData.experiment1.experimentId = expId;
    experiment3fpData.experiment1.Raw_value = Raw_value;
    const assetRegistry = await getAssetRegistry('org.criotam.prototype.iotAsset.experiment3fpData');
    await assetRegistry.update(experiment3fpData.experiment1);

    let event = factory.newEvent(NS, 'experiment3fpDataread');
    event.experimentId = experiment3fpData.experiment1.experimentId;
    emit(event);
}

/**
 * add experiment 3 emg sensor
 * @param {org.criotam.prototype.iotTransactions.experiment3emgDataAdd} experiment3emgDataAdd
 */
async function experiment3emgDataAdd(experiment3emgData) {
    const NS1 = 'org.criotam.prototype.iotAssets';
    const NS2 = 'org.criotam.prototype.iotTransaction';
    let factory = getFactory();
    let Experiment = factory.newResource(NS1, "experiment3emgData", experiment3fpData.experimentId);
    Experiment.experimentId = experiment3emgData.experimentId;
    Experiment.emg_Raw_value = experiment3emgData.emg_Raw_value;
    Experiment.experimenter = experiment3emgData.experimenter;
    Experiment.player = experiment3emgData.player;

    const assetRegistry = await getAssetRegistry('org.criotam.prototype.iotAsset.experiment3emgData');
    await assetRegistry.add(Experiment);

    let event = factory.newEvent(NS2, 'experiment3emgDataAdded');
    event.experimentId = experiment3emgData.experimentId;
    emit(event);
}

/**
 * read experiment 3 emg Data
 * @param {org.criotam.prototype.iotTransactions.readexperiment3emgData} readexperiment3emgData
 */
async function readexperiment3emgData(experiment3emgData) {
    var NS = 'org.criotam.prototype.iotTransactions';
    let Raw_value = experiment3emgData.Raw_value;
    let expId = getExperimentId(Raw_value);
    let factory = getFactory();
    emg_Raw_value = getExpemg(Raw_value);
    experiment3emgData.experiment1.emg_Raw_value = emg_Raw_value;
    experiment3emgData.experiment1.experimentId = expId;
    experiment3emgData.experiment1.Raw_value = Raw_value;
    const assetRegistry = await getAssetRegistry('org.criotam.prototype.iotAssets.experiment3emgData');
    await assetRegistry.update(experiment2emgData.experiment1);

    let event  = factory.newEvent(NS, 'experiment3emgData');
    event.experimentId = experiment3emgData.experiment1.experimentId;
    emit(event);
}

// functions to retrieve relevant data from string recieved

// sample data identifier_exp1lc:-0.01:0.06:498706

 function getExperimentId(data) {
        let sensorData = data.split("$");
        console.log(sensorData);
        let singleDatapoint = sensorData[0].split(":");
        let experiment_number = singleDatapoint[0].split("_")[1].substr(3,1);
        let experimentId = "E"+ "0" + experiment_number;
        return experimentId;
 }

 function getExperimentNumber(data) {
    var sensorData = data.split("$");
    var singleDatapoint = sensorData[0].split(":");
    var experiment_number = singleDatapoint[0].split("_")[1].substr(3,1);
    return experiment_number;
 }

 function getExplc(data) {
    let lc1_Raw_value = [];
    let lc2_Raw_value = [];
   var sensorData = data.split("$");
   var l = sensorData.length;
   
   
   for(var i = 0; i < l; i++ ) {
       console.log(i);
       var singleDatapoint = sensorData[i].split(":"); //identifier_exp1lc:-0.01:0.06:498706
       lc1_Raw_value.push(singleDatapoint[1]);
       lc2_Raw_value.push(singleDatapoint[2]);
       console.log(lc1_Raw_value);
       console.log(lc2_Raw_value);
   }
   return [lc1_Raw_value, lc2_Raw_value];
 }

 function getExpemg(data) {
     let emg_Raw_value = [];
    var sensorData = data.split("$");
    var l = sensorData.length;
    for(var i =0; i <l ; i++){
        var singleDatapoint = sensorData[i].split(":");
        emg_Raw_value.push(singleDatapoint[1]);
    }
    return emg_Raw_value;
 }

 function getExp3fp(data) {
     let lc1_Raw_value = [];
     let lc2_Raw_value = [];
     let lc3_Raw_value = [];
    var sensorData = data.split("$");
    var l = sensorData.length;
    console.log(sensorData);
    for(var i =0; i < l; i++ ) {
        var singleDatapoint = sensorData[i].split(":");
        lc1_Raw_value.push(singleDatapoint[1]);
        lc2_Raw_value.push(singleDatapoint[2]);
        lc3_Raw_value.push(singleDatapoint[3]);

    }
    return [lc1_Raw_value, lc2_Raw_value, lc3_Raw_value];
 }